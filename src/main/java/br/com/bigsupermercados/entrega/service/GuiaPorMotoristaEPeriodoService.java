package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.dto.GuiaByMotoristaGroupLojaDTO;
import br.com.bigsupermercados.entrega.controller.form.RelatorioMotoristaPorLojaForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Service
public class GuiaPorMotoristaEPeriodoService {

	@Autowired
	private GuiaRepository repository;

	public List<Guia> guiaPorMotoristaEPeriodo(RelatorioMotoristaPorLojaForm dados) {
		List<Guia> guiasPorMotorista = repository.guiaPorMotoristaEPeriodo(dados.getMotorista(), dados.getDataInicial(),
				dados.getDataFinal());

		return guiasPorMotorista;

//		Map<String, Object> parametros = new HashMap<>();
//		parametros.put("format", "pdf");
//		parametros.put("matriculaMotorista", bordero.getMotorista().getCodigo());
//		parametros.put("nomeMotorista", bordero.getMotorista().getNome());
//		parametros.put("codigoBordero", bordero.getCodigo());
//		parametros.put("nomeLoja", bordero.getMotorista().getLoja().getRazao());
//		parametros.put("cnpjLoja", bordero.getMotorista().getLoja().getCnpj());
//
//		List<LancamentoHoleriteDTO> lancamentos = LancamentoHoleriteDTO.converter(bordero.getLancamentos());
//
//		LancamentoHoleriteDTO lancamento = new LancamentoHoleriteDTO(1L, ModoLancamento.ACRESCIMO.getDescricao(),
//				"Valor à receber", bordero.getValorAReceberBruto());
//
//		lancamentos.add(lancamento);
//
//		JRDataSource jrDataSource = new JRBeanCollectionDataSource(lancamentos);
//
//		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/Holerite-Motorista.jasper");
//
//		try {
//			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, jrDataSource);
//			return JasperExportManager.exportReportToPdf(jasperPrint);
//		} finally {
//
//		}
	}

	
	
	public Page<Guia> buscarPaginado(PageRequest pageable, RelatorioMotoristaPorLojaForm dados) {
		List<Guia> guias = repository.guiaPorMotoristaEPeriodo(dados.getMotorista(), dados.getDataInicial(),
				dados.getDataFinal());
		
		GuiaByMotoristaGroupLojaDTO.converter(guias);
		
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Guia> guiasList = new ArrayList<>();

		if (guias.size() < startItem) {
			guias = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, guias.size());
			guiasList = guias.subList(startItem, toIndex);
		}

		Page<Guia> guiaPage = new PageImpl<Guia>(guiasList, PageRequest.of(currentPage, pageSize),
				guias.size());

		return guiaPage;
	}
}
