package br.com.triadworks.bugtracker.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.service.Autenticador;
import br.com.triadworks.bugtracker.service.AutenticadorImpl;
import br.com.triadworks.bugtracker.util.FacesUtil;

@ManagedBean
public class LoginBean {
	
	private String login;
	private String senha;
	@ManagedProperty("#{usuarioLogado}")
	private UsuarioLogado usuarioLogado;
	
	@ManagedProperty("#{autenticador}")
	private Autenticador autenticador;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String logar() {
		Usuario usuario = getAutenticador().autentica(login, senha);
		boolean ehValido = (usuario != null);
		System.out.println("Login e senha são válidos? "+ehValido);
		if (ehValido) {
			usuarioLogado.loga(usuario);
			return "lista?faces-redirect=true";
		}
		
		FacesUtil facesUtil = new FacesUtil();
		facesUtil.adicionaMensagemDeErro("Login e senha não conferem!");
		
		return null;
	}
	
	public String sair(){
		usuarioLogado.desloga();
		return "/pages/usuario/login?faces-redirect=true";
	}
	
	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public Autenticador getAutenticador() {
		return autenticador;
	}
	public void setAutenticador(Autenticador autenticador) {
		this.autenticador = autenticador;
	}

}
