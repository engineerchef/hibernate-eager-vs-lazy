package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Course;
import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

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

			System.out.println("Instructor: "+instructor);
			
			System.out.println("Courses: "+instructor.getCourses());
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
}
