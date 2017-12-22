package edu.university.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto implements Serializable {

    private Long id;
    private String name;
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

}
