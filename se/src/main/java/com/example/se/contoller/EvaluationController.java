package com.example.se.contoller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.se.entity.*;
import com.example.se.mapper.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

import static java.lang.Math.*;


@CrossOrigin
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Resource
    private EvaluationMapper EvaluationMapper;
    @Resource
    private LearnMapper LearnMapper;
    @Resource
    private StudentMapper StudentMapper;
    @Resource
    private LessonMapper LessonMapper;

    @RequestMapping("/all")
    public List<Evaluation> findPage() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<Evaluation> wrapper = Wrappers.<Evaluation>lambdaQuery();
        List<Evaluation> evaluation = EvaluationMapper.selectList(wrapper);
        return evaluation;
    }


    //新增评教
    //0：新增失败  1:正常新增  2:learnid已经存在
    @RequestMapping("/add")
    @CrossOrigin
    @ResponseBody
    public Integer addStudentEntities(@RequestBody Evaluation EvaluationEntity) {
        if ( EvaluationEntity.getContent()== null || EvaluationEntity.getHomework()== null || EvaluationEntity.getLearnid()== null|| EvaluationEntity.getComment()== null|| EvaluationEntity.getTeaching()== null) {
            System.out.println("属性不全");
            return 3;
        }
        else{
        Boolean flag = judgeByLearnid(EvaluationEntity.getLearnid());
        if (flag==true) {
            QueryWrapper<Evaluation> evaluation = new QueryWrapper<Evaluation>();
            evaluation.eq("id", EvaluationEntity.getId());
            int aresult = EvaluationMapper.update(EvaluationEntity, evaluation);
            if (aresult == 1)  return 2;
            else return 0;
        } else {
        int result =EvaluationMapper.insert(EvaluationEntity);
        System.out.println(EvaluationEntity);
        if (result == 1) return 1;
        else return 0;}
    }}

    //利用learnid查询是否评教
    @RequestMapping("/query/boolean/learnid/{int_id}")
    public Boolean judgeByLearnid(@PathVariable Integer int_id) {
        System.out.println(("----- judge ByLearnid method test ------"));
        QueryWrapper<Evaluation> wrapper = new QueryWrapper<Evaluation>();
        wrapper.eq("learnid",int_id);
        List<Evaluation> evaluation = EvaluationMapper.selectList(wrapper);
        if(evaluation.size()!=0){return true;}
        else return false;
    }

    //利用learnid查询具体评教信息
    @RequestMapping("/query/learnid/{int_id}")
    public List<Evaluation> ByLearnid(@PathVariable Integer int_id) {
        System.out.println(("----- Learnid method test ------"));
        QueryWrapper<Evaluation> wrapper = new QueryWrapper<Evaluation>();
        wrapper.eq("learnid",int_id);
        List<Evaluation> evaluation = EvaluationMapper.selectList(wrapper);
        return evaluation;
    }


    //利用stu_les_id查询具体评教信息
    @RequestMapping("/query/stulesid/{stu_id}/{les_id}")
    public List<Evaluation> Bystulesid(@PathVariable Integer stu_id,@PathVariable Integer les_id) {
        QueryWrapper<Learn> awrapper = new QueryWrapper<Learn>();
        awrapper.eq("stuid",stu_id).eq("lesid",les_id);
        List<Learn> learn = LearnMapper.selectList(awrapper);

        if(learn.size()==0) return null;
        else{
        QueryWrapper<Evaluation> wrapper = new QueryWrapper<Evaluation>();
        wrapper.eq("learnid",learn.get(0).getId());
        List<Evaluation> evaluation = EvaluationMapper.selectList(wrapper);
        return evaluation;}
    }


    //利用les_id查询具体评教信息
    @RequestMapping("/query/lesid/{les_id}")
    public List<Evaluation> Bylesid(@PathVariable Integer les_id) {
        QueryWrapper<Learn> awrapper = new QueryWrapper<Learn>();
        awrapper.eq("lesid",les_id);
        List<Learn> learn = LearnMapper.selectList(awrapper);
        List<Evaluation> evaluation=new ArrayList<>();
        for(int i=0;i<learn.size();i++){
            QueryWrapper<Evaluation> wrapper = new QueryWrapper<Evaluation>();
            wrapper.eq("learnid",learn.get(i).getId());
            List<Evaluation> evaluationtemp = EvaluationMapper.selectList(wrapper);
            evaluation.addAll(evaluationtemp);
        }
        return evaluation;

    }


    //利用stu_id推荐一门课
    @RequestMapping("/reco/{stu_id}")
    public List<Lesson> Bystuid(@PathVariable Integer stu_id) {
        QueryWrapper<Student> awrapper = new QueryWrapper<Student>();
        awrapper.orderByAsc("id");
        List<Student> student = StudentMapper.selectList(awrapper);
        int stu_num = student.size();
        System.out.println(student);

        QueryWrapper<Lesson> bwrapper = new QueryWrapper<Lesson>();
        bwrapper.orderByAsc("id");
        List<Lesson> lesson = LessonMapper.selectList(bwrapper);
        int les_num = lesson.size();
        System.out.println(lesson);


        int needstu=-1;
        for(int i=0;i<student.size();i++){
            if(Objects.equals(student.get(i).getId(), stu_id)){
                needstu=i;
            }
        }
        if(needstu==-1) return null;
        else {

            float[][] table = new float[stu_num][les_num];

            for (int i = 0; i < stu_num; i++) {
                Integer tstuid = student.get(i).getId();
                for (int j = 0; j < les_num; j++) {
                    Integer tlesid = lesson.get(j).getId();
                    List<Evaluation> teva = Bystulesid(tstuid, tlesid);
                    if (teva!=null&&teva.size() == 1) {
                        float tscore = (teva.get(0).getTeaching() + teva.get(0).getContent() + teva.get(0).getHomework()) / 3;
                        table[i][j] = tscore;
                    } else {
                        table[i][j] = 0;
                    }
                }
            }

            float[] dis = new float[stu_num];
            float temp=0;
            for (int i = 0; i < stu_num; i++) {
                temp=0;
                for (int j = 0; j < les_num; j++) {
                    temp=temp+(table[i][j]-table[needstu][j])*(table[i][j]-table[needstu][j]);
                }
                float sco = (float) sqrt(temp)+1;
                sco=1/sco;
                dis[i]=sco;
            }

            float[] scoles = new float[les_num];
            for (int j = 0; j < les_num; j++) {
                temp=0;
                QueryWrapper<Learn> wwrapper = new QueryWrapper<Learn>();
                wwrapper.eq("lesid",lesson.get(j).getId()).eq("stuid",student.get(needstu).getId());
                List<Learn> learn = LearnMapper.selectList(wwrapper);
                if(learn.size()!=0) temp=temp-1000;
                for (int i = 0; i < stu_num; i++) {
                    temp = temp + dis[i] * table[i][j];
                }
                scoles[j]=temp;
            }

            int idles=-1;
            float tles=scoles[0];
            for (int j = 0; j < les_num; j++) {
                if(tles<=scoles[j]){
                    tles=scoles[j];
                    idles=j;
                }
            }

            if(idles==-1) return null;
            else {
                QueryWrapper<Lesson> ewrapper = new QueryWrapper<Lesson>();
                ewrapper.eq("id",lesson.get(idles).getId());
                List<Lesson> reclesson = LessonMapper.selectList(ewrapper);
                return reclesson;
            }

        }

    }


}

