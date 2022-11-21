package com.example.se.contoller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.se.entity.Lesson;
import com.example.se.entity.Student;
import com.example.se.entity.Learn;
import com.example.se.entity.Teacher;
import com.example.se.mapper.LessonMapper;
import com.example.se.mapper.StudentMapper;
import com.example.se.mapper.LearnMapper;
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
@RequestMapping("/learn")
public class LearnController {
    @Resource
    private LearnMapper LearnMapper;

    @Resource
    private com.example.se.mapper.LessonMapper LessonMapper;

    @Resource
    private com.example.se.mapper.StudentMapper StudentMapper;

    @RequestMapping("/all")
    public List<Learn> findPage() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<Learn> wrapper = Wrappers.<Learn>lambdaQuery();
        List<Learn> learn = LearnMapper.selectList(wrapper);
        return learn;
    }

    //利用stuid查询学生修了的课程
    @RequestMapping("/query/stuid/{int_id}")
    public List<Learn> getLearnEntitiesByStuid(@PathVariable Integer int_id) {
        System.out.println(("----- select learn method test ------"));
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("stuid", int_id);
        List<Learn> learn = LearnMapper.selectByMap(columnMap);
        return learn;
    }

    //利用stuid和lesid查询learnid
    @RequestMapping("/query/stu_lesid/{stu_id}/{les_id}")
    public Integer getLearnEntitiesByStuid(@PathVariable Integer stu_id, @PathVariable Integer les_id) {
        System.out.println(("----- select learn method test ------"));
        QueryWrapper<Learn> awrapper = new QueryWrapper<Learn>();
        awrapper.eq("stuid", stu_id).eq("lesid", les_id);
        List<Learn> learn = LearnMapper.selectList(awrapper);
        if (learn.size() != 0) {
            return learn.get(0).getId();
        } else return 0;
    }

    //利用stuid查询学生修了的课程lesson
    @RequestMapping("/query/lesson/{int_id}")
    public List<Lesson> getEntitiesByStuid(@PathVariable Integer int_id) {
        System.out.println(("----- select learn method test ------"));
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("stuid", int_id);
        List<Learn> learn = LearnMapper.selectByMap(columnMap);
        List<Lesson> lesson=new ArrayList<>();
        for(int i=0;i<learn.size();i++){
            QueryWrapper<Lesson> awrapper = new QueryWrapper<Lesson>();
            awrapper.eq("id",learn.get(i).getLesid());
            List<Lesson> Lessontemp = LessonMapper.selectList(awrapper);
            lesson.addAll(Lessontemp);
        }
        return lesson;
    }

    //新增学习记录，同时判断是否id是否重复
    //0：新增失败  1:正常新增  2:该id已存在 3:属性不全 4:学生编号不存在  5:课程编号不存在
    @RequestMapping("/add")
    @CrossOrigin
    @ResponseBody
    public Integer addLearnEntity(@RequestBody Learn LearnEntity) {
        if ( LearnEntity.getLesid() == null ||  LearnEntity.getStuid() == null) {
            System.out.println("属性不全");
            return 3;
        } else {
            HashMap<String, Object> columnMap = new HashMap<>();
            columnMap.put("id", LearnEntity.getId());
            List<Learn> Learn = LearnMapper.selectByMap(columnMap);

            QueryWrapper<Lesson> bwrapper = new QueryWrapper<Lesson>();
            bwrapper.eq("id", LearnEntity.getLesid());
            List<Lesson> Lesson = LessonMapper.selectList(bwrapper);

            QueryWrapper<Student> cwrapper = new QueryWrapper<Student>();
            cwrapper.eq("id", LearnEntity.getStuid());
            List<Student> Student = StudentMapper.selectList(cwrapper);

            if (Learn.size() != 0) {
                System.out.println("该课程id已存在");
                QueryWrapper<Learn> learn = new QueryWrapper<Learn>();
                learn.eq("id", LearnEntity.getId());
//                int aresult = LearnMapper.update(LearnEntity, learn);
//                if (aresult == 1) return 2;
//                else return 0;
                return 2;
            }
            else if(Student.size()==0){
                System.out.println("该学生id不存在");
                return 4;
            }
            else if(Lesson.size()==0){
                System.out.println("该课程 id不存在");
                return 5;
            }
            else {
                int result = LearnMapper.insert(LearnEntity);
                System.out.println(LearnEntity);
                if (result == 1) return 1;
                else return 0;
            }
        }
    }


}
