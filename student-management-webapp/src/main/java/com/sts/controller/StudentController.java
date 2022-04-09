package com.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sts.entity.Student;
import com.sts.service.DatabaseServices;
import com.sts.service.MainService;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {
	
	@Autowired
	private DatabaseServices databaseServices;
	
	@Autowired
    private MainService mainService;
	
	@PostMapping("")
	public ResponseEntity<Void> save(@RequestBody Student student){
		databaseServices.save(student);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Student>> get(@RequestParam(value = "_search", required = false, defaultValue = "") String search, @RequestParam(value = "_limit", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(value = "_page", required = false, defaultValue = "1") Integer pageIndex){
		if(pageIndex==0)
			pageIndex=1;
	if(search.equals("") && pageIndex==1 && pageSize==5) {
		List<Student> a=databaseServices.show(pageIndex-1);
		return new ResponseEntity<List<Student>>(a, HttpStatus.OK);
	}
		
		pageIndex--;
		 if(search.equals(""))
			 search="id-";
		try {
	            List<Student> mainObjList = mainService.search(search, pageSize, pageIndex);
	            return new ResponseEntity<List<Student>>(mainObjList, HttpStatus.OK);
	        } catch (Exception ex) {
	            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
	        }
		
		//return databaseServices.showAll();
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Student>> get(){
		List<Student> a=databaseServices.showAll();
		return new ResponseEntity<List<Student>>(a, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		
		try {
			databaseServices.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Student student){
		try {
			databaseServices.update(id, student);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
	
}
