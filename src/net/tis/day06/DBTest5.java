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
		Connection conn; // DB서버정보및 user/pwd기억, CN참조해서 명령어생성
		Statement ST; // 정적인명령어 ST=CN.createStatement(X);
		ResultSet rs; //rs가 sql입력문을 가지고 있다
		// msg= "insert into test values("+code+",'"+name+"','"+title+"',sysdate, 0)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이브로드
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			ST = conn.createStatement();

			System.out.println("찾으실 이름을 검색하세요");
			String nick = sc.next();
			String sql = "select*from test where name like '%" + nick + "%'";
			rs = ST.executeQuery(sql);
			int OK = ST.executeUpdate(sql);
			if (OK > 0) {
				// 저장성공후 전체출력
				
				rs = ST.executeQuery("select*from test where name like '%" + nick + "%'");
				while (rs.next() == true) {
					int a = rs.getInt("code");
					String b = rs.getString("name");
					String c = rs.getString("title");
					Date dt = rs.getDate("wdate");
					int hit = rs.getInt("cnt");
					System.out.println(a + "\t" + b + "\t" + c + "\t" + dt + "\t" + hit);
				}
				
			}else {System.out.println("찾는 값이 없습니다");
			}
			db4.view(ST);
			System.out.println("======================");
		} catch (Exception ex) {
			System.out.println("에러: " + ex.toString());
		}

	}

}
