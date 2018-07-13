package com.scp.TestMain;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.AppUtil.HibernateUtil;

import com.scp.Entities.UserInfo;

import com.scp.ServiceImplementation.UserServiceImpl;
import com.scp.Services.UserService;
import com.scp.test.login.UserLogin;

public class TestMainClass {
	static Session s1=null;
	static Transaction tr1=null;
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		UserService usrService=new UserServiceImpl();
		UserInfo userObj=null;
		String opt;
		int uId;
		List listOfUsers;
		do {
			s1 = HibernateUtil.getSessionFactory().openSession();
			tr1 = s1.beginTransaction();
			System.out.println(
					"1.Add User\n2.Update User\n3.Get Particular User based on Id\n4.Get All User Details\n5.Delete User based on Id\n6.Exit\n");
			System.out.println("Enter ur choice : ");

			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				userObj = inputFromUser();
				usrService.addUser(userObj,s1, tr1);
				break;
			case 2:
				listOfUsers = usrService.getAllUsers(s1, tr1);
				System.out.println("Enter Customer Id which U want  to update : ");
				uId = sc.nextInt();
				Transaction tr2 = s1.beginTransaction();
				userObj = usrService.getUser(uId, s1, tr2);
				Transaction tr3 = s1.beginTransaction();
				usrService.updateCustomer(listOfUsers, userObj, s1, tr3);
				break;
			case 3:
				System.out.println("Enter User Id which U want : ");
				uId = sc.nextInt();
				userObj = usrService.getUser(uId, s1, tr1);
				System.out.println(userObj);
				break;
			case 4:
				listOfUsers = usrService.getAllUsers(s1, tr1);
				System.out.println(listOfUsers);
				break;
			case 5:
				System.out.println("Enter cust Id which u want to delete : ");
				uId = sc.nextInt();
				usrService.deleteUser(uId, s1, tr1);
				break;
			case 6:
				System.out.println("Sorry ... U r exited");
				System.exit(0);
				break;
			default:
				System.out.println("Ooopss...U r entred wrong choice :-) ");
			}
			System.out.println("If U want to continue press (y/n) : ");
			opt = sc.next();
		} while ("y".equals(opt));

		
		
		
		System.out.println("Hiiiiiiii");
		
		
		
		
		
		getDatafromDB(s1, tr1);
		System.out.println("byeeeeeeeeee");
	}
	
	private static UserInfo inputFromUser() {
		System.out.println("Enter user id : ");
		int uId=sc.nextInt();
		System.out.println("Enter user Name : ");
		String userName=sc.next();
		System.out.println("Enter password : ");
		String password=sc.next();
		return new UserInfo(uId,userName, password);
	}

	public static String getDatafromDB(Session s1,Transaction tr1) throws IOException{
		UserInfo userObj=s1.get(UserInfo.class,101);
		String userName=userObj.getUserName();
		String password=userObj.getPassword();
		return "pass";
		//System.out.println("DB : "+userName+" : "+password);
		//authenticate(userName, password);
	}
}
