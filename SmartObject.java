
//Kadir PEKDEMÝR 150121069


public abstract class SmartObject {
	private String alias,macId,IP;
	private boolean connectionStatus;
	 
	public SmartObject() {
			
	}
	public boolean connect(String IP) {
		this.IP=IP;
		System.out.println(this.alias + " connection established");
		return connectionStatus = true;
		
	}
	public boolean disconnect() {
      return connectionStatus = false;
	}
	public void SmartObjectToString() {
		System.out.println("This is SmartCamera device " +this.alias +"\n"+"	MacId: "+ this.macId+"\n"+"	IP: " + this.IP);
	}
	public boolean controlConnection() {
		if(!(connectionStatus)) {
			System.out.println("This device is not connected. SmartCamera ->" + this.alias);
			return false;
		}
		return true;
		
	}
	public abstract boolean testObject();
	public abstract boolean shutDownObject();
		
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public boolean isConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
}
