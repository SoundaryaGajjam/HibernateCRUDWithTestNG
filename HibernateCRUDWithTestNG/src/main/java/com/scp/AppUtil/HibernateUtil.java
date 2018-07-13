package com.scp.AppUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	static SessionFactory sFactory=null;
	public static SessionFactory getSessionFactory(){
		if(null==sFactory)
			sFactory=new Configuration().configure().buildSessionFactory();
		return sFactory;
	}
	
	public static void FlushNCommit(Session session,Transaction tr){
		if(null!=session)
			session.flush();
		
		if(null!=tr)
			tr.commit();
		
	}
}
