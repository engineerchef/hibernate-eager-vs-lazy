package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session=factory.getCurrentSession();

		try {
			Instructor theInstructor=new Instructor("Tolga","Duran","tolgaduran1980@gmail.com");

			InstructorDetail theDetail=new InstructorDetail("http://www.tolgaduran.com/youtube","Luv 2 Code!!!");

			theInstructor.setInstructorDetail(theDetail);

			session.beginTransaction();

			session.save(theInstructor);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
