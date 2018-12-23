package pack;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.GarbageCollectorMXBean;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame implements ActionListener {
	JLabel imgLabel;
	private JMenuItem mntmEditVehicle;

	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/images/titlebarIcon.png")));
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height-40);
		System.out.print(screenSize.height);
		System.out.print(screenSize.width);
		setTitle("Vehicle Service Manager");
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu mnAdd = new JMenu("Vehicles");
		menuBar.add(mnAdd);
		
		JMenuItem mntmAddVehicles = new JMenuItem("Add Vehicle/Service");
		mntmAddVehicles.setIcon(new ImageIcon("C:\\Users\\91870\\Downloads\\if_BT_c3tool_905663(2).png"));
		mntmAddVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new vehicleEntry();
			}
		});
		mnAdd.add(mntmAddVehicles);
		
		mntmEditVehicle = new JMenuItem("Edit Vehicle");
		mntmEditVehicle.setIcon(new ImageIcon(Home.class.getResource("/images/editVehicleMenuItem_icon.png")));
		mntmEditVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rg=JOptionPane.showInputDialog(new JTextField(), "Registration no").toUpperCase();
				if(rg!=null)new editVehicleDetails(rg);
				
//				new editVehicleDetails(JOptionPane.showInputDialog(new JTextField(), "Registration no").toUpperCase());
			}
		});
		mnAdd.add(mntmEditVehicle);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(Home.class.getResource("/images/exitMenuItem_icon.png.png")));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JMenuItem mntmEditService = new JMenuItem("Edit Service");
		mntmEditService.setIcon(new ImageIcon(Home.class.getResource("/images/editVehicleMenuItem_icon.png")));
		mntmEditService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid=JOptionPane.showInputDialog(new JTextField(), "Service ID").toUpperCase();
				if(sid!=null)new editServiceDetails(sid);
			}
		});
		mnAdd.add(mntmEditService);
		mnAdd.add(mntmExit);
		
		JMenu mnView = new JMenu("View");
		mnView.setIcon(null);
		menuBar.add(mnView);
		
		JMenu mnViewVehicle = new JMenu("Vehicles");
		mnView.add(mnViewVehicle);
		
		JMenuItem mntmByRegNo = new JMenuItem("By Reg No");
		mntmByRegNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String r=JOptionPane.showInputDialog(new JTextField(), "Registration No:").toUpperCase();
				if(r!=null)new viewVehicleByReg(r);
			}
		});
		mnViewVehicle.add(mntmByRegNo);
		
		JMenuItem mntmViewAllVehicles = new JMenuItem("View All Vehicles");
		mntmViewAllVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new viewAllVehicles();
			}
		});
		mnViewVehicle.add(mntmViewAllVehicles);
		
		JMenu mnViewService = new JMenu("Services");
		mnView.add(mnViewService);
		
		JMenuItem mntmByServiceId = new JMenuItem("By Service Id");
		mntmByServiceId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField jt=new JTextField();
				String sid=JOptionPane.showInputDialog(jt, "Enter Service ID");
				//System.out.println(sid);
				if(sid!=null)new viewServiceBySID(sid);
			}
		});
		mnViewService.add(mntmByServiceId);
		
		JMenuItem mntmByRegistrationNo = new JMenuItem("By Registration No");
		mntmByRegistrationNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField jt=new JTextField();
				String rg=JOptionPane.showInputDialog(jt, "Enter Registration No");
				//System.out.println(sid);
				if(rg!=null)new viewServiceByReg(rg);
				
			}
		});
		mnViewService.add(mntmByRegistrationNo);
		
		JMenuItem mntmViewAll = new JMenuItem("View All");
		mntmViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new viewAllServices();
			}
		});
		mnViewService.add(mntmViewAll);
		
		JMenu mnDelete = new JMenu("Delete");
//		menuBar.add(mnDelete);
		
		JMenuItem mntmService = new JMenuItem("Service");
		mntmService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField jt=new JTextField();
				String sid=JOptionPane.showInputDialog(jt, "Enter Service ID");
				//System.out.println(sid);
				if(sid!=null)new deleteService(sid);
			}
		});
		mnDelete.add(mntmService);
		
		JMenuItem mntmVehicle = new JMenuItem("Vehicle");
		mntmVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField jt=new JTextField();
				String rg=JOptionPane.showInputDialog(jt, "Enter Registration No");
				//System.out.println(sid);
				if(rg!=null)new deleteVehicle(rg);
			}
		});
		mnDelete.add(mntmVehicle);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpMenu = new JMenuItem("Help Menu");
		mnHelp.add(mntmHelpMenu);
		
		JMenuItem mntmAboutServiceCentre = new JMenuItem("About Service Centre Manager");
		mntmAboutServiceCentre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object options[]= {"OK!"};
				ImageIcon ic=new ImageIcon(Home.class.getResource("/images/info_sign.png"));
				String msg="<center><b>Vehicle Service Center Manager</b>This software Was Developed by Auaf Anwar Alvi"
						+"<br>and Mohd. Emad Uddin Qidwai using Java and MySQL"
						+"<br>for their College Mini Project"
						+"<br>\tVersion:1.0.0"
						+"<br>\tDate:08/10/2018"
						+"<br>Contact: ausaf.alvi@gmail.com<br>emad.sfc@gmail.com</center>";
				JLabel jl=new JLabel("<html><b><font color=#215C49>"+msg+"<font></b></html>");
				
				//JLabel jl=new JLabel("<html><b><font color=blue>"+hello+""<font></b></html>");
				//JOptionPane.showMessageDialog(null, msg, "About Us", JOptionPane.INFORMATION_MESSAGE, ic);
				JOptionPane.showMessageDialog(null, jl, "About Us", JOptionPane.INFORMATION_MESSAGE, ic);
				
			}
		});
		mnHelp.add(mntmAboutServiceCentre);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		imgLabel=new JLabel("");
		imgLabel.setIcon(new ImageIcon(Home.class.getResource("/images/homeBackground.jpeg")));
		getContentPane().add(imgLabel,BorderLayout.NORTH);
		
		// TODO Auto-generated constructor stub
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Home();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
