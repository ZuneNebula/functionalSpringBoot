package com.stackroute.functionalSpringBoot.handler;

import com.stackroute.functionalSpringBoot.model.Student;
import com.stackroute.functionalSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class StudentHandler {

    private StudentRepository studentRepository;

    @Autowired
    public StudentHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Mono<ServerResponse> getAllStudents(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(studentRepository.findAll(),Student.class);
    }

    public Mono<ServerResponse> getStudentById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .body(studentRepository.findById(id),Student.class);
    }

    public Mono<ServerResponse> addStudent(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(student -> this.studentRepository.save(student))
                .flatMap(s -> ServerResponse.created(URI.create("/students/" + s.getId())).build());
    }

    public Mono<ServerResponse> updateStudent(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(student -> this.studentRepository.save(student))
                .flatMap(s -> ServerResponse.created(URI.create("/students/" + s.getId())).build());
    }
    public Mono<ServerResponse> deleteStudent(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .body(studentRepository.deleteById(id),Student.class);
    }

}
