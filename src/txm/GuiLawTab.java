package txm;

import javax.swing.JPanel;

import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;

import java.awt.Dimension;

import javax.swing.JComboBox;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

class GuiLawTab extends JPanel{

	private static final long serialVersionUID = 2362954328122476971L;
	private JTextField textField;
	private JTextField txtDecide;
	private String[] choices={"Καμία Εκτίμηση","Ισχύει","Δεν Ισχύει"};
	JTabbedPane chartTabs;
	JTextPane txtpnInfo;
	JTextArea txtrComment;
	JTextArea txtrChoice;
	JComboBox comboBox=new JComboBox(choices);;
	String comment="Εδώ γράψτε το σχόλιό σας";
	int lawNum;
	GuiLawTabs parent;
	
	public GuiLawTab(int lawNum, String lawInfo, final GuiLawTabs parent) {
		this.parent=parent;
		this.lawNum=lawNum;
		setMinimumSize(new Dimension(10, 30));
		setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[:50%:50%,grow][30%:30%:30%,grow][:20%:20%]", "[30px:30px:30px,grow][:80%:80%][50px:50px:50px,grow][:10%:10%,grow]"));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBorder(null);
		textField.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
		textField.setText(this.lawNum+"ος Νόμος του Lehman");
		add(textField, "cell 0 0 2 1,growx");
		textField.setColumns(10);
		
		chartTabs=new JTabbedPane();
		chartTabs.setTabPlacement(JTabbedPane.BOTTOM);
		chartTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		chartTabs.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		chartTabs.setBackground(Color.WHITE);
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>(parent.cgen.getCharts(lawNum, parent.name));
		for(int i=0; i<charts.size();i++){
			ChartPanel p=new ChartPanel(charts.get(i));
			String title=charts.get(i).getTitle().getText();
			chartTabs.addTab(title,null,p,null);
		}
		add(chartTabs, "cell 0 1 2 1,grow");
		
		txtpnInfo = new JTextPane();
		txtpnInfo.setMaximumSize(new Dimension(200, 2147483647));
		txtpnInfo.setBackground(Color.WHITE);
		txtpnInfo.setEditable(false);
		txtpnInfo.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		txtpnInfo.setText(lawInfo);
		add(txtpnInfo, "cell 2 0 1 4,grow");
		
		txtrComment = new JTextArea();
		txtrComment.setMinimumSize(new Dimension(0, 50));
		txtrComment.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		txtrComment.setBackground(Color.WHITE);
		txtrComment.setText(comment);
		add(txtrComment, "cell 0 2 1 2,grow");
		
		txtDecide = new JTextField();
		txtDecide.setMinimumSize(new Dimension(4, 20));
		txtDecide.setEditable(false);
		txtDecide.setHorizontalAlignment(SwingConstants.CENTER);
		txtDecide.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		txtDecide.setBorder(null);
		txtDecide.setBackground(Color.WHITE);
		add(txtDecide, "cell 1 2,growx");
		txtDecide.setColumns(10);
		
		if(this.lawNum==7){	
			txtDecide.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
			txtDecide.setText("Ο 7ος Νόμος είναι λογική συνέπεια των 2 και 6");
			
			txtrChoice = new JTextArea();
			txtrChoice.setBackground(Color.WHITE);
			txtrChoice.setEditable(false);
			txtrChoice.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
			txtrChoice.setText(choices[this.parent.getChoices()[6]]);
			add(txtrChoice, "cell 1 3,alignx center,aligny top");
		}
		else{
			txtDecide.setText("Επιλέξτε αν ο νόμος "+this.lawNum+" ισχύει");
			
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					parent.updateAll();
				}
			});
			comboBox.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
			comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(comboBox, "cell 1 3,alignx center,aligny top");
		}
	}
	
	public String getComment(){
		return txtrComment.getText();
	}
	
	public int getChoice(){
		int i=comboBox.getSelectedIndex();
		if(i==0||i==1||i==2)
			return comboBox.getSelectedIndex();
		else
			return 0;
	}
	
	public void putLaw7Eval(int eval){
		txtrChoice.setText(choices[eval]);
	}
}