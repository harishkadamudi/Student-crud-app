package com.Students.Stud_Details.Repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Students.Stud_Details.Model.Student;

@Repository
public interface StudRepo extends JpaRepository<Student,Integer> {

	Optional<Student> findByFirstNameIgnoreCase(String firstName);
	Optional<Student> findByLastNameIgnoreCase(String lastName);
	Optional<Student> findByEmail(String email);
}