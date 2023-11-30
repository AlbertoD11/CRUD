package com.example.crud.persistence.repos;

import com.example.crud.persistence.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRepository extends JpaRepository<UserBookEntity, Long> {


    List<UserBookEntity> findByUserId(Long userId);
}
