package com.delazari.java_spring_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delazari.java_spring_api.entities.dtos.CardDTO;
import com.delazari.java_spring_api.entities.services.CardServices;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

	@Autowired
	private CardServices cardService;
	
	@PostMapping
	public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDto) {
		return new ResponseEntity<>(cardService.create(cardDto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CardDTO> readCardById(@PathVariable Long id){
		return ResponseEntity.ok(cardService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CardDTO>> readAllCard() {
		return ResponseEntity.ok(cardService.getAll());
	}
	
	@PutMapping
	public ResponseEntity<CardDTO> updateCard(@RequestBody CardDTO cardDTO){
		return ResponseEntity.ok(cardService.update(cardDTO));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCard(@PathVariable Long id){
		cardService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}