package com.StgrManager.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StgrManager.Repositories.StagiaireRepository;
import com.StgrManager.autre.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final StagiaireRepository stagiaireRepository;
	private final JwtUtil jsJwtUtil;

	public AuthenticationController(AuthenticationManager authenticationManager,
			StagiaireRepository stagiaireRepository, JwtUtil jsJwtUtil) {
		this.authenticationManager = authenticationManager;
		this.stagiaireRepository = stagiaireRepository;
		this.jsJwtUtil = jsJwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest req) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(req.getLogin(), req.getMot_de_passe()));
		final UserDetails user = stagiaireRepository.findByLogin(req.getLogin());
		if(user != null) {
			return ResponseEntity.ok(jsJwtUtil.generateToken(user));
		}
		return ResponseEntity.status(400).body("email ou mot de pass invalide");
	}

}
