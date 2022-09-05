package com.gabuanii.student.repository;

import com.gabuanii.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	
	
}
