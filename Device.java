 /**
* This Device class is an abstract class which represents one of many devices that exists on any network.
* Each device contains a name, an IP address, and a MAC address. For the purpose of this project,
* there are two objects that will be derived from this class: switch and server.
* @author Andrew Dao and Palistha Karmacharya
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Device {

   private String deviceName;
   private String deviceIP;
   private String deviceMAC;

 /**
 * Default constructor
*/
   public Device(){
   }

 /**
 * Constructor with deviceName, deviceIP, deviceMAC parameters
*/
   public Device(String deviceName, String deviceIP, String deviceMAC){
      this.deviceName = deviceName;
      this.deviceIP = deviceIP;
      this.deviceMAC = deviceMAC;
   }

 /**Validates the device name
* @param String deviceName, device name to be validated cannot be null or empty
   * @return true if the condition above is true and return false if the condition above is false

*/
   public static boolean validateDeviceName(String deviceName) {
      if(deviceName != null && !deviceName.trim().isEmpty())
         return true;
      else
         return false;
   }

/**Sets the device name
* @param deviceName, the device name to be set
*/
   public void setDeviceName(String deviceName) {
      this.deviceName = deviceName;
   }

  /**
* Returns the device name
* @return deviceName, the device name
*/
   public String getDeviceName() {
      return this.deviceName;
   }

/**Validates the device IP address
* @param String deviceIP, device IP address to be validated
* must be in the following format 0.0.0.0.0
* @return true if the condition above is true and return false if the condition above is false
*/
   public static boolean validateDeviceIP(String deviceIP) {

      Pattern pattern = Pattern.compile("\\d{3}.\\d{3}.\\d{3}.\\d{3}");

      Matcher matcher = pattern.matcher(deviceIP);

      if (matcher.matches())
         return true;
      else
         return false;
   }

   /**
* Returns the device IP address
* @return deviceIP, the IP address
*/
   public String getDeviceIP() {
      return this.deviceIP;
   }

/**Sets the device IP address
* @param deviceIP, the device IP address to be set
*/
   public void setDeviceIP(String deviceIP) {
      this.deviceIP = deviceIP;
   }

 /**Validates the device MAC address
* @param String deviceMAC, device MAC address to be validated
* must be in the following format 00-00-00-00-00-00
* @return true if the condition above is true and return false if the condition above is false
*/
   public static boolean validateDeviceMAC(String deviceMAC) {

      Pattern pattern = Pattern.compile("^([0-9A-Fa-f]{2}[-]){5}([0-9A-Fa-f]{2})$");
	  //"^([0-9A-Fa-f]{2}[-]){6})$")
      Matcher matcher = pattern.matcher(deviceMAC);

      if (matcher.matches())
         return true;
      else
         return false;
   }

 /**
* Returns the device MAC address
* @return deviceMAC, the MC address
*/
   public String getDeviceMAC() {
      return this.deviceMAC;
   }

 /**Sets the device MAC address
* @param deviceMAC, the device MAC address to be set
*/
   public void setDeviceMAC(String deviceMAC) {
      this.deviceMAC = deviceMAC;
   }

/**Abstract method
* subclasses print a String
* @return String s, formatted report paticular to the subclass
*/
   public abstract String toString();

}//end device class

