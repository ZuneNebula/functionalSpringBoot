package com.stackroute.functionalSpringBoot.repository;

import com.stackroute.functionalSpringBoot.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
}
