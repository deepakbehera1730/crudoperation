package com.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.crud.dto.IUsersDto;
import com.crud.dto.UsersDto;
import com.crud.entity.Users;

public interface CrudService { // naming format

	public String addUser(Users user);



	List<UsersDto> getAllDto();

	public void deleteUser(long id);

	public String updateUser(Users user, long id) throws Exception;

	Page<IUsersDto> findAllwithPage(String search, String from, String to);

	public UsersDto getByIdfromDto(long id);

}
