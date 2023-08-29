package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNãoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		
		Estado estado = estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow(() -> new EntidadeNãoEncontradaException(
						String.format("Estado com ID: %d não encontrado", cidade.getEstado().getId())));
		
		if(estado == null) {
			throw new EntidadeNãoEncontradaException(String.format("Estado com ID: %d não encontrado", cidade.getEstado().getId()));
		}
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
}
