package br.zprint.security;

import br.zprint.ApplicationContextLoad;
import br.zprint.model.Usuario;
import br.zprint.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JWTTokenAutenticacaoService  {

    private static final long EXPIRATION_TIME = 172800000;
    private static final String SECRET = "dkk&%$kks983794--";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public void addAuthentication(HttpServletResponse response, String email) throws IOException {
        String JWT = Jwts.builder()
                         .setSubject(email)
                         .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                         .signWith(SignatureAlgorithm.HS512, SECRET)
                         .compact();

        String token = TOKEN_PREFIX + " " + JWT;
        response.addHeader(HEADER_STRING, token);

        liberarcaoCors(response);
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(HEADER_STRING);

        try {
            if (token != null) {
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody().getSubject();

                if (user != null) {
                    Usuario usuario = ApplicationContextLoad.getApplicationContext()
                            .getBean(UsuarioRepository.class).findByLogin(user);

                    if (usuario != null) {
                        return new UsernamePasswordAuthenticationToken(
                                usuario.getEmail(),
                                usuario.getSenha(),
                                usuario.getAuthorities()
                        );
                    }
                }
            }
        }catch (io.jsonwebtoken.ExpiredJwtException e){
            e.printStackTrace();
        }catch (AuthenticationException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.printf("erro");
        }

        liberarcaoCors(response);
        return null;
    }

    private void liberarcaoCors(HttpServletResponse response) {
        if(response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        if(response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }

        if(response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }

        if(response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }
}
