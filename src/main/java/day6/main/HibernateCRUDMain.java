package day6.main;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import day6.beans.Restaurant;
import day6.utils.HibernateUtils;

public class HibernateCRUDMain {
	
	private static void deleteRestaurant() {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Class<Restaurant> entityClassType = Restaurant.class;
		Serializable entityId = 106;
		Restaurant foundRestaurant = session.load(entityClassType, entityId);
		Transaction tx = session.beginTransaction();
			session.delete(foundRestaurant);
		tx.commit();
		session.close();
		factory.close();
		System.out.println("Record deleted");
	}
	
	private static void showOneRestaurant() {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Class<Restaurant> entityClassType = Restaurant.class;
		
		Serializable entityId = 101;
		Restaurant foundRestaurant = session.load(entityClassType, entityId);
		System.out.println(foundRestaurant);
		System.out.println("===================================");
		
		System.out.println(foundRestaurant.getClass().getName());
		System.out.println(foundRestaurant.getRestaurantId());
		System.out.println(foundRestaurant.getName());
		System.out.println(foundRestaurant.getCuisine());
		System.out.println(foundRestaurant.getBranchCount());
		session.close();
		factory.close();
	}


	private static void createNewRestaurant() {
		// TODO Auto-generated method stub
		//Building Configuration Object
		Configuration hibernateConfig = new Configuration();//Looks for hibernate.properties
		//Obtaining SessionFactory
		// hibernateConfig = hibernateConfig.configure();//Looks for hibernate.cfg.xml
		SessionFactory hibernateFactory = hibernateConfig.configure().buildSessionFactory();
		//Obtaining Session
		Session hibernateSession = hibernateFactory.openSession();
		//Creating Entity Class Object
		Restaurant rst[] = new Restaurant[5];
		rst[0] = new Restaurant(101, "Hotel Sliversand", "Thai", 7);
		rst[1] = new Restaurant(103, "Sliversand", "desert food", 17);
		rst[2] = new Restaurant(104, "Hotel", "food", 70);
		rst[3] = new Restaurant(105, "Hotel Chandru", "Ulhasnagar food", 37);
		rst[4] = new Restaurant(106, "xxxxxx", "Expensive food", 23);
		
		
		//Obtaining and Starting 'Transaction' using 'Session'
		Transaction hibernateTransaction = hibernateSession.beginTransaction();
		
		for(Restaurant rest: rst) {
		//Attaching the Entity Class Object to the "Session'
			hibernateSession.save(rest);
		}
		
		//Committing the 'Transaction'
		hibernateTransaction.commit();
		
		
		//Closing the Session
		hibernateSession.close();
		//Closing the SessionFactory
		hibernateFactory.close();
		System.out.println("Restaurant created successfully.");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		createNewRestaurant();
//		showOneRestaurant();
		deleteRestaurant();
		
	}



	
}
