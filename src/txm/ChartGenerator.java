package txm;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

class ChartGenerator {
	
	GuiAppWindow appWindow;
	
	public ChartGenerator(GuiAppWindow appWindow, String name) {
		this.appWindow=appWindow;
	}

	public ArrayList<JFreeChart> getCharts(int law, String name){
		switch(law){
		case 1:
			return law1(appWindow.ctrl,name);
		case 2:
			return law2(appWindow.ctrl,name);
		case 3:
			return law35(appWindow.ctrl,name);
		case 4:
			return law4(appWindow.ctrl,name);
		case 5:
			return law35(appWindow.ctrl,name);
		case 6:
			return law6(appWindow.ctrl,name);
		case 7:
			return law7(appWindow.ctrl,name);
		case 8:
			return law8(appWindow.ctrl,name);
		default:
			return law1(appWindow.ctrl,name);
		}
	}
	
	private ArrayList<JFreeChart> law1(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		/* 1-bar changesOp / id
		 * 2-bar changesDs / id
		 * 3-bar plhthos ekdosewn	/ etos
		 */
		DefaultCategoryDataset objDataset1= new DefaultCategoryDataset();
		DefaultCategoryDataset objDataset2= new DefaultCategoryDataset();
		DataSoftVersion version = null;
		for(int i=0; i<ctrl.getSoftHistory(name).getVersions().size();i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			objDataset1.setValue(version.getDataInt("changesOp"),"",String.valueOf(version.getDataInt("id")));
			objDataset2.setValue(version.getDataInt("changesDs"),"",String.valueOf(version.getDataInt("id")));
		}
		charts.add(makeBarChart("Πλήθος αλλαγών λειτουργιών","Πλήθος αλλαγών","Έκδοση",objDataset1));
		charts.add(makeBarChart("Πλήθος αλλαγών δομών δεδομένων","Πλήθος αλλαγών","Έκδοση",objDataset2));
		
		objDataset1= new DefaultCategoryDataset();
		int size=ctrl.getSoftHistory(name).getVersions().size();
		int years[]=new int[size];
		int i=0;
		while(i<size){
			years[i]=ctrl.getSoftHistory(name).getVersions().get(i).getDate().getYear();
			i++;
		}
		i=0;
		int v=1;
		while(i<size-1){
			if(years[i]==years[i+1]){
				v++;
			}
			else{
				objDataset1.setValue(v, "", String.valueOf(years[i]));
				v=1;
			}
			i++;
		}
		objDataset1.setValue(v, "", String.valueOf(years[i]));
		charts.add(makeBarChart("Πλήθος Εκδόσεων ανα Έτος","Πλήθος Εκδόσεων","Έτος",objDataset1));
		return charts;
	}
	
	private ArrayList<JFreeChart> law2(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		/* 1-line complexityOp / id
		 * 2-line complexityDs / id
		 * 3-bar  maintAct	/ id
		 */
		DefaultCategoryDataset objDataset1= new DefaultCategoryDataset();
		DefaultCategoryDataset objDataset2= new DefaultCategoryDataset();
		DefaultCategoryDataset objDataset3= new DefaultCategoryDataset();
		DataSoftVersion version = null;
		for(int i=0; i<ctrl.getSoftHistory(name).getVersions().size();i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			objDataset1.setValue(version.getDataFloat("complexityOp"),"",String.valueOf(version.getDataInt("id")));
			objDataset2.setValue(version.getDataFloat("complexityDs"),"",String.valueOf(version.getDataInt("id")));
			objDataset3.setValue(version.getDataInt("maintAct"),"",String.valueOf(version.getDataInt("id")));
		}
		charts.add(makeLineChart("Πολυπλοκότητα λειτουργιών","Πολυπλοκότητα","Έκδοση",objDataset1,false));
		charts.add(makeLineChart("Πολυπλοκότητα δομών δεδομένων","Πολυπλοκότητα","Έκδοση",objDataset2,false));
		charts.add(makeBarChart("Πλήθος δραστηριοτήτων συντήρησης","Πλήθος δραστηριοτήτων","Έκδοση",objDataset3));
		return charts;
	}
	
	private ArrayList<JFreeChart> law35(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		/* 1-line growthOp / id
		 * 2-line growthDs / id
		 */
		DefaultCategoryDataset objDataset1= new DefaultCategoryDataset();
		DefaultCategoryDataset objDataset2= new DefaultCategoryDataset();
		DataSoftVersion version = null;
		for(int i=0; i<ctrl.getSoftHistory(name).getVersions().size();i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			objDataset1.setValue(version.getDataInt("growthOp"),"",String.valueOf(version.getDataInt("id")));
			objDataset2.setValue(version.getDataInt("growthDs"),"",String.valueOf(version.getDataInt("id")));
		}
		charts.add(makeLineChart("Ρυθμός ανάπτυξης λειτουργιών","Ρυθμός ανάπτυξης","Έκδοση",objDataset1,false));
		charts.add(makeLineChart("Ρυθμός ανάπτυξης δομών δεδομένων","Ρυθμός ανάπτυξης","Έκδοση",objDataset2,false));
		return charts;
	}
	
