package com.assignment.gatewayservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.gatewayservice.entity.RedisUserEntity;

@Repository
public interface RedisUserRepository extends CrudRepository<RedisUserEntity, String>{

}
