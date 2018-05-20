package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Course;
import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			Instructor instructor=session.get(Instructor.class, id);

			System.out.println("luv2code: Instructor: "+instructor);
			
			// option 1: call getter method while session is open
			System.out.println("luv2code: Courses: "+instructor.getCourses());
			
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
