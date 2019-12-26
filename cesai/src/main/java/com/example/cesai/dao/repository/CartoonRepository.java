package com.example.cesai.dao.repository;

import com.example.cesai.dao.entity.CartoonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoonRepository extends JpaRepository<CartoonEntity, Integer> {

}
