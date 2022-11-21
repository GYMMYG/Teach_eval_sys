package com.example.se.entity;


//import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

import javax.persistence.Column;

@TableName("Student")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @TableId(value = "id")
    private Integer id;
    private String name;
    private String password;
    private String college;
    private Long telephone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getPassword(), student.getPassword()) && Objects.equals(getCollege(), student.getCollege()) && Objects.equals(getTelephone(), student.getTelephone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), getCollege(), getTelephone());
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }
}
