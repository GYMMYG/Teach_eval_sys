package com.example.se.entity;
//import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.JoinColumn;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("Lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Lesson {
    @TableId(value = "id")
    private Integer id;
    private Integer teacherid;
    private String name;
    private Integer credit;
    private Integer hours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(getId(), lesson.getId()) && Objects.equals(getTeacherid(), lesson.getTeacherid()) && Objects.equals(getName(), lesson.getName()) && Objects.equals(getCredit(), lesson.getCredit()) && Objects.equals(getHours(), lesson.getHours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeacherid(), getName(), getCredit(), getHours());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
