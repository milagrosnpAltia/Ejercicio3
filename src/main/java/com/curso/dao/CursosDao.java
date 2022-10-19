package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.curso.model.Curso;

public interface CursosDao extends JpaRepository<Curso, String> {
	@Query(value = "select c from Curso c where c.precio between ?1 and ?2")
	public List<Curso> listaDeCursosPorPrecio(double precioMin, double precioMax);
}
