package com.sts.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sts.entity.Student;
import com.sts.repository.StudentRepository;

@Service
public class DatabaseServices {
	
	@Autowired
	private StudentRepository studentRepository;
	@Value("${pagination}")
	private int pagination;
	
	public Student save(Student student) {
		return studentRepository.save(student);
	}
	
	public void delete(int id) {
		Optional<Student> optional=this.studentRepository.findById(id);
		Student student=optional.get();
		this.studentRepository.delete(student);
	}
	
	public Student update(int id, Student update) {
		update.setId(id);
		return save(update);
	}
	
	public List<Student> show(int page){
		Pageable pageable=PageRequest.of(page, pagination); //creating a pagination requirement, and the parameters passed are page number and number of contacts per page
		Page<Student> pages=this.studentRepository.findAll(pageable);
		return pages.getContent();
	}
	
	public List<Student> showAll(){
		return this.studentRepository.findAll();
	}
}