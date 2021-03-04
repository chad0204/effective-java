package com.pc.lambda.transform;

import static com.pc.lambda.transform.Transformer.DTO_2_VO;

/**
 *
 * @author pengchao
 * @date 10:32 2020-08-11
 */
public class Service {

    public UserVo getBy(Long id) {
        UserDto userDto = new UserDto();
        userDto.setId(1001L);
        userDto.setName("other");
        return DTO_2_VO.apply(userDto);
    }


}
