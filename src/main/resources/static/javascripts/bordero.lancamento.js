function processaLancamento() { 
	  let pegaBody = document.getElementById('itensLancamento');
	  let elemento;
	  let tipoLancamento = document.getElementById('tipoLancamentoSelect');
	  let valor = document.getElementById('valor');
	  
	  elemento = "<tr class=lancamento>" +
	  				"<td class=text-center>0</td>" +
	  				"<td>" + tipoLancamento.value + "</td>" +
	  				"<td>" + valor.value + "</td>" +
	  				"<td></td>" +
  				"</tr>";
  	
  		pegaBody.innerHTML += elemento;	  
}