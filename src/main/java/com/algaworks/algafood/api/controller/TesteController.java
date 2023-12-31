package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	//Não precisa do @RequestParam, bind pode ser feito automaticamente
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> consultarPorNome(@RequestParam("nome") String nome){
		System.out.println(nome);
		return cozinhaRepository.findByNome(nome);
	}
	
}
