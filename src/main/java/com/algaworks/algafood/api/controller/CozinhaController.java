package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNãoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

//@Controller
//@ResponseBody
//@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	
//	Para retornar em xml
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhasXmlWrapper listarXml(){
//		System.out.println("Teste");
//		return new CozinhasXmlWrapper(cozinhaRepository.listar());
//	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		
		if(cozinha.isPresent())
			return ResponseEntity.ok(cozinha.get());
		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
		
		
		//Criação de response personalizado
//		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//		return ResponseEntity.ok(cozinha);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "uri");
//		
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
//		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
		
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
		
		if(cozinhaAtual.isPresent()) {
			//cozinhaAtual.setNome(cozinha.getNome());
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			
			Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
			
			return ResponseEntity.ok(cozinhaSalva);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
		try {
			
			cadastroCozinhaService.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}catch (EntidadeNãoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
