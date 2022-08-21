package com.pc.lianlian.mevl.mevl2;

import com.pc.lianlian.UserDTO;
import org.mvel2.MVEL;

import java.util.HashMap;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/17 15:14
 */
public class MvelClient {

    public static void main(String[] args) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("a", 1000);
        param.put("b", 1000);
        param.put("c", "10");
        param.put("d", "10");


        UserDTO userDTO = new UserDTO();
        userDTO.setName("myname");

        param.put("user", userDTO);

        Object res = MVEL.eval("user.name; a==b;c;", param);
        System.out.println(res);


        Object eval = MVEL.eval("user.name", param);
        System.out.println(eval);

    }
}
