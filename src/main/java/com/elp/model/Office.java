package com.elp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ASUS on 2017/7/2.
 * 职位
 * -所属部门编号 departmentNum(String varchar)
 * -职位描述 officeInfo(String varchar)
 * -职位名 officeName(String varchar)
 */
@Entity
@Table(name = "tb_office")
public class Office extends  BaseEntity{
    @Column(length = 32)
    private String departmentNum;
    @Column(nullable = false,length = 51)
    private String officeInfo;
    @Column(nullable = false,length = 21)
    private String officeName;

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }

    public String getOfficeInfo() {
        return officeInfo;
    }

    public void setOfficeInfo(String officeInfo) {
        this.officeInfo = officeInfo;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
