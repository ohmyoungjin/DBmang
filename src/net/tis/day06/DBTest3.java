package net.tis.day06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest3 {
//  Connection CN ; //DB���������� user/pwd���, CN�����ؼ� ��ɾ����
//  Statement ST ;  //�����θ�ɾ� ST=CN.createStatement(X);
//  ResultSet RS ;  //��ȸ�Ѱ����� RS=ST.executeQuery(select~),RS.next()
//  String msg="insert" ;  ������ ����ϴ� ���ڿ� 

	public void DB3() {
		DBTest4 db4 = new DBTest4();
		PreparedStatement pstmt;
		Scanner sc = new Scanner(System.in);
		Connection conn; // DB���������� user/pwd���, CN�����ؼ� ��ɾ����
		Statement ST; // �����θ�ɾ� ST=CN.createStatement(X);
		ResultSet rs;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̺�ε�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			// Scanner sc = new Scanner(System.in);
			// SQL> delete from test where code > 6600 ;
			// SQL> commit ;
			ST = conn.createStatement();
			System.out.println("�ڵ带 �Է��Ͻÿ�");
			int code = sc.nextInt();
//			String sql = "select * from test where code ="+code;
//			 rs =ST.executeQuery(sql);
			 String sql="select count(*) cnt from test where code =" +code ;
			 rs=ST.executeQuery(sql);
			 if(rs.next()) {
				 if(rs.getInt("cnt")>=1) {
					 System.out.println("�ߺ��� ���Դϴ�");
					 return;
				 }			 
			 }	
//			if(rs1==1) {
//				System.out.println("�̹� ���� �����մϴ�");
//				return;
//			}
			sql="insert into test values(?,?,?,sysdate,0)";
			System.out.println("�̸��� �Է��Ͻÿ�");
			String name = sc.nextLine();
			System.out.println("������ �Է��Ͻÿ�");
			String title = sc.nextLine();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, name);
			pstmt.setString(3, title);
				db4.view(ST);
//			
		
			
		} catch (Exception ex) {
			System.out.println((ex));
		}
	}
	
	public static void main(String[] args) {
	}// main end
}// class END
