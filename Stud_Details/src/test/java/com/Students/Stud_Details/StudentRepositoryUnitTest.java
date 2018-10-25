package com.Students.Stud_Details;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.Students.Stud_Details.Model.Student;
import com.Students.Stud_Details.Repo.StudRepo;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryUnitTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private StudRepo studrepo;
	
	@Test
	public void findByFirstNameIgnorecase_thenReturnEmployee()
	{
		Student stud = new Student();
		stud.setFirstName("Subham");
		stud.setLastName("Choudhary");
		stud.setEmail("choudharysubham47@gmail.com");
		entityManager.persist(stud);
		entityManager.flush();
		
		Optional<Student> studentfoundbyfirstname = studrepo.findByFirstNameIgnoreCase(stud.getFirstName());
		assertThat(studentfoundbyfirstname.get().getFirstName());
	}

}
