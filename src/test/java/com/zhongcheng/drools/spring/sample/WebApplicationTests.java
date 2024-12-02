package com.zhongcheng.drools.spring.sample;

import com.alibaba.fastjson.JSONObject;
import com.zhongcheng.drools.spring.sample.entity.QmsRule;
import com.zhongcheng.drools.spring.sample.entity.RoleDict;
import com.zhongcheng.drools.spring.sample.service.QmsRuleService;
import org.apache.commons.lang3.StringUtils;
import org.drools.compiler.lang.api.*;
import org.drools.compiler.lang.descr.AndDescr;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.mvel.DrlDumper;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import javax.smartcardio.Card;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
 
@SpringBootTest(classes = DroolsSpringSampleApplication.class)
class WebApplicationTests {
 
    String param = "param";
    String drools = "drools";
    private final KieServices kieServices = KieServices.Factory.get();
    @Autowired
    private QmsRuleService qmsRuleService;
 
    @Test
    @Profile("dev")
    void contextLoads() throws UnsupportedEncodingException {
        int int1 = 1;
        int int2 = 1;
        int int3 = 5;
        String drlString = getDrlRules();
//        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kb.add(ResourceFactory.newByteArrayResource(drlString.getBytes(StandardCharsets.UTF_8)), ResourceType.DRL);
//        KnowledgeBuilderErrors errors = kb.getErrors();
//        for (KnowledgeBuilderError error : errors) {
//            System.out.println("规则文件正确性有误：" + error);
//        }

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        kieFileSystem.write("src/main/resources/default.drl",ResourceFactory.newByteArrayResource(drlString.getBytes()));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieModule kieModule = kieBuilder.getKieModule();

//        KieRepository kieRepository = kieServices.getRepository();
//        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);

        KieContainer kieContainer= kieServices.newKieContainer(kieModule.getReleaseId());
        KieSession kieSession = kieContainer.newKieSession();

        RoleDict roleDict = new RoleDict();
        roleDict.setScheduling_cycle(int1);
        roleDict.setReport_term_of_validity(int2);
        roleDict.setEarly_warning_period(int3);
        roleDict.setCode("report_type_test");
        HashMap<String, Boolean> paramValue = new HashMap<>();
//        paramValue.put("result", false);
        kieSession.setGlobal(param, paramValue);
        kieSession.insert(roleDict);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(paramValue.get("result"));
    }
 
    String getDrlRules() {
        PackageDescrBuilder pkgDescBuilder = DescrFactory.newPackage().name(drools)
                .newImport()
                .target(RoleDict.class.getName())
                .end()
                .name(drools)
                .newGlobal()
                .identifier(param)
                .type(HashMap.class.getName())
                .end();
 
        List<QmsRule> rules = qmsRuleService.list();
        for (QmsRule rule : rules) {
            RuleDescrBuilder ruleDescrBuilder = pkgDescBuilder.newRule().name(rule.getRuleCode());
            //attribute
            ruleDescrBuilder.attribute("salience", "0");
 
            //lhs
            PatternDescrBuilder<CEDescrBuilder<RuleDescrBuilder, AndDescr>> pattern = ruleDescrBuilder.lhs().pattern(Card.class.getSimpleName());
            List<String> ruleCondition = JSONObject.parseArray(rule.getRuleCondition()).toJavaList(String.class);
            for (String constraint : ruleCondition) {
                if (!StringUtils.isEmpty(constraint)) {
                    pattern.constraint(constraint);
                }
            }
            pattern.type(RoleDict.class.getName());
            pattern.end();
 
            //rhs
            ruleDescrBuilder.rhs(param + ".put(\"result\",true);");
            ruleDescrBuilder.end();
        }
        pkgDescBuilder.end();
 
        //dump to String;
        PackageDescr packageDescr = pkgDescBuilder.getDescr();
        DrlDumper dumper = new DrlDumper();
        String drl = dumper.dump(packageDescr);
        System.out.println(drl);
        return drl;
    }
}
