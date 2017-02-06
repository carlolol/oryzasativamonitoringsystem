package ui;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MoistUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, moisture1, moisture2;
	private SystemUI systemUI;
	private Random rand;
	
	private int h, w, resH, resW;
	
	private JButton btnConnect, tempB, moistB, homeB, minimizeB, exitB, aboutB, nextB, previousB;
	private JLabel lblBg, lblMoistureSensor1, lblCurrentMoist, lblAverageMoist, lblBlock1, lblBlock2;
	private JTextField textField1, textField2;
	private LoginHandler loginHandler;
	
	private JFreeChart moistLine1, moistLine2;
	private ChartPanel chartPanel1, chartPanel2;
	public static DefaultCategoryDataset mdataset1, mdataset2;
	public static int[] statMoist1, statMoist2;
	
	private Thread thread;
	
	public MoistUI(SystemUI systemUI)
	{	
		rand = new Random();
		
		generateRandomValue();
		
		// GUI components
		resH = SystemUI.getH();
		resW = SystemUI.getW();
		h = resH / 2;
		w = resW / 2;
		
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		lblMoistureSensor1 = new JLabel("Moisture Sensor #1");
		lblMoistureSensor1.setForeground(Color.BLACK);
		lblMoistureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor1.setBounds(w-500, h-300, 180, 50);
		centerP.add(lblMoistureSensor1);
			
		btnConnect = new JButton("Connect");
		btnConnect.setBackground(Color.WHITE);
		btnConnect.setForeground(Color.BLACK);
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConnect.setBounds(w-600, h-285, 100, 23);
		btnConnect.setActionCommand("Connect");
		btnConnect.addActionListener(loginHandler);
		centerP.add(btnConnect);
				
		lblCurrentMoist = new JLabel("Moisture content");
		lblCurrentMoist.setForeground(Color.BLACK);
		lblCurrentMoist.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMoist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCurrentMoist.setBounds(w+200, h-300, 600, 50);
		centerP.add(lblCurrentMoist);
				
		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField1.setForeground(Color.WHITE);
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setText("0%");
		textField1.setEditable(false);
		textField1.setOpaque(false);
		textField1.setBounds(w+400, h-230, 200, 90);
		centerP.add(textField1);
		textField1.setColumns(10);
				
		lblAverageMoist = new JLabel("Average moisture content");
		lblAverageMoist.setForeground(Color.BLACK);
		lblAverageMoist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageMoist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAverageMoist.setBounds(w+200, h-110, 600, 50);
		centerP.add(lblAverageMoist);
				
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0%");
		textField2.setEditable(false);
		textField2.setOpaque(false);
		textField2.setBounds(w+400, h-40, 200, 90);
		centerP.add(textField2);
		textField2.setColumns(10);		
	
		exitB = new JButton("");
		exitB.setToolTipText("Exit");
		exitB.setIcon(new ImageIcon("../Thesis/Images/x.png"));
		exitB.setRolloverIcon(new ImageIcon("../Thesis/Images/xhover.png"));
		exitB.setBounds(resW-60, 15, 40, 40);
		exitB.setOpaque(false);
		exitB.setContentAreaFilled(false);
		exitB.setBorderPainted(false);
		exitB.setActionCommand("Exit");
		exitB.addActionListener(loginHandler);
		centerP.add(exitB);
		
		minimizeB = new JButton("");
		minimizeB.setToolTipText("Minimize");
		minimizeB.setIcon(new ImageIcon("../Thesis/Images/minimize.png"));
		minimizeB.setRolloverIcon(new ImageIcon("../Thesis/Images/minimizehover.png"));
		minimizeB.setBounds(resW-105, 15, 40, 40);
		minimizeB.setOpaque(false);
		minimizeB.setContentAreaFilled(false);
		minimizeB.setBorderPainted(false);
		minimizeB.setActionCommand("Minimize");
		minimizeB.addActionListener(loginHandler);
		centerP.add(minimizeB);
		
		aboutB = new JButton("");
		aboutB.setToolTipText("About");
		aboutB.setIcon(new ImageIcon("../Thesis/Images/help.png"));
		aboutB.setRolloverIcon(new ImageIcon("../Thesis/Images/helphover.png"));
		aboutB.setBounds(resW-150, 15, 40, 40);
		aboutB.setOpaque(false);
		aboutB.setContentAreaFilled(false);
		aboutB.setBorderPainted(false);
		aboutB.setActionCommand("About");
		aboutB.addActionListener(loginHandler);
		centerP.add(aboutB);
		
		nextB = new JButton("");
		nextB.setToolTipText("Next sensor");
		nextB.setIcon(new ImageIcon("../Thesis/Images/next.png"));
		nextB.setRolloverIcon(new ImageIcon("../Thesis/Images/nexthover.png"));
		nextB.setBounds(w+520, h+100, 60, 50);
		nextB.setOpaque(false);
		nextB.setContentAreaFilled(false);
		nextB.setBorderPainted(false);
		nextB.setActionCommand("Next");
		nextB.addActionListener(loginHandler);
		centerP.add(nextB);
		
		previousB = new JButton("");
		previousB.setToolTipText("Previous sensor");
		previousB.setIcon(new ImageIcon("../Thesis/Images/previous.png"));
		previousB.setRolloverIcon(new ImageIcon("../Thesis/Images/previoushover.png"));
		previousB.setBounds(w+420, h+100, 60, 50);
		previousB.setOpaque(false);
		previousB.setContentAreaFilled(false);
		previousB.setBorderPainted(false);
		previousB.setEnabled(false);
		previousB.setActionCommand("Previous");
		previousB.addActionListener(loginHandler);
		centerP.add(previousB);
		
		tempB = new JButton("");
		tempB.setToolTipText("Temperature");
		tempB.setIcon(new ImageIcon("../Thesis/Images/tempIcon.png"));
		tempB.setRolloverIcon(new ImageIcon("../Thesis/Images/tempIconhover.png"));
		tempB.setOpaque(false);
		tempB.setContentAreaFilled(false);
		tempB.setBorderPainted(false);
		tempB.setBounds(resW-240, resH-85, 60, 60);
		tempB.setActionCommand("Temp");
		tempB.addActionListener(loginHandler);
		centerP.add(tempB);
		
		moistB = new JButton("");
		moistB.setToolTipText("Moisture");
		moistB.setIcon(new ImageIcon("../Thesis/Images/moistureIcon.png"));
		moistB.setRolloverIcon(new ImageIcon("../Thesis/Images/moistureIconhover.png"));
		moistB.setBounds(resW-160, resH-85, 60, 60);
		moistB.setOpaque(false);
		moistB.setContentAreaFilled(false);
		moistB.setBorderPainted(false);
		moistB.setActionCommand("Moist");
		moistB.addActionListener(loginHandler);
		moistB.setEnabled(false);
		centerP.add(moistB);
		
		homeB = new JButton("");
		homeB.setToolTipText("Home");
		homeB.setIcon(new ImageIcon("../Thesis/Images/home.png"));
		homeB.setRolloverIcon(new ImageIcon("../Thesis/Images/homehover.png"));
		homeB.setBounds(resW-80, resH-85, 60, 60);
		homeB.setOpaque(false);
		homeB.setContentAreaFilled(false);
		homeB.setBorderPainted(false);
		homeB.setActionCommand("Home");
		homeB.addActionListener(loginHandler);
		centerP.add(homeB);
		
		lblBlock1 = new JLabel();
		lblBlock1.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock1.setBounds(w+380, h-290,300,180);
		centerP.add(lblBlock1);
		
		lblBlock2 = new JLabel();
		lblBlock2.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock2.setBounds(w+380, h-100,300,180);
		centerP.add(lblBlock2);
		
		generateGraph();
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
		centerP.add(lblBg);
		
		add(centerP);
		
		// starts updating the home UI
		startThread();
	}
	
	public void generateRandomValue()
	{
		statMoist1 = new int[24];
		statMoist2 = new int[24];
		
		// moisture
		for(int a = 0; a < statMoist1.length; a++)
			statMoist1[a] = rand.nextInt(10) + 30;
		
		for(int a = 0; a < statMoist2.length; a++)
			statMoist2[a] = rand.nextInt(10) + 30;
		
		mdataset1 = new DefaultCategoryDataset();
		for(int a = 0; a < statMoist1.length; a++)
			mdataset1.addValue(statMoist1[a], "moisture content reading", "" + a + ":00");
		
		mdataset2 = new DefaultCategoryDataset();
		for(int a = 0; a < statMoist2.length; a++)
			mdataset2.addValue(statMoist2[a], "moisture content reading", "" + a + ":00");	
	}
	
	public void generateGraph()
	{
		moistLine1 = ChartFactory.createLineChart("Moisture #1", "Time", "Moisture", mdataset1);
		chartPanel1 = new ChartPanel(moistLine1);
		chartPanel1.setPreferredSize(new Dimension(900, 500));
		chartPanel1.setMouseZoomable(false);
		
		moisture1 = new JPanel();
		moisture1.setBounds(w-600, h-250, 900, 505);
		moisture1.add(chartPanel1, BorderLayout.CENTER);
		moisture1.validate();
		centerP.add(moisture1);
		
		moistLine2 = ChartFactory.createLineChart("Moisture #2", "Time", "Moisture", mdataset2);
		chartPanel2 = new ChartPanel(moistLine2);
		chartPanel2.setPreferredSize(new Dimension(900, 500));
		chartPanel2.setMouseZoomable(false);	
		
		moisture2 = new JPanel();
		moisture2.setBounds(w-600, h-250, 0, 0);
		moisture2.add(chartPanel2, BorderLayout.CENTER);
		moisture2.validate();
		centerP.add(moisture2);
	}
	
	// responsible for updating the moisture UI
	public void startThread()
	{
		thread = new Thread()
		{
			public void run(){
				int x = 0;
				
				for(x = 1; x>0; x++)
				{
					try 
					{
						Thread.sleep(2000);
						x++;
						textField1.setText((rand.nextInt(15) + 10) + "%");
						textField2.setText((rand.nextInt(15) + 10) + "%");
						generateRandomValue();
						generateGraph();
						moisture1.validate();
					} 
					catch(Exception e) 
					{
						System.out.print("ERROR");
					}
				}
			}
		};
		thread.start();
	}
	
	// functionality
	private class LoginHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String action = e.getActionCommand();
			
			if(action.equals("Exit"))
			{
				int result = JOptionPane.showConfirmDialog(null, "Are you sure? All connection will be disconnected.", 
						"Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(result == JOptionPane.YES_OPTION)
				{
	              System.exit(0);
	            }
			}
			else if(action.equals("Minimize"))
			{
				systemUI.setState(Frame.ICONIFIED);
			}
			else if(action.equals("About"))
			{
				JOptionPane.showMessageDialog(null, "Oryza Sativa Grains Monitoring System\nv.09\n\n"
						+ "Thesis by: \nMarc Angelo Martinez\nCarl Louie Aruta\nMelvin Uy",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(action.equals("Connect"))
			{
				btnConnect.setText("Disconnect");
				btnConnect.setActionCommand("Disconnect");
				//textField1.setText(statMoist1[23] + "%");
			}
			else if(action.equals("Disconnect"))
			{
				btnConnect.setText("Connect");
				btnConnect.setActionCommand("Connect");
				//textField1.setText(statMoist1[23] + "%");
			}
			else if(action.equals("Next"))
			{
				nextB.setEnabled(false);
				previousB.setEnabled(true);
				moisture1.setBounds(w-600, h-250, 0, 0);
				moisture1.validate();
				moisture2.setBounds(w-600, h-250, 900, 505);
				moisture2.validate();
				
				lblMoistureSensor1.setText("Moisture Sensor #2");
			}
			else if(action.equals("Previous"))
			{
				previousB.setEnabled(false);
				nextB.setEnabled(true);
				moisture2.setBounds(w-600, h-250, 0, 0);
				moisture2.validate();
				moisture1.setBounds(w-600, h-250, 900, 505);
				moisture1.validate();
				
				lblMoistureSensor1.setText("Moisture Sensor #1");
			}
			else if(action.equals("Temp"))
			{
				systemUI.showTemp();
			}
			else if(action.equals("Moist"))
			{
				systemUI.showMoist();
			}
			else if(action.equals("Home"))
			{
				thread.suspend();
				HomeUI.thread.resume();
				systemUI.showMain();
			}
			repaint();
		}
	}
}