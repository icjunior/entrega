function escondeInput(){
	const comboTipoGuia = document.getElementById('tipoGuiaSelect');
	const tipoGuia = comboTipoGuia.options[tipoGuiaSelect.selectedIndex].value;
	
	if(tipoGuia === 'NOTA_FISCAL'){
		document.getElementById('divPDV').style.display="none";		
		document.getElementById('lbNumeroNota').style.display="block";
		document.getElementById('lbNumeroCupom').style.display="none";
	} else {
		document.getElementById('divPDV').style.display="block";
		document.getElementById('lbNumeroCupom').style.display="block";
		document.getElementById('lbNumeroNota').style.display="none";
	}
	
}
