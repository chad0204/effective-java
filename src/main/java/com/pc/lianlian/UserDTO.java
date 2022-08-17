package com.pc.lianlian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author pengchao
 * @since 2022/8/17 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String name;

    private Integer age;

    private Boolean adult = false;

    private List<String> accounts;
}
