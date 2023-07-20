package com.StgrManager.autre;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.StgrManager.Repositories.StagiaireRepository;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private StagiaireRepository stagiaireRepository;
	private final JwtUtil jwtUtil;

	public JwtAuthFilter(StagiaireRepository stagiaireRepository, JwtUtil jwtUtil) {
		this.stagiaireRepository = stagiaireRepository;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = req.getHeader("Authorization");
		final String jwtToken;
		final String login;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			login = jwtUtil.extractUsername(jwtToken);

			if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = stagiaireRepository.findByLogin(login);
				if (userDetails != null && jwtUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}

		filterChain.doFilter(req, res);
	}

}
