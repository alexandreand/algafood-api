package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
//	List<Restaurante> listar();
//	Restaurante buscar(Long id);
//	Restaurante salvar(Restaurante restaurante);
//	void remover(Restaurante restaurante);
	
}
