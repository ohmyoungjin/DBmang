package net.tis.day06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest2 {
//  Connection CN ; //DB서버정보및 user/pwd기억, CN참조해서 명령어생성
//  Statement ST ;  //정적인명령어 ST=CN.createStatement(X);
//  ResultSet RS ;  //조회한결과기억 RS=ST.executeQuery(select~),RS.next()
//  String msg="insert" ;  쿼리문 기억하는 문자열 

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
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이브로드
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "system", "1234");

			// Scanner sc = new Scanner(System.in);
			// SQL> delete from test where code < 6600 ;
			// SQL> commit ;
			ST = conn.createStatement();

			// sql = "insert into student values(st_seq.nextval, ?,?,?,sysdate,0) ";
//			pstmt = CN.prepareStatement(sql);
//			// 4. ?(물음표)에 값 배정
//			pstmt.setString(1, name);		// 1번째 물음표에 name 배정
//			pstmt.setString(2, score);			// 2번째 물음표에 score 배정
//			pstmt.setString(3, phone);		// 3번째 물음표에 phone 배정
			// 4번째 물음표에 email 배정

			String sql = "select rownum,a.*from test a";
			rs = ST.executeQuery(sql);
			
			while (rs.next()) {// next는 rs가 있는 횟수만큼 들여보내준다
				String code = rs.getString(1);
				String name = rs.getString(2);
				String title = rs.getString(3);
				String wdate = rs.getString(4);
				String cnt = rs.getString(5);

				System.out.println(code + " " + name + " " + title + " " + wdate + " " + cnt);	
				a+=1;
				
			}System.out.println("레코드 갯수는"+d4.dbCount(ST)+"개 입니다");
			
		} catch (Exception ex) {
			System.out.println("에러: " + ex.toString());
		}
	}

	public static void main(String[] args) {

	}// main end
}// class END
