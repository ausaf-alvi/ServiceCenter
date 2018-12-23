package pack;
import java.sql.*;
public class serviceCentreDBConnection{
	private static String id="root";
	private static String pass="1234";
	private static String dBName="service_center";
	private static Connection c;
	static Connection connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dBName,id,pass);
			System.out.println("Connected :"+c.toString());
		}
		catch(ClassNotFoundException se){
			se.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return c;
	}
	public static void main(String args[]){
		try {
			connect();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setId(String id1) {
		id = id1;
	}
	public static void setPass(String pass1) {
		pass = pass1;
	}
	public static void setDbName(String dBName1) {
		dBName = dBName1;
	}
}