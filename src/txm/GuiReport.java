package txm;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.Box;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Component;

import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

class GuiReport extends JPanel {
	/**
	 */
	private static final long serialVersionUID = -8425452776450565711L;
	private Box verticalBox;
	private JButton btnSavereport;
	private JTextPane txtpnReport;
	private Component verticalStrut;
	String txtReport=new String();
	GuiAppWindow appWindow;
	String name;
	int updateFlag=0;
	private JFileChooser fc=new JFileChooser();
	/**
	 * Create the panel.
	 */
	public GuiReport(GuiAppWindow appWindow, String name) {
		
		this.appWindow=appWindow;
		this.name=name;
		updateReport();
		
		setBackground(Color.WHITE);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		verticalBox = Box.createVerticalBox();
		springLayout.putConstraint(SpringLayout.NORTH, verticalBox, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, verticalBox, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, verticalBox, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, verticalBox, -10, SpringLayout.EAST, this);
		add(verticalBox);
		
		txtpnReport = new JTextPane();
		txtpnReport.setBackground(Color.WHITE);
		txtpnReport.setText(txtReport);
		txtpnReport.setEditable(false);
		verticalBox.add(txtpnReport);
		
		btnSavereport = new JButton("Αποθήκευση Αναφοράς");
		btnSavereport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				saveReport();
			}
		});
		btnSavereport.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSavereport.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		verticalBox.add(btnSavereport);
		
		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
	}

	public void updateReport(){
		txtReport=appWindow.ctrl.getSoftHistory(name).getReport();
		if(updateFlag>0)
			txtpnReport.setText(txtReport);
		if(updateFlag==0)
			updateFlag++;
	}
	
	private void saveReport(){
		ArrayList<String> noGoodLaws=new ArrayList<String>();
		for(int i=0;i<8;i++){
			if("Καμία Εκτίμηση"==appWindow.ctrl.getSoftHistory(name).getLawChoice(i))
				noGoodLaws.add(String.valueOf(i+1));
		}
		if(!noGoodLaws.isEmpty()){
			String message="Η αποθήκευση αναφοράς δεν είναι δυνατή \nΔώστε εκτίμηση για:";
			for(int i=0; i<noGoodLaws.size();i++)
				message+=" Νόμο "+noGoodLaws.get(i)+" , ";
			JOptionPane.showMessageDialog(null, message);
		}
		else{
			try{	
				if(JFileChooser.APPROVE_OPTION==fc.showSaveDialog(getRootPane())){
					File file=fc.getSelectedFile();
					Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));	
					writer.write(txtReport);
					writer.close();
					JOptionPane.showMessageDialog(null,"Η αναφορά αποθηκεύτηκε στο : "+file.getAbsolutePath());
				}
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Δεν ήταν δυνατή η δημιουργία και εγγραφή του αρχείου.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
