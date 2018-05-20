package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Course;
import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session=factory.getCurrentSession();

		try {
			Instructor theInstructor=new Instructor("Susan","Public","suasn.public@gmail.com");

			InstructorDetail theDetail=new InstructorDetail("http://www.youtube.com","Gaming");

			theInstructor.setInstructorDetail(theDetail);

			session.beginTransaction();

			session.save(theInstructor);

			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
}
