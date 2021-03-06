function pesquisaCPF() {

	cpfInput = document.getElementById('cpf');
	nomeInput = document.getElementById('nome');
	telefoneInput = document.getElementById('telefone');
	cepInput = document.getElementById('cep');
	enderecoInput = document.getElementById('endereco');
	numeroInput = document.getElementById('numero');
	bairroInput = document.getElementById('bairro');
	cidadeInput = document.getElementById('cidade');
	porcentagemHidden = document.getElementById('hiddenPorcentagem');
	complementoInput = document.getElementById('complemento');
	
	getCPF(cpfInput.value).then((resposta) => {
		nomeInput.value = resposta.nome;
		telefoneInput.value = resposta.telefone;
		cepInput.value = resposta.cep;
		enderecoInput.value = resposta.endereco;
		numeroInput.value = resposta.numero;
		bairroInput.value = resposta.bairro;
		cidadeInput.value = resposta.cidade;
		complementoInput.value = resposta.complemento;
		porcentagemHidden.value = resposta.porcentagem;
		
	})
	.catch((erro) => {
		console.warn('deu erro');
	})
}

const getCPF = async (cpf) => {
	const uri = `/entrega/cliente/buscaPorCPF?filtro=${cpf}`;

	const requestInfo = {
		method : 'GET',
		headers : {
			'Content-type' : 'application/json'
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}