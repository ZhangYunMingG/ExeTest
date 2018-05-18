package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlConnectTest {
	public static void main(String[] args) throws Exception {
		
		 String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
	      String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=pointloan-nj";// ����Դ
	      try {
	          Class.forName(JDriver);// �������ݿ����棬���ظ����ַ���������
	      } catch (ClassNotFoundException e) {
	          // e.printStackTrace();
	          System.out.println("�������ݿ�����ʧ��");
	          System.exit(0);
	      }
	      System.out.println("���ݿ������ɹ�");
	      try {


	          String user = "sa";
	          String password = "sa";
	          Connection con = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
	          System.out.println("�������ݿ�ɹ�");
	          Statement stmt = con.createStatement();// ����SQL�������
//	          // ������
//	          System.out.println("��ʼ������");
//	          String query = "create table TABLE1(ID NCHAR(2),NAME NCHAR(10))";// ������SQL���
//	          stmt.executeUpdate(query);// ִ��SQL�������
//	          System.out.println("�����ɹ�");
//	          // ��������
//	          System.out.println("��ʼ��������");
//	          String a1 = "INSERT INTO TABLE1 VALUES('1','���')";// ��������SQL���
//	          String a2 = "INSERT INTO TABLE1 VALUES('2','ΰ��')";
//	          String a3 = "INSERT INTO TABLE1 VALUES('3','�Ÿ�')";
//	          stmt.executeUpdate(a1);// ִ��SQL�������
//	          stmt.executeUpdate(a2);
//	          stmt.executeUpdate(a3);
//	          System.out.println("�������ݳɹ�");
//	          // ��ȡ����
//	          System.out.println("��ʼ��ȡ����");


	          ResultSet rs = stmt.executeQuery("SELECT * FROM organ");// ����SQL����ѯ�����(����)
	          // ѭ�����ÿһ����¼
	          while (rs.next()) {
	              // ���ÿ���ֶ�
	              System.out.println(rs.getString("organName") + "\t" + rs.getString("organCode"));
	          }
	          System.out.println("��ȡ���");
	          // �ر�����
	          stmt.close();// �ر������������
	          con.close();// �ر����ݿ�����
	      } catch (Exception e) {
	          e.printStackTrace();
	          System.out.println("���ݿ����Ӵ���");
	          System.exit(0);
	      }
	  }
	}