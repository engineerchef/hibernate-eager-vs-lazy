package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.tolgaduran.demo.entity.Course;
import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session=factory.getCurrentSession();

		try {
			session.beginTransaction();

			int id=1;

			// option 2: Hibernate query with HQL
			Query<Instructor> query=session.createQuery("select i from Instructor i join fetch i.courses where i.id=:theInstructorId",Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", id);
			
			// execute query and get instructor
			Instructor instructor=query.getSingleResult();

			System.out.println("luv2code: Instructor: "+instructor);

			session.getTransaction().commit();

			// close the session
			session.close();

			System.out.println("\nluv2code: The session is now closed!\n");
			System.out.println("luv2code: Courses: "+instructor.getCourses());

			System.out.println("luv2code: Done!!!");
		} finally {
			session.close();
			factory.close();
		}
	}
}
