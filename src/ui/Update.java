package ui;

import model.*;
import static ui.com.fonts.Font.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Update extends JFrame implements ActionListener {
	JLabel Medicine, Expiry, Agency, Quantity, MRP, minimum_quantity, unit, price;
	JTextField Medicine_text, Expiry_text, Agency_text, Quantity_text, MRP_text, minimum_quantity_text, unit_text, price_text;
	JButton Updatebutton, Clearbutton, Newbutton, Removebutton;
	ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");


	Update() {


		//JPanel bottompanel = new JPanel();

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel topanel = new JPanel();

		JLabel mainLable = new JLabel("");

		//mainLable.setFont(new Font("arial", 0, 40));
		Image img1 = img.getImage();
		Image img2 = img1.getScaledInstance(900,200,Image.SCALE_SMOOTH);
		ImageIcon Img = new ImageIcon(img2);
		mainLable.setIcon(Img);
		topanel.add(mainLable);
		add(topanel);

		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(7, 1, 20, 5));

		//JPanel center = new JPanel();

		JPanel f = new JPanel();
		f.setLayout(new FlowLayout());
		JPanel s = new JPanel();
		s.setLayout(new FlowLayout());
		JPanel t = new JPanel();
		t.setLayout(new FlowLayout());

		JPanel ft = new JPanel(new FlowLayout());


		Medicine = new JLabel("Medicine Name :  ");
		Medicine.setFont(text);
		f.add(Medicine);

		Medicine_text = new JTextField(15);
		Medicine_text.setFont(field);
		f.add(Medicine_text);

		Expiry = new JLabel(" Expiry Date :         ");
		Expiry.setFont(text);
		s.add(Expiry);

		Expiry_text = new JTextField(15);
		Expiry_text.setFont(field);
		s.add(Expiry_text);

		minimum_quantity = new JLabel("  Min. Quantity :    ");
		minimum_quantity.setFont(text);
		t.add(minimum_quantity);

		minimum_quantity_text = new JTextField(15);
		minimum_quantity_text.setFont(field);
		t.add(minimum_quantity_text);

		Agency = new JLabel("   Agency Name :     ");
		Agency.setFont(text);
		ft.add(Agency);

		Agency_text = new JTextField(15);
		Agency_text.setFont(field);
		ft.add(Agency_text);

		Quantity = new JLabel("      Quantity :       ");
		Quantity.setFont(text);
		f.add(Quantity);

		Quantity_text = new JTextField(15);
		Quantity_text.setFont(field);
		f.add(Quantity_text);


		MRP = new JLabel("       MRP:                 ");
		MRP.setFont(text);
		s.add(MRP);

		MRP_text = new JTextField(15);
		MRP_text.setFont(field);
		s.add(MRP_text);

		unit = new JLabel("       Unit :          	     ");
		unit.setFont(text);
		t.add(unit);

		unit_text = new JTextField(15);
		unit_text.setFont(field);
		t.add(unit_text);

		JLabel pl = new JLabel("                   ");
		ft.add(pl);


		price = new JLabel("Price :             ");
		price.setFont(text);
		ft.add(price);

		price_text = new JTextField(15);
		price_text.setFont(field);
		ft.add(price_text);

		JPanel bottomFPanel = new JPanel(new FlowLayout());
		JPanel bottomSPanel = new JPanel();

		Updatebutton = new JButton(" Add / Update in Stock ");
		Updatebutton.setFont(button);
		Updatebutton.setBackground(Color.BLUE);
		Updatebutton.setForeground(Color.WHITE);
		Updatebutton.addActionListener(this);
		bottomFPanel.add(Updatebutton);

		JLabel bfl = new JLabel("              ");
		bottomFPanel.add(bfl);

		Clearbutton = new JButton("Clear All Fields");
		Clearbutton.setFont(button);
		Clearbutton.setBackground(Color.BLUE);
		Clearbutton.setForeground(Color.WHITE);
		Clearbutton.addActionListener(this);
		bottomFPanel.add(Clearbutton);

//		Newbutton = new JButton("        New Medicine         ");
//		Newbutton.setFont(new Font("arial", 0, 20));
//		Newbutton.addActionListener(this);
//		bottomSPanel.add(Newbutton);

		JLabel bsl = new JLabel("                  ");
		bottomSPanel.add(bsl);

		ImageIcon home = new ImageIcon("src/Images/home.png");
		Image home1 = home.getImage();
		Image home2 = home1.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		ImageIcon Home = new ImageIcon(home2);

		Removebutton = new JButton("");
		Removebutton.setIcon(Home);
		Removebutton.setBackground(Color.WHITE);

		Removebutton.addActionListener(this);
		bottomSPanel.add(Removebutton);

		mainpanel.add(f);
		mainpanel.add(s);
		mainpanel.add(ft);
		mainpanel.add(t);

		//mainpanel.add(center,BorderLayout.CENTER);
		mainpanel.add(bottomFPanel);
		mainpanel.add(bottomSPanel);

		add(mainpanel);

		Expiry_text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char ch = ke.getKeyChar();

				if (Character.isDigit(ch) || ch == '-' || ch == '/')
				{

					return;
				}

				ke.setKeyChar('\b');
			}
		});


		Agency_text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char ch = ke.getKeyChar();

				if (Character.isLetter(ch) || ch == ' ')
				{

					return;
				}
				ke.setKeyChar('\b');
			}
		});

		Quantity_text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char ch = ke.getKeyChar();

				if (Character.isDigit(ch))
				{

					return;
				}
				ke.setKeyChar('\b');
			}
		});

		MRP_text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char ch = ke.getKeyChar();

				if (Character.isDigit(ch) || ch == '.') {
					String str = MRP_text.getText();

					if (str.contains(".") && ch == '.')
					{
						ke.setKeyChar('\b');
					}
					return;
				}
				ke.setKeyChar('\b');
			}
		});


		//mainpanel.setBackground(Color.red);
		//setSize(1000, 750);
		//setLocation(100, 0);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
		setIconImage(titleicon.getImage());


	}

	ImageIcon logins = new ImageIcon("src/Images/AlertLogo.png");
	Image logins1 = logins.getImage();
	Image logins2 = logins1.getScaledInstance(60,60,Image.SCALE_SMOOTH);
	ImageIcon Logins = new ImageIcon(logins2);

	public void actionPerformed(ActionEvent ae)
	{
		String s = ae.getActionCommand();


		if (s.equals(" Add / Update in Stock "))
		{
			String medicineName = Medicine_text.getText();
			String expiryDate = Expiry_text.getText();
			String quantity = Quantity_text.getText();
			String str3 = MRP_text.getText();
			String agencyName = Agency_text.getText();

			//boolean text = Medicine_text.getText().isEmpty();

			if(SecondScreen.getConnection()==null)
			{
				JOptionPane.showMessageDialog(null,"! Connection failed with database Please Start xampp servaer .." );
				return ;
			}

			if(medicineName.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Enter Valid Name","Empty Field",JOptionPane.INFORMATION_MESSAGE,Logins);
				minimum_quantity_text.requestFocus();
				return;
			}
			if((Quantity_text.getText()).isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Enter Valid Quantity","Empty Field",JOptionPane.INFORMATION_MESSAGE,Logins);
				Quantity_text.requestFocus();
				return;
			}


			//System.out.println(text);
//
//			if (medicineName.length() == 0) {
//				JOptionPane.showMessageDialog(null,"Enter Valid Medicine Name");

//				if (dialogbutton == JOptionPane.YES_OPTION) {
//					Medicine_text.setText("");
//					Medicine_text.requestFocus();
//				}
//			}else if(qt < 0)
//			{
//
//			}else if(expiryDate.compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(java.time.LocalDate.now())  )){
//
//			}else{
//
//			}
			int unit = Integer.parseInt(unit_text.getText());
			double price = Double.parseDouble(price_text.getText());
			int minimumQuantity = Integer.parseInt(minimum_quantity_text.getText());


			double mrp = Double.parseDouble(str3);

			int qt = Integer.parseInt(quantity);
			Date date = null;

			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(expiryDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			UpdateMedicines um = new UpdateMedicines(medicineName, qt, expiryDate, unit, mrp, agencyName, price, minimumQuantity);
			if (um.decide()) {
				int dialogbuttonforupdate = JOptionPane.showConfirmDialog(null, "want to update", "Warning", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,Logins);

				if (dialogbuttonforupdate == JOptionPane.NO_OPTION) {
					System.out.println("No Fire");
				}

				if (dialogbuttonforupdate == JOptionPane.YES_OPTION) {
					um.update();
					Medicine_text.setText("");
					Expiry_text.setText("");
					Agency_text.setText("");
					Quantity_text.setText("");
					MRP_text.setText("");
					minimum_quantity_text.setText("");
					unit_text.setText("");
					price_text.setText("");
				}
				//System.out.println("call to update function it is saying that ur medicine is belongs to db you only ha sto update the db");
			} else {
				int dialogbuttonforinsert = JOptionPane.showConfirmDialog(null, "want to insert", "Warning", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,Logins);

				if (dialogbuttonforinsert == JOptionPane.NO_OPTION) {
					System.out.println("No Fire");
				}

				if (dialogbuttonforinsert == JOptionPane.YES_OPTION) {
					um.insert();
					Medicine_text.setText("");
					Expiry_text.setText("");
					Agency_text.setText("");
					Quantity_text.setText("");
					MRP_text.setText("");
					minimum_quantity_text.setText("");
					unit_text.setText("");
					price_text.setText("");
				}
			}




			return;
		}

		if (s.equals("Clear All Fields")) {
			Medicine_text.setText("");
			Expiry_text.setText("");
			Agency_text.setText("");
			Quantity_text.setText("");
			MRP_text.setText("");
			unit_text.setText("");
			price_text.setText("");
			minimum_quantity_text.setText("");
		}



		if (s.equals(""))
		{
			dispose();
			obj = null;
		}

	}

	public static Update getUpdate(){                          //Singletone Method
		if (obj == null){
			synchronized(Update.class){
				if (obj == null){
					obj = new Update();//instance will be created at request time
				}

			}
		}
		return obj;

	}


	private static Update obj =  new Update();
	
}
