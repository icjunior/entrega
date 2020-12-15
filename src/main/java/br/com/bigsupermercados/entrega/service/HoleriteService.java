package br.com.bigsupermercados.entrega.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.dto.LancamentoHoleriteDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class HoleriteService {

	public byte[] gerar(Bordero bordero) throws Exception {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("matriculaMotorista", bordero.getMotorista().getCodigo());
		parametros.put("nomeMotorista", bordero.getMotorista().getNome());
		parametros.put("codigoBordero", bordero.getCodigo());
		parametros.put("nomeLoja", bordero.getMotorista().getLoja().getRazao());
		parametros.put("cnpjLoja", bordero.getMotorista().getLoja().getCnpj());

		List<LancamentoHoleriteDTO> lancamentos = LancamentoHoleriteDTO.converter(bordero.getLancamentos());

		JRDataSource jrDataSource = new JRBeanCollectionDataSource(lancamentos);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/Holerite-Motorista.jasper");

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, jrDataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {

		}
	}
}
