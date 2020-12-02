package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.bigsupermercados.entrega.modelo.entrega.Usuario;
import br.com.bigsupermercados.entrega.repository.entrega.Usuarios;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;
import br.com.bigsupermercados.entrega.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByLoginIgnoreCase(usuario.getLogin());

		if (usuarioExistente.isPresent() && usuario.isNovo()) {
			throw new RegistroJaCadastradoException("Nome do usuário já cadastrado");
		}

		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}

		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());

		return repository.saveAndFlush(usuario);
	}

	@Transactional
	public void excluir(Usuario usuario) {
		try {
			repository.delete(usuario);
			repository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar usuário. Já foi usado em algum momento");
		}
	}

	public Page<Usuario> buscarPaginado(Pageable pageable) {
		List<Usuario> usuarios = repository.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Usuario> usuariosList = new ArrayList<>();

		if (usuarios.size() < startItem) {
			usuarios = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, usuarios.size());
			usuariosList = usuarios.subList(startItem, toIndex);
		}

		Page<Usuario> usuarioPage = new PageImpl<Usuario>(usuariosList, PageRequest.of(currentPage, pageSize),
				usuarios.size());

		return usuarioPage;
	}
}
