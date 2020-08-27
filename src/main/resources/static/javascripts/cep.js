function pesquisaCEP() {

	enderecoInput = document.getElementById('endereco');
	bairroInput = document.getElementById('bairro');
	cidadeInput = document.getElementById('cidade');
	cepInput = document.getElementById('cep');
	
	getCEP(cepInput.value).then((resposta) => {
		enderecoInput.value = resposta.endereco;
		bairroInput.value = resposta.bairro;
		cidadeInput.value = resposta.cidade;
	})
	.catch((erro) => {
		console.warn('deu erro');
	})
}

const getCEP = async (cep) =>  {
	const uri = `/entrega/endereco/${cep}`;

	const requestInfo = {
		method : 'GET',
		headers : {
			'Content-type' : 'application/json'
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}