	private ArrayList<JFreeChart> law4(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		/* 1-line workRateOp / id
		 * 2-line workRateDs / id
		 */
		DefaultCategoryDataset objDataset1= new DefaultCategoryDataset();
		DefaultCategoryDataset objDataset2= new DefaultCategoryDataset();
		DataSoftVersion version = null;
		for(int i=0; i<ctrl.getSoftHistory(name).getVersions().size();i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			objDataset1.setValue(version.getDataFloat("workRateOp"),"",String.valueOf(version.getDataInt("id")));
			objDataset2.setValue(version.getDataFloat("workRateDs"),"",String.valueOf(version.getDataInt("id")));
		}
		charts.add(makeLineChart("Ρυθμός εργασιών (λειτουργίες)","Ρυθμός εργασιών","Έκδοση",objDataset1,false));
		charts.add(makeLineChart("Ρυθμός εργασιών (δομές δεδομένων)","Ρυθμός εργασιών","Έκδοση",objDataset2,false));
		return charts;
	}
	
	private ArrayList<JFreeChart> law6(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		/* 1-line totalOp / id
		 * 2-line totalDs / id
		 */
		DefaultCategoryDataset objDataset1= new DefaultCategoryDataset();
		DefaultCategoryDataset objDataset2= new DefaultCategoryDataset();
		DataSoftVersion version = null;
		for(int i=0; i<ctrl.getSoftHistory(name).getVersions().size();i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			objDataset1.setValue(version.getDataInt("totalOp"),"",String.valueOf(version.getDataInt("id")));
			objDataset2.setValue(version.getDataInt("totalDs"),"",String.valueOf(version.getDataInt("id")));
		}
		charts.add(makeLineChart("Πλήθος λειτουργιών","Πλήθος","Έκδοση",objDataset1,false));
		charts.add(makeLineChart("Πλήθος δομών δεδομένων","Πλήθος","Έκδοση",objDataset2,false));
		return charts;
	}
	
	private ArrayList<JFreeChart> law7(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		/*	law 2 + law 6
		 */
		charts.addAll(law2(ctrl,name));
		charts.addAll(law6(ctrl,name));
		return charts;
	}
	
	private ArrayList<JFreeChart> law8(CInterface ctrl, String name){
		ArrayList<JFreeChart> charts=new ArrayList<JFreeChart>();
		
		DefaultCategoryDataset objDataset1= new DefaultCategoryDataset();
		DataSoftVersion version = null;
		int size=ctrl.getSoftHistory(name).getVersions().size();
		for(int i=0; i<size;i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			objDataset1.setValue((float)version.getDataInt("totalOp"),"Πραγματικό",String.valueOf(version.getDataInt("id")));
		}
		
		
		float evaE[]=new float[size];
		evaE[0]=evaE[1]=(float)0;
		float actE[]=new float[size];
		actE[0]=(float)0;
		float actS[]=new float[size];		//actual totalOp
		float undS[]=new float[size];		//assisting 1/Σ(1/actS^2)
		float preS[]=new float[size];		//predicted totalOp
		
		for(int i=0; i<size;i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			actS[i]=(float)version.getDataInt("totalOp");
		}
		
		undS[1]=(1/(actS[0]*actS[0]));
		for(int i=2; i<size;i++){
			undS[i]=(1/(actS[i-1]*actS[i-1]))+undS[i-1];
		}
		
		
		for(int i=1; i<size;i++){
			actE[i]=(actS[i]-actS[1])/undS[i];
		}
		
		float tmp=0;
		for(int i=1; i<size;i++){
			tmp+=actE[i-1];
			evaE[i]=tmp/i;
		}
		
		
		preS[0]=actS[0];
		objDataset1.setValue(preS[0],"Εκτιμώμενο",String.valueOf(ctrl.getSoftHistory(name).getVersions().get(0).getDataInt("id")));
		for(int i=1; i<size;i++){
			version=ctrl.getSoftHistory(name).getVersions().get(i);
			preS[i]=actS[i-1]+(evaE[i]/(actS[i-1]*actS[i-1]));
			objDataset1.setValue(preS[i],"Εκτιμώμενο",String.valueOf(version.getDataInt("id")));
		}

		charts.add(makeLineChart("Πλήθος λειτουργιών","Πλήθος","Έκδοση",objDataset1,true));
		return charts;
	}

	private JFreeChart makeBarChart(String title, String verticalLabel, String horizontalLabel, DefaultCategoryDataset objDataset){
		JFreeChart objChart = ChartFactory.createBarChart(
					title,     //Chart title
					horizontalLabel,     //Domain axis label
					verticalLabel,         //Range axis label
					objDataset,         //Chart Data 
					PlotOrientation.VERTICAL, // orientation
					false,             // include legend?
					true,             // include tooltips?
					false             // include URLs?
			);
		return objChart;
	}
	
	private JFreeChart makeLineChart(String title, String verticalLabel, String horizontalLabel, DefaultCategoryDataset objDataset, Boolean legend){
		JFreeChart objChart = ChartFactory.createLineChart(
					title,
					horizontalLabel,
					verticalLabel,
					objDataset,
					PlotOrientation.VERTICAL,
					legend,
					true,
					false);
		return objChart;
	}
	
//	private JFreeChart makeLineChart(){	}
	
	
}
