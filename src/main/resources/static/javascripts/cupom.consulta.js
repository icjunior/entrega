function pesquisaCupom() {

	data = document.getElementById('dataCupom').value;
	loja = document.getElementById('lojaCupom').value;
	pdv = document.getElementById('pdvCupom').value;
	cupom = document.getElementById('numeroCupom').value;
	chaveAcesso = document.getElementById('hiddenChaveAcesso');
		
	getCupom(data, loja, pdv, cupom).then((resposta) => {
		chaveAcesso.value = resposta.m45xb;		
	})
	.catch((erro) => {
		swal(':-(', 'Cupom fiscal não encontrado!', 'warning');
	})
}

const getCupom = async (data, loja, pdv, cupom) =>  {
	const uri = `/entrega/cupom/busca/${data}/${loja}/${pdv}/${cupom}`;

	const requestInfo = {
		method : 'GET',
		headers : {
			'Content-type' : 'application/json'
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}