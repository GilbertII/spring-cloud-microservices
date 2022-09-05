package com.gabuanii.student.service;

import com.gabuanii.student.entity.Student;
import com.gabuanii.student.repository.StudentRepository;
import com.gabuanii.student.request.CreateStudentRequest;
import com.gabuanii.student.response.AddressResponse;
import com.gabuanii.student.response.StudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CommonService commonService;

    public StudentResponse createStudent(CreateStudentRequest studentRequest) {

        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAddressId(studentRequest.getAddressId());
        studentRepository.save(student);

        // get address from address service
        // AddressResponse addressResponse = getAddress(student.getAddressId()); // using webClient
        AddressResponse addressResponse = commonService.getAddress(student.getAddressId());

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(addressResponse);

        return studentResponse;
    }

    public StudentResponse getById(long id) {

        log.info("Inside StudenService getById {}",id);

        Student student = studentRepository.findById(id).get();

        // get address from address service
        // AddressResponse addressResponse = getAddress(student.getAddressId()); // using webClient

        AddressResponse addressResponse = commonService.getAddress(student.getAddressId());

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(addressResponse);

        return studentResponse;
    }


}
