package com.scp.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.Entities.UserInfo;

public interface UserService {
	public boolean addUser(UserInfo userObj, Session s1, Transaction tr1);

	public UserInfo getUser(int uId, Session s1, Transaction tr1);

	public List getAllUsers(Session s1, Transaction tr1);

	public void deleteUser(int uId, Session s1, Transaction tr1);

	public void updateCustomer(List listOfUsers, UserInfo userObj, Session s1, Transaction tr3);

	
}
