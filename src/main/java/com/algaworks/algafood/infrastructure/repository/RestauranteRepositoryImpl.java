package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {

		TypedQuery<Restaurante> result = manager.createQuery("from Restaurante", Restaurante.class);
		return result.getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		
		return manager.find(Restaurante.class, id);
	}

	@Override
	public Restaurante salvar(Restaurante restaurante) {
		
		return manager.merge(restaurante);
	}

	@Override
	public void remover(Restaurante restaurante) {
		manager.remove(buscar(restaurante.getId()));
		
	}

}
