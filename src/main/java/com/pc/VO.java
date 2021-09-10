package com.pc;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author pengchao
 * @date 14:51 2021-01-22
 */
public class VO {

    private String id;
    private String name;

    public VO() {
    }

    public VO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VO)) return false;

        VO vo = (VO) o;

        if (!getId().equals(vo.getId())) return false;
        return getName().equals(vo.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
