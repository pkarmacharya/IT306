 /**
* The Virtual Switch object is a subclass of the switch class. 
* In a networking role, these specific types of switches allow users
* to connect other networking components together. This object contains a name and a MAC address
* which is inherited from its parent switch.
* @author Andrew Dao and Palistha Karmacharya
*/

//VirtualSwitch inherits deviceName and deviceMAC - no need to declare
//Constructor - create a constructor that accepts two parameters in super class
//or *set deviceMAC to null inside constructor?
//Include information for both devices?

public class VirtualSwitch extends Switch {

   private int numOfSwitches;

/**
* default constructor
*/
   public VirtualSwitch(){
      this.numOfSwitches = numOfSwitches;
   }

/* Returns the number of parent switches
* @return numOfSwitches,the number of parent switches to the virtual switch
*/
   public int getNumOfSwitches(){
      return this.numOfSwitches;
   }

/**Sets the number of parent switches
  * @param numOfSwitches, the number of parent switches to be set
 */
   public void setNumOfSwitches (int numOfSwitches) {
      this.numOfSwitches = numOfSwitches;
   }

/**Prints the number of virtual switches
* @return String s, formatted report of Switch
*/
   public  String toString(){
      String s = "";
      s = "Number of Virtual Switches: " + this.getNumOfSwitches();
      return s;
   }
}

