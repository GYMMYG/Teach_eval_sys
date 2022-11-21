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

@TableName("Learn")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Learn {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer stuid;
    private Integer lesid;
    private Integer score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Learn)) return false;
        Learn learn = (Learn) o;
        return Objects.equals(getId(), learn.getId()) && Objects.equals(getStuid(), learn.getStuid()) && Objects.equals(getLesid(), learn.getLesid()) && Objects.equals(getScore(), learn.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStuid(), getLesid(), getScore());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public Integer getLesid() {
        return lesid;
    }

    public void setLesid(Integer lesid) {
        this.lesid = lesid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
