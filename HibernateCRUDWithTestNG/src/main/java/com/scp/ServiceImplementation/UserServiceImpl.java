package com.scp.ServiceImplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.scp.AppUtil.HibernateUtil;
import com.scp.Entities.UserInfo;
import com.scp.Services.UserService;

public class UserServiceImpl implements UserService{
	List listOfUsers=new ArrayList<>();
	Scanner sc=new Scanner(System.in);
	UserInfo userObj=null;

	public boolean addUser(UserInfo userObj,Session session1, Transaction tr1) {
		listOfUsers.add(userObj);
		session1.save(userObj);
		HibernateUtil.FlushNCommit(session1, tr1);
		return true;
	}

	@Override
	public UserInfo getUser(int uId, Session s1, Transaction tr1) {
		userObj=s1.get(UserInfo.class, uId);
		HibernateUtil.FlushNCommit(s1, tr1);
		if(null!=userObj)
			return userObj;
		return null;
	}

	@Override
	public List getAllUsers(Session s1, Transaction tr1) {
		Query query=s1.createQuery("from UserInfo");
		HibernateUtil.FlushNCommit(s1, tr1);
		return query.list();
	}

	@Override
	public void deleteUser(int uId, Session s1, Transaction tr1) {
		listOfUsers=getAllUsers(s1, tr1);
		Iterator<UserInfo>itr=listOfUsers.iterator();
		Transaction tr2=s1.beginTransaction();
		while(itr.hasNext()){
			userObj=itr.next();
			if(null !=userObj && (uId==userObj.getUserId())){
				listOfUsers.remove(userObj);
				s1.delete(userObj);
				HibernateUtil.FlushNCommit(s1, tr2);
			}
		}
		
	}

	@Override
	public void updateCustomer(List listOfUsers, UserInfo userObj, Session s1, Transaction tr3) {
		if(null!=userObj && (listOfUsers.contains(userObj))){
			System.out.println("Enter new Password : ");
			String password=sc.next();
			userObj.setPassword(password);
			s1.update(userObj);
			HibernateUtil.FlushNCommit(s1, tr3);
		}
		
	}
	
}
