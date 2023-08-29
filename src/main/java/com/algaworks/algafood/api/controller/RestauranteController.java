package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNãoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public List<Restaurante> listarXML(){
//		return restauranteRepository.listar();
//	}
//	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
		
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
		
		if(restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		}catch(EntidadeNãoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante, @PathVariable Long restauranteId){
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
		if(restauranteAtual.isPresent()) {
			
			try {
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
				
				Restaurante restaurantesSalvo = cadastroRestauranteService.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restaurantesSalvo);
			}catch(EntidadeNãoEncontradaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
		System.out.println("--------------------------");
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
		
		if(restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		System.out.println(campos);
		merge(campos, restauranteAtual.get());
		
		return salvar(restauranteAtual.get(), restauranteId);
		//return ResponseEntity.notFound().build();
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
		
		camposOrigem.forEach((nomePropriedade, valorPropriendade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			System.out.println(nomePropriedade + " = " + valorPropriendade);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
			
		});
	}
	
}
