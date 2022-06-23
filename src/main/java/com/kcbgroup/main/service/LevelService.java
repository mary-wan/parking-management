package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.models.Levels;

@Service
public interface LevelService {
	
	List<Levels> getAllLevels();
	
	ResponseEntity<?> getLevelByNumber(String levelNumber);

}
