package com.pc.lianlian.mevl.demo;

/**
 *
 * @author pengchao
 * @since 2022/8/19 10:07
 */
public enum FactorTypeEnum {
    ORIGINAL(0, "原始参数"),
    PROCESSED(1, "加工参数");

    private Integer code;
    private String type;

    FactorTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
