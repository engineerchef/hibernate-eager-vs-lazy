package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session=factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			int theId=2;
			InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class, theId);
			
			System.out.println("tempInstructorDetail: "+tempInstructorDetail);
			
			System.out.println("the associated instructýor: "+tempInstructorDetail.getInstructor());
			
			// now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: "+tempInstructorDetail);
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);

			session.getTransaction().commit();
		}catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
