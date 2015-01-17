package txm;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class CtrlParser {
	
	public DataSoftHistory parseFile(File file){
		DataSoftHistory SoftHistory=new DataSoftHistory();
		
		ArrayList<DataSoftVersion> versions=new ArrayList<DataSoftVersion>();
		
		if(!file.getAbsolutePath().endsWith(".txt"))							//check if file ends with .txt
			return SoftHistory;
		
		ArrayList<String> lines=new ArrayList<String>(lineSplitter(file));		//get text lines from file
		if(lines.size()<5)														//check if there are more than 4 lines
			return SoftHistory;
		
		String[] startData=new String[3];										//name, initOp, initDs
		if((checkFirstFourLines(lines,startData))==0)							//check first four lines for data model acceptance and get the name, initOp, initDs values
			return SoftHistory;
		
		try{
			LocalDate date = null;
			String[] line;
			for(int i=4;i<lines.size();i++){
				line=lines.get(i).split("\\;");
				if(line.length!=8)
					return SoftHistory;

				String[] dline=line[1].split("/");
				dline=line[1].split("/");
				date=LocalDate.of(Integer.parseInt(dline[2]), Integer.parseInt(dline[1]), Integer.parseInt(dline[0]));
				
				versions.add(new DataSoftVersion(Integer.parseInt(line[0]),
												date,
												Integer.parseInt(line[2]), 
												Integer.parseInt(line[3]), 
												Integer.parseInt(line[4]), 
												Integer.parseInt(line[5]), 
												Integer.parseInt(line[6]), 
												Integer.parseInt(line[7])));
			}
			Collections.sort(versions);
			for(int i=0; i<versions.size()-1;i++){
				if(versions.get(i).getDate().isEqual(versions.get(i+1).getDate()))
					return SoftHistory;
			}
			
			SoftHistory.putSoftHistoryData(file,
					startData[0],
					Integer.valueOf(startData[1]),
					Integer.valueOf(startData[2]),
					versions);
			SoftHistory.putApproveOption(2);
			
		} catch(NumberFormatException e){
			SoftHistory.putApproveOption(0);
			return SoftHistory;
		}
		return SoftHistory;
	}
	
	private ArrayList<String> lineSplitter(File file){
		ArrayList<String> tmpList=new ArrayList<String>();
		
		try {
			Scanner input=new Scanner(file);
			while(input.hasNextLine())
				tmpList.add(input.nextLine());
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return tmpList;
	}
	
	private int checkFirstFourLines(ArrayList<String> lines, String[] startData){
		String[] line;
		
		line=lines.get(0).split("\\;");
		if((line.length!=2)||!(line[0].equals("Name")))
			return 0;
		startData[0]=line[1];
		
		line=lines.get(1).split("\\;");
		if((line.length!=2)||!(line[0].equals("Initial Number of Operations")))
			return 0;
		startData[1]=line[1];
		
		line=lines.get(2).split("\\;");
		if((line.length!=2)||!(line[0].equals("Initial Number of Data Structures")))
			return 0;
		startData[2]=line[1];
		
		line=lines.get(3).split("\\;");
		if((line.length!=8)||!(line[0].equals("ID"))||
							!(line[1].equals("Date"))||
							!(line[2].equals("Operations Added"))||
							!(line[3].equals("Operations Deleted"))||
							!(line[4].equals("Operations Updated"))||
							!(line[5].equals("Data Structures Added"))||
							!(line[6].equals("Data Structures Deleted"))||
							!(line[7].equals("Data Structures Updated")))
			return 0;
		return 1;
	}
	
}
