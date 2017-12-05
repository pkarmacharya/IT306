// NetworkApplication.java - NetworkApplication class
// Andrew Dao & Palistha Karmacharya, G00951496, adao4@masonlive.gmu.edu & pkarmach@masonlive.gmu.edu, 19 Nov 2017

/**
	* Class that initializes the Tiered Network Management Application
	* contains the methods with which to access, add, remove, modify, sort, and report network objects
	* @author Andrew Dao & Palistha Karmacharya
*/


/*
	Need to do
	* configure switch and virtual switch
	* remove client by client id, network by network id and device byIP address or MAC address
	* sort method (just clients)
	* modify method (X)
	* max num devices to a network?
	* max num networks available?
*/


import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class NetworkApplication{
	public static void main(String[] args){
		final int EXIT_OPTION = 6;

		int option = 0;
		int addOption = 0;
		int removeOption = 0;
		int searchOption = 0;

		LinkedList<Client> clients = new LinkedList<Client>();
		LinkedList<Network> networks = new LinkedList<Network>();
		LinkedList<Device> devices = new LinkedList<Device>();

		do{
			option = enterOption();
			switch(option){
				case 1: //add
					addOption = enterAddOption();

					switch(addOption){
						case 1:
							int userAddSelection = 0;
							if(clients.size() < Client.MAX_NUM_CLIENTS){
								do{
									clients.add(addClient());
									if(clients.size() < Client.MAX_NUM_CLIENTS) userAddSelection = JOptionPane.showConfirmDialog(null, "Would you like to add another client?");
									else{
										userAddSelection = 1;
										JOptionPane.showMessageDialog(null, "Maximum number of clients has been reached (" + Client.MAX_NUM_CLIENTS + ")");
									}//end else
								}while(userAddSelection == 0);
							}//end if
							else JOptionPane.showMessageDialog(null, "Maximum number of clients has been reached (" + Client.MAX_NUM_CLIENTS + ")");

							break;
						case 2:
							do{
								networks.add(addNetwork(networks, devices));
							}while(JOptionPane.showConfirmDialog(null, "Would you like to add another network?") == JOptionPane.YES_OPTION);
							break;
						case 3:
							do{
								addDevice(devices); //find network in linkedlist and use it as parameter
							}while(JOptionPane.showConfirmDialog(null, "Would you like to add another device?") == JOptionPane.YES_OPTION);
							break;
						case 4:
							String clientID = "";
							if(!clients.isEmpty() && !networks.isEmpty()){
								do{
									clientID = JOptionPane.showInputDialog("Please input the client ID you wish to associate a network to");

								}while(!verifyClient(clientID, clients));
							}//end if
							else JOptionPane.showMessageDialog(null, "There is not at least one client or network pair to associate");

							break;
						case 5:
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "Returning back to main menu");
							break;
						default:
							JOptionPane.showMessageDialog(null, "Error, Invalid input");
							break;

					}//end switch
					break;

				case 2: //remove
					removeOption = enterRemoveOption();
					int userRemoveSelection = 0;

					switch(removeOption){
						case 1:
							if(!clients.isEmpty()){
								do{
									removeClient(clients);
									if(!clients.isEmpty()) userRemoveSelection = JOptionPane.showConfirmDialog(null, "Would you like to remove another client?");
									else{
										userRemoveSelection = 1;
										JOptionPane.showMessageDialog(null, "All users have been deleted");
									}//end else
								}while(userRemoveSelection == 0);
							}//end if
							else JOptionPane.showMessageDialog(null, "There are currently no clients existing within this list");
							break;
						case 2:
							if(!networks.isEmpty()){
								do{
									removeNetwork(networks);
									if(!networks.isEmpty()) userRemoveSelection = JOptionPane.showConfirmDialog(null, "Would you like to remove another network?");
									else{
										userRemoveSelection = 1;
										JOptionPane.showMessageDialog(null, "All networks have been deleted");
									}//end else
								}while(userRemoveSelection == 0);
							}//end if
							else JOptionPane.showMessageDialog(null, "There are currently no networks existing within this list");
							break;
						case 3:
							if(!devices.isEmpty()){
								do{
									removeDevice(devices);
									if(!devices.isEmpty()) userRemoveSelection = JOptionPane.showConfirmDialog(null, "Would you like to remove another device?");
									else{
										userRemoveSelection = 1;
										JOptionPane.showMessageDialog(null, "All devices have been deleted");
									}//end else
								}while(userRemoveSelection == 0);
							}//end if
							else JOptionPane.showMessageDialog(null, "There are currently no devices existing within this list");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "Returning back to main menu");
							break;
						default:
							JOptionPane.showMessageDialog(null, "Error, Invalid input");
							break;

					}//end switch
					break;

				case 3: //search
					searchOption = enterSearchOption();

					switch(searchOption){
						case 1:
							if(!clients.isEmpty()){
								do{
									searchClientID(clients);
								}while(JOptionPane.showConfirmDialog(null, "Would you like to search for another client?") == JOptionPane.YES_OPTION);
							}//end if
							else JOptionPane.showMessageDialog(null, "There are no clients to search for");
							break;
						case 2:
							if(!networks.isEmpty()){
								do{
									searchNetworkID(networks);
								}while(JOptionPane.showConfirmDialog(null, "Would you like to search for another network?") == JOptionPane.YES_OPTION);
							}//end if
							else JOptionPane.showMessageDialog(null, "There are no networks to search for");
							break;
						case 3:
							if(!devices.isEmpty()){
								do{
									searchDeviceID(devices);
								}while(JOptionPane.showConfirmDialog(null, "Would you like to search for device network?") == JOptionPane.YES_OPTION);
							}//end if
							else JOptionPane.showMessageDialog(null, "There are no devices to search for");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "Returning back to main menu");
							break;
						default:
							JOptionPane.showMessageDialog(null, "Error, Invalid input");
							break;
					}//end switch

					break;
				case 4: //sort
					if(!clients.isEmpty()) sortClient(clients);
					else JOptionPane.showMessageDialog(null, "No client information currently exists");
					break;
				case 5://report
					display(clients, networks, devices);
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Exiting Application now");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Error, Invalid input");
					break;
			}//end switch



		}while(option != EXIT_OPTION);

	}//end main

	/**
		* Method used to read in a file
		* @return an integer representing the option a user has chosen
	*/

	public static void readFile(){
		String path = "NetworkReport.txt";
		Scanner scan = null;
		String name = "";

		try{
			FileReader file = new FileReader(path);
			BufferedReader reader = new BufferedReader(file);
			String data = "";
			while ((data = reader.readLine()) != null){
				scan = new Scanner(data);
			}//end while
			reader.close();
		}//end try
		catch(FileNotFoundException e){
			e.printStackTrace();
		}//end catch
		catch(IOException e){
			e.printStackTrace();
		}//end catch
	}//end enterStudent

	/**
		* Method used to prompt a user to input an option into the menu. This method will also validate if the option entered is valid
		* @return an integer representing the option a user has chosen
	*/

	private static int enterOption(){
		final String MENU = "Enter the corresponding key for each option\n"
			+ "\n1 --- Add\n2 --- Remove\n3 --- Search\n4 --- Sort Client Name\n5 --- Report\n6 --- Exit Application";
		int option = 0;

		do{
			try{option = Integer.parseInt(JOptionPane.showInputDialog(MENU + "\n\nPlease enter an option: "));}
			catch(NumberFormatException e){option = 0;}
			if(option >= 7 || option <= 0)JOptionPane.showMessageDialog(null, "Invalid option, please select again");
		}while(option >=7 || option <= 0);

		return option;
	}//end enterOption method

	/**
		* Method used to prompt a user to input an option into the add menu. This method will also validate if the option entered is valid
		* @return an integer representing the option a user has chosen
	*/

	private static int enterAddOption(){

		final String MENU = "Enter the corresponding key for each option\n"
			+ "\n1 --- Add Client\n2 --- Add Network\n3 --- Add Device\n4 --- Associate Network\n5 --- Associate Devices\n6 --- Exit Add Menu";
		int option = 0;

		do{
			try{option = Integer.parseInt(JOptionPane.showInputDialog(MENU + "\n\nPlease enter an option: "));}
			catch(NumberFormatException e){option = 0;}
			if(option >= 7 || option <= 0)JOptionPane.showMessageDialog(null, "Invalid option, please select again");
		}while(option >= 7 || option <= 0);

		return option;

	}//end method

	/**
		* Method used to prompt a user to input an option into the remove menu. This method will also validate if the option entered is valid
		* @return an integer representing the option a user has chosen
	*/

	private static int enterRemoveOption(){

		final String MENU = "Enter the corresponding key for each option\n"
			+ "\n1 --- Remove Client\n2 --- Remove Network\n3 --- Remove Device\n4 --- Exit Add Menu";
		int option = 0;

		do{
			try{option = Integer.parseInt(JOptionPane.showInputDialog(MENU + "\n\nPlease enter an option: "));}
			catch(NumberFormatException e){option = 0;}
			if(option >= 5 || option <= 0)JOptionPane.showMessageDialog(null, "Invalid option, please select again");
		}while(option >= 5 || option <= 0);

		return option;

	}//end method

	/**
		* Method that ensures that an option entered within the search submenu is validated
		* @return an integer representing a user's selection
	*/

	private static int enterSearchOption(){

		final String MENU = "Enter the corresponding key for each option\n"
			+ "\n1 --- Search for Client\n2 --- Search for Network\n3 --- Search for Device\n4 --- Exit Add Menu";
		int option = 0;

		do{
			try{option = Integer.parseInt(JOptionPane.showInputDialog(MENU + "\n\nPlease enter an option: "));}
			catch(NumberFormatException e){option = 0;}
			if(option >= 5 || option <= 0)JOptionPane.showMessageDialog(null, "Invalid option, please select again");
		}while(option >= 5 || option <= 0);

		return option;

	}//end method

	/**
		* Method that prompts a user to input information on a client
		* @return a Client object that contains user defined clientele information
	*/

	private static Client addClient(){
		String name = "";
		boolean isValid = false;

		do{
			name = JOptionPane.showInputDialog("Please enter the name of the Client");
			isValid = Client.validateClientName(name);
			if(!isValid) JOptionPane.showMessageDialog(null, "Error, client name cannot be empty");
		}while(!isValid);

		Client client = new Client(name);
		JOptionPane.showMessageDialog(null, "Client Created\n\n" + client.toString());
		return client;

	}//end createClient method

	/**
		* Method that prompts a user to input information on a particular network
		* @param a list of available networks
		* @return one Network object that contains user defined network information
	*/

	private static Network addNetwork(LinkedList<Network> networks, LinkedList<Device> devices){
		String networkID = "";
		boolean supportsWifi = false;
		boolean supportsVLAN = false;
   		boolean hasUPS = false;
   		int numOfVLAN = 0;

		do{
			networkID = JOptionPane.showInputDialog("Please enter the Network ID in the format:\nXXX.XXX.XXX.XXX");
			if(!Network.validateDeviceIP(networkID)) JOptionPane.showMessageDialog(null, "Error, network ID must be in the format XXX.XXX.XXX.XXX");
			if(!verifyUniqueNetID(networkID, networks, devices)) JOptionPane.showMessageDialog(null,"Error, this network ID already exists. Please re-enter a unique ID");
		}while(!Network.validateDeviceIP(networkID) || !verifyUniqueNetID(networkID, networks, devices));

		if(JOptionPane.showConfirmDialog(null, "Does this network support Wi-Fi?") == JOptionPane.YES_OPTION) supportsWifi = true;
		if(JOptionPane.showConfirmDialog(null, "Does this network have a UPS?") == JOptionPane.YES_OPTION) hasUPS = true;
		if(JOptionPane.showConfirmDialog(null, "Does this network support VLAN?") == JOptionPane.YES_OPTION){
			supportsVLAN = true;
			do{
				try{numOfVLAN = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of VLANs inside this network"));}
				catch(NumberFormatException e){numOfVLAN = -1;}
				if(numOfVLAN > Network.MAX_NUM_VLAN || numOfVLAN <= Network.MIN_NUM_VLAN)
					JOptionPane.showMessageDialog(null, "Invalid option, please enter a value less than or equal to 500");
			}while(numOfVLAN > Network.MAX_NUM_VLAN || numOfVLAN <= Network.MIN_NUM_VLAN);
		}//end if

		Network network = new Network(networkID, supportsWifi, supportsVLAN, numOfVLAN, hasUPS);
		JOptionPane.showMessageDialog(null, "Network Created\n\n" + network.toString());

		return network;

	}//end method

	/**
		* Method that ensures an IP address is unique
		* @param an IP address of a possible network and a list of available networks
		* @return a true or false statement that determines whether or not the IP address hasn't already been used
	*/

	private static boolean verifyUniqueNetID(String networkID, LinkedList<Network> networks, LinkedList<Device> devices){
		boolean isUnique = true;
		Iterator<Network> networkIT = networks.iterator();
		Iterator<Device> deviceIT = devices.iterator();
		Network network = null;
		Device device = null;

		while(networkIT.hasNext()){
			network = networkIT.next();
			if(network.getNetworkID().equalsIgnoreCase(networkID)) isUnique = false;
		}//end while

		while(deviceIT.hasNext()){
			device = deviceIT.next();
			if(device.getDeviceIP().equalsIgnoreCase(networkID)) isUnique = false;
		}//end while

		return isUnique;

	}//end method

	/**
		* Method that prompts user to input device information and to create a device object
		* @param a list of available devices
	*/

	private static void addDevice(LinkedList<Device> devices){
		String deviceType, deviceName, deviceIP, deviceMAC = "";

		do{
			deviceName = JOptionPane.showInputDialog("Please enter the device name");
			if(!Device.validateDeviceName(deviceName)) JOptionPane.showMessageDialog(null, "Error, please re-enter a valid name");
		}while(!Device.validateDeviceName(deviceName));

		do{
			deviceIP = JOptionPane.showInputDialog("Please enter the device IP in the format XXX.XXX.XXX.XXX");
			if(!Device.validateDeviceIP(deviceIP)) JOptionPane.showMessageDialog(null, "Error, please re-enter a valid IP address" +
				" in the format\nXXX.XXX.XXX.XXX");
		}while(!Device.validateDeviceIP(deviceIP));

		do{
			deviceMAC = JOptionPane.showInputDialog("Please enter the device's MAC address in the format XX-XX-XX-XX-XX-XX");
			if(!Device.validateDeviceMAC(deviceMAC)) JOptionPane.showMessageDialog(null, "Error, please re-enter a valid MAC address" +
				" in the format\n XX-XX-XX-XX-XX-XX");
		}while(!Device.validateDeviceMAC(deviceMAC));

		do{
			deviceType = JOptionPane.showInputDialog("Please enter the type of the device\n(Server, Switch, or Virtual Switch)");
			if(deviceType.equalsIgnoreCase("Server")){
				String serverType = "";
				do{
					serverType = JOptionPane.showInputDialog("Please enter the server type\n(email, filestorage, or web)");
					if(!Server.validateServerType(serverType)) JOptionPane.showMessageDialog(null, "Error, server type can be either email, filestorage, or web");
				}while(!Server.validateServerType(serverType));

				devices.add(new Server(deviceName, deviceIP, deviceMAC, serverType));
				}//end if
			else if(deviceType.equalsIgnoreCase("Switch")){
				devices.add(new Switch(deviceName, deviceIP, deviceMAC));
				}//end else if
			else if(deviceType.equalsIgnoreCase("Virtual Switch")){
				VirtualSwitch virtualSwitch = new VirtualSwitch(deviceName, deviceIP, deviceMAC);

				devices.add(virtualSwitch);

				}//end else if
			else JOptionPane.showMessageDialog(null, "Error, device type can be either a server, switch, or virtual switch");
		}while(!deviceType.equalsIgnoreCase("Server") && !deviceType.equalsIgnoreCase("Switch") && !deviceType.equalsIgnoreCase("Virtual Switch"));

	}//end method

	/**
		* Method that removes a specified client from the client list
		* @param a list of clients
	*/

	private static boolean verifyClient(String clientID, LinkedList<Client> clients){
		boolean isValid = false;
		boolean isFound = false;
		Client client = null;
		Iterator<Client> it = clients.iterator();

		while(it.hasNext() && !isFound){
			client = it.next();
			if(client.getClientID().equalsIgnoreCase(clientID)){
				isFound = true;
				isValid = true;
				JOptionPane.showMessageDialog(null,"Client acquired");
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "Client was not found");

		return isValid;
	}//end method

	private static void removeClient(LinkedList<Client> clients){
		String clientID = "";
		boolean isFound = false;
		Client client = null;

		clientID = JOptionPane.showInputDialog("Please input the Client ID of the client you wish to remove");

		Iterator<Client> it = clients.iterator();

		while(it.hasNext() && !isFound){
			client = it.next();
			if(client.getClientID().equalsIgnoreCase(clientID)){
				clients.remove(client);
				isFound = true;
				JOptionPane.showMessageDialog(null,"Client Removed");
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "This client does not exist");

	}//end method

	/**
		* Method that removes a specified network from the network list
		* @param a list of networks
	*/

	private static void removeNetwork(LinkedList<Network> networks){
		String IPaddress = "";
		IPaddress = JOptionPane.showInputDialog("Please input the IP address of the network you wish to remove");
		Network network = null;
		boolean isFound = false;

		Iterator<Network> it = networks.iterator();

		while(it.hasNext() && !isFound){
			network = it.next();
			if(network.getNetworkID().equalsIgnoreCase(IPaddress)){
				networks.remove(network);
				isFound = true;
				JOptionPane.showMessageDialog(null,"Network Removed");
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "This network does not exist");

	}//end method

	/**
		* Method that removes a specific device based on its MAC address
		* @param a list of all devices
	*/

	private static void removeDevice(LinkedList<Device> devices){
		String MACaddress = "";
		MACaddress = JOptionPane.showInputDialog("Please input the MAC address of the device you wish to remove");
		Device device = null;
		boolean isFound = false;

		Iterator<Device> it = devices.iterator();

		while(it.hasNext()){
			device = it.next();
			if(device.getDeviceMAC().equalsIgnoreCase(MACaddress)){
				devices.remove(device);
				isFound = true;
				JOptionPane.showMessageDialog(null,"Device Removed");
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "This device does not exist");

	}//end method

	/**
		* Method that searches for a specific client given their unique Client ID
		* @param a list of all clients
	*/

	private static void searchClientID(LinkedList<Client> clients){
		String search = "";
		Iterator<Client> it = clients.iterator();
		Client client = null;
		boolean isFound = false;

		search = JOptionPane.showInputDialog("Please enter the client's ID");

		while(it.hasNext() && !isFound){
			client = it.next();
			if(client.getClientID().equalsIgnoreCase(search)){
				JOptionPane.showMessageDialog(null, "Client Found\n\n" + client.toString());
				isFound = true;
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "This client does not exist");

	}//end method

	/**
		* Method that searches for a specific network given its IP address
		* @param a list of all networks
	*/

	private static void searchNetworkID(LinkedList<Network> networks){
		String search = "";
		Iterator<Network> it = networks.iterator();
		Network network = null;
		boolean isFound = false;

		search = JOptionPane.showInputDialog("Please enter the network's ID (XXX.XXX.XXX.XXX)");

		while(it.hasNext()){
			network = it.next();
			if(network.getNetworkID().equalsIgnoreCase(search)){
				JOptionPane.showMessageDialog(null, "Network Found\n\n" + network.toString());
				isFound = true;
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "This network does not exist");

	}//end method

	/**
		* Method that searches for a specific device given its MAC address
		* @param a list of all devices
	*/

	private static void searchDeviceID(LinkedList<Device> devices){

		String search = "";
		Iterator<Device> it = devices.iterator();
		Device device = null;
		boolean isFound = false;

		search = JOptionPane.showInputDialog("Please enter the device's MAC address (XX-XX-XX-XX-XX-XX)");

		while(it.hasNext()){
			device = it.next();
			if(device.getDeviceMAC().equalsIgnoreCase(search)){
				JOptionPane.showMessageDialog(null, "Device Found\n\n" + device.toString());
				isFound = true;
			}//end if
		}//end while

		if(!isFound) JOptionPane.showMessageDialog(null, "This network does not exist");

	}//end method

	/**
		* Method to sort clients' names in alphabetical order
		* @param a list of client information
	*/

	private static void sortClient(LinkedList<Client> clients){
		String msg = "---Sorted: Client Name---\n\n";
		Collections.sort(clients, new compareClientNames());

		for(Client e:clients){
			msg += e + "\n";
		}//end for

		JOptionPane.showMessageDialog(null, msg);

	}//end method

	/**
		* Method that presents to the user a list of all entities within the program
		* @param a list of clients, networks, and devices
	*/

	private static void display(LinkedList<Client>clients, LinkedList<Network> networks, LinkedList<Device> devices){
		String output = "";
		Iterator<Client> it = clients.iterator();
		Iterator<Network> it2 = networks.iterator();
		Iterator<Device> it3 = devices.iterator();

		while(it.hasNext()) output += (Client) it.next() + "\n";
		if(!clients.isEmpty()) output += "\n";

		while(it2.hasNext()) output += (Network) it2.next() + "\n";
		if(!networks.isEmpty()) output += "\n";

		while(it3.hasNext()) output += (Device) it3.next() + "\n";

		if(output.trim().equalsIgnoreCase("")) JOptionPane.showMessageDialog(null, "There are no records to show");
		else{
			JOptionPane.showMessageDialog(null, output);
			writeToFile(output);
		}//end else

	}//end method

	/**
		* Method that writes data collected by the application to a text file
		* @param a string containing a list of all entities within the program
	*/

	public static void writeToFile(String output){
		String path = "NetworkReport.txt";

		try{
			FileWriter fileWrite = new FileWriter(new File(path));
			BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);

			bufferedWrite.write(output);
			bufferedWrite.close();
		}//end try
		catch(FileNotFoundException e){
			e.printStackTrace();
		}//end catch
		catch(IOException e){
			e.printStackTrace();
		}//end catch

	}//end writeToFile

}//end class