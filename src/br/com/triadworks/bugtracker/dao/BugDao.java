package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Comentario;

@Repository
@Transactional
public class BugDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Bug> lista() {
			return entityManager.createQuery("select b from Bug b", Bug.class)
					.getResultList();
	}

	public void salva(Bug bug) {
		entityManager.persist(bug);
	}

	public void atualiza(Bug bug) {
			entityManager.merge(bug);
	}

	public void remove(Bug bug) {
		entityManager.remove(entityManager.merge(bug));
	}

	public Bug busca(Integer id) {
			return entityManager.find(Bug.class, id);
	}

	public List<Bug> listaPaginada(int inicio, int quantidade) {
			return entityManager
					.createQuery("select b from Bug b", Bug.class)
					.setFirstResult(inicio)
					.setMaxResults(quantidade)
					.getResultList();
	}

	public int contaTodos() {
			Long count = entityManager
					.createQuery("select count(b) from Bug b", Long.class)
					.getSingleResult();
			return count.intValue();
	}
	
	public List<Bug> getBugsDoUsuario(Integer id) {
			return entityManager
					.createQuery("select b from Bug b where b.responsavel.id = :id", Bug.class)
					.setParameter("id", id)
					.getResultList();
	}
	
	public Bug buscaComComentarios(Integer id) {
			Bug bug = entityManager.find(Bug.class, id);
			if (bug != null)
				bug.getComentarios().size();
			return bug;
	}
	
	public void comenta(Integer id, Comentario comentario) {
			Bug bug = this.busca(id);
			bug.comenta(comentario);
	}
	
	public void fecha(Integer id, Comentario comentario) {
			Bug bug = this.busca(id);
			bug.fecha(comentario);
	}
	
}
