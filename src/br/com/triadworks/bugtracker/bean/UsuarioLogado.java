package br.com.triadworks.bugtracker.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.triadworks.bugtracker.modelo.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioLogado implements Serializable{
	
	private static final long serialVersionUID = 8545758358742253019L;
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void loga(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isLogado(){
		boolean result = usuario != null;
		return result;
	}

	public void desloga() {
		usuario = null;
	}
	
	
}
