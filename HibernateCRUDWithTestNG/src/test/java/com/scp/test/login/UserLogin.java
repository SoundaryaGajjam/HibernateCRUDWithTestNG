package com.scp.test.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scp.AppUtil.HibernateUtil;
import com.scp.Entities.UserInfo;
import com.scp.TestMain.TestMainClass;

import oracle.security.o3logon.O3LoginClientHelper;

public class UserLogin {
	
	static Session s1=null;
	static Transaction tr1=null;
	
	
	@Test(dataProvider="getData")
	public static void authenticate(String userName,String password,String expt) throws IOException{
		
		s1=HibernateUtil.getSessionFactory().openSession();
		tr1=s1.beginTransaction();
		
		String actual=TestMainClass.getDatafromDB(s1, tr1);
		//System.out.println("Actual -------"+actual);
		//System.out.println("Expt----------"+expt);
		Assert.assertEquals(expt, actual);
		
		
		
	}
	
	
	@DataProvider(name="getData")
	public static Object[][] getData() throws IOException{
		File file=new File("C:\\Users\\Shree\\Desktop\\User.xlsx");
		FileInputStream fis=new FileInputStream(file);
		Workbook wb=new XSSFWorkbook(fis);
		
		Sheet firstSheet=wb.getSheetAt(0);
		int cnt=0;
	
		Object[][] objArr=new Object[firstSheet.getPhysicalNumberOfRows()][3];
		Iterator<Row>itr=firstSheet.iterator();
		
		while(itr.hasNext()){
			
			for(int i=0;i<firstSheet.getPhysicalNumberOfRows();i++){
			Row nxtRow=itr.next();
			cnt++;
			
			Iterator<Cell> citr=nxtRow.cellIterator();
			while(citr.hasNext()){
				
				for(int j=0;j<3;j++){
				Cell cell=citr.next();
				if(cnt==1)
					continue;
				objArr[i][j]=cell.getStringCellValue();
				
				}
			
			}
		}
			
		     
		}
		
	
		return objArr;
		
}
	
	
}
