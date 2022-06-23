package com.kcbgroup.main.service.implemetation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.models.Levels;
import com.kcbgroup.main.repositories.LevelRepository;
import com.kcbgroup.main.service.LevelService;

@Component
public class LevelServiceImplentation implements LevelService{

	@Autowired 
	LevelRepository levelRepository;
	
	@Override
	public List<Levels> getAllLevels() {
		
		return levelRepository.findAll();
	}

	@Override
	public ResponseEntity<?> getLevelByNumber(String levelNumber) {
		
		if (levelRepository.findByLevelNumber(levelNumber) != null) {
			
			return new ResponseEntity<>(levelRepository.findByLevelNumber(levelNumber), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Level not found", HttpStatus.NOT_FOUND);
		}
	}

}
