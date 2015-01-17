package txm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

class CInterface {
	
	private CtrlParser cParser=new CtrlParser();
	private Map<String,DataSoftHistory> softHistories=new HashMap<String,DataSoftHistory>();
	
	public String[] addSoftHistory(File file) {
		
		DataSoftHistory tmpSoftHistory=cParser.parseFile(file);
		if(tmpSoftHistory.getApproveOption()==2){
			if(softHistories.containsKey(tmpSoftHistory.getName()))
				tmpSoftHistory.putApproveOption(1);
			else
				softHistories.put(tmpSoftHistory.getName(), tmpSoftHistory);
		}
		
		return new String[]{tmpSoftHistory.getName(), String.valueOf(tmpSoftHistory.getApproveOption())} ;
	}
	
	public void removeSoftHistory(String name){
		softHistories.remove(name);
	}
	
	public DataSoftHistory getSoftHistory(String name) {
		return softHistories.get(name);
	}
}
