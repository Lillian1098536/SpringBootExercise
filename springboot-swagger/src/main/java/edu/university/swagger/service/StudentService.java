package edu.university.swagger.service;

import edu.university.swagger.dao.StudentDao;
import edu.university.swagger.exception.StudentNotFoundException;
import edu.university.swagger.model.Student;
import edu.university.swagger.model.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@EntityScan(basePackageClasses = {Student.class})
public class StudentService {

    @Autowired
    private final StudentDao studentDao;
    @Autowired
    private final ModelMapper modelMapper;

    public StudentService(StudentDao studentDao, ModelMapper modelMapper) {
        this.studentDao = studentDao;
        this.modelMapper = modelMapper;
    }

    public List<StudentDto> fetchAllStudents() {
        List<StudentDto> studentDtos;
        try {
            List<Student> students = studentDao.findAll();
            if (students.isEmpty()) {
                throw new StudentNotFoundException(); // return 200
            }
            studentDtos = students.stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
        } catch (Exception ex) {
            log.debug("fail to fetchAllStudents={}", ex.getMessage());
            throw ex;
        }
        return studentDtos;
    }

    public StudentDto fetchStudentById(Long id) {
        StudentDto studentDto;
        try {
            Student student = studentDao.findOne(id);
            if (student == null) {
                throw new StudentNotFoundException(); // return 200
            }
            studentDto = modelMapper.map(student, StudentDto.class);
        } catch (Exception ex) {
            log.debug("fail to fetchStudentById={}", ex.getMessage());
            throw ex;
        }
        return studentDto;
    }

    public void saveStudents(List<StudentDto> studentDtos) {
        try {
            studentDtos.forEach(sDto -> {
                Student student = modelMapper.map(sDto, Student.class);
                studentDao.save(student);
            });
        } catch (Exception ex) {
            log.debug("fail to saveStudents={}", ex.getMessage());
            throw ex;
        }
    }

    public void removeStudents() {
        try {
            studentDao.deleteAll();
        } catch (Exception ex) {
            log.debug("fail to removeStudents={}", ex.getMessage());
            throw ex;
        }
    }

    public void removeStudentById(Long id) {
        try {
            studentDao.delete(id);
        } catch (Exception ex) {
            log.debug("fail to removeStudentById={}", ex.getMessage());
            throw ex;
        }
    }
}
