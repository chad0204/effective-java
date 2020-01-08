package com.pc.book.chapter6.enum_;

/**
 *  枚举的构造器强制是私有的
 *
 *  真正的不可变类,所以域都应该是final
 *
 * @author dongxie
 * @date 14:23 2020-01-07
 */
public enum OnePiece {

    LUFEI("路飞", 1000000),
    NAMEI("娜美", 500000),
    SUOLONG("索隆", 800000);

    private final String name;//域应该都是final
    private final Integer reward;


    OnePiece(String name, Integer reward) {
        this.name = name;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public Integer getReward() {
        return reward;
    }

}
