function criarBordero(){
	
	const codigoMotorista = document.getElementById('motorista').value;
	const token = $("meta[name='_csrf']").attr("content");
	
	postBordero(codigoMotorista, token)
		.then((resposta) => {
			swal('Pronto!', 'Borderô criado com sucesso!', 'success');
			window.location.href = `/entrega/bordero`;
		})
		.catch((erro) => {
			swal('Atenção!', 'Falha ao criar borderô!', 'error');
			console.log(erro);
		})
	
}

const postBordero = async (codigoMotorista, token) => {
	const uri = `/entrega/bordero/novo`;

	const requestInfo = {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json',
		 	'X-CSRF-TOKEN' : token
		},
		body : JSON.stringify(codigoMotorista)
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}

