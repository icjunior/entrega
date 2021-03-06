function buscaPorLogradouro() {
	const logradouroInput = document.getElementById('enderecoInput').value;
	const cidadeSelect = document.getElementById('cidadeSelect');
	const codigoCidade = cidadeSelect.options[cidadeSelect.selectedIndex].value;
	
	let pegaBody = document.getElementById('enderecos');
	let elemento;
	
	getLogradouro(logradouroInput, codigoCidade).then((resposta) => {  
	  resposta.forEach((endereco) => {
		elemento = "<tr onclick=\"populaCampos(this)\">" +
		  				"<td>" + endereco.cep + "</td>" +
		  				"<td>" + endereco.logradouro + "</td>" +
		  				"<td>" + endereco.bairro + "</td>" +
		  				"<td>" + endereco.cidade + "</td>" +
	  				"</tr>";
		pegaBody.innerHTML += elemento;
	  })
  		
	})
	.catch((erro) => {
		swal(':-(', 'Endereço não encontrado!', 'warning');
	})
}

const getLogradouro = async (logradouro, cidade) => {
	console.log(logradouro);
	const uri = `/entrega/endereco/pesquisaEnderecoPorLogradouro?logradouro=${logradouro}&cidade=${cidade}`;

	const requestInfo = {
		method : 'GET',
		headers : {
			'Content-type' : 'application/json'
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}


function populaCampos(element){	
	let cepInput = document.getElementById('cep');
	let enderecoInput = document.getElementById('endereco');
	let bairroInput = document.getElementById('bairro');
	let cidadeInput = document.getElementById('cidade');
	
	cepInput.value = element.cells[0].textContent;
	enderecoInput.value = element.cells[1].textContent;
	bairroInput.value = element.cells[2].textContent;
	cidadeInput.value = element.cells[3].textContent;
	
	$('.modal').modal('hide');
}