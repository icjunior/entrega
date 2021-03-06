package br.com.bigsupermercados.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;

@Service
public class CadastroLojaService {

	@Autowired
	private Lojas repository;

	@Transactional
	public void salvar(Loja loja) {
		repository.save(loja);
	}

	
}
