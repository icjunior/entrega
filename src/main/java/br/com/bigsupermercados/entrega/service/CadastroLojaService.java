package br.com.bigsupermercados.entrega.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.Loja;
import br.com.bigsupermercados.entrega.repository.Lojas;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroLojaService {

	@Autowired
	private Lojas lojas;

	@Transactional
	public void salvar(Loja loja) {
		lojas.save(loja);
	}

	@Transactional
	public void excluir(Loja loja) {
		try {
			lojas.delete(loja);
			lojas.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cerveja. Já foi usada em algum momento");
		}
	}
}
