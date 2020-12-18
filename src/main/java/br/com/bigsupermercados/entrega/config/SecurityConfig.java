package br.com.bigsupermercados.entrega.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.bigsupermercados.entrega.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**").antMatchers("/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/cidade").hasRole("CADASTRAR_CIDADE")
				.antMatchers("/loja").hasRole("CADASTRAR_LOJA")
				.antMatchers("/cliente").hasRole("CADASTRAR_CLIENTE")
				.antMatchers("/motorista").hasRole("CADASTRAR_MOTORISTA")
				.antMatchers("/usuarios").hasRole("CADASTRAR_USUARIO")
				.antMatchers("/tipoLancamento").hasRole("CADASTRAR_TIPO_LANCAMENTO")
				.antMatchers("/bairro").hasRole("CADASTRAR_BAIRRO")
				.antMatchers("/endereco").hasRole("CADASTRAR_ENDERECO")
				.antMatchers("/guia").hasRole("GERENCIAR_GUIA")
				.antMatchers("/bordero").hasRole("GERENCIAR_BORDERO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.sessionManagement()	
				.invalidSessionUrl("/login");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
