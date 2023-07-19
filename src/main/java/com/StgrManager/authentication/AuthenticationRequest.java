package com.StgrManager.authentication;

import javax.validation.constraints.NotEmpty;


public class AuthenticationRequest {
	
	@NotEmpty(message = "Ce champ ne peut pas être vide")
    private String login;
	@NotEmpty(message = "Ce champ ne peut pas être vide")
    private String mot_de_passe;
	
	public AuthenticationRequest() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
}
