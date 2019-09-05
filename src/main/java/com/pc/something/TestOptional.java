package com.pc.something;

import java.util.Optional;

/**
 *
 * @author pengchao
 * @date 15:25 2019-09-04
 */
public class TestOptional {

    public static void main(String[] args) {

        Optional<User> operation = Optional.of(new User());

        Optional<User> optionalNull = Optional.of(null);//传null会NPE




        operation.get();

    }
}
