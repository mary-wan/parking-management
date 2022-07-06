package com.kcbgroup.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcbgroup.main.models.Levels;
import com.kcbgroup.main.service.LevelService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LevelController {
	
	@Autowired
	private LevelService levelService;
	
	@RequestMapping(value = "/levels", method = RequestMethod.GET)
	public List<Levels> getLevels() {	
		return levelService.getAllLevels();	
	}
	
	@RequestMapping(value = "/level/{levelNumber}", method = RequestMethod.GET)
	public ResponseEntity<?> getLevel(@PathVariable String levelNumber) {		
		return levelService.getLevelByNumber(levelNumber);		
	}

}
