package br.com.bigsupermercados.entrega.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = GuiaRepository.class, entityManagerFactoryRef = "entregaEntityManager")
public class EntregaDBConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "entrega.datasource")
	public DataSource entregaDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entregaEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("entregaDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("br.com.bigsupermercados.entrega.modelo.entrega").build();

	}
}
