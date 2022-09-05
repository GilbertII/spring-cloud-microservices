package com.gabuanii.student.service;

import com.gabuanii.student.entity.Student;
import com.gabuanii.student.feign.AddressFeignClient;
import com.gabuanii.student.repository.StudentRepository;
import com.gabuanii.student.request.CreateStudentRequest;
import com.gabuanii.student.response.AddressResponse;
import com.gabuanii.student.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private AddressFeignClient addressFeignClient;

    public StudentResponse createStudent(CreateStudentRequest studentRequest) {

        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAddressId(studentRequest.getAddressId());
        studentRepository.save(student);

        // get address from address service
        // AddressResponse addressResponse = getAddress(student.getAddressId()); // using webClient
        AddressResponse addressResponse = getAddress(student.getAddressId());

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(addressResponse);

        return studentResponse;
    }

    public StudentResponse getById(long id) {

        Student student = studentRepository.findById(id).get();

        // get address from address service
        // AddressResponse addressResponse = getAddress(student.getAddressId()); // using webClient

        AddressResponse addressResponse = getAddress(student.getAddressId());

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(addressResponse);

        return studentResponse;
    }

    public AddressResponse getAddress(long addressId) {
        return addressFeignClient
                .getAddress(addressId)
                .getBody();
    }
}
