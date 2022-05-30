package com.KXXXX.student.repository;

import com.KXXXX.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query(value = "select se from StudentEntity se where se.nim = :nim")
    Optional<StudentEntity> findStudentEntityByNim(String nim);
    @Query(value = "select sa from StudentEntity sa where sa.nama like %:name%")
    List<StudentEntity> findStudentName(String name);
}
