package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session=factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			int id=2;
			
			Instructor theInstructor=session.get(Instructor.class, id);
			
			if(theInstructor!=null) {
				System.out.println("Deleting: "+theInstructor);
				session.delete(theInstructor);
			}

			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
