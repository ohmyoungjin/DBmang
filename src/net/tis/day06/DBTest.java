package net.tis.day06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest {
//  Connection CN ; //DB���������� user/pwd���, CN�����ؼ� ��ɾ����
//  Statement ST ;  //�����θ�ɾ� ST=CN.createStatement(X);
//  ResultSet RS ;  //��ȸ�Ѱ����� RS=ST.executeQuery(select~),RS.next()
//  String msg="insert" ;  ������ ����ϴ� ���ڿ� 

	public static void main(String[] args) {
		PreparedStatement pstmt;
		DBTest2 d2 = new DBTest2();
		DBTest3 d3 = new DBTest3();
		DBTest4 d4 = new DBTest4();
		DBTest5 d5 = new DBTest5();
		DBTest6 d6 = new DBTest6();
		Scanner sc = new Scanner(System.in);
		Connection CN; // DB���������� user/pwd���, CN�����ؼ� ��ɾ����
		Statement ST; // �����θ�ɾ� ST=CN.createStatement(X);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̺�ε�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "system", "1234");
			System.out.println("����Ŭ���Ἲ��!!");
			
			while (true) {
				System.out.println("1.��ȸ 2.�߰� 3.���� 4.�̸��˻� 5.������Ʈ 9.������");
				int num = sc.nextInt();
				if (num == 1) {
					d2.DB2();
				}
				if (num == 2) {
					d3.DB3();
				}
				if (num == 3) {
					d4.DB4();
				}
				if (num == 4) {
					d5.DB5();
				}
				if(num == 9) {
					System.out.println("DB�� �����մϴ�.");
					break;
				}
				if(num == 5) {
					d6.DB6();
				}
				
			} // while end
				// Scanner sc = new Scanner(System.in);
				// SQL> delete from test where code > 6600 ;
				// SQL> commit ;

		} catch (Exception ex) {
			System.out.println("����: " + ex.toString());
		}
	}// main end
}// class END
