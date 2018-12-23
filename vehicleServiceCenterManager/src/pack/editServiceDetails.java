package pack;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class editServiceDetails extends JDialog implements ActionListener{
	private JTextField rg;
	private JTextField sid;
	private JTextField sc;
	private JTextField pd;
	private JTextField sd;
	private JTextField nd;
	private JButton submit;
	@SuppressWarnings("rawtypes")
	private JComboBox stype;
	private boolean alreadyExists=false;
	

	@SuppressWarnings("unchecked")
	public editServiceDetails(String sID) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		checkServiceID(sID);
		if (alreadyExists) {
			//setMinimumSize(new Dimension(300, 300));
			setTitle("Edit Service Details");
			
			// TODO Auto-generated constructor stub
			getContentPane().setLayout(null);

			JLabel lblNewLabel = new JLabel("Reg. No.");
			lblNewLabel.setBounds(26, 11, 129, 25);
			getContentPane().add(lblNewLabel);
			JLabel lblNewLabel_1 = new JLabel("Service ID");
			lblNewLabel_1.setBounds(26, 59, 70, 14);
			getContentPane().add(lblNewLabel_1);
			JLabel lblNewLabel_3 = new JLabel("Service Type");
			lblNewLabel_3.setBounds(26, 101, 70, 14);
			getContentPane().add(lblNewLabel_3);
			JLabel lblNewLabel_4 = new JLabel("Service Cost");
			lblNewLabel_4.setBounds(26, 140, 70, 14);
			getContentPane().add(lblNewLabel_4);
			JLabel lblNewLabel_5 = new JLabel("Pickup Date");
			lblNewLabel_5.setBounds(26, 171, 70, 14);
			getContentPane().add(lblNewLabel_5);
			JLabel lblNewLabel_6 = new JLabel("Submit Date");
			lblNewLabel_6.setBounds(26, 204, 70, 14);
			getContentPane().add(lblNewLabel_6);
			JLabel lblNewLabel_7 = new JLabel("Next Date");
			lblNewLabel_7.setBounds(26, 236, 70, 14);
			getContentPane().add(lblNewLabel_7);
			rg = new JTextField();
			rg.setEditable(false);
			rg.setBounds(149, 13, 86, 20);
			getContentPane().add(rg);
			rg.setColumns(10);
			sid = new JTextField();
			sid.setBounds(149, 56, 86, 20);
			getContentPane().add(sid);
			sid.setColumns(10);
			sid.setText(sID);
			sid.setEditable(false);
			sc = new JTextField();
			sc.setText("");
			sc.setBounds(149, 137, 86, 20);
			getContentPane().add(sc);
			sc.setColumns(10);
			pd = new JTextField();
			pd.setText("");
			pd.setBounds(149, 168, 86, 19);
			getContentPane().add(pd);
			pd.setColumns(10);
			sd = new JTextField();
			sd.setText("");
			sd.setBounds(149, 198, 86, 20);
			getContentPane().add(sd);
			sd.setColumns(10);
			nd = new JTextField();
			nd.setBounds(149, 230, 86, 20);
			getContentPane().add(nd);
			nd.setColumns(10);
			stype = new JComboBox(new String[] { "Free", "Paid",});
//			stype.setMaximumRowCount(1);
			//stype.setModel(new DefaultComboBoxModel(new String[] { "Free", "Paid",}));
			stype.setBounds(149, 98, 86, 20);
			getContentPane().add(stype);
			submit = new JButton("Submit");
			submit.addActionListener(this);
			submit.setBounds(149, 279, 86, 25);
			getContentPane().add(submit);
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancel.setBounds(26, 280, 89, 23);
			getContentPane().add(btnCancel);
			autoFillForm(sID);
			setSize(384, 390);
			setLocationRelativeTo(null);
			setModal(true);
			setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Vehicle Not Found");
			dispose();
		}
		
	}

	private void autoFillForm(String sID) {
		// TODO Auto-generated method stub
		try {
			String st="SELECT * FROM services WHERE ServiceId="+sID;
			Connection con=serviceCentreDBConnection.connect();
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String rgno=rs.getString("regNo");
				String sType=rs.getString("serviceType");
				String sCost=rs.getString("serviceCost");
				String pDate=rs.getString("pickupDate");
				String sDate=rs.getString("submitDate");
				String nDate=rs.getString("nextDate");
				rg.setText(rgno);
				sc.setText(sCost);
				pd.setText(pDate);
				sd.setText(sDate);
				nd.setText(nDate);
				if(sType.equalsIgnoreCase("Free"))
					{stype.setSelectedItem("Free");;}
				else  {stype.setSelectedItem("Paid");;}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		editServiceDetails obb=new editServiceDetails("1234");
		obb.setVisible(true);
		//obb.setModal(true);
		//obb.setSize(450, 450);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj =arg0.getSource();
		if(obj==submit)
		{
			if(DateValidation.isValid(pd) && DateValidation.isValid(sd) && DateValidation.isValid(nd)) {
				try {
					Connection con=serviceCentreDBConnection.connect();
					String st = "UPDATE services set serviceType=?,"
							+"serviceStatus=?,serviceCost=?,pickupDate=?,submitDate=?,nextDate=? "
							+" WHERE ServiceId=?";						
					PreparedStatement ps = con.prepareStatement(st);
					ps.setString(7, sid.getText().toUpperCase());
					//ps.setString(1, rg.getText().toUpperCase());
					ps.setString(1, stype.getSelectedItem().toString().toUpperCase());
					ps.setString(2, "In Process");
					ps.setString(3, sc.getText().toUpperCase());
					ps.setString(4, pd.getText().toUpperCase());
					ps.setString(5, sd.getText().toUpperCase());
					ps.setString(6, nd.getText().toUpperCase());
					System.out.println(ps.toString());
					int rs=ps.executeUpdate();
					if(rs==1 ) {//&& rs2==1) {
						JOptionPane.showMessageDialog(null, "Service Details Editted");
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Some Error Occured. Try Again Later");
						dispose();
					}

					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Enter Date in YYYY-MM-DD Format");
			}
			
			
		}
		// TODO Auto-generated method stub
		
	}
	

	
	public void checkServiceID(String sID) {

		if(sID!=null) {
			System.out.println("This is string reg"+sID+"-end");
			String st="SELECT * FROM services WHERE ServiceId like ?";
			Connection con=serviceCentreDBConnection.connect();
			try{
				PreparedStatement ps=con.prepareStatement(st);
				ps.setString(1, sID);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					/*This loop will only work when th give regno exists
					System.out.println("Name: "+rs.getString("cname"));
					if((rs.getString("cname"))!=null)System.out.println("Data exists");
					 */
					//set flag alreadyExists to true
					alreadyExists=true;
				}
			}
			catch(SQLException se) {
				System.out.println("Error_Occured");
				se.printStackTrace();
			}
		}
	}
}
