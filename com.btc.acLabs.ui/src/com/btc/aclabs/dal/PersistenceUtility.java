package com.btc.aclabs.dal;
//singleton class --only a single object can be created

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
		//em.close();
	}

	public List<Requirements> readAll() {
		List<Requirements> requirements = em.createQuery("SELECT r FROM com.btc.aclabs.dto.Requirements r").getResultList();
		return requirements;
	}
	public void clearDataBase()
	{
		this.em.getTransaction().begin();
		List<Requirements> l=this.readAll();
		for(int i=0;i<l.size();i++)
			em.remove(l.get(i));
		this.em.getTransaction().commit();
		
	}
	public void deleteRequirement(Requirements r)
	{
		this.em.getTransaction().begin();
		this.em.remove(r);
		this.em.getTransaction().commit();
	}
}
