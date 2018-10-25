package com.Students.Stud_Details.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Students.Stud_Details.Model.Student;
import com.Students.Stud_Details.Repo.StudRepo;

@RestController
public class StudentController {
	@Autowired
	private StudRepo studRepo;
	
	//This MEthod Will Create a single records
	//*
	//*
	@PostMapping("/Student")
	public ResponseEntity<Optional<Student>> createEmployee(@RequestBody Student student)
	{
		Student save = studRepo.save(student);
		return ResponseEntity.ok(java.util.Optional.ofNullable(save));
	}
	
	//This MEthod Will Create a Multiple records
		//*
		//*
	@PostMapping("/Student/bulk")
	public ResponseEntity<String> createBulkStudent(@RequestBody List<Student> student)
	{
		Optional<List<Student>> list = Optional.of(studRepo.saveAll(student));
		long Count = list.orElse(Collections.emptyList()).stream().count();
		return ResponseEntity.ok("No Of Students Insterted : "+ Count);
	}
	
	//This MEthod Will fetch all records
		//*
		//*
	@GetMapping("/Students")
	public ResponseEntity<List<Student>> getStudent()
	{
		List<Student> student = studRepo.findAll();
		return ResponseEntity.ok(student);
	}
	
	//This MEthod Will fetch record on the basis of ID records
		//*
		//*
	@GetMapping("/StudentId/{studId:[0-9]+}")
	public ResponseEntity<Student> student(@PathVariable Integer studId)
	{
		return buildResponse(studRepo.findById(studId));
	}
	
	//This MEthod Will fetch record on the basis of FirstName records
			//*
			//*
	@GetMapping("/StudentFn/{firstName:[a-zA-Z]+}")
	public ResponseEntity<Student> studentByName(@PathVariable String firstName)
		{
			return buildResponse(studRepo.findByFirstNameIgnoreCase(firstName));
		}
	
	//This MEthod Will fetch record on the basis of LastName records
			//*
			//*
	@GetMapping("/StudentLn/{lastName:[a-zA-Z]+}")
	public ResponseEntity<Student> studentByLastName(@PathVariable String lastName)
	{
		return buildResponse(studRepo.findByLastNameIgnoreCase(lastName));
	}
	
	//This MEthod Will fetch record on the basis of Email records
			//*
			//*
	@GetMapping("/StudentEmail/{email}")
	public ResponseEntity<Student> studentByEmail(@PathVariable String email)
	{
		return buildResponse(studRepo.findByEmail(email));
	}
	
	//This MEthod Will Update Record 
			//*
			//*
	@PutMapping("/StudentPut")
	public ResponseEntity<Optional<Student>> updateStudent(@RequestBody Student student)
	{
		Optional<Student> byId = studRepo.findById(student.getStudId());
		if(byId.isPresent())
		{
			Student stud = byId.get();
			stud.setFirstName(student.getFirstName());
			stud.setLastName(student.getLastName());
			stud.setEmail(student.getEmail());
			final Student save = studRepo.saveAndFlush(stud);
			return ResponseEntity.ok(java.util.Optional.ofNullable(save));
		}
		else
		{
			return ResponseEntity.badRequest().build();		}
	}

	
	//This MEthod Will Delete the records by id
			//*
			//*
	@DeleteMapping("/StudentDel/{studId:[0-9]+}")
	public ResponseEntity<String> delete(@PathVariable Integer studId)
	{
		try
		{
			studRepo.deleteById(studId);
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("Could't Delete");
		}
		return ResponseEntity.ok("Record Deleted");
	}

	
	private ResponseEntity<Student> buildResponse(Optional<Student> student) {
	        if (student.isPresent()) {
	            return ResponseEntity.ok(student.get());
	        } else
	            return ResponseEntity.noContent().build();
	    }
}
