package com.example.se.contoller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.se.entity.Learn;
import com.example.se.entity.Student;
import com.example.se.entity.Lesson;
import com.example.se.entity.Teacher;
import com.example.se.mapper.StudentMapper;
import com.example.se.mapper.LessonMapper;
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
@RequestMapping("/lesson")
public class LessonController {
    @Resource
    private LessonMapper LessonMapper;
    @Resource
    private com.example.se.mapper.TeacherMapper TeacherMapper;

    @RequestMapping("/all")
    public List<Lesson> findPage() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<Lesson> wrapper = Wrappers.<Lesson>lambdaQuery();
        List<Lesson> lesson = LessonMapper.selectList(wrapper);
        return lesson;
    }

    //利用lesid查询学生修了的课程
    @RequestMapping("/query/lesid/{int_id}")
    public List<Lesson> getLessonEntitiesByStuid(@PathVariable Integer int_id) {
        System.out.println(("----- select lesson method test ------"));
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", int_id);
        List<Lesson> Lesson = LessonMapper.selectByMap(columnMap);
        return Lesson;
    }

    //模糊查询
    @RequestMapping("/query/complex/{str_tea_name}/{str_les_name}/{int_credit}/{int_hours}")
    public List<Lesson> getcomplex(@PathVariable String str_tea_name,@PathVariable String str_les_name,@PathVariable Integer int_credit,@PathVariable Integer int_hours) {
        System.out.println(("----- query/complex/{str_tea_name}/{str_les_name}/{int_credit}/{int_hours}test ------"));
        List<Teacher> teacher=new ArrayList<>();
        if(Objects.equals(str_tea_name, String.valueOf(-1))){
            LambdaQueryWrapper<Teacher> wrapper = Wrappers.<Teacher>lambdaQuery();
            teacher = TeacherMapper.selectList(wrapper);
            System.out.println("1");
        }
        else{
            QueryWrapper<Teacher> wrapper_name = new QueryWrapper<Teacher>();
            wrapper_name.like(StringUtils.isNotBlank(str_tea_name),"Name",str_tea_name);
            teacher = TeacherMapper.selectList(wrapper_name);
            System.out.println("2");

        }
        System.out.println(teacher);

        List<Lesson> lesson=new ArrayList<>();

        for(int i=0;i<teacher.size();i++){
            QueryWrapper<Lesson> wrapper_Lesson = new QueryWrapper<Lesson>();
            if(!Objects.equals(str_les_name, String.valueOf(-1))){
            wrapper_Lesson.like("name",str_les_name);}
            if(!Objects.equals(int_credit, -1)){
            wrapper_Lesson.eq("credit",int_credit);}
            if(!Objects.equals(int_hours, -1)){
            wrapper_Lesson.eq("hours",int_hours);}

            wrapper_Lesson.eq("teacherid",teacher.get(i).getId());

            System.out.println(LessonMapper.selectList(wrapper_Lesson));

            lesson.addAll(LessonMapper.selectList(wrapper_Lesson));
        }
        return lesson;

    }

    //利用teacherid查询学生修了的课程
    @RequestMapping("/query/teacherid/{int_id}")
    public List<Lesson> getLessonEntitiesByteacherid(@PathVariable Integer int_id) {
        System.out.println(("----- select lesson method test ------"));
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("teacherid", int_id);
        List<Lesson> Lesson = LessonMapper.selectByMap(columnMap);
        return Lesson;
    }


    //新增课程，同时判断是否课程id是否重复
    //0：新增失败  1:正常新增  2:该课程id已存在 3:属性不全 4:老师编号不存在
    @RequestMapping("/add")
    @CrossOrigin
    @ResponseBody
    public Integer addLessonEntity(@RequestBody Lesson LessonEntity) {
        if (LessonEntity.getId() == null || LessonEntity.getCredit()== null || LessonEntity.getName()== null || LessonEntity.getHours()== null|| LessonEntity.getTeacherid()== null) {
            System.out.println("属性不全");
            return 3;
        } else {

            HashMap<String, Object> columnMap = new HashMap<>();
            columnMap.put("id", LessonEntity.getId());
            List<Lesson> Lesson = LessonMapper.selectByMap(columnMap);

            QueryWrapper<Teacher> bwrapper = new QueryWrapper<Teacher>();
            bwrapper.eq("id", LessonEntity.getTeacherid());
            List<Teacher> Teacher = TeacherMapper.selectList(bwrapper);

            if (Lesson.size() != 0) {
                System.out.println("该课程id已存在");
                QueryWrapper<Lesson> lesson = new QueryWrapper<Lesson>();
                lesson.eq("id", LessonEntity.getId());

                return 2;
            }
            else if(Teacher.size()==0){
                System.out.println("该teacer id不存在");
                return 4;
            }
            else {
                int result = LessonMapper.insert(LessonEntity);
                System.out.println(LessonEntity);
                if (result == 1) return 1;
                else return 0;
            }
        }
    }

}

