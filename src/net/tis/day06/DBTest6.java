package net.tis.day06;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

public class DBTest6 {
	Statement ST;
	Connection conn;
	Result rs;
	Scanner sc = new Scanner(System.in);
	DBTest4 d4 = new DBTest4();
	public void DB6() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			ST = conn.createStatement();
			System.out.println("�ٲٰ���� code ���� ���Ͻʽÿ�");
			String updatecode =sc.next();
			System.out.println("�ٲ� �ʵ带 ���Ͻʽÿ�");
			String updatefield =sc.next();
			System.out.println("�ٲ� �����͸� ���Ͻʽÿ�");
			String updatedata =sc.next();
			String sql="update test set "+updatefield +"='"+updatedata+ "' where code="+updatecode;
			
			int OK = ST.executeUpdate(sql);
			if(OK>0) {
				System.out.println("���� �Ϸ�!");
				d4.view(ST);
			}
		}catch(Exception ex) {}
	}
}
