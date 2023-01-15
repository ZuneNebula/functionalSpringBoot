package com.stackroute.functionalSpringBoot.router;

import com.stackroute.functionalSpringBoot.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;


@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction routes(StudentHandler studentHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/students"), studentHandler::getAllStudents)
                .andRoute(RequestPredicates.GET("/students/{id}"), studentHandler::getStudentById)
                .andRoute(RequestPredicates.POST("/students"), studentHandler::addStudent)
                .andRoute(RequestPredicates.PUT("/students"), studentHandler::updateStudent)
                .andRoute(RequestPredicates.DELETE("/students/{id}"), studentHandler::deleteStudent);
    }
}
