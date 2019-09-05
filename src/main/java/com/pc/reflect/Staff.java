package com.pc.reflect;

import java.util.ArrayList;

/**
 *
 * @author pengchao
 * @date 16:46 2019-09-03
 */
public class Staff extends ArrayList<Position> {

    public Staff(String... titles) {
        add(titles);
    }

    public void add(String title,Person person) {
        add(new Position(title,person));
    }

    public void add(String... titles) {
        for(String title:titles)
            add(new Position(title));
    }

    //判断职位空缺
    public boolean positionAvaible(String title) {
        for (Position position: this) {
            if(position.getTitle().equals(title) &&
            position.getPerson()==Person.NULL) {
                return true;
            }
        }
        return false;
    }

    //填补职位
    public void fillPosition(String title,Person person) {
        for (Position position:this) {
            if(position.getTitle().equals(title) &&
            position.getPerson()==Person.NULL) {
                position.setPerson(person);
                return;
            }
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        Staff staff = new Staff("President","CTO","CFO","CEO","Hr","Coder");

        if(staff.positionAvaible("CEO")) {
            staff.fillPosition("CEO",new Person("Me","Last","hangzhou"));
        }

        if(staff.positionAvaible("CEO")) {
            staff.fillPosition("CEO",new Person("You","Last","hangzhou"));
        }

        System.out.println();

    }
}
