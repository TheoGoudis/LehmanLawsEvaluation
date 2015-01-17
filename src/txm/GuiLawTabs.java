package txm;

import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;

class GuiLawTabs extends JTabbedPane {

	private static final long serialVersionUID = 7288598783835127854L;
	
	private int lawChoices[]={0,0,0,0,0,0,0,0};
	private String[] lawComments=new String[8];
	private GuiLawTab[] laws=new GuiLawTab[8];
	private GuiHelpLawInfo lawInfo=new GuiHelpLawInfo();
	private GuiAppWindow appWindow;
	String name;
	ChartGenerator cgen;
	
	public GuiLawTabs(GuiAppWindow appWindow, String name) {
		this.appWindow=appWindow;
		this.name=name;
		cgen=new ChartGenerator(appWindow, name);
		
		setTabPlacement(JTabbedPane.LEFT);
		setFont(new Font("DejaVu Sans", Font.PLAIN, 11));
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setBackground(Color.WHITE);
		
		for(int i=0;i<8;i++){
			laws[i]=new GuiLawTab(i+1,lawInfo.getLaws()[i],this);
			this.addTab((i+1)+"ος Νόμος",laws[i]);
		}
		
	}

	public void updateAll(){
		for(int i=0;i<8;i++){
			lawChoices[i]=laws[i].getChoice();
			lawComments[i]=laws[i].getComment();
		}
		updateChoice7();
		appWindow.ctrl.getSoftHistory(name).putUserInput(lawChoices, lawComments);
	}

	private void updateChoice7(){
		int i=0;
		if((lawChoices[1]!=0) && (lawChoices[5]!=0)){
			if((lawChoices[1]==1) && (lawChoices[5]==1))
				i=1;
			else
				i=2;
		}
		else
			i=0;
		lawChoices[6]=i;
		laws[6].putLaw7Eval(i);
	}
	
	public int[] getChoices(){
		return lawChoices;  
	}
}