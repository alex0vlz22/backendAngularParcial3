package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cancion;

public interface RepoCancion extends JpaRepository<Cancion, Integer>{

	Cancion findById(int id);
	
}
