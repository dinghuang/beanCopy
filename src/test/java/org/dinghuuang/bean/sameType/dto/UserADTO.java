package org.dinghuuang.bean.sameType.dto;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/27 11:40
 * @Modify By: edisionding
 */
public class UserADTO {

    private String userId;

    private String userid;

    private String name;

    private String name2;

    private String name3;

    private Boolean isCheck;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    @Override
    public String toString() {
        return "UserADTO{" +
            "userId='" + userId + '\'' +
            ", userid='" + userid + '\'' +
            ", name='" + name + '\'' +
            ", name2='" + name2 + '\'' +
            ", name3='" + name3 + '\'' +
            ", isCheck=" + isCheck +
            '}';
    }
}
