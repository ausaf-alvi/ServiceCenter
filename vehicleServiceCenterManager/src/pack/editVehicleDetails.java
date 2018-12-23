package pack;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class editVehicleDetails extends JDialog implements ActionListener,FocusListener {

	private JTextField tpurchasedate;
	private JComboBox ttype;
	private JTextField tmodel;
	private JTextField tbrand;
	private JTextField tmobile;
	private JTextField tname;
	private JTextField regNo;
	private JButton Cancel;
	private JButton Submit;
	private boolean alreadyExists=false;
	private JTextField tentrydate;
	
	public editVehicleDetails(String reg) {
		// TODO Auto-generated constructor stub
		checkRegistrationNo(reg);
		if(!alreadyExists) {
			JOptionPane.showMessageDialog(null, "Vehicle Not Found");
			dispose();
			
		}
		else {
			setTitle("Enter Vehicle Details");
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			getContentPane().setLayout(null);

			this.setSize(385,462);
			this.setLocationRelativeTo(null);

			
			JLabel lblNewLabel = new JLabel("Reg. No:");
			lblNewLabel.setBounds(62, 31, 103, 14);
			getContentPane().add(lblNewLabel);
			
			JLabel lblType = new JLabel("Type:");
			lblType.setBounds(62, 70, 103, 14);
			getContentPane().add(lblType);
			
			JLabel lblOwnerName = new JLabel("Customer Name:");
			lblOwnerName.setBounds(62, 109, 103, 14);
			getContentPane().add(lblOwnerName);
			
			JLabel lblMobile = new JLabel("Mobile:");
			lblMobile.setBounds(62, 148, 103, 14);
			getContentPane().add(lblMobile);
			
			JLabel lblBrand = new JLabel("Brand:");
			lblBrand.setBounds(62, 187, 103, 14);
			getContentPane().add(lblBrand);
			
			JLabel lblModel = new JLabel("Model:");
			lblModel.setBounds(62, 226, 103, 14);
			getContentPane().add(lblModel);
			
			JLabel lblPurchaseDate = new JLabel("Purchase Date:");
			lblPurchaseDate.setBounds(62, 265, 103, 14);
			getContentPane().add(lblPurchaseDate);
			
			ttype = new JComboBox();
			ttype.setModel(new DefaultComboBoxModel(new String[] {"Geared 2-Wheeler", "Gearless 2-Wheeler", "4-Wheeler"}));
			ttype.setBounds(196, 56, 137, 28);
			getContentPane().add(ttype);
			
			tpurchasedate = new JTextField();
			tpurchasedate.setBounds(196, 251, 137, 28);
			getContentPane().add(tpurchasedate);
			tpurchasedate.setColumns(10);
			
			Cancel = new JButton("Cancel");
			Cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			Cancel.setMnemonic('c');
			Cancel.setBackground(new Color(220, 20, 60));
			
			Cancel.setBounds(76, 367, 89, 23);
			getContentPane().add(Cancel);
			
			Submit = new JButton("Submit");
			//Submit.setEnabled(false);
			Submit.setMnemonic('s');
			Submit.addActionListener(this);
			Submit.setForeground(new Color(0, 0, 0));
			Submit.setBackground(new Color(50, 205, 50));
			Submit.setBounds(214, 367, 89, 23);
			getContentPane().add(Submit);

			
			tmodel = new JTextField();
			tmodel.setColumns(10);
			tmodel.setBounds(196, 212, 137, 28);
			getContentPane().add(tmodel);
			
			tbrand = new JTextField();
			tbrand.setColumns(10);
			tbrand.setBounds(196, 173, 137, 28);
			getContentPane().add(tbrand);
			
			tmobile = new JTextField();
			tmobile.setColumns(10);
			tmobile.setBounds(196, 134, 137, 28);
			getContentPane().add(tmobile);
			
			tname = new JTextField();
			tname.setColumns(10);
			tname.setBounds(196, 95, 137, 28);
			getContentPane().add(tname);
			
			regNo = new JTextField();
			regNo.addFocusListener(this);
			regNo.setColumns(10);
			regNo.setBounds(196, 17, 137, 28);
			getContentPane().add(regNo);
			regNo.setText(reg);
			regNo.setEditable(false);
			
			tentrydate = new JTextField();
			tentrydate.setColumns(10);
			tentrydate.setBounds(196, 290, 137, 28);
			getContentPane().add(tentrydate);
			//tentrydate.setText(todayDate);
			//tentrydate.setEditable(false);
			
			JLabel lblEntryDate = new JLabel("First Entry Date:");
			lblEntryDate.setBounds(62, 304, 103, 14);
			getContentPane().add(lblEntryDate);
			setModal(true);
			getContentPane().setVisible(true);
			this.setVisible(true);
			autoFillForm(reg);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new editVehicleDetails("JH34PQ9901");

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object src=arg0.getSource();
		System.out.println(src);
		if(src==Submit) {
				if(regNo.getText()=="")System.out.println("Value is null");
				if(DateValidation.isValid(tentrydate)&&DateValidation.isValid(tpurchasedate)) {
					try {
						Connection con=serviceCentreDBConnection.connect();
						String st="update vehicles set type=?,cname=?,cmobile=?,brand=?,"
								+ "model=?,purchaseDate=?,firstEntryDate=? "
								+" where regno=?";
						PreparedStatement ps=con.prepareStatement(st);
						ps.setString(8, regNo.getText().toUpperCase());
						ps.setString(1, ttype.getSelectedItem().toString().toUpperCase());
						ps.setString(2, tname.getText().toUpperCase());
						ps.setString(3, tmobile.getText().toUpperCase());
						ps.setString(4, tbrand.getText().toUpperCase());
						ps.setString(5, tmodel.getText().toUpperCase());
						ps.setString(6, tpurchasedate.getText().toUpperCase());
						ps.setString(7, tentrydate.getText().toUpperCase());
						System.out.println("\n\nValue of ps:"+ps.toString()+"\n");
						int rs=ps.executeUpdate();
						//System.out.println(rs);
						//if rs=1 then it indicates successful execution of query
						if(rs==1) {
							JOptionPane.showMessageDialog(null, "Vehicle Edited Succesfully!!");
							
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Some Error Occurred. Re-enter details");
						}
						
						}
						catch(SQLException se) {se.printStackTrace();}
				}
				else {
					JOptionPane.showMessageDialog(null, "Enter Date in YYYY-MM-DD Format");
				}
		}
}
	
	
	public boolean checkRegistrationNo(String reg) {

		if(reg==null) {alreadyExists=false;return alreadyExists;}
		System.out.println("This is string reg"+reg+"-end");
		String st="SELECT * FROM vehicles WHERE regno=?";
		Connection con=serviceCentreDBConnection.connect();
		try{
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, reg);
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
		return alreadyExists;
	}
	
	private void autoFillForm(String reg) {
		// TODO Auto-generated method stub
		String st="SELECT * FROM vehicles WHERE regno=?";
		Connection con=serviceCentreDBConnection.connect();
		try{
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, reg);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				/*This loop will only work when th give regno exists
				System.out.println("Name: "+rs.getString("cname"));
				if((rs.getString("cname"))!=null)System.out.println("Data exists");
				*/
				regNo.setEditable(false);
				tpurchasedate.setText(rs.getString("purchaseDate"));
				//tpurchasedate.setEditable(false);
				ttype.setSelectedItem(rs.getString("type"));
				//ttype.setEnabled(false);
				tmodel.setText(rs.getString("model"));
				//tmodel.setEditable(false);
				tbrand.setText(rs.getString("brand"));
				//tbrand.setEditable(false);
				tmobile.setText(rs.getString("cmobile"));
				//tmobile.setEditable(false);
				tname.setText(rs.getString("cname"));
				//tname.setEditable(false);
				tentrydate.setText(rs.getString("firstEntryDate"));
			}
			
		}
		catch(SQLException se) {
			System.out.println("Error_Occured");
			se.printStackTrace();
		}
		
	}
}
