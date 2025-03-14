///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package PassCamp.ass.main.security;
//
//import PassCamp.ass.main.service.implement.LoginServiceImpl;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
///**
// *
// * @author AD
// */
//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final LoginServiceImpl loginService;
//
//    @Bean
//    public UserDetailsService loginDetailsService() {
//        return loginService;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(loginService);
//
//        return provider;
//    }
//
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
//            throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(httpForm -> {
//                    httpForm.loginPage("/login").permitAll();
//                })
//                .authorizeHttpRequests(registry -> {
//                    registry.requestMatchers(
//                            "/account/register"
//                    ).permitAll();
//                    registry.requestMatchers(
//                            HttpMethod.GET,
//                            "/item"
//                    ).permitAll();
//                    registry.anyRequest().authenticated();
//                })
//                .build();
//
//    }
//}
