package com.example.demo.db.repository;

import com.example.demo.db.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findById(long id);
    UserEntity findByEmail(String email);
    List<UserEntity> findAllByActive(boolean state);
}
