package txm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.Dimension;

import javax.swing.Box;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Cursor;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.io.File;

public class GuiAppWindow {
	
	public CInterface ctrl=new CInterface();
	private Map<String,GuiSoftHistoryTab> softHistoryTabs;
	
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private Box verticalBox;
	private JTextField txtIntrotitle;
	private Component verticalStrut;
	private JTextPane txtpnIntrotxt;
	private Component verticalStrut_1;
	private JButton btnNewfile;
	private Component verticalGlue;
	private JTextField txtAuthor;
	private JFileChooser fc;
	private String[] mErrors={"Το αρχείο ιστορικού συστήματος που εισάγατε δεν έχει την αποδεκτή μορφή \n Η αποδεκτή μορφή είναι ένα αρχείο txt (κατάληξη .txt) με περιεχόμενα που συμφωνούν με το προκαθορισμένο Μοντέλο Δεδομένων",
							"Το αρχείο ιστορικού συστήματος που εισάγατε υπάρχει ήδη"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAppWindow window = new GuiAppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		softHistoryTabs=new HashMap<String,GuiSoftHistoryTab>();
		fc=new JFileChooser();
		
		frame = new JFrame();
		frame.setName("mainFrame");
		frame.setMinimumSize(new Dimension(950, 550));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		frame.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1100, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Νέο Ιστορικό Συστήματος", null, panel, null);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		verticalBox = Box.createVerticalBox();
		sl_panel.putConstraint(SpringLayout.NORTH, verticalBox, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, verticalBox, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, verticalBox, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, verticalBox, -10, SpringLayout.EAST, panel);
		verticalBox.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(verticalBox);
		
		txtIntrotitle = new JTextField();
		txtIntrotitle.setMaximumSize(new Dimension(2147483647, 60));
		txtIntrotitle.setBorder(null);
		txtIntrotitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtIntrotitle.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 20));
		txtIntrotitle.setEditable(false);
		txtIntrotitle.setBackground(Color.WHITE);
		txtIntrotitle.setText("Lehman Laws of Softwrare Evolution");
		verticalBox.add(txtIntrotitle);
		txtIntrotitle.setColumns(1);
		
		verticalStrut = Box.createVerticalStrut(30);
		verticalStrut.setMinimumSize(new Dimension(0, 10));
		verticalStrut.setMaximumSize(new Dimension(32767, 60));
		verticalBox.add(verticalStrut);
		
		txtpnIntrotxt = new JTextPane();
		txtpnIntrotxt.setEditable(false);
		txtpnIntrotxt.setMaximumSize(new Dimension(2147483647, 600));
		txtpnIntrotxt.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		txtpnIntrotxt.setBackground(Color.WHITE);
		txtpnIntrotxt.setText("Οι αποφάσεις που λαμβάνουμε και ο προγραμματισμός της όλης διαδικασίας συντήρησης ενός συστήματος λογισμικού υποβοηθούνται σημαντικά από την κατανόηση του πως εξελίσσεται το λογισμικό με την πάροδο του "
				+ "χρόνου. Ενδιαφερόμαστε για τις αλλαγές που γίνονται, το μέγεθος του λογισμικού, την πολυπλοκότητά του, το ρυθμό αύξησης του μεγέθους, κ.α. Υπό ιδανικές συνθήκες, η εξέλιξη του λογισμικού δεν είναι "
				+ "ανεξέλεγκτη, γίνεται με ένα οργανωμένο τρόπο ο οποίος καθορίζεται από 2 αντικρουόμενες τάσεις που εξασφαλίζουν την ισορροπημένη εξέλιξη του λογισμικού. Η πρώτη (θετική) τάση αφορά σε "
				+ "δραστηριότητες που αυξάνουν τις λειτουργίες που προσφέρει το λογισμικό με σκοπό την ικανοποίηση των συνεχώς αυξανόμενων απαιτήσεων που έχουν οι χρήστες του λογισμικού. Η δεύτερη (αρνητική) "
				+ "τάση αφορά σε δραστηριότητες συντήρησης των λειτουργιών που προσφέρει ήδη το λογισμικό, οι οποίες κατ’ επέκταση περιορίζουν την ανεξέλεγκτη αύξηση των λειτουργιών του λογισμικού, που προστάζει η "
				+ "θετική τάση εξέλιξης του λογισμικού. Η ύπαρξη των 2 αυτών τάσεων που εξασφαλίζουν την ισορροπημένη εξέλιξη του λογισμικού παρατηρήθηκε για πρώτη φορά στις εμπειρικές μελέτες που έκανε "
				+ "ο Meir Lehman και οι συνεργάτες τη δεκαετία του 70 σε πραγματικά συστήματα λογισμικού. Οι μελέτες αυτές οδήγησαν στη διατύπωση ενός συνόλου κανόνων που περιγράφουν την εξέλιξη του λογισμικού οι "
				+ "οποίοι είναι γνωστοί ως Νόμοι του Lehman. Πιο συγκεκριμένα, οι Νόμοι του Lehman στην τρέχουσα μορφή τους και μετά από 40 χρόνια περαιτέρω έρευνας επιγραμματικά είναι: \n\n"
				+ " 1.Συνεχής Αλλαγή \n 2.Αυξανόμενη Πολυπλοκότητα\n 3.Αυτορυθμιζόμενη Εξέλιξη\n 4.Διατήρηση της Εργασιακής Σταθερότητας\n 5.Διατήρηση της Εξοικείωσης\n "
				+ "6.Συνεχής Αύξηση\n 7.Πτωτική Ποιότητα\n 8.Σύστημα Ανατροφοδότησης\n\n"
				+ "Το παρόν εργαλείο έχει ως στόχο την παρακολούθιση της εξέλιξης συστημάτων λογισμικού και τη διευκόλυνση της αποτίμισης του κατά πόσο το σύνολο των Νόμων που διατύπωσε"
				+ " ο Lehman ισχύει.");
		verticalBox.add(txtpnIntrotxt);
		
		verticalStrut_1 = Box.createVerticalStrut(10);
		verticalStrut_1.setMaximumSize(new Dimension(32767, 20));
		verticalStrut_1.setMinimumSize(new Dimension(0, 5));
		verticalBox.add(verticalStrut_1);
		
		btnNewfile = new JButton("Εισαγωγή νέου Ιστορικού Συστήματος Λογισμικού");
		btnNewfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(JFileChooser.APPROVE_OPTION==fc.showOpenDialog(frame)){
						File file=fc.getSelectedFile();
						String retStr[]=ctrl.addSoftHistory(file);
						if(retStr[1].equals("2"))
							addSoftHistory(retStr[0]);
						else
							JOptionPane.showMessageDialog(null, mErrors[Integer.parseInt(retStr[1])], "Σφάλμα", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		btnNewfile.setVerticalAlignment(SwingConstants.TOP);
		btnNewfile.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewfile.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		btnNewfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewfile.setBorder(UIManager.getBorder("Button.border"));
		btnNewfile.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnNewfile);
		
		verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);
		
		txtAuthor = new JTextField();
		txtAuthor.setBorder(null);
		txtAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAuthor.setText("by TXM Software");
		txtAuthor.setFont(new Font("DejaVu Sans", Font.ITALIC, 11));
		txtAuthor.setEditable(false);
		txtAuthor.setBackground(Color.WHITE);
		txtAuthor.setMaximumSize(new Dimension(2147483647, 20));
		verticalBox.add(txtAuthor);
		txtAuthor.setColumns(10);
	}
	
	private void addSoftHistory(String name){
		GuiSoftHistoryTab tmpSoftHistory=new GuiSoftHistoryTab(name,this);
		softHistoryTabs.put(name, new GuiSoftHistoryTab(name,this));
		tabbedPane.add(tmpSoftHistory.getMyName(), tmpSoftHistory);
	}
	
	public void removeSoftHistory(GuiSoftHistoryTab child){
		ctrl.removeSoftHistory(child.getMyName());
		softHistoryTabs.remove(child.getMyName());
		tabbedPane.remove(child);
	}
}
