function incluirCupom() {

	const bordero   = document.getElementById('hiddenBordero').value;
	const data      = document.getElementById('dataCupom').value;
	const loja      = document.getElementById('lojaCupom').value;
	const pdv       = document.getElementById('pdvCupom').value;
	const cupom     = document.getElementById('numeroCupom').value;
	const valor     = document.getElementById('valorCupom').value;
	const token     = $("meta[name='_csrf']").attr("content");
	const motorista = document.getElementById('hiddenMotorista').value;
	
	putValidacao(bordero, data, loja, pdv, cupom, valor, motorista, token)
		.then((resposta) => {
			swal(':-)', 'Guia validada com sucesso!', 'success');	
		})
		.catch((erro) => {
			swal(':-(', 'Guia não existe ou não pertence a esse borderô!', 'error');
		});
	
}

putValidacao = async (bordero, data, loja, pdv, cupom, valor, motorista, token) => {
	const uri = `/entrega/guia/vincularBordero?motorista=${motorista}&data=${data}&loja=${loja}&pdv=${pdv}&cupom=${cupom}&valor=${valor}&bordero=${bordero}`;
	
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
