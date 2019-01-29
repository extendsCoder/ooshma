package com.ooshma.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.ooshma.domain.Customer;

public class CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveCustomerInformation(Customer customer){
		boolean isSuccessful = true;
		Session session = this.getSessionFactory().openSession();
		try{
			
			NativeQuery query = session.createSQLQuery(
					"INSERT INTO Customer (login,name,address,location,email,contact,password) "
					+ "VALUES (:login,:name,:address,:location,:email,:contact,:password");
			query.setParameter("login", customer.getLogin());
			query.setParameter("name", customer.getName());
			query.setParameter("address", customer.getAddress());
			query.setParameter("location", customer.getLocation());
			query.setParameter("email", customer.getEmailAddress());
			query.setParameter("contact", customer.getContactNumber());
			query.setParameter("password", customer.getPassword());
			query.executeUpdate();
		}catch(Exception ex){
			isSuccessful = false;
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return isSuccessful;
	}
	
	public Customer retrieveCustomerInformationByLogin(String login){
		Session session = this.getSessionFactory().openSession();
		try{
			String sql = "SELECT * FROM Customer where login = :login";
			NativeQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Customer.class);
			sqlQuery.setParameter("login", login);
			Customer customer = null;
			customer = (Customer) sqlQuery.list();
			return customer;
		}finally{
			session.close();
		}
	}
	
	public Customer retrieveCustomerInformationByEmailAddress(String emailAddress){
		Session session = this.getSessionFactory().openSession();
		try{
			String sql = "SELECT * FROM Customer where email = :email";
			NativeQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Customer.class);
			sqlQuery.setParameter("email", emailAddress);
			Customer customer = null;
			customer = (Customer) sqlQuery.list();
			return customer;
		}finally{
			session.close();
		}
	}
	
	public boolean updateCustomerInformation(Customer customer, String login){
		boolean isSuccessful = true;
		Session session = null;
		try{
			session = this.getSessionFactory().openSession();
			String sql = "update Customer set name = :name, address = :address, location = :location"
					+ ", contact = :contact, password = :password"
					+ " where login = :login";
			NativeQuery query = session.createSQLQuery(sql);
			query.setParameter("login", login);
			query.setParameter("name", customer.getName());
			query.setParameter("address", customer.getAddress());
			query.setParameter("location", customer.getLocation());
			query.setParameter("contact", customer.getContactNumber());
			query.setParameter("password", customer.getPassword());
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
