package br.com.triadworks.bugtracker.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtil;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;
	private List<Usuario> usuarios;
	private FacesUtil facesUtil = new FacesUtil();
	
	public void gravar(){
		getUsuarioDao().adiciona(usuario);
		usuario = new Usuario();
		
		facesUtil.adicionaMensagemDeSucesso("Usuário salvo com sucesso!");
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void excluir(Usuario u){
		getUsuarioDao().remove(u);
		usuarios = getUsuarioDao().lista();
		
		facesUtil.adicionaMensagemDeSucesso("Usuário deletado com sucesso!");
	}
	
	public void editar(){
		getUsuarioDao().atualiza(usuario);
		usuario = new Usuario();
		
		facesUtil.adicionaMensagemDeSucesso("Usuário alterado com sucesso!");
	}
	
	public List<Usuario> lista(){
		usuarios = getUsuarioDao().lista();
		return usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
