package space.nixus.swaggex.filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import space.nixus.swaggex.service.AuthenticationService;

public class BearerFilter implements Filter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                var bearer = ((HttpServletRequest)request).getHeader("Authorization");
            if(bearer != null && bearer.startsWith("Bearer", 0)) {
                var parts = bearer.split("\\s+");
                if(AuthenticationService.validateToken(jdbcTemplate, parts[1])) {
                    chain.doFilter(request, response);
                    return;
                }
            }     
            ((HttpServletResponse)response).sendError(401);
    }
}
