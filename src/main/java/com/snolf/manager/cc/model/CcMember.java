package com.snolf.manager.cc.model;

import com.snolf.base.BaseEntity;
/**
 * <p>Title: CcMember.java </p>
 * <p>Description 999消费商俱乐部会员信息 </p>
 * <p>Company: http://www.hnxianyi.com </p>
 * @author Wjj
 * @CreateDate 2018/3/19 15:37
 */ 
public class CcMember extends BaseEntity{
    private String id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别（0：女 1：男）
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 会员等级
     */
    private Integer memberLevel;
    /**
     * 星级
     */
    private Integer starLevel;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 推荐码
     */
    private String recommendCode;
    /**
     * 状态（0:启用 1:禁用）
     */
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}