package com.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sts.entity.User;
import com.sts.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	 @GetMapping("")
	    public ResponseEntity<List<User>> get(@RequestParam(value = "_search", required = false, defaultValue = "") String search, @RequestParam(value = "_limit", required = false, defaultValue = "5") Integer pageSize,
	            @RequestParam(value = "_page", required = false, defaultValue = "1") Integer pageIndex){
		 	pageIndex--;
		 	List<User> res=userServiceImpl.search(search, pageSize, pageIndex);
	        return new ResponseEntity<List<User>>(res, HttpStatus.OK);

	    }
	 
	 @PostMapping("")
		public ResponseEntity<Void> save(@RequestBody User user){
			userServiceImpl.save(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
}
