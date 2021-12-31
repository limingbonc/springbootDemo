package com.example.demo.examples.copyBean.test;

import com.example.demo.examples.copyBean.domain.GenderEnum;
import com.example.demo.examples.copyBean.domain.Student;
import com.example.demo.examples.copyBean.domain.StudentVO;
import com.example.demo.examples.copyBean.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: liming522
 * @description:
 * @date: 2021/11/16 11:41 上午
 * @hope: The newly created file will not have a bug
 */
public class Test {
    public static void main(String[] args) {

        Student student = Student.builder().name("小明").age(6).gender(GenderEnum.Male).height(121.1).birthday(new Date()).build();
        System.out.println(student);
        //这行代码便是实际要用的代码
        StudentVO studentVO = StudentMapper.INSTANCE.student2StudentVO(student);
        System.out.println(studentVO);


        List<Student> listStudent = new ArrayList<Student>();
        listStudent.add(student);

        List<StudentVO> studentVOList = StudentMapper.INSTANCE.studentList2StudentVOList(listStudent);
        System.out.println(studentVOList);
    }

}
