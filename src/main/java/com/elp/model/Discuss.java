package com.elp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ASUS on 2017/7/2.
 * 讨论
 * -课时编号 lessonNum(String varchar 外键)
 * -回复讨论编号 reDiscussNum(String varchar 自连外键)
 * -发言人编号 talkUserNum(Stringvarchar 外键)
 * -讨论内容 discussContent(String varchar)
 */
@Entity
@Table(name = "tb_discuss")
public class Discuss extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String lessonNum;
    @Column(length = 32)
    private String reDiscussNum;
    @Column(nullable = false,length = 32)
    private String talkUserNum;
    @Column(nullable = false,length = 200)
    private String discussContent;

    public String getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getReDiscussNum() {
        return reDiscussNum;
    }

    public void setReDiscussNum(String reDiscussNum) {
        this.reDiscussNum = reDiscussNum;
    }

    public String getTalkUserNum() {
        return talkUserNum;
    }

    public void setTalkUserNum(String talkUserNum) {
        this.talkUserNum = talkUserNum;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }
}
