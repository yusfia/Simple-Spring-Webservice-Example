package com.KXXXX.student.controller;

import com.KXXXX.student.dto.OutputDto;
import com.KXXXX.student.dto.StudentDto;
import com.KXXXX.student.dto.StudentRegisterDto;
import com.KXXXX.student.service.StudentService;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @PostMapping("/registration")
    public ResponseEntity<OutputDto<StudentDto>> registration(@RequestBody StudentRegisterDto studentRegisterDto){
        StudentDto studentDto = studentService.registerNewStudent(studentRegisterDto);
        OutputDto<StudentDto> output = new OutputDto();
        output.setData(studentDto);
        output.setMessage("Data berhasil di simpan");
        return ResponseEntity.ok(output);
    }

    @GetMapping("/show-all")
    public ResponseEntity<OutputDto<List<StudentDto>>> showAllStudents(){
        List<StudentDto> studentDtoList = studentService.getAllStudents();
        OutputDto<List<StudentDto>> output = new OutputDto<>();
        output.setMessage("Data berhasil di ambil!");
        output.setData(studentDtoList);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OutputDto<StudentDto>> findStudent(@PathVariable("id") Long id){
        StudentDto studentDto = studentService.getStudent(id);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(studentDto);
        if (studentDto == null){
            output.setMessage("Student tidak ditemukan");
        } else {
            output.setMessage("Student ditemukan");
        }
        return ResponseEntity.ok(output);
    }

    @GetMapping("/find-nim/{nim}")
    public ResponseEntity<OutputDto<StudentDto>> findStudentByNim(@PathVariable("nim") String nim){
        StudentDto studentDto = studentService.getStudentByNim(nim);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(studentDto);
        if (studentDto == null){
            output.setMessage("Student tidak ditemukan");
        } else {
            output.setMessage("Student ditemukan");
        }
        return ResponseEntity.ok(output);
    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<OutputDto<List<StudentDto>>> showStudentByName(@PathVariable("name") String name){
        List<StudentDto> studentDtoList = studentService.getStudentByName(name);
        OutputDto<List<StudentDto>> output = new OutputDto<>();
        output.setMessage("Data berhasil di ambil!");
        output.setData(studentDtoList);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/delete-id/{id}")
    public ResponseEntity<OutputDto<StudentDto>> destroyStudent(@PathVariable("id") Long id){
        StudentDto studentDto = new StudentDto();
        OutputDto<StudentDto> output = new OutputDto<>();
        try {
            studentService.deleteStudent(id);
            studentDto = studentService.getStudent(id);
            output.setData(studentDto);
            output.setMessage("Berhasil di hapus!");
            return ResponseEntity.ok(output);
        } catch (Exception e){
            output.setData(studentDto);
            output.setMessage(e.getMessage());
            return ResponseEntity.ok(output);
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<OutputDto<StudentDto>> registration(@PathVariable("id") Long id, @RequestBody StudentDto studentDto){
        StudentDto result = studentService.updateStudent(id, studentDto);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(result);
        output.setMessage("Data berhasil di update");
        return ResponseEntity.ok(output);
    }
}
