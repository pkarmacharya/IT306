/**
* This Server class is a subclass to superclass Device. A server may be used for multiple purposes
* such as email, file storage, or web media. This object contains a name, IP address, MAC address, server type
* including email, file storage, or web.
* @author Andrew Dao and Palistha Karmacharya
*/

import java.util.Hashtable;

//disclude default constructors
//Constructor - super keyword
//Constructor without serverType?

public class Server extends Device {

   private String serverType;
   private enum serverTypes {EMAIL, FILESTORAGE, WEB};
   private Hashtable<String, String> ht = new Hashtable<String, String>();

 /**
 * Default constructor
*/
   public Server(){
   }

 /**
* Constructor with deviceName, deviceIP, deviceMAC parameters calls the superclass Device constructor
*/
   public Server(String deviceName, String deviceIP, String deviceMAC){
      super(deviceName, deviceIP, deviceMAC);
   }

  /**
* Constructor with deviceName, deviceIP, deviceMAC parameters calls the superclass Device constructor
* includes serverType parameter paticular to the subclass Server
*/
   public Server(String deviceName, String deviceIP, String deviceMAC, String serverType){
      super(deviceName, deviceIP, deviceMAC);
      this.serverType = serverType;
   }

 /**Validates the server type
* @param String serverType, server type must be e-mail, file storage, or web
* @return true if the condition above is true and return false if the condition above is false
*/
      //ENUM APPROACH
   public static boolean validateServerType(String serverType) {

      serverType.replace(" ","");
      serverType.replace("-","");
      serverType.toUpperCase();

      for (serverTypes c : serverTypes.values()) {
         if (c.name().equalsIgnoreCase(serverType)) return true;
      }

      return false;
   }//end validate method

/**Sets the server type
* @param serverType, the server type to be set
*/
   public void setServerType(String serverType) {

      serverType.replace(" ","");
      serverType.replace("-","");
      serverType.toUpperCase();

      this.serverType = serverType;
   }

  //ORIGINAL APPROACH
  /*
   public boolean validateServerType(String serverType) {

      serverType.replace(" ","");
      serverType.replace("-","");


      if ((serverType.equalsIgnoreCase("email")) || (serverType.equalsIgnoreCase("filestorage")) || (serverType.equalsIgnoreCase("web")))
         return true;
      else
         return false;
   }

   public void setServerType(String serverType) {
      this.serverType = serverType;
   }
   */

   //HASHTABLE APPROACH
   /*
   public boolean validateServerType(String serverType) {
      try{
         int validateClientID = Integer.parseInt(serverType);
         if (validateClientID == 1 || validateClientID == 2 || validateClientID == 3)
            return true;
         else
            return false;
      }catch (NumberFormatException ex) {
         return false;
      }
   }

   public void setServerType(String serverType) {
      ht.put("1", "E-mail");
      ht.put("2", "File Storage");
      ht.put("3", "Web");
      this.serverType = ht.get(serverType);
   }
   */

/*Returns the server type
* @return serverType, the server type is e-mail, file storage, or web
*/
   public String getServerType() {
      return this.serverType;
   }


 /**Prints the device name, IP address, MAC address, and server type
* @return String s, formatted report of Server
*/
   public  String toString(){
      String s = "";
      s = "Server Name: " + super.getDeviceName() + "\n" +
          "Server IP: " + super.getDeviceIP() + "\n" +
          "Server MAC: " + super.getDeviceMAC() + "\n" +
          "Server Type: " + getServerType();

      return s;
   }


}

