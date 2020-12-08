function cidade_bairro() {

	const comboCidade = document.getElementById('cidadeSelect');
	const codigoCidade = comboCidade.options[cidadeSelect.selectedIndex].value;
	const comboBairro = document.getElementById('bairroSelect');
	
	getBairros(codigoCidade)
		.then((cidades) => {
			comboBairro.innerHTML = "";
			cidades.forEach((cidade) => {
				option = new Option(cidade.nome, cidade.codigo);
  				comboBairro.options[comboBairro.options.length] = option;
			})
		})
		.catch((erro) => {
			console.log(erro);
		})
}

const getBairros = async (codigoCidade) => {
	const uri = `/entrega/bairro/buscaPorCidade?codigoCidade=${codigoCidade}`;

	const requestInfo = {
		method : 'GET',
		headers : {
			'Content-type' : 'application/json'
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();	
}