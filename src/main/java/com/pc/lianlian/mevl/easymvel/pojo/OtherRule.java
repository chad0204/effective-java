package com.pc.lianlian.mevl.easymvel.pojo;

import org.jeasy.rules.annotation.*;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/17 16:26
 */
@Rule(name = "既不被3整除也不被8整除", description = "打印number自己")
public class OtherRule {
    @Condition
    public boolean isOther(@Fact("number") int number){
        return number % 3 != 0 || number % 8 != 0;
    }

    @Action
    public void printSelf(@Fact("number") int number){
        System.out.print(number);
    }

    @Priority
    public int getPriority(){
        return 3;
    }
}

