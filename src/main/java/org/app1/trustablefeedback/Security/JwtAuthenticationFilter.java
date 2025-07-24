package org.app1.trustablefeedback.Security;

import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app1.trustablefeedback.ClientDetails.ClientDetails;
import org.app1.trustablefeedback.ClientDetails.ClientDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final ClientDetailsService clientDetailsService;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(ClientDetailsService clientDetailsService, JwtService jwtService) {
        this.clientDetailsService = clientDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException{
        //pega o header
        final String header = request.getHeader("Authorization");

        //verifica se tem bearer
        if (header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response); //continua sem role
            return;
        }

        final String token = header.substring(7);
        @Nullable
        final String email = jwtService.extractUsername(token);

        //garantindo que não está autenticado
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            ClientDetails clientDetails = clientDetailsService.loadUserByUsername(email);

            if (jwtService.validateToken(token, clientDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                        (email, null, clientDetails.getAuthorities());

                //aprovação
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
