package br.zprint.security;

import br.zprint.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@Component
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .disable()
            .authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/index").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTApiAuthenticacaoFilter(), UsernamePasswordAuthenticationFilter.class)
//            .exceptionHandling().authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> {
//                String json = String.format("{\"message\": \"%s\"}", "Usuário ou senha inválida");
//                httpServletResponse.setStatus(httpServletResponse.SC_FORBIDDEN);
//                httpServletResponse.setContentType("application/json");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(json);
//            }))
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
