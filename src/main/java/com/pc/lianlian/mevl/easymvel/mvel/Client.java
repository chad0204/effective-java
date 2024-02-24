package com.pc.lianlian.mevl.easymvel.mvel;


import com.alibaba.fastjson.JSON;
import com.pc.lianlian.UserDTO;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;


/**
 *
 * 规则也可以通过yaml文件加载
 *
 * @author pengchao
 * @since 2022/8/17 16:36
 */
public class Client {


    public static void main(String[] args) throws Exception {
        //创建一个User实例(Fact)
        UserDTO tom = UserDTO.builder().name("tom").age(17).build();
        Facts facts = new Facts();
        facts.put("user", tom);


        //创建规则1：如果大于18岁，设置状态为成年
        Rule ageRule = new MVELRule()
                .name("age rule")//规则名称
                .description("Check if person's age is > 18 and marks the person as adult")//规则描述
                .priority(1)//执行顺序
//                .when("user.age < 18")
                .then("user.adult=false");
        //创建规则2：如果成年，可以喝酒，否则不可以喝酒
        Rule alcoholRule = new MVELRule()
                .name("alcohol rule")//规则名称
                .description("children are not allowed to buy alcohol")//规则描述
                .priority(2)//执行顺序
//                .when("user.adult== false")
                .then("System.out.println('Shop: Sorry, you are not allowed to buy alcohol');");


        Rules rules = new Rules();
        rules.register(ageRule);
        rules.register(alcoholRule);

        //创建规则执行引擎，并执行规则
        RulesEngine rulesEngine = new DefaultRulesEngine();
        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);
        System.out.println(JSON.toJSONString(tom));
    }
}
