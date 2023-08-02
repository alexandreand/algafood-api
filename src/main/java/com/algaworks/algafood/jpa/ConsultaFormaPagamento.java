package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class ConsultaFormaPagamento {
	public static void main(String[] args) {
		ApplicationContext apc = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository consulta = apc.getBean(FormaPagamentoRepository.class);
		
		List<FormaPagamento> listaFormaPagamento = consulta.listar();
		
		for(FormaPagamento f: listaFormaPagamento) {
			System.out.println(f.getDescricao());
		}
		
		
	}
}
