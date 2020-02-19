package com.pc.optional;

/**
 * 对象类
 *
 * @author dongxie
 * @date 10:06 2019-12-26
 */
public class Model {

    private String name;
    private Integer id;

    public Model() {
    }

    public Model(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
