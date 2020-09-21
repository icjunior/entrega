function gravaLancamentoBordero(){
	const itens = document.getElementsByClassName("lancamento");
	const lancamentos = [];
	const bordero = document.getElementById('hiddenBordero');
	const token = $("meta[name='_csrf']").attr("content");
	
	for(let i = 0; i < itens.length; i++){
		colunas = itens[i].cells;
    	lancamento = {
    		tipoLancamento: colunas[1].textContent,
    		valor: colunas[2].textContent
    	}
    	lancamentos.push(lancamento);
	};
	
	postLancamento(lancamentos, bordero.value, token)
		.then((resposta) => {
			swal('Pronto!', 'Lançamentos salvos com sucesso!', 'success');
		})
		.catch((erro) => {
			swal('Atenção!', 'Houveram falhas ao salvar lançamentos!', 'error');
			console.log(erro);
		})
}

const postLancamento = async (lancamentos, bordero, token) => {
	const uri = `/entrega/lancamentoBordero/lancar/${bordero}`;
	
	const requestInfo = {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json',
			"X-CSRF-TOKEN" : token
		},
		body: JSON.stringify(lancamentos)
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}