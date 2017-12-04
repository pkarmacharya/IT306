/**
* This Network class is used to model one particular network.The attributes of this class include whether or not
* it supports WiFi and VLAN capabilities.It also includes the number of VLANs (up to maximum number of 500)
* on the network and whether or not it has an uninterruptable power supply.
* @author Andrew Dao and Palistha Karmacharya
*/
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Network {
   public static final int MAX_NUM_VLAN = 500;
   public static final int MIN_NUM_VLAN = 0;
   private String networkID;
   private boolean supportsWifi;
   private boolean supportsVLAN;
   private boolean hasUPS;
   private int numOfVLAN;
   private static int totalNetworks;

   private LinkedList<Device> device;

  /**
 * Default constructor
*/
   public Network(){
      this.device = new LinkedList<Device>();
      totalNetworks++;
   }

 /**
 * Constructor with supportsWifi, supportsVLAN, numOFVLAN, and hasUPS parameters
*/
   public Network(String networkID, boolean supportsWifi, boolean supportsVLAN, int numOfVLAN, boolean hasUPS){
      this();
      this.supportsWifi = supportsWifi;
      this.supportsVLAN = supportsVLAN;
      this.numOfVLAN = numOfVLAN;
      this.hasUPS = hasUPS;
      this.numOfVLAN = numOfVLAN;
      this.networkID = networkID;

   }

   /*
   public void addDevice(Device newDevice)
   {

      device.add(newDevice);
   if(TOTAL_DEVICES==NUM_DEVICES)
      {
         NUM_DEVICES= NUM_DEVICES*2;
         LinkedList<Device> device2 = new LinkedList[NUM_DEVICES];
         for(int i=0; i<device.length; i++)
         {
            divice2[i]= device[i];
         }
         device = device2;
         device[TOTAL_DEVICES]= newDevice;
         TOTAL_DEVICES++;
      }
      else
      {

         device[TOTAL_DEVICES]= newDevice;
         TOTAL_DEVICES++;
      }

   }
   */

/**
* Returns if it is wifi supported
* @return boolean, true if it is wifi supported and
* false if it is not wifi supported
*/
   public String getNetworkID(){
   	return this.networkID;
   }//end method

   public boolean getSupportsWifi() {
      return this.supportsWifi;
   }

/**Sets if it is wifi supported
* @param supportsWifi, the boolean value to be set
*/
   public void setSupportsWifi(boolean supportsWifi) {
      this.supportsWifi = supportsWifi;
   }

 /**
* Returns if it supports VLANs
* @return boolean, true if it supports VLANs and
* false if it does not support VLANs
*/
   public boolean getSupportsVLAN() {
      return this.supportsVLAN;
   }

 /**Sets if it supports VLANs
* @param supportsVLAN, the boolean value to be set
*/
   public void setSupportsVLAN(boolean supportsVLAN) {
      this.supportsVLAN = supportsVLAN;
   }

   /*
   public boolean validateNumOfVLAN(int numOfVLAN){
      if (countNumOfVLAN + numOfVLAN > MAX_NUM_VLAN)
         return true;
      else
         return false;
   }

   public int getNumOfVLANLeft() {
      return MAX_NUM_VLAN - countNumOfVLAN;
   }
   */

  /**Sets the number of VLANs
  * @param numOfVLAN, the number of VLANS on the network to be set
  */
   public void setNumOfVLAN(int numOfVLAN) {
      this.numOfVLAN = numOfVLAN;
   }


/* Returns the number of VLANs
* @return numOfVLAN, number of VLANs on the network
*/
   public int getNumOfVLAN() {
      return this.numOfVLAN;
   }

  /**
* Returns if it has an uninterruptable power supply (UPS)
* @return boolean, true if it does have an UPS and
* false if it does not have an UPS
*/
   public boolean getHasUPS() {
      return this.hasUPS;
   }

 /**Sets if it has UPS
  * @param hasUPS, the boolean value to be set
  */
   public void setHasUPS(boolean hasUPS) {
      this.hasUPS = hasUPS;
   }

   public LinkedList<Device> getDeviceList(){
   	return this.device;
   }//end method

   /**Validates the network ID
   * @param String network ID to be validated
   * must be in the following format 0.0.0.0.0
   * @return true if the condition above is true and return false if the condition above is false
   */
   public static boolean validateDeviceIP(String networkID) {

   	Pattern pattern = Pattern.compile("\\d{3}.\\d{3}.\\d{3}.\\d{3}");

   	Matcher matcher = pattern.matcher(networkID);

	if (matcher.matches())
		return true;
	else
		return false;
   }//end method


/**Prints if it supports Wifi, VLAN, the number of VLANs, and if has an UPS
* @return String s, formatted report of Network
*/
   public  String toString(){
      String s = "";
      s = "Network ID: " + getNetworkID() + "\n" +
          "Supports Wifi?: " + getSupportsWifi() + "\n" +
          "Supports VLAN?: " + getSupportsVLAN() + "\n" +
          "Has Uninterruptable Power Supply?: " + getHasUPS() + "\n" +
          "Number of VLAN: " + getNumOfVLAN();

      return s;
   }//end method

}//end network class
