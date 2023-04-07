import java.util.Calendar;

//Kadir PEKDEMÝR 150121069

public class SmartLight extends SmartObject implements LocationControl,Programmable{
	private boolean hasLightTurned;
	private Calendar programTime;
	private boolean programAction;
	
	
	public SmartLight(String alias,String macId) {
		setAlias(alias);
		setMacId(macId);
		
	}
	public void turnOnLight() {
		Calendar calendar = Calendar.getInstance();
		if(isConnectionStatus()) {
			if(!hasLightTurned) {
				System.out.println("Smart Light -"+ getAlias()+"Light has been already turned on");
			}
			else {
				System.out.println("Smart Light -"+ getAlias()+" Light is turned on now(Current time : " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)+")");
				hasLightTurned=true;
			}
		}
	}
	public void turnOffLight() {
		Calendar calendar = Calendar.getInstance();
		if(isConnectionStatus()) {
			if(hasLightTurned) {
				System.out.println("Smart Light -"+ getAlias()+"Light is turned off now (Current time : " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)+")");
				hasLightTurned=false;
			}
			else {
				System.out.println("Smart Light -"+ getAlias()+"Light has been already turned off");
			}
		}
		
	}
	@Override
	public boolean testObject() {
		if (controlConnection()) {
			System.out.println("Test is starting for SmartLight");
			SmartObjectToString();
			turnOnLight();
			turnOffLight();
			System.out.println("Test completed for SmartLight");
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
			if(hasLightTurned);
			hasLightTurned = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	
	@Override
	public void setTimer(int seconds) {
		if (controlConnection()) {
			this.programTime = Calendar.getInstance();
			if (hasLightTurned == true) {
				System.out.println("Smart light - " + getAlias() + " will be turned off "+ seconds +" seconds later!"
						+ "(Current time:"+ programTime.get(Calendar.HOUR) +":" + programTime.get(Calendar.MINUTE) +":"+ programTime.get(Calendar.SECOND) +")");
				
				programTime.add(Calendar.SECOND, seconds);
				programAction = false;
			}
			else
				System.out.println("Smart light - " + getAlias() + " will be turned on " + seconds + " seconds later!"
						+ "(Current time:"+ programTime.get(Calendar.HOUR) +":" + programTime.get(Calendar.MINUTE) +":"+ programTime.get(Calendar.SECOND) +")");
			    programTime.add(Calendar.SECOND, seconds);
				programAction = true;
			
		
		}
		
	}
	@Override
	public void cancelTimer() {
		if(controlConnection()) {
			programTime = null;
		}
		
	}
	
	@Override
	public void runProgram() {
		if (controlConnection()) {
			try {
				if (Calendar.getInstance().getTime().getSeconds() == this.programTime.getTime().getSeconds()) {
					if(programAction == true) {
						System.out.println("runProgram -> Smart Light - " + getAlias());
						turnOnLight();

					}
					else {
				    	System.out.println("runProgram -> Smart Light - " + getAlias());
				    	turnOffLight();
			       }
					
				}
			}
			catch (Exception e) {
				
			}
		}
	}
	@Override
	public void onLeave() {
		if(controlConnection()) {
			hasLightTurned = false;
			System.out.println("On Leave -> Smart Light - Living Room Light");
			turnOffLight();
		}
	
		
	}
	@Override
	public  void onCome() {
		if(controlConnection()) {
			hasLightTurned = true;
			System.out.println("On Come -> Smart Light - Living Room Light");
			turnOnLight();
		}
		
	}
	public boolean isHasLightTurned() {
		return hasLightTurned;
	}
	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
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
