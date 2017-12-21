package edu.university.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto implements Serializable {

    private Long id;
    private String name;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

}
