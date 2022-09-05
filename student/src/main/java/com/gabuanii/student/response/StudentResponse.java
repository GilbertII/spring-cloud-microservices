package com.gabuanii.student.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabuanii.student.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

	private long id;
	private String firstName;
	private String lastName;
	private String email;

	@JsonProperty("address")
	private AddressResponse addressResponse;

	public StudentResponse(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
	}


}
