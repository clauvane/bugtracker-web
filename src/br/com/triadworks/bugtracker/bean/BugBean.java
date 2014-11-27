package br.com.triadworks.bugtracker.bean;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.util.FacesUtil;

@ManagedBean
public class BugBean {
	
	private List<Usuario> usuarios;
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;
	
	private List<Bug> bugs;
	private Bug bug = new Bug();
	
	@ManagedProperty("#{bugDao}")
	private BugDao bugDao;
	
	@PostConstruct
	public void init(){
		bug.setResponsavel(new Usuario());
	}
	
	public void adiciona(){
		bugDao.salva(bug);
		new FacesUtil().adicionaMensagemDeSucesso("Bug adicionado com sucesso!");
	}
	
	public void altera(){
		bugDao.atualiza(bug);
		new FacesUtil().adicionaMensagemDeSucesso("Bug alterado com sucesso!");
	}
	
	public void lista(){
		bugs = bugDao.lista(); 
	}
	
	public void remove(Bug bug){
		bugDao.remove(bug);
		bugs = bugDao.lista();
		new FacesUtil().adicionaMensagemDeSucesso("Bug removido com sucesso!");
	}
	
	public List<Status> getTodosOsStatus(){
		return Arrays.asList(Status.values());
	}
	
	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public BugDao getBugDao() {
		return bugDao;
	}

	public void setBugDao(BugDao bugDao) {
		this.bugDao = bugDao;
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = getUsuarioDao().lista();
		}
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
