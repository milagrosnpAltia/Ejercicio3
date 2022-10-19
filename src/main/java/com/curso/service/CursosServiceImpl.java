package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.CursosDao;
import com.curso.model.Curso;

@Service
public class CursosServiceImpl implements CursosService {

	@Autowired
	CursosDao cursosDao;
	
	@Override
	public List<Curso> obtenerCursos() {
		return cursosDao.findAll();
	}

	@Override
	public List<Curso> altaCurso(Curso curso) {
		if(curso != null) {
			cursosDao.save(curso);
		}
		return cursosDao.findAll();
	}

	@Override
	public List<Curso> borrarCurso(String codigo) {
		cursosDao.deleteById(codigo);
		return cursosDao.findAll();
	}

	@Override
	public void modificarCurso(String codigo, int horas) {
		Curso curso = cursosDao.findById(codigo).orElse(null);
		if(curso != null) {
			curso.setDuracion(horas);
			cursosDao.save(curso);
		}
	}

	@Override
	public Curso infoCurso(String codigo) {
		return cursosDao.findById(codigo).orElse(null);
	}

	@Override
	public List<Curso> obtenerCursosPorPrecio(double precioMin, double precioMax) {
		return cursosDao.listaDeCursosPorPrecio(precioMin, precioMax);
	}

}
