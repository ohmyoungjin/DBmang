package net.tis.day06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest2 {
//  Connection CN ; //DB���������� user/pwd���, CN�����ؼ� ��ɾ����
//  Statement ST ;  //�����θ�ɾ� ST=CN.createStatement(X);
//  ResultSet RS ;  //��ȸ�Ѱ����� RS=ST.executeQuery(select~),RS.next()
//  String msg="insert" ;  ������ ����ϴ� ���ڿ� 

	public void DB2() {
//		String sql=null;
//		Connection con = null;
//		 PreparedStatement pstmt = null;
		Connection conn;
		Statement ST;
		ResultSet rs;
		DBTest4 d4 = new DBTest4(); 
		int a =0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̺�ε�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "system", "1234");

			// Scanner sc = new Scanner(System.in);
			// SQL> delete from test where code < 6600 ;
			// SQL> commit ;
			ST = conn.createStatement();

			// sql = "insert into student values(st_seq.nextval, ?,?,?,sysdate,0) ";
//			pstmt = CN.prepareStatement(sql);
//			// 4. ?(����ǥ)�� �� ����
//			pstmt.setString(1, name);		// 1��° ����ǥ�� name ����
//			pstmt.setString(2, score);			// 2��° ����ǥ�� score ����
//			pstmt.setString(3, phone);		// 3��° ����ǥ�� phone ����
			// 4��° ����ǥ�� email ����

			String sql = "select rownum,a.*from test a";
			rs = ST.executeQuery(sql);
			
			while (rs.next()) {// next�� rs�� �ִ� Ƚ����ŭ �鿩�����ش�
				String code = rs.getString(1);
				String name = rs.getString(2);
				String title = rs.getString(3);
				String wdate = rs.getString(4);
				String cnt = rs.getString(5);

				System.out.println(code + " " + name + " " + title + " " + wdate + " " + cnt);	
				a+=1;
				
			}System.out.println("���ڵ� ������"+d4.dbCount(ST)+"�� �Դϴ�");
			
		} catch (Exception ex) {
			System.out.println("����: " + ex.toString());
		}
	}

	public static void main(String[] args) {

	}// main end
}// class END
