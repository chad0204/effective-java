package com.pc.book.chapter2.singleton;

/**
 * 包含单个元素的枚举是最好的单例
 *
 * @author dongxie
 * @date 14:49 2019-12-23
 */
public enum Elvis {


    INSTANCE(1001L,"name");

    private Long id;
    private String name;

    Elvis(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Elvis getInstance() {
        return INSTANCE;
    }
}
