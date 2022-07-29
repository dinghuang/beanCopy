package org.dinghuuang.bean.extend.dto;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/28 14:30
 * @Modify By: edisionding
 */
public class ExtendA2DTO extends ExtendAParentDTO {

    private String sub;

    private String parent;

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "{" +
            "sub='" + sub + '\'' +
            ", parent='" + parent + '\'' +
            '}';
    }
}
