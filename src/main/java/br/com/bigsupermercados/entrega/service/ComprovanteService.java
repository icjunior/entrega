package br.com.bigsupermercados.entrega.service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.TipoGuiaEnum;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ComprovanteService {

	public byte[] gerarComprovante() throws Exception {
		
		TipoGuiaEnum tipoGuia = TipoGuiaEnum.CUPOM_PDV;
		
		List<TipoGuiaEnum> tiposGuia = Arrays.asList(tipoGuia);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");

		JRDataSource jrDataSource = new JRBeanCollectionDataSource(tiposGuia);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/comprovante-guia-entrega.jasper");

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, jrDataSource);
//			return jasperPrint.setRightMargin(rightMargin);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {

		}
	}
}
