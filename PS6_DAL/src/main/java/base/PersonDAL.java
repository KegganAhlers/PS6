package base;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL  {


	/**
	 * addPerson - Method adds a Person to the database
	 * @param Per
	 * @return
	 */
	public static PersonDomainModel addPerson(PersonDomainModel Per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(Per);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Per;
	}
	
	
	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PerGet = null;		
		ArrayList<PersonDomainModel> Pers = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List Persons = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = Persons.iterator(); iterator.hasNext();) {
				PersonDomainModel Per = (PersonDomainModel) iterator.next();
				Pers.add(Per);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Pers;
	}		
	
	
	public static PersonDomainModel getPerson(UUID PerID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PerGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where PersonId = :id ");
			query.setParameter("id", PerID.toString());
			
			List<?> list = query.list();
			PerGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return PerGet;
	}		
	
	public static void deletePerson(UUID PerID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PerGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel Per = (PersonDomainModel) session.get(PersonDomainModel.class, PerID);
			session.delete(Per);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	

	public static PersonDomainModel updatePerson(PersonDomainModel Per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PerGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(Per);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return Per;
	}		
	
	
	
	
	
	
}
