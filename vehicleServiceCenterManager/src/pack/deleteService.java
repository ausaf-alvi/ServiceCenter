package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class deleteService {

	public deleteService(String sid) {
		// TODO Auto-generated constructor stub
		try {
			String st="DELETE FROM services WHERE ServiceId="+sid;
			Connection con=serviceCentreDBConnection.connect();
			PreparedStatement ps=con.prepareStatement(st);
			int i=ps.executeUpdate();
			if(i==1) {
				JOptionPane.showConfirmDialog(null, new String("Deletion of Data of "+sid+"vis Successfull"));
				
			}
			else {
				JOptionPane.showConfirmDialog(null, new String("Deletion Unsuccessfull"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
