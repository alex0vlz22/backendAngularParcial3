package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cancion;
import com.example.demo.repository.RepoCancion;

@RestController
@RequestMapping({"/angularParcial3"})
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CtlCancion {
	
	@Autowired
	private RepoCancion repo;
	
	@PostMapping("/add")
	public Cancion guardar(@RequestBody Cancion cancion) {
		return this.repo.save(cancion);
	}
	
	@GetMapping("/canciones")
	public List<Cancion> listar(){
		return this.repo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cancion> buscarPorId(@PathVariable("id") int id){
		Cancion cancion = this.repo.findById(id);
		if(cancion == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(cancion);
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Cancion> editar(@RequestBody Cancion cancion, @PathVariable("id") int id){
		Cancion c = this.repo.findById(cancion.getId());
		if(c==null)
			return ResponseEntity.notFound().build();
		else
			cancion.setId(id);
			this.repo.save(cancion);
			return ResponseEntity.ok(cancion);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Cancion> eliminar(@PathVariable("id") int id) {
		Cancion cancion = this.repo.findById(id);
		if(cancion == null)
			return ResponseEntity.notFound().build();
		else
			this.repo.delete(cancion);
			return ResponseEntity.ok(cancion);
	}

}
