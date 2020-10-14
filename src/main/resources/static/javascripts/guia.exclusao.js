Brewer = Brewer || {};

Brewer.DialogoExcluir = (function() {

	function DialogoExcluir() {
		this.exclusaoBtn = $('.js-exclusao-guia');
	}

	DialogoExcluir.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));

		if (window.location.search.indexOf('excluido') > -1) {
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		}
	}

	function onExcluirClicado(evento) {
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');

		swal({
			title : 'Tem certeza?',
			text : 'Deseja realmente excluir a guia ' + objeto + ' ?',
			showCancelButton : true,
			confirmButtonCollor : '#DD6B55',
			confirmButtonText : 'Sim, exclua agora!',
			closeOnConfirm: false,
			cancelButtonText: 'Não'
		}, onExcluirConfirmado.bind(this, url));
	}

	function onExcluirConfirmado(url) {
	console.log(url);
		$.ajax({
			url : url,
			method : 'PATCH',
			success : onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this)
		});
	}

	function onExcluidoSucesso() {
		var urlAtual = window.location.href;
		var separator = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual
				+ separator + 'excluido';

		window.location = novaUrl;
	}
	
	function onErroExcluir(e){
		swal('Oops!', e.responseText, 'error');
	}

	return DialogoExcluir;
}());

$(function() {
	var dialogo = new Brewer.DialogoExcluir();
	dialogo.iniciar();
});
