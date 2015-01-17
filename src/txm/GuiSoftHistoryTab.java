package txm;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SpringLayout;
import javax.swing.Box;

import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class GuiSoftHistoryTab extends JPanel {
	
	private static final long serialVersionUID = -2973242479474242071L;
	private String name;
	private Box verticalBox;
	private Box horizontalBox;
	private JButton button_0;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	
	GuiLawTabs lawsPane;
	GuiReport reportPane;
	GuiFile filePane;
	/**
	 * Create the panel.
	 */
	public GuiSoftHistoryTab(String name, final GuiAppWindow appWindow) {
		this.name=name;
		setBackground(Color.WHITE);
		setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lawsPane=new GuiLawTabs(appWindow,this.name);
		reportPane=new GuiReport(appWindow,this.name);
		filePane=new GuiFile(appWindow,this.name);
		
		verticalBox = Box.createVerticalBox();
		springLayout.putConstraint(SpringLayout.NORTH, verticalBox, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, verticalBox, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, verticalBox, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, verticalBox, 0, SpringLayout.EAST, this);
		add(verticalBox);
		
		horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		verticalBox.add(horizontalBox);
		
		button_0 = new JButton("Νόμοι Lehman");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(verticalBox.isAncestorOf(reportPane))
					verticalBox.remove(reportPane);
				else if(verticalBox.isAncestorOf(filePane))
					verticalBox.remove(filePane);
				verticalBox.add(lawsPane);
				verticalBox.revalidate();
				verticalBox.repaint();
				
			}
		});
		button_0.setAlignmentX(Component.CENTER_ALIGNMENT);
		horizontalBox.add(button_0);
		
		button_1 = new JButton("Προβολή Αναφοράς");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(verticalBox.isAncestorOf(filePane))
					verticalBox.remove(filePane);
				else if(verticalBox.isAncestorOf(lawsPane)){
					lawsPane.updateAll();
					reportPane.updateReport();
					verticalBox.remove(lawsPane);
				}
				verticalBox.add(reportPane);
				verticalBox.revalidate();
				verticalBox.repaint();
			}
		});
		button_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		horizontalBox.add(button_1);
		
		button_2 = new JButton("Προβολή Ιστορικού");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(verticalBox.isAncestorOf(reportPane))
					verticalBox.remove(reportPane);
				else if(verticalBox.isAncestorOf(lawsPane)){
					lawsPane.updateAll();
					verticalBox.remove(lawsPane);
				}
				verticalBox.add(filePane);
				verticalBox.revalidate();
				verticalBox.repaint();
			}
		});
		button_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		horizontalBox.add(button_2);
		
		button_3 = new JButton("Διαγραφή Ιστορικού");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeThisTab(appWindow);
			}
		});
		button_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		horizontalBox.add(button_3);
		
		verticalBox.add(lawsPane);
	}
	
	public String getMyName(){
		return this.name;
	}
	
	private void removeThisTab(GuiAppWindow appWindow){
		appWindow.removeSoftHistory(this);
	}
}
