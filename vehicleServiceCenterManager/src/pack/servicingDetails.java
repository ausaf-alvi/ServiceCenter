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

public class servicingDetails extends JDialog implements ActionListener{
	private JTextField rg;
	private JTextField sid;
	private JTextField sc;
	private JTextField pd;
	private JTextField sd;
	private JTextField nd;
	private JButton submit;
	private JComboBox stype;

	@SuppressWarnings("unchecked")
	public servicingDetails(String regno) {
		setModal(true);
		//setMinimumSize(new Dimension(300, 300));
		setTitle("Service Details");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// TODO Auto-generated constructor stub
		getContentPane().setLayout(null);
		

		
		JLabel lblNewLabel = new JLabel("Reg. No.");
		lblNewLabel.setBounds(26, 11, 127, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Service ID");
		lblNewLabel_1.setBounds(26, 59, 127, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Service Type");
		lblNewLabel_3.setBounds(26, 101, 127, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Service Cost");
		lblNewLabel_4.setBounds(26, 140, 127, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pickup Date");
		lblNewLabel_5.setBounds(26, 171, 127, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Submit Date");
		lblNewLabel_6.setBounds(26, 204, 127, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Next Date");
		lblNewLabel_7.setBounds(26, 236, 127, 14);
		getContentPane().add(lblNewLabel_7);
		
		rg = new JTextField(regno);
		rg.setEditable(false);
		rg.setBounds(176, 13, 86, 20);
		getContentPane().add(rg);
		rg.setColumns(10);
		
		sid = new JTextField();
		sid.setBounds(176, 56, 86, 20);
		getContentPane().add(sid);
		sid.setColumns(10);
		sid.setText(generateServiceId(regno));
		sid.setEditable(false);
		
		sc = new JTextField();
		sc.setText("");
		sc.setBounds(176, 137, 86, 20);
		getContentPane().add(sc);
		sc.setColumns(10);
		
		pd = new JTextField();
		pd.setText("");
		pd.setBounds(176, 168, 86, 19);
		getContentPane().add(pd);
		pd.setColumns(10);
		
		sd = new JTextField();
		sd.setText("");
		sd.setBounds(176, 198, 86, 20);
		getContentPane().add(sd);
		sd.setColumns(10);
		
		nd = new JTextField();
		nd.setBounds(176, 230, 86, 20);
		getContentPane().add(nd);
		nd.setColumns(10);
		
		
		stype = new JComboBox();
//		stype.setMaximumRowCount(1);
		stype.setModel(new DefaultComboBoxModel(new String[] {"Free", "Paid",}));
		stype.setBounds(176, 98, 86, 20);
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
		
		
		setLocation(300, 300);
		setSize(384,390);
		setModal(true);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		servicingDetails obb=new servicingDetails("UP32FQ2281");
		//obb.setVisible(true);
		//obb.setModal(true);
		//obb.setSize(450, 450);
		//obb.generateServiceId("UP32FQ2281");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj =arg0.getSource();
		if(obj==submit)
		{
			try {
				int rs;
				if ( DateValidation.isValid(pd) && DateValidation.isValid(sd) && DateValidation.isValid(nd)) {
					Connection con = serviceCentreDBConnection.connect();
					String st = "INSERT INTO services (ServiceId,regNo,serviceType,"
							+ "serviceStatus,serviceCost,pickupDate,submitDate,nextDate) " + "values(?,?,?,?,?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(st);
					ps.setString(1, sid.getText().toUpperCase());
					ps.setString(2, rg.getText().toUpperCase());
					ps.setString(3, stype.getSelectedItem().toString().toUpperCase());
					ps.setString(4, "In Process");
					ps.setString(5, sc.getText().toUpperCase());
					ps.setString(6, pd.getText().toUpperCase());
					ps.setString(7, sd.getText().toUpperCase());
					ps.setString(8, nd.getText().toUpperCase());
					System.out.println(ps.toString());
					rs = ps.executeUpdate();
					String st2 = "UPDATE vehicles set noOfServices=?,lastServiceId=?  WHERE regno " + "like \""
							+ rg.getText().toUpperCase() + "\" ";
					//+"(noOfServices,lastServiceId) "
					//+"VALUES(?,?)";
					PreparedStatement ps2 = con.prepareStatement(st2);
					int noOfServices = Integer.parseInt(sid.getText().substring(4));
					noOfServices++;
					ps2.setString(1, String.valueOf(noOfServices));
					ps2.setString(2, sid.getText());
					int rs2 = ps2.executeUpdate();
				
				if(rs==1 ) {//&& rs2==1) {
					JOptionPane.showMessageDialog(null, "Service Details Added");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Some Error Occured. Try Again Later");
					dispose();
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Enter Date in YYYY-MM-DD Format");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		
	}
	
	private String generateServiceId(String reg) {
		String id="\\";
		String idPostfix="0";
		String idPrefix=reg.substring(reg.length()-4);//extracts last four digits of registration no
		int r=0;
		System.out.println(idPrefix+"--"+idPostfix);
		try {
			Connection con2=serviceCentreDBConnection.connect();
			String st="SELECT COUNT(*) FROM services WHERE regNo like \""+reg+"\"";
			System.out.println(st);
			PreparedStatement ps=con2.prepareStatement(st);
			//ps.setString(1, reg);
			ResultSet rs=ps.executeQuery();
			//int result=Integer.parseInt(r2);
			while(rs.next()) {
				idPostfix=rs.getString("Count(*)");
				r=(Integer.parseInt(idPostfix))+1;
				idPostfix=String.valueOf(r);
				}
			System.out.println(r);

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		id=idPrefix.concat(idPostfix);
		return id;
	}
}
