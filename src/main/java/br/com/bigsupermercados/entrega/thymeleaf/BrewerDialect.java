package br.com.bigsupermercados.entrega.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.bigsupermercados.entrega.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.bigsupermercados.entrega.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.bigsupermercados.entrega.thymeleaf.processor.MessageElementTagProcessor;
import br.com.bigsupermercados.entrega.thymeleaf.processor.OrderElementTagProcessor;
import br.com.bigsupermercados.entrega.thymeleaf.processor.PaginationElementTagProcessor;

@Component
public class BrewerDialect extends AbstractProcessorDialect {

	public BrewerDialect() {
		super("AlgaWorks Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}
}
