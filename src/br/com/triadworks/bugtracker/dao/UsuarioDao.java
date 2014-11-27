package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.modelo.Usuario;

@Repository
@Transactional
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Usuario> lista() {
		TypedQuery<Usuario> typedQuery = entityManager.createQuery("select u from Usuario u", Usuario.class);
		return typedQuery.getResultList();
	}

	public void adiciona(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public void atualiza(Usuario usuario) {
		entityManager.merge(usuario);
	}

	public void remove(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
	}

	public Usuario busca(Integer id) {
		return entityManager.find(Usuario.class, id);
	}

	public Usuario buscaPor(String login, String senha) {
		try{
			return entityManager
					.createQuery(
							"select u from Usuario u "
									+ "where u.login = :login and u.senha = :senha", Usuario.class)
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

}
