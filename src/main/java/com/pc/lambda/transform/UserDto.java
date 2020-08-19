package com.pc.lambda.transform;

/**
 *
 * @author pengchao
 * @date 10:29 2020-08-11
 */
public class UserDto {

    private Long id;

    private String name;

    public UserDto() {
    }

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
