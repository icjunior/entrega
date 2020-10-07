function pesquisaCupom() {

	const data = document.getElementById('dataCupom').value;
	const loja = document.getElementById('lojaCupom').value;
	const pdv = document.getElementById('pdvCupom').value;
	const cupom = document.getElementById('numeroCupom').value;
	let chaveAcesso = document.getElementById('hiddenChaveAcesso');
		
	getCupom(data, loja, pdv, cupom).then((resposta) => {
		chaveAcesso.value = resposta.m45xb;		
		console.log(resposta.m45xb);
	})
	.catch((erro) => {
		swal(':-(', 'Cupom fiscal não encontrado!', 'warning');
	})
}

const getCupom = async (data, loja, pdv, cupom) =>  {
	const uri = `/entrega/cupom/busca?data=${data}&loja=${loja}&pdv=${pdv}&cupom=${cupom}`;

	const requestInfo = {
		method : 'GET',
		headers : {
			'Content-type' : 'application/json'
		}
	};
	
	const resposta = await fetch(uri, requestInfo);
	
	return resposta.json();
}