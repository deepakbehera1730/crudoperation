package com.crud.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import com.crud.dto.IUsersDto;
import com.crud.dto.UsersDto;
import com.crud.entity.Users;
import com.crud.exception.UsersNotFoundException;
import com.crud.repository.Repositorys;
import com.crud.service.CrudService;
  
@Service
public class CrudServiceImpl implements CrudService {

	@Autowired
	private Repositorys repository;

	@Override
	public Page<IUsersDto> findAllwithPage(String search, String pageNo, String pageSize) {

		Pageable paging = PageRequest.of(Integer.parseInt(pageNo) - 1, Integer.parseInt(pageSize));
		Page<IUsersDto> cvList;
		if ((search == "") || (search == null) || (search.length() == 0)) {
			cvList = repository.findByIsActiveTrueOrderById(paging, IUsersDto.class);
		} else {
			cvList = repository.findByEmailContainingIgnoreCaseAndIsActiveTrueOrderById(StringUtils.trimLeadingWhitespace(search),
					paging, IUsersDto.class);
		}
		return cvList;

		// doubt UsersDto
	}

	@Override
	public UsersDto getByIdfromDto(long id) throws Exception {
		Users users = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new UsersNotFoundException());
		
		if(users==null) {
			throw new Exception("Id Not Found");
		}

		UsersDto usersDto = new UsersDto();
		usersDto.setId(users.getId());
		usersDto.setName(users.getName());
		usersDto.setEmail(users.getEmail());
		return usersDto;
	}

	@Override
	public void addUser(Users user) {

		repository.save(user);
	
	}
//
//	@Override
//	public void deleteUser(long id) {
//		repository.deleteById(id);
////
//
//	}

	@Override
	public Users updateUser(Users user, long id) throws Exception {

		Users user1 = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new UsersNotFoundException());
//		 user1.setId(user.getId()); // we cannot  change id because id is a primary key
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		return repository.save(user1);
		 // dont pass null
	}

	// converter of entity to dto
	@Override
	public List<UsersDto> getAllDto() {
		// TODO Auto-generated method stub
		List<Users> l1 = repository.findAll();
		List<UsersDto> user = new ArrayList<>();
		for (int i = 0; i < l1.size(); i++) {
			UsersDto dto = new UsersDto();
			dto.setName(l1.get(i).getName());
			dto.setEmail(l1.get(i).getEmail());
			user.add(dto);
		}
		return user;
	}

	@Override
	public Users deleteEntity(long id) throws UsersNotFoundException{
		
		Users entity = repository.findById(id).orElseThrow(() -> new UsersNotFoundException());
		entity.setIsActive(!entity.getIsActive());
		Users entityResponse = repository.save(entity);
		return entityResponse;
	}
 public Users aciveEntity(long id) throws UsersNotFoundException{
		
		Users entity = repository.findById(id).orElseThrow(() -> new UsersNotFoundException());
//		entity.getIsActive(true);
	
		Users entityResponse = repository.save(entity);
		return entityResponse;


 }




}
