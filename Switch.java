/**
* This Switch class is a subclass to superclass Device. In a networking sense,
* the role of a switch is to enable networks to function. The switch object contains a name,
* IP address, MAC address, and whether or not it is a parent of a switch.
* @author Andrew Dao and Palistha Karmacharya
*/

//disclude default constructors
//Constructor - super keyword

public class Switch extends Device {

   private boolean isParent;

/**
* default constructor
*/
   public Switch(){
   }

/**
* Constructor with deviceName, deviceIP, deviceMAC parameters calls the superclass Device constructor
*/
   public Switch(String deviceName, String deviceIP, String deviceMAC){
      super(deviceName, deviceIP, deviceMAC);
   }

  /**
* Constructor with deviceName, deviceIP, deviceMAC parameters calls the superclass Device constructor
* includes isParent parameter paticular to the subclass Switch
*/
   public Switch(String deviceName, String deviceIP, String deviceMAC, boolean isParent){
      super(deviceName, deviceIP, deviceMAC);
      this.isParent = isParent;
   }

/*Returns if it is a parent to a virtual switch
* @return boolean, true if it is a parent to a virtual switch and
* false if it is not a parent to a virtual switch
*/
   public boolean getIsParent(){
      return this.isParent;
   }

/**Sets if it is a parent to a virtual switch
* @param isParent, the boolean to be set
*/
   public void setIsParent(boolean isParent) {
      this.isParent = isParent;
   }

 /**Prints the device name, IP address, MAC address, and if it is a parent switch
* @return String s, formatted report of Swtich
*/
   public String toString(){
      String s = "";
      s = "Server Name: " + super.getDeviceName() + "\n" +
          "Server IP: " + super.getDeviceIP() + "\n" +
          "Server MAC: " + super.getDeviceMAC() + "\n" +
          "Parent? " + getIsParent();
      return s;
   }


}

