package com.example.demo.db.repository;

import com.example.demo.db.entity.LoginAttemptEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginAttemptRepository extends CrudRepository<LoginAttemptEntity, Integer> {

    Optional<LoginAttemptEntity> findByUserIp(String userIp);

}
