import java.util.ArrayList;
import java.util.Collections;
//Kadir PEKDEMÝR 150121069


public class SmartHome {
	 private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>();
		
	public SmartHome() {
		
	}
	public boolean addSmartObject(SmartObject smartObject) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding new SmartObject");
		System.out.println("--------------------------------------------------------------------------");
		smartObjectList.add(smartObject);
		int a = 99;
		a += smartObjectList.size();
		smartObject.connect("10.0.0."+a);
		smartObject.testObject();
		return true;
	
		 
	}
	public boolean removeSmartObject(SmartObject smartObject) {
		return smartObjectList.remove(smartObject);
		
	}
	public void controlLocation(boolean onCome) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LocationControl : OnCome");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i <smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof LocationControl) {
				
				if(onCome) {
					((LocationControl) smartObjectList.get(i)).onCome();
				}
				else {
					((LocationControl) smartObjectList.get(i)).onLeave();
				}	
			}
		}
	}
	public void controlMotion(boolean hasMotion,boolean isDay) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("MotionControl: HasMotion, isDay");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof MotionControl) {
				((MotionControl) smartObjectList.get(i)).controlMotion(hasMotion,isDay);
			}
		}
	}
	public void controlProgrammable() {
		System.out.println("---------------------------------------------------------------------------\n"
				+ "---------------------------------------------------------------------------\n"
				+ "Programmable: runProgram\n"
				+ "---------------------------------------------------------------------------");
		for ( int i = 0; i < smartObjectList.size(); i++) {
			   if (smartObjectList.get(i) instanceof Programmable) {
				 
				   ((Programmable) smartObjectList.get(i)).runProgram();
					
				}
		   }
		
	}
	public void controlTimer (int seconds) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = " + seconds + "seconds");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Programmable) {
				if(seconds == 0) {
					((Programmable) smartObjectList.get(i)).cancelTimer();
				}
				else {
					((Programmable) smartObjectList.get(i)).setTimer(seconds);
				}
			}
		}
	}
	public void controlTimerRandomly() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = 0, 5 or 10 seconds randomly");
		System.out.println("--------------------------------------------------------------------------");
		
		for (int i = 0; i < smartObjectList.size(); i++) {
			int a = 5*(int)(Math.random()*3);
			if(smartObjectList.get(i) instanceof Programmable) {
				if(a == 0) {
					((Programmable) smartObjectList.get(i)).cancelTimer();
				}
				else {
					((Programmable) smartObjectList.get(i)).setTimer(a);
				}
			}
		}
	} 
	public void sortCameras() {
		ArrayList<SmartCamera> smartCamera = new ArrayList<SmartCamera>();
		
		for (int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Comparable) {
				smartCamera.add(((SmartCamera)smartObjectList.get(i)));
			}
		}
		Collections.sort(smartCamera);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("--------------------------------------------------------------------------");
		for (SmartCamera str : smartCamera) {
			System.out.println(str);
		}
	}
	
	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}
	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}

}
