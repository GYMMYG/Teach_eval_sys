package com.example.se.contoller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.se.entity.Student;
import com.example.se.entity.Teacher;
import com.example.se.entity.Administrator;
import com.example.se.mapper.AdministratorMapper;
import com.example.se.mapper.StudentMapper;
import com.example.se.mapper.TeacherMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentMapper StudentMapper;

    @Resource
    private TeacherMapper TeacherMapper;

    @Resource
    private AdministratorMapper AdministratorMapper;

    @RequestMapping("/all")
    public List<Student> findPage() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<Student> wrapper = Wrappers.<Student>lambdaQuery();
        List<Student> student = StudentMapper.selectList(wrapper);
        return student;
    }

    //利用id查询学生
    @RequestMapping("/query/id/{int_id}")
    public List<Student> getStudentEntitiesById(@PathVariable Integer int_id) {
        System.out.println(("----- select stu method test ------"));
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", int_id);
        List<Student> student = StudentMapper.selectByMap(columnMap);
        return student;
    }


    //新增学生，同时判断是否学号重复
    //0：新增失败  1:正常新增  2:该学生id已存在 3：属性不全
    @RequestMapping("/add")
    @CrossOrigin
    @ResponseBody
    public Integer addStudentEntities(@RequestBody Student StudentEntity) {
        if (StudentEntity.getId() == null || StudentEntity.getName()== null || StudentEntity.getPassword()== null || StudentEntity.getCollege()== null|| StudentEntity.getTelephone()== null) {
            System.out.println("属性不全");
            return 3;
        } else {
        List<Student> liststudent = getStudentEntitiesById(StudentEntity.getId());
        if (liststudent.size() != 0) {
            System.out.println("该学生id已存在");
            QueryWrapper<Student> student = new QueryWrapper<Student>();
            student.eq("id", StudentEntity.getId());
            return 2;
        } else {
            int result = StudentMapper.insert(StudentEntity);
            System.out.println(StudentEntity);
            if (result == 1) return 1;
            else return 0;
        }
    }}

    //用户登录查询
    @RequestMapping("/login/{int_userid}/{str_password}")
    public Integer judgelogin(@PathVariable Integer int_userid,@PathVariable String str_password) throws ParseException{

        QueryWrapper<Student> login = new QueryWrapper<Student>();
        login.eq("id", int_userid).eq("password", str_password);
        List<Student> users = StudentMapper.selectList(login);
        if(users.size()==1) {
            System.out.println("登陆成功");
            return 1;
        }
        else{
                QueryWrapper<Student> alogin = new QueryWrapper<Student>();
                alogin.eq("id", int_userid);
                List<Student> user = StudentMapper.selectList(alogin);
                if(user.size()==1) {
                    System.out.println("密码输错");
                    return 2;
                }
                else{System.out.println("不存在该用户");
                    return 3;}
        }
    }

    //name模糊查询查询学生
    @RequestMapping("/query/name/{str_name}")
    public List<Student> getStudentList(@PathVariable String str_name) {
        if(Objects.equals(str_name, String.valueOf(-1))){
            List<Student> student=findPage();
            return student;
        }
        else{
            System.out.println(("----- select stu method test ------"));
            QueryWrapper<Student> wrapper_name = new QueryWrapper<Student>();
            wrapper_name.like(StringUtils.isNotBlank(str_name),"Name",str_name);
            List<Student> student = StudentMapper.selectList(wrapper_name);
            return student;
        }

    }



    //利用id查询返回学生和老师或者姓名
    @RequestMapping("/allidname/{int_id}")
    public String allidname(@PathVariable Integer int_id) {
        System.out.println(("----- select allidname method test ------"));
        QueryWrapper<Student> s_wrapper_name = new QueryWrapper<Student>();
        s_wrapper_name.eq("Id",int_id);
        List<Student> student = StudentMapper.selectList(s_wrapper_name);

        QueryWrapper<Teacher> t_wrapper_name = new QueryWrapper<Teacher>();
        t_wrapper_name.eq("Id",int_id);
        List<Teacher> teacher = TeacherMapper.selectList(t_wrapper_name);

        QueryWrapper<Administrator> a_wrapper_name = new QueryWrapper<Administrator>();
        a_wrapper_name.eq("Id",int_id);
        List<Administrator> administrator  = AdministratorMapper.selectList(a_wrapper_name);


        if(student.size()!=0){return student.get(0).getName();}
        else if(teacher.size()!=0){return teacher.get(0).getName();}
        else if(administrator.size()!=0){return "管理员";}
        else return null;
    }


    //利用id查询返回学生
    @RequestMapping("/idname/{int_id}")
    public String idname(@PathVariable Integer int_id) {
        System.out.println(("----- select allidname method test ------"));
        QueryWrapper<Student> s_wrapper_name = new QueryWrapper<Student>();
        s_wrapper_name.eq("Id",int_id);
        List<Student> student = StudentMapper.selectList(s_wrapper_name);
        if(student.size()!=0){return student.get(0).getName();}
        else return null;
    }




}
