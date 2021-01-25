package com.pc;

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
