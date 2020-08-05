package com.example.demo.db.repository;

import com.example.demo.db.entity.HistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<HistoryEntity, Long> {

    HistoryEntity findById(long id);
}
