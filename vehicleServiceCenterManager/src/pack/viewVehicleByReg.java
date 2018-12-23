package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class viewVehicleByReg extends JDialog implements ActionListener {
	
	private boolean alreadyExists=false;
	private JTable table;
	private JButton btnCancel;
	DefaultTableModel m;
	
	public viewVehicleByReg(String reg) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		checkRegistrationNo(reg);
		if(alreadyExists){
			getContentPane().setBackground(new Color(248, 248, 255));
			getContentPane().setLayout(null);
			setTitle("Vehicle Details");
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancel.setOpaque(false);
			btnCancel.setForeground(new Color(0, 0, 0));
			btnCancel.setBackground(UIManager.getColor("Button.background"));
			btnCancel.setBounds(10, 11, 89, 23);
			getContentPane().add(btnCancel);
			
			table = new JTable();
			table.setFillsViewportHeight(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//table.setBounds(50, 324, 647, -250);
			getContentPane().add(table);
			
			
			String cols[]=new String[] {"Reg. No","Type","Cust. Name"
					,"Contact","Brand","Model","Purchase Date","No. Services",
					"Last Service Id","First Entry Date"};
			table=new JTable();
			 m=new DefaultTableModel();
			m.setColumnIdentifiers(cols);
			table.setModel(m);
			Connection co=serviceCentreDBConnection.connect();
			String st="select * from vehicles where regno=?";
			try{
				PreparedStatement ps=co.prepareStatement(st);
				ps.setString(1,reg);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					String type=rs.getString("type");
					String cname=rs.getString("cname");
					String mob=rs.getString("cmobile");
					String brand=rs.getString("brand");
					String model=rs.getString("model");
					String pDate=rs.getString("purchaseDate");
					String noServices=rs.getString("NoOfServices");
					String lastId=rs.getString("lastServiceId");
					String firstEntry=rs.getString("firstEntryDate");
					m.addRow(new Object[]{reg,type,cname,mob,brand,model,pDate,noServices,lastId,firstEntry});
					}
				table.setModel(m);
				}
			catch(SQLException se){
				se.printStackTrace();
				}
			table.setDefaultEditor(Object.class, null);//stops editability of table cells
			
			JScrollPane jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(50, 50, 950, 300);
			getContentPane().add(jsp);
			// TODO Auto-generated constructor stub
			setModal(true);
			setVisible(true);
			setSize(1050, 500);
			setLocation(100, 100);
		}
		else {
			JOptionPane.showMessageDialog(null, "No Such Record");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new viewVehicleByReg("UP32FQ2281");
	}

	public void checkRegistrationNo(String reg) {

		if(reg!=null) {
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
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
