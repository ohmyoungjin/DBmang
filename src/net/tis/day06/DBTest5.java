package net.tis.day06;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest5 {
	public void DB5() {
		DBTest4 db4 =  new DBTest4();
		PreparedStatement pstmt;
		Scanner sc = new Scanner(System.in);
		Connection conn; // DB���������� user/pwd���, CN�����ؼ� ��ɾ����
		Statement ST; // �����θ�ɾ� ST=CN.createStatement(X);
		ResultSet rs; //rs�� sql�Է¹��� ������ �ִ�
		// msg= "insert into test values("+code+",'"+name+"','"+title+"',sysdate, 0)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̺�ε�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			ST = conn.createStatement();

			System.out.println("ã���� �̸��� �˻��ϼ���");
			String nick = sc.next();
			String sql = "select*from test where name like '%" + nick + "%'";
			rs = ST.executeQuery(sql);
			int OK = ST.executeUpdate(sql);
			if (OK > 0) {
				// ���强���� ��ü���
				
				rs = ST.executeQuery("select*from test where name like '%" + nick + "%'");
				while (rs.next() == true) {
					int a = rs.getInt("code");
					String b = rs.getString("name");
					String c = rs.getString("title");
					Date dt = rs.getDate("wdate");
					int hit = rs.getInt("cnt");
					System.out.println(a + "\t" + b + "\t" + c + "\t" + dt + "\t" + hit);
				}
				
			}else {System.out.println("ã�� ���� �����ϴ�");
			}
			db4.view(ST);
			System.out.println("======================");
		} catch (Exception ex) {
			System.out.println("����: " + ex.toString());
		}

	}

}
