package br.com.triadworks.bugtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

@Service("autenticador")
@Transactional
public class AutenticadorImpl implements Autenticador {
	
	private UsuarioDao dao;
	
	@Autowired
	public AutenticadorImpl(UsuarioDao dao) {
		this.dao = dao;
	}

	@Override
	public Usuario autentica(String login, String senha) {
		Usuario usuario = getDao().buscaPor(login, senha);
		return usuario;
	}

	public UsuarioDao getDao() {
		return dao;
	}

	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

}