package edu.university.swagger.controller;

import edu.university.swagger.model.StudentDto;
import edu.university.swagger.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> studentDtos = studentService.fetchAllStudents();
        log.debug("get all students={}", studentDtos);
        return ResponseEntity.ok().body(studentDtos);
    }

    @ResponseBody
    @RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.fetchStudentById(id);
        log.debug("get student={}", studentDto);
        return ResponseEntity.ok().body(studentDto);
    }

    @ResponseBody
    @RequestMapping(value = "/saveStudents", method = RequestMethod.POST)
    public ResponseEntity<List<StudentDto>> saveStudents(@RequestBody List<StudentDto> studentDtos) {
        studentService.saveStudents(studentDtos);
        log.debug("save Students={}", studentDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDtos);
    }

    @ResponseBody
    @RequestMapping(value = "/removeStudents", method = RequestMethod.DELETE)
    public ResponseEntity<List<StudentDto>> removeStudents() {
        List<StudentDto> studentDtos = studentService.fetchAllStudents();
        studentService.removeStudents();
        log.debug("remove all students={}", studentDtos);
        return ResponseEntity.ok().body(studentDtos);
    }

    @ResponseBody
    @RequestMapping(value = "/removeStudent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentDto> removeStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.fetchStudentById(id);
        studentService.removeStudentById(id);
        log.debug("remove student={}", studentDto);
        return ResponseEntity.ok().body(studentDto);
    }

}
