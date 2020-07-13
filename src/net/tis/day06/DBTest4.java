package net.tis.day06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest4 {
	public void DB4() {
		DBTest2 db2 = new DBTest2();
		PreparedStatement pstmt;
		Scanner sc = new Scanner(System.in);
		Connection conn; // DB���������� user/pwd���, CN�����ؼ� ��ɾ����
		Statement ST; // �����θ�ɾ� ST=CN.createStatement(X);
		ResultSet rs;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̺�ε�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			ST = conn.createStatement();
		
//			System.out.print("������ �ʵ� �Է�: ");
//			String name = sc.next();
			System.out.print("������ ��ȣ �Է�: ");
			String values = sc.next();
			// sql�� �ۼ�
			String sql = "select * from test where code = ?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, name );
			pstmt.setString(1, values );
			
			rs = pstmt.executeQuery();
			
			// �ش� �л��� ������ ���ڵ� ����
			if(rs.next()){
				sql = "delete from test where code = ?";
				pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, name);
				pstmt.setString(1, values);
				int result = pstmt.executeUpdate();
				
				if(result >= 1) {
					System.out.println("���ڵ� ���� ����");
					System.out.println("==============================");
				}else
					System.out.println("���ڵ� ���� ����");
			}else{
				System.out.println("�ش� �ʵ尡 �����ϴ�.");
				System.out.println("==============================");
			} view(ST);
			System.out.println("==============================");
		} catch (Exception ex) {
			System.out.println("����: " + ex.toString());
		}
	}// method end
	public static void view(Statement ST) throws SQLException{
		ResultSet rs = ST.executeQuery("select rownum,a.*from test a");
		System.out.println("===========================");
		while (rs.next()) {// next�� rs�� �ִ� Ƚ����ŭ �鿩�����ش�
			
			
			String code = rs.getString(1);
			String name = rs.getString(2);
			String title = rs.getString(3);
			String wdate = rs.getString(4);
			String cnt = rs.getString(5);
	
			System.out.println(code + " " + name + " " + title + " " + wdate + " " + cnt);
		}System.out.println("===========================");
		System.out.println("���ڵ��� �� ������ "+dbCount(ST)+"�� �Դϴ�");
	}
		public static int dbCount(Statement ST) throws SQLException{ // ���ڵ� ���� Statement
		      int mycount=0;
		      ResultSet rs = ST.executeQuery("select*from test");
		      try {
		       String msg="select count(*) cnt from test";
		         rs = ST.executeQuery(msg);
		         while(rs.next()) {
		            mycount = rs.getInt("cnt");
		         }
		         
		      }catch(Exception e) {System.out.println(e);}
		      return mycount;
		   }
	
}// class end