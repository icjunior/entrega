function abrirModal(event) {

	$('#exampleModal').on('show.bs.modal', function (event) {
				  var button = $(event.relatedTarget) // Botão que acionou o modal
				  var recipient = button.data('whatever') // Extrai informação dos atributos data-*
				  // Se necessário, você pode iniciar uma requisição AJAX aqui e, então, fazer a atualização em um callback.
				  // Atualiza o conteúdo do modal. Nós vamos usar jQuery, aqui. No entanto, você poderia usar uma biblioteca de data binding ou outros métodos.
				  var modal = $(this)
				  modal.find('.modal-title').text('Nova mensagem para ' + recipient)
				  modal.find('.modal-body input').val(recipient)
				})
}



function reentrega(event){ 

	const objetoClicado = event.currentTarget;
	
	const guia = objetoClicado.data('guia');
	
	postGuia(token, guia)
		.then((resposta) => {
		
		})
		.catch((erro) => {
			
		})
}

postGuia = (token, guia) => {
	
	
	
	return responsta.json();

}