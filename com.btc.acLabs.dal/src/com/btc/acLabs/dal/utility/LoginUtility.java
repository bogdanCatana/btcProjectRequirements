package com.btc.acLabs.dal.utility;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.btc.acLabs.bl.dmos.User;


public class LoginUtility {
	private static LoginUtility instance;
	private EntityManagerFactory emf;
	private EntityManager em;

	private LoginUtility() {
		this.emf = Persistence.createEntityManagerFactory("$objectdb/db/users.odb");

	}

	public static LoginUtility getInstance() {

		if (instance == null)
			instance = new LoginUtility();

		instance.em = instance.emf.createEntityManager();
		return instance;
	}

	public void create(User r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		//em.close();
	}

	public List<User> readAll() {
		List<User> users = em.createQuery("SELECT u FROM com.btc.acLabs.bl.internal.dmos.UserImpl u").getResultList();
		return users;
	}
	
}
