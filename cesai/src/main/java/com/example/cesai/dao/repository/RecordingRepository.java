package com.example.cesai.dao.repository;

import com.example.cesai.dao.entity.RecordingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends JpaRepository<RecordingEntity, Integer> {
}
