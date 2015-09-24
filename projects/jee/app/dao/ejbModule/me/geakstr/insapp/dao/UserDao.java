package me.geakstr.insapp.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import me.geakstr.insapp.dao.entities.User;

@Stateless
public class UserDao extends AbstractDao<User> {
	@PersistenceContext(unitName = "insapp-dao-layer-pu")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UserDao() {
		super(User.class);
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		Query q = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :usrname AND u.password = :pswrd");
		q.setParameter("usrname", username);
		q.setParameter("pswrd", password);

		List<User> result = q.getResultList();
		return result == null || result.isEmpty() ? null : result.get(0);
	}
}
