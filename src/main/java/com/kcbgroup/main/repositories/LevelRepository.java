package com.kcbgroup.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcbgroup.main.models.Levels;

public interface LevelRepository extends JpaRepository<Levels, Long> {
	
	Levels findByLevelNumber(String levelNumber);

}
