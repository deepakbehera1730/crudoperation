package com.crud.controller;

import javax.validation.Valid;

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

import com.crud.dto.ErrorDto;
import com.crud.dto.IUsersDto;
import com.crud.dto.Sucessdto;
import com.crud.dto.UsersDto;
import com.crud.entity.Users;
import com.crud.exception.UsersNotFoundException;
import com.crud.serviceimpl.CrudServiceImpl;

@RestController
@RequestMapping("/users")
// Use ProperName 
public class UserController {

	@Autowired
	private CrudServiceImpl crudServiceImpl; // naming convention
//get by all and pagination  with Dto and response entity

	@GetMapping("/")

	public ResponseEntity<?> getAllInfoCv(@RequestParam(value = "search") String search,
			@RequestParam(value = "pageNo") String pageNo, @RequestParam(value = "pageSize") String pageSize) {
		Page<IUsersDto> cvs = crudServiceImpl.findAllwithPage(search, pageNo, pageSize);
		if (cvs.getTotalElements() != 0) {
			return new ResponseEntity<>((cvs.getContent()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getbyId(@PathVariable long id) throws Exception  {
	
		try {
			UsersDto usres = crudServiceImpl.getByIdfromDto(id);
		return new ResponseEntity<>(new Sucessdto("Sucesss", "sucess", usres), HttpStatus.OK);
		}
		catch (UsersNotFoundException e) {
			return new ResponseEntity<>(new ErrorDto("idCanNotExit",e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}

	// Add data
	@PostMapping() // avoid that type
	public ResponseEntity<?> addData(@Valid @RequestBody Users user) {
		try {
			crudServiceImpl.addUser(user);

			return new ResponseEntity<>(new Sucessdto(" Succesfully", "Added Users", null), HttpStatus.CREATED);
		} catch (Exception E) {

			return new ResponseEntity<>(new ErrorDto("canNotPutData", E.toString()), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateData(@Valid @RequestBody Users user, @PathVariable long id) throws Exception {
		try {
			crudServiceImpl.updateUser(user, id);
			return new ResponseEntity<>(new Sucessdto("sucess", "sucess", null), HttpStatus.OK);
		} catch (UsersNotFoundException exception) {
			return new ResponseEntity<>(new ErrorDto("cantNotUpdatedUsers ",exception.toString()),HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable(value = "id") long id) throws UsersNotFoundException {
		try {

			Users entity = crudServiceImpl.deleteEntity(id);
			return new ResponseEntity<>(new Sucessdto("Success", "success", null), HttpStatus.OK);
		} catch (UsersNotFoundException e) {
			return new ResponseEntity<>(new ErrorDto(e.getMessage(), "entityNotFound"), HttpStatus.NOT_FOUND);
		}
		
	}

}