function buscarCupom() {

	const cupom     = document.getElementById('numeroCupom').value;
	const valor     = document.getElementById('valorCupom').value;
	const token     = $("meta[name='_csrf']").attr("content");
	const motorista = document.getElementById('hiddenMotorista').value;
	let pegaBody    = document.getElementById('bodyCupom');
	let elemento;
	
	
	getCupom(cupom, valor, motorista, token)
		.then((resposta) => {
			console.log(resposta);
			 elemento = "<tr class=\"lancamento\" onclick=\"incluirCupom(this)\">" +
	  				"<td>" + resposta.codigo + "</td>" +
	  				"<td>" + resposta.data + "</td>" +
	  				"<td>" + resposta.loja + "</td>" +
	  				"<td>" + resposta.pdv + "</td>" +
	  				"<td>" + resposta.cupom + "</td>" +
	  				"<td>" + resposta.valor + "</td>" +
	  				"<td>" + resposta.bairro + "</td>" +
	  				"<td>" + resposta.porcentagem  + "</td>" +
  				"</tr>";
  	
  			pegaBody.innerHTML += elemento;
		})
		.catch((error) => {
			swal(':-(', 'Guia não existe!', 'error');
		})
}


function incluirCupom(element) {

	const bordero   = document.getElementById('hiddenBordero').value;
	const codigoCupom = element.cells[0].textContent;
	const token     = $("meta[name='_csrf']").attr("content");
	
	putValidacao(bordero, codigoCupom, token)
		.then((resposta) => {
			document.getElementById('tblCupom').deleteRow(1);
			swal(':-)', 'Guia validada com sucesso!', 'success');
		})
		.catch((erro) => {
			swal(':-(', 'Guia não existe ou não pertence a esse borderô!', 'error');
		});
	
}

putValidacao = async (bordero, codigoCupom, token) => {
	const uri = `/entrega/guia/vincularBordero?cupom=${codigoCupom}&bordero=${bordero}`;
	
	const requestInfo = {
		method : 'PUT',
		headers : {
			'Content-type' : 'application/json',
			"X-CSRF-TOKEN" : token
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}

getCupom = async(cupom, valor, motorista, token) => {

	const uri = `/entrega/guia/vincularBordero?motorista=${motorista}&cupom=${cupom}&valor=${valor}`;
	
	const requestInfo = {
		method: 'GET',
		headers: {
			'Content-type' : 'application/json',
			"X-CSRF-TOKEN" : token
		}
	}
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}
