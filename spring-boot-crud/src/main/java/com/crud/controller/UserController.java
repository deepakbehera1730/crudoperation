package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.IUsersDto;
import com.crud.dto.UsersDto;
import com.crud.entity.Users;
import com.crud.serviceimpl.CrudServiceImpl;

@RestController
@RequestMapping("/users")
// Use ProperName 
public class UserController {

	@Autowired
	private CrudServiceImpl crudServiceImpl; // naming convention
//get by all and pagination  with Dto and response entity

	@GetMapping("/getPagination")

	public ResponseEntity<?> getAllInfoCv(@RequestParam(value = "search") String search,
			@RequestParam(value = "pageNo") String pageNo, @RequestParam(value = "pageSize") String pageSize) {
		Page<IUsersDto> cvs = crudServiceImpl.findAllwithPage(search, pageNo, pageSize);
		if (cvs.getTotalElements() != 0) {
			return new ResponseEntity<>((cvs.getContent()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}
	// Get by id with Dto

	@GetMapping("/{id}")
	public UsersDto getbyId(@PathVariable long id) throws Exception {
		return crudServiceImpl.getByIdfromDto(id);
	}

	// Add data
	@PostMapping() // avoid that type
	public String addData(@RequestBody Users user) {
		crudServiceImpl.addUser(user);
		return "Successfull Added User";
	}

	// Delete Data
	@PutMapping("/{id}")
	public String updateData(@RequestBody Users user, @PathVariable long id) throws Exception {
		crudServiceImpl.updateUser(user, id);
		return " Successfully Updated User";
	}

	@DeleteMapping("/{id}")
	public String deleteData(@PathVariable long id) {
		crudServiceImpl.deleteUser(id);
		return " Deleted ";
	}

//	@GetMapping("/admin/{pageNo}/{pageSize}")
//	public List<UsersDto> getAllCv(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
//		Page<UsersDto> cvs = crudServiceImpl.findAllwithPage( pageNo, pageSize);
//
//	    	List<UsersDto> r= cvs.getContent();
//		return r;
//	}

}