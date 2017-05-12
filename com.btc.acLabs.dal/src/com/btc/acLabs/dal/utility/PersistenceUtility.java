package com.btc.acLabs.dal.utility;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.btc.acLabs.bl.dmos.Requirement;

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

	public void create(Requirement r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		//em.close();
	}

	public List<Requirement> readAll() {
		List<Requirement> requirements = em.createQuery("SELECT r FROM com.btc.acLabs.bl.internal.dmos.RequirementImpl r").getResultList();
		return requirements;
	}
	public void clearDataBase()
	{
		this.em.getTransaction().begin();
		List<Requirement> l=this.readAll();
		for(int i=0;i<l.size();i++)
			em.remove(l.get(i));
		this.em.getTransaction().commit();
		
	}
	public void deleteRequirement(Requirement r)
	{
		this.em.getTransaction().begin();
		this.em.remove(r);
		this.em.getTransaction().commit();
	}
}
