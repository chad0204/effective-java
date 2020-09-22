package com.pc.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dongxie
 * @date 11:26 2020-04-07
 */
public class TestLambda {

    public static void main(String[] args) {


        List<Model> list = Arrays.asList(
                new Model(1L,"a",null),
                new Model(2L,"bb",2.2),
                new Model(2L,"cc",null),
                new Model(3L,"dd",1.1));


        Double d =list.stream().filter(x->x.getMile()!=null).filter(x->x.getMile()!=2.2).mapToDouble(Model::getMile).sum();

        System.out.println();




        List<Model> listFilter = list.stream().filter(x->x.getId()==4L).collect(Collectors.toList());


        Map<Long,List<Model>> map = listFilter.stream().collect(Collectors.groupingBy(Model::getId));

        System.out.println(map);

    }
}



class Model {
    private Long id;
    private String name;
    private Double mile;

    public Model(Long id, String name, Double mile) {
        this.id = id;
        this.name = name;
        this.mile = mile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMile() {
        return mile;
    }

    public void setMile(Double mile) {
        this.mile = mile;
    }
}