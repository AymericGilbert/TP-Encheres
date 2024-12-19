package fr.eni.tp.encheres.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecuriteConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(Directive.ALL));
		
		   
		
		http
			.authorizeHttpRequests((authorize) -> authorize
					.requestMatchers("/", "/index").permitAll()
					.requestMatchers("/css/**").permitAll()
					.requestMatchers("/images/**").permitAll()
					.requestMatchers("/...").hasAnyRole("USER")//ADMIN
					.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			//.formLogin(Customizer.withDefaults());
			//personnalise la connexion 
			.formLogin(form -> form
					.loginPage("/login")
					.permitAll())
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout" , "GET")) // defini l'url permettant de se deconnecter
					.addLogoutHandler(clearSiteData)// vide les donnee de l'utilisateur
					)	
			;
		
		return http.build();
	}

	//@Bean
	public UserDetailsService userDetailsService() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String passwordChiffre = passwordEncoder.encode("Pa$$wOrd");
		
		UserDetails user = User.builder()
				.username("user")
				.password(passwordChiffre)
				.roles("USER")
				.build();
			UserDetails admin = User.builder()
				.username("admin")
				.password(passwordChiffre)
				.roles("USER", "ADMIN")
				.build();
			return new InMemoryUserDetailsManager(user, admin);
	}
	
		@Bean
		public UserDetailsService userDetailsService(DataSource dataSource) {
			JdbcUserDetailsManager  jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
			
			//cofiguration de la requete permettant de verifier que l'utilisateur est autorisé à se connecter
			jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, mot_de_passe, 1 FROM UTILISATEURS WHERE email = ?");
			jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, CASE administrateur WHEN 1 THEN 'ADMIN' WHEN 0 THEN 'USER' END FROM UTILISATEURS WHERE email = ?");
			
			return jdbcUserDetailsManager;
		}
		
}		