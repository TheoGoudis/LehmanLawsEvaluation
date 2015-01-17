package txm;

import java.io.File;
import java.util.ArrayList;

class DataSoftHistory{
	private String[] choices={"Καμία Εκτίμηση","Ισχύει","Δεν Ισχύει"};
	private File file;
	private String name;								//software name
	private int initOp;									//initial number of operations
	private int initDs;									//initial number of data structures
	private String[]  lawComments=new String[8];
	private int[] lawChoices=new int[8];
	private ArrayList<DataSoftVersion> versions;
	private int approveOption;
		
	DataSoftHistory(){
		initialize();
	}

	private void initialize(){
		for(int i=0;i<8;i++){
			lawComments[i]="Κανένα σχόλιο.";
			lawChoices[i]=0;
		}
		approveOption=0;
		name="";
		initOp=0;
		initDs=0;
	}
	
	public void putSoftHistoryData(File file, String name, int initOp, int initDs, ArrayList<DataSoftVersion> versions){
		this.file=file;
		this.name=name;
		this.initOp=initOp;
		this.initDs=initDs;
		this.versions=new ArrayList<DataSoftVersion>(versions);
		generateExtraData();
	}
	
	private void generateExtraData(){
		versions.get(0).generateExtraDataFirst(initOp, initDs);
		for(int i=1;i<versions.size();i++)
			versions.get(i).generateExtraData(versions.get(i-1).getDataInt("totalOp"), versions.get(i-1).getDataInt("totalDs"), versions.get(i-1).getDate());
	}
	
	public void putLawEvaluation(int key, int lawChoice, String lawComment){
		lawChoices[key]=lawChoice;
		lawComments[key]=lawComment;
	}
	
	public String getLawChoice(int key){
		return choices[lawChoices[key]];
	}
	
	
	public String getLawComment(int key){
		return lawComments[key];
	}
	
	public void putUserInput(int[] choices, String[] comments){
		for(int i=0;i<8;i++){
			lawChoices[i]=choices[i];
			lawComments[i]=comments[i];
		}
	}
	
	public void putApproveOption(int approveOption){
		this.approveOption=approveOption;
	}
	
	public int getApproveOption(){
		return approveOption;
	}
	
	public String getName(){
		return name;
	}
	
	public File getFile(){
		return file;
	}
	
	public ArrayList<DataSoftVersion> getVersions(){
		return versions;
	}

	public String getReport() {
		String retStr=new String();
		retStr+="\n Όνομα Συστήματος : "+name+
				"\n\n Σχόλια και αποτιμήσεις για την επαλήθευση των νόμων του Lehman\n\n";
		for(int i=0;i<8;i++){
			retStr+="\nΝόμος "+String.valueOf(i+1)+"\n\t Αποτίμηση : "+choices[lawChoices[i]]+"\n\t Σχόλιο : "+lawComments[i];
		}
		return retStr;
	}
	
}
