//package org.example.project1.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////		http.authorizeHttpRequests((requests) -> requests
////				.requestMatchers("/api/**").authenticated()
////				.requestMatchers("/exhibition/**").authenticated()
////				.anyRequest().permitAll())
////			// .csrf((csrf) -> csrf
////			// 	.ignoringRequestMatchers(PathRequest.toH2Console()))
////			.csrf(AbstractHttpConfigurer::disable)
//////			.csrf().disable()
////			.headers((headers) -> headers
////				.addHeaderWriter(new XFrameOptionsHeaderWriter(
////					XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
////			.formLogin((formLogin) -> formLogin
////				.loginPage("/login_form"))
//////				.defaultSuccessUrl("/home/main"))
////			.logout((logout) -> logout
////				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////				.logoutSuccessUrl("/")
////				.permitAll()
////				.invalidateHttpSession(true)
////				.deleteCookies("JSESSIONID")
////			);
//
//		http
//				.csrf(AbstractHttpConfigurer::disable)
//				.formLogin(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests((requests) -> requests
//						.requestMatchers("/api/**").authenticated()
//						.requestMatchers("/exhibition/**").authenticated()
//						.requestMatchers("/h2-console/**").permitAll()
//						.anyRequest().permitAll())
//				.httpBasic();
//
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}
