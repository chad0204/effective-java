package com.pc.mapstruct;

import lombok.*;

import java.util.List;

/**
 *
 * @author pengchao
 * @since 2022/9/2 14:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Long id;

    private String name;

    private Integer age;

    private String address;

    private List<JobDto> jobs;

}
