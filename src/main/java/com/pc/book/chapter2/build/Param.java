package com.pc.book.chapter2.build;


/**
 * 自己测试玩玩
 *
 * @author dongxie
 * @date 11:14 2019-12-19
 */
public class Param {

    private Long id;
    private String name;
    private Integer type;


    /**
     * 提供两种（有参和无参）创建构建器的方法
     */
    public static Param.ParamBuilder newBuilder() {
        return new ParamBuilder();
    }

    public static Param.ParamBuilder newBuilder(Param param) {
        return new ParamBuilder(param);
    }



    public static class ParamBuilder {
        private Param param;


        public ParamBuilder() {
            param = new Param();
        }

        public ParamBuilder(Param param) {//可以传入构建未完成的param对象继续构建
            this.param = param;
        }

        public ParamBuilder id(Long id) {
            this.param.id = id;
            return this;
        }

        public ParamBuilder name(String name) {
            this.param.name = name;
            return this;
        }

        public ParamBuilder type(Integer type) {
            this.param.type = type;
            return this;
        }

        public Param build() {
            //可以做一些业务操作，比如校验和计算
            return this.param;
        }

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }
}
