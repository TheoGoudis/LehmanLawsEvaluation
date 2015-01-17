package txm;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.border.EmptyBorder;

class GuiFile extends JPanel {
	/**
	 */
	private static final long serialVersionUID = -1851019437987088199L;
	private JScrollPane scrollPane;
	private JTextPane txtpnFile;
	private String txtFile="";
	/**
	 * Create the panel.
	 */
	public GuiFile(GuiAppWindow appWindow, String name) {
		
		try {
			Scanner input=new Scanner(appWindow.ctrl.getSoftHistory(name).getFile());
			while(input.hasNextLine())
				txtFile+=input.nextLine()+"\n";
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1920, 1080);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		txtpnFile = new JTextPane();
		txtpnFile.setBorder(new EmptyBorder(20, 20, 20, 20));
		txtpnFile.setEditable(false);
		txtpnFile.setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		txtpnFile.setText(txtFile);
		scrollPane.setViewportView(txtpnFile);

	}

}
