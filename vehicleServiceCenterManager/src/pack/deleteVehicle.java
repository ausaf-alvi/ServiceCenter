package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class deleteVehicle {

	public deleteVehicle(String reg) {
		// TODO Auto-generated constructor stub
		try {
			String st="DELETE FROM vehicles WHERE regno="+reg;
			Connection con=serviceCentreDBConnection.connect();
			PreparedStatement ps=con.prepareStatement(st);
			int i=ps.executeUpdate();
			if(i==1) {
				JOptionPane.showConfirmDialog(null, new String("Deletion of Data of "+reg+"vis Successfull"));
				
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
