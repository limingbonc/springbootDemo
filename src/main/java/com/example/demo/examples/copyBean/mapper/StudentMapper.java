package com.example.demo.examples.copyBean.mapper;


import com.example.demo.examples.copyBean.domain.Student;
import com.example.demo.examples.copyBean.domain.StudentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author: liming522
 * @description:
 * @date: 2021/11/16 11:32 上午
 * @hope: The newly created file will not have a bug
 */
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(source = "gender.name", target = "gender"),
            @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    StudentVO student2StudentVO(Student student);


    List<StudentVO> studentList2StudentVOList(List<Student> student);
}
