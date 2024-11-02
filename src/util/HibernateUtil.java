package util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.UserAccount;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getConnection() {
		if(sessionFactory == null) {
		Configuration conf = new Configuration();
		
		Properties settings = new Properties();
		
		// Hibernate settings equivalent to hibernate.cfg.xml's properties
		settings.put(Environment.DRIVER, "org.postgresql.Driver");
		settings.put(Environment.URL, "jdbc:postgresql://localhost:5433/student_management");
		settings.put(Environment.USER, "postgres");
		settings.put(Environment.PASS, "pazofils");
		settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

		settings.put(Environment.SHOW_SQL, "true");

		settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

		settings.put(Environment.HBM2DDL_AUTO, "create");
		conf.setProperties(settings);
		
		conf.addAnnotatedClass(UserAccount.class);
		
		sessionFactory = conf.buildSessionFactory();
		return sessionFactory;
		}else {
			return sessionFactory;
		}
	}
}
