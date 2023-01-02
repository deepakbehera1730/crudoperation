package com.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.dto.IUsersDto;
import com.crud.entity.Users;

@Repository
public interface Repositorys extends JpaRepository<Users, Long> {

//	Page<IUsersDto> findByOrderById(Pageable paging, Class<IUsersDto> class1);

	Page<IUsersDto> findByEmailContainingIgnoreCaseOrderById(String trimLeadingWhitespace, Pageable paging,
			Class<IUsersDto> class1);

	Optional<Users> findByIdAndIsActiveTrue(long id);

	Page<IUsersDto> findByIsActiveTrueOrderById(Pageable paging, Class<IUsersDto> class1);

	Page<IUsersDto> findByEmailContainingIgnoreCaseAndIsActiveTrueOrderById(String trimLeadingWhitespace,
			Pageable paging, Class<IUsersDto> class1);


}