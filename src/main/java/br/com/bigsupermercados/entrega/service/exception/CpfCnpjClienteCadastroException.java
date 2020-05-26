package br.com.bigsupermercados.entrega.service.exception;

public class CpfCnpjClienteCadastroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfCnpjClienteCadastroException(String message) {
		super(message);
	}
}
