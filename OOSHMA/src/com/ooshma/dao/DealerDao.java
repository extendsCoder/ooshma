package com.ooshma.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.ooshma.domain.Dealer;

public class DealerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveDealerInformation(Dealer dealer){
		boolean isSuccessful = true;
		Session session = this.getSessionFactory().openSession();
		try{
			
			NativeQuery query = session.createSQLQuery(
					"INSERT INTO Dealer (login,name,address,location,email,contact,password,cost,active) "
					+ "VALUES (:login,:name,:address,:location,:email,:contact,:password,:cost,:active");
			query.setParameter("login", dealer.getLogin());
			query.setParameter("name", dealer.getName());
			query.setParameter("address", dealer.getAddress());
			query.setParameter("location", dealer.getLocation());
			query.setParameter("email", dealer.getEmailAddress());
			query.setParameter("contact", dealer.getContactNumber());
			query.setParameter("password", dealer.getPassword());
			query.setParameter("price", dealer.getPrice());
			query.setParameter("active", dealer.getActive());
			query.executeUpdate();
		}catch(Exception ex){
			isSuccessful = false;
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return isSuccessful;
	}
	
	public Dealer retrieveDealerInformationByLogin(String login){
		Session session = this.getSessionFactory().openSession();
		try{
			String sql = "SELECT * FROM Dealer where login = :login";
			NativeQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Dealer.class);
			sqlQuery.setParameter("login", login);
			Dealer dealer = null;
			dealer = (Dealer) sqlQuery.list();
			return dealer;
		}finally{
			session.close();
		}
	}
	
	public Dealer retrieveDealerInformationByEmailAddress(String emailAddress){
		Session session = this.getSessionFactory().openSession();
		try{
			String sql = "SELECT * FROM Dealer where email = :email";
			NativeQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Dealer.class);
			sqlQuery.setParameter("email", emailAddress);
			Dealer dealer = null;
			dealer = (Dealer) sqlQuery.list();
			return dealer;
		}finally{
			session.close();
		}
	}
	
	public List<Dealer> retrieveAllDealers(){
		List<Dealer> dealers = new ArrayList<Dealer>();
		Session session = this.getSessionFactory().openSession();
		try{
			String sql = "SELECT * FROM Dealer";
			NativeQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Dealer.class);
			dealers = (List<Dealer>) sqlQuery.list();
			return dealers;
		}finally{
			session.close();
		}
	}
	
	public boolean updateDealerInformation(Dealer dealer, String login){
		boolean isSuccessful = true;
		Session session = null;
		try{
			session = this.getSessionFactory().openSession();
			String sql = "update Dealer set name = :name, address = :address, location = :location"
					+ ", contact = :contact, password = :password, price = :price, active = :price"
					+ " where login = :login";
			NativeQuery query = session.createSQLQuery(sql);
			query.setParameter("login", login);
			query.setParameter("name", dealer.getName());
			query.setParameter("address", dealer.getAddress());
			query.setParameter("location", dealer.getLocation());
			query.setParameter("contact", dealer.getContactNumber());
			query.setParameter("password", dealer.getPassword());
			query.setParameter("price", dealer.getPrice());
			query.setParameter("active", dealer.getActive());
			query.executeUpdate();
		}catch(Exception ex){
			isSuccessful = false;
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return isSuccessful;
	}
}
