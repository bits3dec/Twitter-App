package com.greatlearning.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
