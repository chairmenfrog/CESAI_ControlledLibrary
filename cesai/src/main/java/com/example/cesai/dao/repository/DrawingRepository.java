package com.example.cesai.dao.repository;

import com.example.cesai.dao.entity.DrawingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawingRepository extends JpaRepository<DrawingEntity, Integer> {
}
