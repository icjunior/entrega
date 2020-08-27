package br.com.bigsupermercados.entrega.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import br.com.bigsupermercados.entrega.modelo.zanthus.TabNotaHeader;
import br.com.bigsupermercados.entrega.repository.zanthus.TabNotaHeaderRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = TabNotaHeaderRepository.class, entityManagerFactoryRef = "zanthusEntityManager")
public class ZanthusDBConfig {

	@Bean
	@ConfigurationProperties(prefix = "zanthus.datasource")
	public DataSource zanthusDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean zanthusEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("zanthusDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("br.com.bigsupermercados.entrega.modelo.zanthus").build();
	}
}
