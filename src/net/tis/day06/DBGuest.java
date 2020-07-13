package net.tis.day06;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class DBGuest {
	Connection CN = null;// DB���������� user/pwd���, CN�����ؼ� ��ɾ����
	Statement ST = null;// �����θ�ɾ� ST=CN.createStatement(X);
	PreparedStatement PST = null; // �����θ�ɾ� PST=CN.prepareStatememt(msg)
	CallableStatement CST = null; // storedprocedure����
	ResultSet RS = null;// RS=ST.executeQuery("select~") ; ��ȸ����� RS���
	String msg = "";
	int Gtotal = 0; // ��ü���ڵ尹��
	Scanner sc = new Scanner(System.in);

	public DBGuest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̺�ε�
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "1234");
			System.out.println("����Ŭ���Ἲ��success ������");
			ST = CN.createStatement();
		} catch (Exception e) {
		}
	}// ������

	public static void main(String[] args) {
		DBGuest gg = new DBGuest();

		Scanner scin = new Scanner(System.in);
		while (true) {
			System.out.print("\n1��ü��� 2������ 3�̸���ȸ �� 9����>>> ");
			int sel = scin.nextInt();
			if (sel == 1) {
				gg.guestSelectAll();
			} else if (sel == 2) {
				gg.guestPage();
			} else if (sel == 3) {
				gg.guestSearchName();
			} else if (sel == 9) {
				gg.myexit();
				break;
			}
		}
		scin.close();
	}// main end

	public int dbCount() {// ���ڵ尹�� Statement
		int mytotal = 0;
		try {
			msg = "select count(*) as cnt from guest";
			RS = ST.executeQuery(msg);
			if (RS.next() == true) {
				mytotal = RS.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mytotal;
	}// end--------------------

	public void guestSelectAll() {// ��ü���
		try {
			System.out.println("=============================================");
			Gtotal = dbCount();
			System.out.println("================================�ѷ��ڵ尹��:" + Gtotal + "��");
			msg = "select rownum rn,g.*from guest g";
			RS = ST.executeQuery(msg);
			while (RS.next() == true) {
				view(msg);
			}
		} catch (Exception e) {
			System.out.println("��ü��ȸ����");
		}
	}// end--------------------

	public void guestPage() {
		try {
			System.out.println("ã����� �������� �Է��Ͻÿ�>>>");
			int a = sc.nextInt();
			sc.nextLine();
			msg = "select*from(select rownum rn,a.*from guest a)where rn between " + "" + ((a - 1) * 10 + 1) + " and "
					+ a * 10;
			view(msg);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}// end--------------------

	public void guestSearchName() {// name�ʵ���ȸ
		System.out.print("\n��ȸ�̸��Է�>>>");
		String data = sc.nextLine();
		try {
			msg = "select count(*) cnt from(select*from guest where name like '%" + data + "%')cnt";
			RS = ST.executeQuery(msg);
			if (RS.next()) {
				if (RS.getInt("cnt") > 0) {
					System.out.println("��ȸ�� ���� " + RS.getInt("cnt") + "�� �Դϴ�");
					System.out.println("��ȸ�ϰ� ���� Page�� �����ϼ���");
					int page = sc.nextInt();
					sc.nextLine();
					msg = "select*from(select rownum rn,g.* from guest g where name like '%" + data
							+ "%')where rn between " + "" + ((page - 1) * 10 + 1) + " and " + page * 10;
					RS = ST.executeQuery(msg);
					view(msg);

				} else {
					System.out.println("��ȸ�� ���� �����ϴ�");
				}
			}
			// msg = "select * from guest where name like '%" + data + "%' ";
		} catch (Exception e) {
			System.out.println("�̸���ȸ����");
		}
	}// end--------------------

	public void view(String msg) throws SQLException {
		System.out.println("===========================");
		RS = ST.executeQuery(msg);
		while (RS.next()) {
			String rn = RS.getString(1);
			String a1 = RS.getString(2);
			String b = RS.getString(3);
			String c = RS.getString(4);
			String d = RS.getString(5);
			String e = RS.getString(6);
			String f = RS.getString(7);
			String h = RS.getString(8);
			System.out.println(rn + "\t" + a1 + "\t" + b + "\t" + c + "\t" + d + "\t" + e + "\t" + f + "\t" + h);
		}
		System.out.println("===========================");
	}// view end;
	public void myexit() {
		System.out.println("���α׷��� �����մϴ�");
		System.exit(1);
	}// end--------------------
}///////////////////////////////////////////// class END
