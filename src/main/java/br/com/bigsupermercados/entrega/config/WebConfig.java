package br.com.bigsupermercados.entrega.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
@EnableAsync
public class WebConfig implements WebMvcConfigurer {

//	@Bean
//	public CacheManager cacheManager() throws URISyntaxException {
//
//		return new JCacheCacheManager(Caching.getCachingProvider()
//				.getCacheManager(getClass().getResource("/env/ehcache.xml").toURI(), getClass().getClassLoader()));
//	}
}