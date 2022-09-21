package com.pc.mapstruct;

import com.google.common.collect.Lists;

/**
 *
 * @author pengchao
 * @since 2022/9/2 14:02
 */
public class Client {

    public static void main(String[] args) {

        PersonDto.PersonDtoBuilder personDto = PersonDto.builder();
        personDto.id(111L);
        personDto.name("pName");
        personDto.age(18);
        personDto.jobs(Lists.newArrayList(JobDto.builder().name("开发").build()));
        Person person = PersonMapper.INSTANCE.dto2entity(personDto.build());


        System.out.println(person);


    }
}
