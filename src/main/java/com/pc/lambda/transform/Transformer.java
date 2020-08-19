package com.pc.lambda.transform;

import java.util.function.Function;

/**
 * 用于实体类转换
 *
 * @author pengchao
 * @date 10:28 2020-08-11
 */
public interface Transformer {


    Function<UserDto,UserVo> DTO_2_VO = userDto -> {
        UserVo userVo = new UserVo();
        userVo.setId(userDto.getId());
        userVo.setName(userDto.getName());
        return userVo;
    };



}
