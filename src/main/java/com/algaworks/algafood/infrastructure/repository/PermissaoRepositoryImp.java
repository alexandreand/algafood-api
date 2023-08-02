package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImp implements PermissaoRepository{

	private EntityManager manager;
	
	@Override
	public List<Permissao> listar() {
		return manager.createQuery(" from forma_pagamento ", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Override
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	public void remover(Permissao permissao) {
		manager.remove(buscar(permissao.getId()));
		
	}
	
}
