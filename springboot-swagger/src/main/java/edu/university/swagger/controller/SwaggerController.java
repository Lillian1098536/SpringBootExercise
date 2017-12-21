package edu.university.swagger.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {

    @ApiOperation(value = "getGreeting")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful retrieval swagger test")})
    @RequestMapping(value = "api/swaggertest", method = RequestMethod.GET)
    public String sayHello() {
        return "Swagger Hello World!";
    }
}
