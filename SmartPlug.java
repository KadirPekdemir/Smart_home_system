import java.util.Calendar;

//Kadir PEKDEMÝR 150121069

public class SmartPlug extends SmartObject implements Programmable{
	private boolean status;
	private Calendar programTime;
	private boolean programAction;
	
	public SmartPlug(String alias, String macId) {
		setAlias(alias);
		setMacId(macId);
	}
	public void turnOn() {
		Calendar calendar = Calendar.getInstance();
		if(controlConnection()) {
			if (status) {
				System.out.println("Smart Plug -"+ getAlias()+" has been already turned on");
			}
			else {
				System.out.println("Smart Plug -"+ getAlias()+" is turned on now(Current time : " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)+")");
				status = true;
			}
		}
	}
	public  void turnOff() {
		Calendar calendar = Calendar.getInstance();
		if(isConnectionStatus()) {
			if(status) {
				System.out.println("Smart Plug -"+ getAlias()+" is turned off now(Current time : " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)+")");
				status=false;
			}
			else {
				System.out.println("Smart Plug -"+ getAlias()+" has been already turned off");
			}
		}
	}
	@Override
	public boolean testObject() {
		if (controlConnection()) {
			System.out.println("Test is starting for SmartPlug");
			SmartObjectToString();
			turnOn();
			turnOff();
			System.out.println("Test completed for SmartPlug");
			return true;
		}
		else {
		   return false;	
		}
		
	}
	@Override
	public boolean shutDownObject() {
		if(controlConnection()) {
			SmartObjectToString();
			if(status);
			status = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setTimer(int seconds) {
		if (controlConnection()) {
			this.programTime = Calendar.getInstance();
			if (status == true) {
				System.out.println("Smart Plug - " + getAlias() + " will be turned off "+ seconds +" seconds later!"
						+ "(Current time:"+ programTime.get(Calendar.HOUR) +":" + programTime.get(Calendar.MINUTE) +":"+ programTime.get(Calendar.SECOND) +")");
				
				programTime.add(Calendar.SECOND, seconds);
				programAction = true;
			}
			else
				System.out.println("Smart Plug - " + getAlias() + " will be turned on " + seconds + " seconds later!"
						+ "(Current time:"+ programTime.get(Calendar.HOUR) +":" + programTime.get(Calendar.MINUTE) +":"+ programTime.get(Calendar.SECOND) +")");
			    programTime.add(Calendar.SECOND, seconds);
				programAction = false;
			
		}
	}
	public void cancelTimer() {
		if(controlConnection()) {
			programTime = null;
		}
	}
	public void runProgram() {
		if (controlConnection()) {
			try {
				if (Calendar.getInstance().getTime().getSeconds() == this.programTime.getTime().getSeconds()) {
					if(programAction == true) {
						System.out.println("runProgram -> Smart Plug - " + getAlias());
						turnOn();

					}
					else {
				    	System.out.println("runProgram -> Smart Plug - " + getAlias());
				    	turnOff();
			       }
					
				}
			}
			catch (Exception e) {
				
			}
		}
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Calendar getProgramTime() {
		return programTime;
	}
	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}
	public boolean isProgramAction() {
		return programAction;
	}
	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}

	
	
	

}
