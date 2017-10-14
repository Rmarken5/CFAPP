package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entities.Team;

public class Test {
	 private static SessionFactory factory; 
	   public static void main(String[] args) {
	      
	      try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		Session session = factory.openSession();
		session.getTransaction().begin();;
		Team team = session.find(Team.class, 1L);
		System.out.println(team);
		session.getTransaction().commit();
	}

}
