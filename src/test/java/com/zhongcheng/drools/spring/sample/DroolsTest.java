package com.zhongcheng.drools.spring.sample;

import com.zhongcheng.drools.spring.sample.entity.RuleResult;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = DroolsSpringSampleApplication.class)
public class DroolsTest {

    @Autowired
    private KieSession kieSession;
    @Autowired
    private KieContainer kieContainer;

    /**
     * 自动执行先后规则
     */
    @Test
    public void test() {

        Map<String, Object> invoice = new HashMap<>();
        invoice.put("invoiceNumber", "12345");
        invoice.put("accumulatedSettlementAmount", 11000.0);

        Map<String, Object> contract = new HashMap<>();
        contract.put("contractCode", "12345");
        contract.put("totalAmountWithTax", 10000.0); // 示例值
        contract.put("changedTotalAmountWithTax", 12000.0); // 示例值

//        KieServices kieServices = KieServices.Factory.get();
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write("src/main/resources/test1/map_obj_rule.drl", kieServices.getResources().newClassPathResource("droolsRule/map_obj_rule.drl"));
//        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
//        kieBuilder.buildAll();
//        KieModule kieModule = kieBuilder.getKieModule();
//
//        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//
//        KieSession kieSession= kieContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.insert(contract);
//        kieSession.setGlobal("inputInvoiceNumber", invoice.get("invoiceNumber"));

        // 返参
        RuleResult resultParam = new RuleResult();
        kieSession.insert(resultParam);
        kieSession.fireAllRules();

//        kieSession.insert(resultParam);
//        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(resultParam.getMsg());
    }

    /**
     * 手动执行先后规则
     */
    @Test
    public void test2() {

        Map<String, Object> invoice = new HashMap<>();
        invoice.put("invoiceNumber", "12345");
        invoice.put("accumulatedSettlementAmount", 11000.0);

        Map<String, Object> contract = new HashMap<>();
        contract.put("contractCode", "12345");
        contract.put("totalAmountWithTax", 10000.0); // 示例值
        contract.put("changedTotalAmountWithTax", 12000.0); // 示例值

//        kieSession = kieContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.insert(contract);
        // 返参
        RuleResult resultParam = new RuleResult();
        kieSession.insert(resultParam);
        kieSession.fireAllRules();
//        kieSession.dispose();

        System.out.println("上一个规则返回结果:"+resultParam.getMsg());

//        kieSession= kieContainer.newKieSession();

        RuleResult resultParam2 = new RuleResult();
        resultParam2.setMsg(resultParam.getMsg());
//        kieSession.insert(invoice);
//        kieSession.insert(contract);
        kieSession.insert(resultParam2);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(resultParam.getMsg());
    }
}
