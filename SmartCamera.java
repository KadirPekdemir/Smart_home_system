import java.util.Calendar;

//Kadir PEKDEMÝR 150121069

public class SmartCamera extends SmartObject implements MotionControl,Comparable<SmartCamera> {
	
	private boolean status;
	private int batteryLife;
	private boolean nightVision;
	
	
	
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		setAlias(alias);
		setMacId(macId);
		this.nightVision= nightVision;
		this.batteryLife= batteryLife;
	}
	public void recordOn(boolean isDay) {
		Calendar calendar = Calendar.getInstance();
		if(controlConnection()) {
			if(isDay) {
				if(status) 
					System.out.println("Smart Camera - " + getAlias() + "has been already turned on");
				else
					System.out.println("Smart Camera -" + getAlias() + " is turned on now");
				status = true;
			}
			else {
				if(!nightVision)
					System.out.println("Sorry! Smart Camera -" + getAlias() + "does not have night vision feature.");
				else
					if(status)
						System.out.println("Smart Camera - " + getAlias() + "has been already turned on");
					else
						System.out.println("Smart Camera -" + getAlias() + " is turned on now");
				status = true;
			}
		
		}
		
	}
	public void recordOff() {
		Calendar calendar = Calendar.getInstance();
		if(controlConnection()) {
			if(status) {
				System.out.println("Smart Camera -" + getAlias() + " is turned off now");
				status = false;
			}
			else {
				System.out.println("Smart Camera - " + getAlias() + "has been already turned off");
			}
		}
		
	}
	@Override
	public boolean testObject() {
		if (isConnectionStatus()) {
			System.out.println("Test is starting for SmartCamera ");
			SmartObjectToString();
			System.out.println("Test is starting for SmartCamera day time");
			recordOn(true);
			recordOff();
			System.out.println("Test is starting for SmartCamera night time");
			recordOn(false);
			recordOff();
			System.out.println("Test completed for SmartCamera");
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
	
	
	public boolean controlMotion(boolean hasMotian,boolean isDay) {
		if(hasMotian) {
			System.out.println("Motion detected!");
		}
		else {
			System.out.println("Motion not detected!");
		}
		if (isDay) {
			recordOn(isDay);
			return true;
		}
		else {
			if(nightVision) 
				recordOn(isDay);
			    return true;
		}
	}
	public int compareTo(SmartCamera smartCamera) {
		if (this.batteryLife > smartCamera.batteryLife) {
			return 1;
		}
		else if (this.batteryLife == smartCamera.batteryLife) {
			return 0;
		}
		else {
			return -1;
		}
		
	}
	@Override
	public String toString() {
		return "SmartCamera ->" + getAlias() +"'" + "s battery life is " + this.batteryLife + " status is recording";
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getBatteryLife() {
		return batteryLife;
	}
	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}
	public boolean isNightVision() {
		return nightVision;
	}
	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
	
	
	
	

}
