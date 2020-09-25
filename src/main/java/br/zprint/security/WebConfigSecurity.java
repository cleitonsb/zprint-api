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
            .authorizeRequests()
            .antMatchers("/index").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            .antMatchers(HttpMethod.GET, "/usuario/avatar/**").permitAll()
            .antMatchers(HttpMethod.GET, "/usuario/**").hasAuthority("usuario_get")
            .antMatchers(HttpMethod.POST, "/usuario/**").hasAuthority("usuario_post")
            .antMatchers(HttpMethod.PUT, "/usuario/**").hasAuthority("usuario_put")
            .antMatchers(HttpMethod.DELETE, "/usuario/**").hasAuthority("usuario_delete")

            .antMatchers(HttpMethod.GET, "/produto/**").hasAuthority("produto_get")
            .antMatchers(HttpMethod.POST, "/produto/**").hasAuthority("produto_post")
            .antMatchers(HttpMethod.PUT, "/produto/**").hasAuthority("produto_put")
            .antMatchers(HttpMethod.DELETE, "/produto/**").hasAuthority("produto_delete")

            .antMatchers(HttpMethod.GET, "/venda/**").hasAuthority("venda_get")
            .antMatchers(HttpMethod.POST, "/venda/**").hasAuthority("venda_post")
            .antMatchers(HttpMethod.PUT, "/venda/**").hasAuthority("venda_put")
            .antMatchers(HttpMethod.DELETE, "/venda/**").hasAuthority("venda_delete")

            .antMatchers(HttpMethod.GET, "/caixa/**").hasAuthority("caixa_get")
            .antMatchers(HttpMethod.POST, "/caixa/**").hasAuthority("caixa_post")
            .antMatchers(HttpMethod.PUT, "/caixa/**").hasAuthority("caixa_put")
            .antMatchers(HttpMethod.DELETE, "/caixa/**").hasAuthority("caixa_delete")

            .antMatchers(HttpMethod.GET, "/pagamento/**").hasAuthority("pagamento_get")
            .antMatchers(HttpMethod.POST, "/pagamento/**").hasAuthority("pagamento_post")
            .antMatchers(HttpMethod.PUT, "/pagamento/**").hasAuthority("pagamento_put")
            .antMatchers(HttpMethod.DELETE, "/pagamento/**").hasAuthority("pagamento_delete")

            .antMatchers("/**").denyAll()

            .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTApiAuthenticacaoFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> {
                String json = String.format("{\"message\": \"%s\"}", "Usuário ou senha inválida");
                httpServletResponse.setStatus(httpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(json);
            }))
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
