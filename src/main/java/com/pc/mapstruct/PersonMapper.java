package com.pc.mapstruct;

import com.alibaba.fastjson.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author pengchao
 * @since 2022/9/2 14:09
 */
@Mapper(uses = PersonMapper.JopConvert.class)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDto entity2dto(Person person);

    Person dto2entity(PersonDto person);

//    JobDto entity2dto(Job job);
//
//    Job dto2entity(JobDto job);

    class JopConvert {
        public String asString(List<JobDto> jobs) {
            return JSON.toJSONString(jobs);
        }
        public List<JobDto> asList(String jobs) {
            return JSON.parseArray(jobs, JobDto.class);
        }
    }
}


