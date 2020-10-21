function validarCupom() {
	const chaveAcessoInput = document.getElementById('chaveCupom');
	const bordero = document.getElementById('hiddenBordero');
	const token = $("meta[name='_csrf']").attr("content");
	
	if(chaveAcessoInput.value == '') {
		swal(':-(', 'Campo de chave de acesso não pode ser em branco!', 'warning');
		return;
	}
	
	putValidacao(bordero.value, chaveAcessoInput.value, token)
		.then((resposta) => {
			swal(':-)', 'Guia validada com sucesso!', 'success');	
		})
		.catch((erro) => {
			swal(':-(', 'Guia não existe ou não pertence a esse borderô!', 'error');
		});
	
}

function validarCupomBordero(event) {
	const botaoClicado = $(event.currentTarget);
	const chaveAcesso = botaoClicado.data('chaveacesso');
	const bordero = botaoClicado.data('bordero');
	const validado = botaoClicado.data('validado');
	const token = $("meta[name='_csrf']").attr("content");	

	if(validado) {
		swal(':-)', 'Guia já está validada!', 'warning');
		return;
	}

	putValidacao(bordero, chaveAcesso, token)
		.then((resposta) => {
			swal(':-)', 'Guia validada com sucesso!', 'success');	
		})
		.catch((erro) => {
			swal(':-(', 'Guia não validada!', 'error');
		});
	
}

putValidacao = async (bordero, chaveAcesso, token) => {
const uri = `/entrega/bordero/validarCupomBordero?bordero=${bordero}&chaveAcesso=${chaveAcesso}`;
	
	const requestInfo = {
		method : 'PATCH',
		headers : {
			'Content-type' : 'application/json',
			"X-CSRF-TOKEN" : token
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}
