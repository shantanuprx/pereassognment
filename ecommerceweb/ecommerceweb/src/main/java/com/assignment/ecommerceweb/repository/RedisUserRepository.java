package com.assignment.ecommerceweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.ecommerceweb.entity.RedisUserEntity;

@Repository
public interface RedisUserRepository extends CrudRepository<RedisUserEntity, String>{

}
