package com.KXXXX.student.service;

import com.KXXXX.student.dto.StudentDto;
import com.KXXXX.student.dto.StudentRegisterDto;
import com.KXXXX.student.entity.StudentEntity;
import com.KXXXX.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDto registerNewStudent(StudentRegisterDto studentRegisterDto){
        StudentEntity newStudentEntity = new StudentEntity();
        newStudentEntity.setEmail(studentRegisterDto.getEmail());
        newStudentEntity.setNama(studentRegisterDto.getNama());
        newStudentEntity.setNim(studentRegisterDto.getNim());
        newStudentEntity.setUsername(studentRegisterDto.getUsername());
        newStudentEntity.setPassword(studentRegisterDto.getPassword());
        newStudentEntity.setAlamat(studentRegisterDto.getAlamat());
        StudentEntity registeredStudent = studentRepository.save(newStudentEntity);
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail(registeredStudent.getEmail());
        studentDto.setNama(registeredStudent.getNama());
        studentDto.setNim(registeredStudent.getNim());
        studentDto.setUsername(registeredStudent.getUsername());
        studentDto.setAlamat(registeredStudent.getAlamat());
        return studentDto;
    }

    public List<StudentDto> getAllStudents(){
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (int i = 0; i < studentEntityList.size(); i++){
            StudentDto studentDto = new StudentDto();
            StudentEntity studentEntity = studentEntityList.get(i);
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    public StudentDto getStudent(Long id){
        StudentDto studentDto = new StudentDto();
        Optional<StudentEntity> studentCheck = studentRepository.findById(id);
        if (studentCheck.isPresent()) {
            StudentEntity studentEntity = studentCheck.get();
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            return studentDto;
        } else {
            return null;
        }
    }

    public StudentDto getStudentByNim(String nim){
        StudentDto studentDto = new StudentDto();
        Optional<StudentEntity> studentCheck = studentRepository.findStudentEntityByNim(nim);
        if (studentCheck.isPresent()) {
            StudentEntity studentEntity = studentCheck.get();
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            return studentDto;
        }else{
            return null;
        }
    }

    public List<StudentDto> getStudentByName(String name){
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<StudentEntity> studentEntityList = studentRepository.findStudentName(name);
        for (int i = 0; i < studentEntityList.size(); i++){
            StudentDto studentDto = new StudentDto();
            StudentEntity studentEntity = studentEntityList.get(i);
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(id);
        studentEntity.setUsername(studentDto.getUsername());
        studentEntity.setNama(studentDto.getNama());
        studentEntity.setAlamat(studentDto.getAlamat());
        studentEntity.setNim(studentDto.getNim());
        studentEntity.setEmail(studentDto.getEmail());
        StudentEntity result = studentRepository.save(studentEntity);
        studentDto.setEmail(result.getEmail());
        studentDto.setNama(result.getNama());
        studentDto.setNim(result.getNim());
        studentDto.setUsername(result.getUsername());
        studentDto.setAlamat(result.getAlamat());
        return studentDto;
    }
}
