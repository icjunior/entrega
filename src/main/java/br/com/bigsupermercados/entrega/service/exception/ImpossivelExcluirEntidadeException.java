package br.com.bigsupermercados.entrega.service.exception;

public class ImpossivelExcluirEntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirEntidadeException(String msg) {
		super(msg);
	}
	
}
