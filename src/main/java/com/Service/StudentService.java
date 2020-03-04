package com.Service;

import com.Exception.RecordNotFoundException;
import com.Model.StudentEntity;
import com.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents()
    {
        List<StudentEntity> studentList = studentRepository.findAll();

        if(studentList.size() > 0) {
            return studentList;
        } else {
            return new ArrayList<StudentEntity>();
        }
    }

    public StudentEntity getStudentById(Long id) throws RecordNotFoundException
    {
        Optional<StudentEntity> student = studentRepository.findById(id);

        if(student.isPresent()) {
            return student.get();
        } else {
            throw new RecordNotFoundException("No student record exist for given id",id);
        }
    }

    @Transactional
    public StudentEntity createOrUpdateStudent(StudentEntity entity) throws RecordNotFoundException
    {

        if(entity.getId()!=null)
        {
            Optional<StudentEntity> student = studentRepository.findById(entity.getId());

            if(student.isPresent())
            {
                StudentEntity newEntity = student.get();
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity = studentRepository.save(newEntity);

                return newEntity;
            } else {
                entity = studentRepository.save(entity);
                return entity;
            }
        }

        else
        {
            entity = studentRepository.save(entity);
            return entity;
        }
    }

    public void deleteStudentById(Long id) throws RecordNotFoundException
    {
        Optional<StudentEntity> student = studentRepository.findById(id);

        if(student.isPresent())
        {
            studentRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No student record exist for given id",id);
        }
    }
}


