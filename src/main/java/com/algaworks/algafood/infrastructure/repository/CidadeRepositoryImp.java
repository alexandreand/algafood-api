package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImp implements CidadeRepository{

	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery(" from Cidade ", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	public void remover(Cidade cidade) {
		manager.remove(buscar(cidade.getId()));
	}

}
