package com.example.se.entity;
//import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.JoinColumn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("Evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evaluation {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
//    @JoinColumn（name=“learnid”）
    private Integer learnid;
    private Integer homework;
    private Integer content;
    private Integer teaching;
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;
        Evaluation that = (Evaluation) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLearnid(), that.getLearnid()) && Objects.equals(getHomework(), that.getHomework()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getTeaching(), that.getTeaching()) && Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLearnid(), getHomework(), getContent(), getTeaching(), getComment());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLearnid() {
        return learnid;
    }

    public void setLearnid(Integer learnid) {
        this.learnid = learnid;
    }

    public Integer getHomework() {
        return homework;
    }

    public void setHomework(Integer homework) {
        this.homework = homework;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getTeaching() {
        return teaching;
    }

    public void setTeaching(Integer teaching) {
        this.teaching = teaching;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
