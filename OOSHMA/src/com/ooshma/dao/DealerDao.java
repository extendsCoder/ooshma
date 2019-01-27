package com.ooshma.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
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
		boolean isSuccessful = false;
		return isSuccessful;
	}
	
	public Dealer retrieveDealerInformationByLogin(String login){
		Dealer dealer = null;
		return dealer;
	}
	
	public Dealer retrieveDealerInformationByEmailAddress(String emailAddress){
		Dealer dealer = null;
		return dealer;
	}
	
	public List<Dealer> retrieveAllDealers(){
		List<Dealer> dealers = new ArrayList<Dealer>();
		return dealers;
	}
	
	public boolean updateDealerInformation(Dealer dealer, String login){
		boolean isSuccessful = false;
		
		return isSuccessful;
	}
}
