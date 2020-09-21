function finalizaBordero() {

	const bordero = document.getElementById('hiddenBordero').value;
	const token = $("meta[name='_csrf']").attr("content");
	
	swal({
		title : 'Tem certeza?',
		text : `Deseja finalizar o borderô ${bordero}?`,
		showCancelButton : true,
		confirmButtonCollor : '#DD6B55',
		confirmButtonText : 'Sim, finalize agora!',
		closeOnConfirm: false
	}, (isConfirm) => {
			if(isConfirm) {
				putFinalizarBordero(bordero, token)
				.then((resposta) => {
					swal(`Pronto!`, `Borderô finalizado com sucesso!`, 'success');
				})
				.catch((erro) => {
				console.log(erro);
					swal('Erro!', `O borderô não foi finalizado. Tente novamente mais tarde.`, 'error');
				})
			}
	})
}

const putFinalizarBordero = async (bordero, token) => {
	const uri = `/entrega/bordero/fechar/${bordero}`;

	const requestInfo = {
		method : 'PUT',
		headers : {
			'Content-type' : 'application/json',
		 	'X-CSRF-TOKEN' : token
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}