package edu.university.swagger.config;

import edu.university.swagger.dao.StudentDao;
import edu.university.swagger.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public StudentService studentService(final StudentDao studentDao, final ModelMapper modelMapper) {
        return new StudentService(studentDao, modelMapper);
    }

}
