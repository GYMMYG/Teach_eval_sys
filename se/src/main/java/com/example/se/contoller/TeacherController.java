package com.example.se.contoller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.se.entity.Administrator;
import com.example.se.entity.Lesson;
import com.example.se.entity.Student;
import com.example.se.entity.Teacher;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherMapper TeacherMapper;


    @RequestMapping("/all")
    public List<Teacher> findPage() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<Teacher> wrapper = Wrappers.<Teacher>lambdaQuery();
        List<Teacher> teacher = TeacherMapper.selectList(wrapper);
        return teacher;
    }

    //用户登录查询
    @RequestMapping("/login/{int_userid}/{str_password}")
    public Integer judgeloginTeacher(@PathVariable Integer int_userid,@PathVariable String str_password) throws ParseException{

        QueryWrapper<Teacher> login = new QueryWrapper<Teacher>();
        login.eq("id", int_userid).eq("password", str_password);
        List<Teacher> users = TeacherMapper.selectList(login);
        if(users.size()==1) {
            System.out.println("登陆成功");
            return 1;
        }
        else{
            QueryWrapper<Teacher> alogin = new QueryWrapper<Teacher>();
            alogin.eq("id", int_userid);
            List<Teacher> user = TeacherMapper.selectList(alogin);
            if(user.size()==1) {
                System.out.println("密码输错");
                return 2;
            }
            else{System.out.println("不存在该用户");
                return 3;}
        }
    }
    // 修改密码
    @RequestMapping("/change-password/{int_userid}/{str_oldPassword}/{str_newPassword}")
    public Integer changeTeacherPassword(@PathVariable Integer int_userid,
                                         @PathVariable String str_oldPassword,
                                         @PathVariable String str_newPassword) {
        // 创建查询Wrapper，验证旧密码是否正确
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", int_userid).eq("password", str_oldPassword);
        List<Teacher> users = TeacherMapper.selectList(queryWrapper);

        if (users.size() == 1) {
            // 旧密码验证通过，更新新密码
            Teacher teacher = users.get(0);
            teacher.setPassword(str_newPassword); // 假设你有setter方法设置密码
            int updateCount = TeacherMapper.updateById(teacher);
            if (updateCount == 1) {
                System.out.println("密码更新成功");
                return 1; // 密码更新成功
            } else {
                System.out.println("密码更新失败");
                return 4; // 密码更新失败
            }
        } else {
            // 验证旧密码是否正确
            QueryWrapper<Teacher> alogin = new QueryWrapper<>();
            alogin.eq("id", int_userid);
            List<Teacher> user = TeacherMapper.selectList(alogin);
            if (user.size() == 1) {
                System.out.println("旧密码错误");
                return 2; // 旧密码错误
            } else {
                System.out.println("不存在该用户");
                return 3; // 不存在该用户
            }
        }
    }

    //新增教师，同时判断是否教师id是否重复
    //0：新增失败  1:正常新增  2:该教师id已存在 3：属性不全
    @RequestMapping("/add")
    @CrossOrigin
    @ResponseBody
    public Integer addTeacherEntity(@RequestBody Teacher TeacherEntity) {
        if (TeacherEntity.getId() == null || TeacherEntity.getName()== null || TeacherEntity.getPassword()== null || TeacherEntity.getCollege()== null|| TeacherEntity.getTelephone()== null) {
            System.out.println("属性不全");
            return 3;
        }
        else{
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", TeacherEntity.getId());
        List<Teacher> Teacher = TeacherMapper.selectByMap(columnMap);
        if (Teacher.size() != 0) {
            System.out.println("该老师id已存在");
            QueryWrapper<Teacher> teacher = new QueryWrapper<Teacher>();
            teacher.eq("id", TeacherEntity.getId());
//            int aresult = TeacherMapper.update(TeacherEntity, teacher);
//            if (aresult == 1)  return 2;
//            else return 0;
            return 2;
        } else {
            int result = TeacherMapper.insert(TeacherEntity);
            System.out.println(TeacherEntity);
            if (result == 1) return 1;
            else return 0;
        }
    }}


    //利用id老师姓名
    @RequestMapping("/idname/{int_id}")
    public String idname(@PathVariable Integer int_id) {


        QueryWrapper<Teacher> t_wrapper_name = new QueryWrapper<Teacher>();
        t_wrapper_name.eq("Id",int_id);
        List<Teacher> teacher = TeacherMapper.selectList(t_wrapper_name);

        if(teacher.size()!=0){return teacher.get(0).getName();}
        else return null;
    }

}
