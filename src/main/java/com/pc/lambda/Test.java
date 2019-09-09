package com.pc.lambda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class Test {

    public static void main(String[] args) {


        Collections.sort(new ArrayList<>(), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        new ArrayList<String>().sort(comparingInt(String::length));


        new ArrayList<Date1>().sort(Comparator.comparing(Date1::getTime));




    }
}

class Date1 {
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
