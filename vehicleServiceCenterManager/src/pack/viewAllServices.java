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

public class viewAllServices extends JDialog implements ActionListener {
	private JTable table;
	private DefaultTableModel m;
	JButton btnCancel;

	public viewAllServices() {
		setTitle("All Services Details");
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
		
		
		String cols[]=new String[] {"Reg. No","Service ID","Service Type"
				,"Service Status","Service Cost","Pickup Date",
				"Submit Date","Next Date",};
		table=new JTable();
		m=new DefaultTableModel();
		m.setColumnIdentifiers(cols);
		table.setModel(m);
		table.setFillsViewportHeight(true);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().add(table);
		try {
			Connection con=serviceCentreDBConnection.connect();
			String st="SELECT * FROM services";
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String sID=rs.getString("ServiceId");
				String rgno=rs.getString("regNo");
				String sType=rs.getString("serviceType");
				String sStatus=rs.getString("serviceStatus");
				String sCost=rs.getString("serviceCost");
				String pDate=rs.getString("pickupDate");
				String sDate=rs.getString("submitDate");
				String nDate=rs.getString("nextDate");
				m.addRow(new Object[] {rgno,sID,sType,sStatus,sCost,pDate,sDate,nDate});
				
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
		new viewAllServices();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
