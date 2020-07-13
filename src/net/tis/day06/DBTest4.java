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
		Connection conn; // DB서버정보및 user/pwd기억, CN참조해서 명령어생성
		Statement ST; // 정적인명령어 ST=CN.createStatement(X);
		ResultSet rs;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이브로드
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			ST = conn.createStatement();
		
//			System.out.print("삭제할 필드 입력: ");
//			String name = sc.next();
			System.out.print("삭제할 번호 입력: ");
			String values = sc.next();
			// sql문 작성
			String sql = "select * from test where code = ?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, name );
			pstmt.setString(1, values );
			
			rs = pstmt.executeQuery();
			
			// 해당 학생이 있으면 레코드 삭제
			if(rs.next()){
				sql = "delete from test where code = ?";
				pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, name);
				pstmt.setString(1, values);
				int result = pstmt.executeUpdate();
				
				if(result >= 1) {
					System.out.println("레코드 삭제 성공");
					System.out.println("==============================");
				}else
					System.out.println("레코드 삭제 실패");
			}else{
				System.out.println("해당 필드가 없습니다.");
				System.out.println("==============================");
			} view(ST);
			System.out.println("==============================");
		} catch (Exception ex) {
			System.out.println("에러: " + ex.toString());
		}
	}// method end
	public static void view(Statement ST) throws SQLException{
		ResultSet rs = ST.executeQuery("select rownum,a.*from test a");
		System.out.println("===========================");
		while (rs.next()) {// next는 rs가 있는 횟수만큼 들여보내준다
			
			
			String code = rs.getString(1);
			String name = rs.getString(2);
			String title = rs.getString(3);
			String wdate = rs.getString(4);
			String cnt = rs.getString(5);
	
			System.out.println(code + " " + name + " " + title + " " + wdate + " " + cnt);
		}System.out.println("===========================");
		System.out.println("레코드의 총 갯수는 "+dbCount(ST)+"개 입니다");
	}
		public static int dbCount(Statement ST) throws SQLException{ // 레코드 갯수 Statement
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