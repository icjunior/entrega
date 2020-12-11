package br.com.bigsupermercados.entrega.service;

import br.com.bigsupermercados.entrega.repository.entrega.Usuarios;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void executar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));			
		}
	}, DESATIVAR {
		@Override
		public void executar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));			
		}
	};
	
	public abstract void executar(Long[] codigos, Usuarios usuarios);
}
