package com.curso.service;

import java.util.List;

import com.curso.model.Curso;

public interface CursosService {
	List<Curso> obtenerCursos();
	List<Curso> altaCurso(Curso curso);
	List<Curso> borrarCurso(String codigo);
	void modificarCurso(String codigo, int horas);
	Curso infoCurso(String codigo);
	List<Curso> obtenerCursosPorPrecio(double precioMin, double precioMax);

}
