package com.example.se.contoller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.se.entity.Evaluation;
import com.example.se.entity.Student;
import com.example.se.entity.Administrator;
import com.example.se.entity.Teacher;
import com.example.se.mapper.AdministratorMapper;
import com.example.se.mapper.EvaluationMapper;
import com.example.se.mapper.StudentMapper;
import com.example.se.mapper.TeacherMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @Resource
    private AdministratorMapper AdministratorMapper;

    @RequestMapping("/all")
    public List<Administrator> findPage() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<Administrator> wrapper = Wrappers.<Administrator>lambdaQuery();
        List<Administrator> administrator = AdministratorMapper.selectList(wrapper);
        return administrator;
    }

    //用户登录查询
    //1:登陆成功 2：存在用户但输错密码 3：不存在该用户
    @RequestMapping("/login/{int_userid}/{str_password}")
    public Integer judgeloginTeacher(@PathVariable Integer int_userid,@PathVariable String str_password) throws ParseException{

        QueryWrapper<Administrator> login = new QueryWrapper<Administrator>();
        login.eq("id", int_userid).eq("password", str_password);
        List<Administrator> users = AdministratorMapper.selectList(login);
        if(users.size()==1) {
            System.out.println("登陆成功");
            return 1;
        }
        else{
            QueryWrapper<Administrator> alogin = new QueryWrapper<Administrator>();
            alogin.eq("id", int_userid);
            List<Administrator> user = AdministratorMapper.selectList(alogin);
            if(user.size()==1) {
                System.out.println("密码输错");
                return 2;
            }
            else{System.out.println("不存在该用户");
                return 3;}

        }
    }
}
