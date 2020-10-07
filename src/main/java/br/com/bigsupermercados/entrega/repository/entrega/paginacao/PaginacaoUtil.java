package br.com.bigsupermercados.entrega.repository.entrega.paginacao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil {

	public void preparar(Criteria criteria, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegisro = paginaAtual * totalRegistrosPorPagina;

		criteria.setFirstResult(primeiroRegisro);
		criteria.setMaxResults(totalRegistrosPorPagina);

		Sort sort = pageable.getSort();
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
	}
}