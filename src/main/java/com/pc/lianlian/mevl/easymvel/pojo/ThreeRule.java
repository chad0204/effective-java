package com.pc.lianlian.mevl.easymvel.pojo;

import org.jeasy.rules.annotation.*;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/17 16:23
 */
@Rule(name = "被3整除", description = "number如果被3整除，打印：number is three")
public class ThreeRule {
    /**
     * Condition:条件判断注解：如果return true， 执行Action
     *
     * @param number
     * @return
     */
    @Condition
    public boolean isThree(@Fact("number") int number) {
        return number % 3 == 0;
    }

    /**
     * Action 执行方法注解
     *
     * @param number
     */
    @Action
    public void threeAction(@Fact("number") int number) {
        System.out.println(number + " is three");
    }

    /**
     * Priority:优先级注解：return 数值越小，优先级越高
     *
     * @return
     */
    @Priority
    public int getPriority() {
        return 1;
    }
}
