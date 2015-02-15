package txm;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

class DataSoftVersion implements Cloneable, Comparable<DataSoftVersion>{
	private int id;
	private LocalDate date;
	private int opAdded;
	private int opDeleted;
	private int opUpdated;
	private int dsAdded;
	private int dsDeleted;
	private int dsUpdated;
	
	private int totalOp=0;
	private int totalDs=0;
	private int changesOp=0;
	private int changesDs=0;
	private int maintAct=0;
	private int growthOp=0;
	private int growthDs=0;
	private float complexityOp=0;
	private float complexityDs=0;
	private float workRateOp=0;
	private float workRateDs=0;
		
	DataSoftVersion(int id, LocalDate date , int opAdded, int opDeleted, int opUpdated, int dsAdded, int dsDeleted, int dsUpdated){
		this.id=id;
		this.date=date;
		this.opAdded=opAdded;
		this.opDeleted=opDeleted;
		this.opUpdated=opUpdated;
		this.dsAdded=dsAdded;
		this.dsDeleted=dsDeleted;
		this.dsUpdated=dsUpdated;
		this.maintAct=this.opDeleted+this.opUpdated+this.dsDeleted+this.dsUpdated;
		this.changesOp=this.opAdded+this.opDeleted+this.opUpdated;
		this.changesDs=this.dsAdded+this.dsDeleted+this.dsUpdated;
	}
	
	public void generateExtraDataFirst(int initOp, int initDs){
		totalOp=initOp;
		totalDs=initDs;
	}
	
	public void generateExtraData(int prevTotalOp, int prevTotalDs, LocalDate prevDate){
		try{
			totalOp=prevTotalOp+opAdded-opDeleted;
			totalDs=prevTotalDs+dsAdded-dsDeleted;
			growthOp=totalOp-prevTotalOp;
			growthDs=totalDs-prevTotalDs;
			if(opAdded!=0)
				complexityOp=(float)(opUpdated+opDeleted)/opAdded;
			else
				complexityOp=0;
			if(dsAdded!=0)
				complexityDs=(float)(dsUpdated+dsDeleted)/dsAdded;
			else
				complexityDs=0;
			int daysPassed=(int) ChronoUnit.DAYS.between(prevDate, date);
			workRateOp=(float)(opAdded+opUpdated+opDeleted)/daysPassed;
			workRateDs=(float)(dsAdded+dsUpdated+dsDeleted)/daysPassed;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error in generateExtraData");
		}
	}
	
	public int getDataInt(String vName){
		if(vName.equals("id"))
			return id;
		else if(vName.equals("opAdded"))
			return opAdded;
		else if(vName.equals("opDeleted"))
			return opDeleted;
		else if(vName.equals("opUpdated"))
			return opUpdated;
		else if(vName.equals("dsAdded"))
			return dsAdded;
		else if(vName.equals("dsDeleted"))
			return dsDeleted;
		else if(vName.equals("dsUpdated"))
			return dsUpdated;
		else if(vName.equals("totalOp"))
			return totalOp;
		else if(vName.equals("totalDs"))
			return totalDs;
		else if(vName.equals("growthOp"))
			return growthOp;
		else if(vName.equals("growthDs"))
			return growthDs;
		else if(vName.equals("maintAct"))
			return maintAct;
		else if(vName.equals("changesOp"))
			return changesOp;
		else if(vName.equals("changesDs"))
			return changesDs;
		else
			return 0;
	}
	
	public LocalDate getDate(){
		return date;
	}
	
	public double getDataFloat(String vName){
		if(vName.equals("complexityOp"))
			return complexityOp;
		else if(vName.equals("complexityDs"))
			return complexityDs;
		else if(vName.equals("workRateOp"))
			return workRateOp;
		else if(vName.equals("workRateDs"))
			return workRateDs;
		else
			return 0;
	}

	public int compareTo(DataSoftVersion o) {
		return getDate().compareTo(o.getDate());
	}	
}
