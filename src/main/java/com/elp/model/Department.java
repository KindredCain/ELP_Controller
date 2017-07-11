package com.elp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ASUS on 2017/7/2.
 * 部门
 * -部门描述 DepartmentInfo（String varchar）
 */
@Entity
@Table(name = "tb_department")
public class Department extends BaseEntity{
    @Column(nullable = false,length = 52)
    private String departmentInfo;

    public String getDepartmentInfo() {
        return departmentInfo;
    }

    public void setDepartmentInfo(String departmentInfo) {
        this.departmentInfo = departmentInfo;
    }
}
