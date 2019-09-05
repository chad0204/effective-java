package com.pc.reflect;

import java.util.regex.Pattern;

public class ShowMethod {

    private static String usage = "afdaf";

    private static Pattern p = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if(args.length<1) {
            System.out.println(usage);
            System.exit(0);
        }

        int lines = 0;

        System.out.println();

    }
}
