function buscarCliente() {
	const nomeInput = document.getElementById('nomeCliente').value;
	let pegaBody = document.getElementById('clientes');
	let elemento;
	
	getCliente(nomeInput).then((resposta) => {  
	  resposta.forEach((cliente) => {
		elemento = "<tr onclick=\"populaCampos(this)\">" +
		  				"<td>" + cliente.nome + "</td>" +
		  				"<td>" + cliente.cpf + "</td>" +
		  				"<td>" + cliente.telefone + "</td>" +
		  				"<td>" + cliente.cep + "</td>" +
		  				"<td>" + cliente.endereco + "</td>" +
		  				"<td>" + cliente.numero + "</td>" +
		  				"<td>" + cliente.bairro + "</td>" +
		  				"<td>" + cliente.cidade + "</td>" +
		  				"<td>" + cliente.porcentagem + "</td>" + 
	  				"</tr>";
		pegaBody.innerHTML += elemento;
	  })
  		
	})
	.catch((erro) => {
		swal(':-(', 'Cliente não encontrado!', 'warning');
	})
}

const getCliente = async (nome) => {
	const uri = `/entrega/cliente/buscaPorNome?filtro=${nome}`;

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
	let nomeInput = document.getElementById('nome');
	let cpfInput = document.getElementById('cpf');
	let telefoneInput = document.getElementById('telefone');
	let cepInput = document.getElementById('cep');
	let enderecoInput = document.getElementById('endereco');
	let numeroInput = document.getElementById('numero');
	let bairroInput = document.getElementById('bairro');
	let cidadeInput = document.getElementById('cidade');
	let hiddenPorcentagem = document.getElementById('hiddenPorcentagem');
	
	nomeInput.value = element.cells[0].textContent;
	cpfInput.value = element.cells[1].textContent;
	telefoneInput.value = element.cells[2].textContent;
	cepInput.value = element.cells[3].textContent;
	enderecoInput.value = element.cells[4].textContent;
	numeroInput.value = element.cells[5].textContent;
	bairroInput.value = element.cells[6].textContent;
	cidadeInput.value = element.cells[7].textContent;
	hiddenPorcentagem.value = element.cells[8].textContent;
	
	$('.modal').modal('hide');
}