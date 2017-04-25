package com.btc.aclabs.dal;
//singleton class --only a single object can be created

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.btc.aclabs.dto.Requirements;

public class PersistenceUtility {
	private static PersistenceUtility instance;
	private EntityManagerFactory emf;
	private EntityManager em;

	private PersistenceUtility() {
		this.emf = Persistence.createEntityManagerFactory("$objectdb/db/requirements.odb");

	}

	public static PersistenceUtility getInstance() {

		if (instance == null)
			instance = new PersistenceUtility();

		instance.em = instance.emf.createEntityManager();
		return instance;
	}

	public void create(Requirements r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		em.close();
	}

	public List<Requirements> readAll() {
		List<Requirements> requirements = em.createQuery("SELECT r FROM Requirements r").getResultList();
		return requirements;
	}
}
