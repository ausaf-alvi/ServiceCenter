package pack;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;

public class viewAllVehicles extends JDialog implements ActionListener {
	private JTable table;
	private DefaultTableModel m;
	JButton btnCancel;

	public viewAllVehicles() {
		setTitle("All Vehicle Details");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 11, 89, 23);
		getContentPane().add(btnCancel);
		
		
		String cols[]=new String[] {"Reg. No","Type","Cust. Name"
				,"Contact","Brand","Model","Purchase Date","No. Services",
				"Last Service Id","First Entry Date"};
		table=new JTable();
		m=new DefaultTableModel();
		m.setColumnIdentifiers(cols);
		table.setModel(m);
		table.setFillsViewportHeight(true);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().add(table);
		try {
			Connection con=serviceCentreDBConnection.connect();
			String st="SELECT * FROM vehicles";
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String type=rs.getString("type");
				String cname=rs.getString("cname");
				String mob=rs.getString("cmobile");
				String brand=rs.getString("brand");
				String model=rs.getString("model");
				String pDate=rs.getString("purchaseDate");
				String noServices=rs.getString("NoOfServices");
				String lastId=rs.getString("lastServiceId");
				String firstEntry=rs.getString("firstEntryDate");
				m.addRow(new Object[] {type,cname,mob,brand,model,pDate,noServices,lastId,firstEntry});
				
			}
			table.setModel(m);
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();			
		}
		getContentPane().add(table);
		table.setBounds(0, 0, 950, 900);
		
		table.setDefaultEditor(Object.class, null);//stops editability of table cells

		JScrollPane jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(50, 50, 950, 300);
		getContentPane().add(jsp);
		
		// TODO Auto-generated constructor stub
		setSize(1050, 500);
		setModal(true);
		setVisible(true);
		setLocation(100, 100);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new viewAllVehicles();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
