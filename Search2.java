import java.util.LinkedList;
public class Search2{

   public static void main(String args[])
   {
        // Start with the empty list
      LinkedList<Client> c = new LinkedList<Client>();

      c.push(new Client("Palistha"));
      c.push(new Client("Andrew"));
      c.push(new Client("Travis"));


      LinkedList<Device> d = new LinkedList<Device>();

      d.push(new Server("Palistha", "123.456.789.777", "01-00-60-00-22-00"));
      d.push(new Switch("Andrew", "111.111.333.777", "41-50-60-00-00"));

      System.out.println(searchClientName(c.head, "Andrew"));
      System.out.println("\n\n");
      System.out.println(searchClientName(c.head, "Josh"));
      System.out.println("\n\n");

      System.out.println(searchClientID(c.head, "A1B"));
      System.out.println("\n\n");
      System.out.println(searchClientID(c.head, "A88"));
      System.out.println("\n\n");

      System.out.println(searchDeviceIP(d.head, "123.456.789.777"));
      System.out.println("\n\n");
      System.out.println(searchDeviceIP(d.head, "111.111.333.777"));
      System.out.println("\n\n");
      System.out.println(searchDeviceIP(d.head, "444.400.574.985"));



   }

   public static String searchClientName(Node<Client> head, String clientName)
   {
      String s;
        // Base case
      if (head == null)
         return "---Client Name " + clientName  +  " NOT FOUND--";

        // If key is present in current node,
        // return client name
      if (head.data.getClientName().equalsIgnoreCase(clientName))

         return "---Client Name " + clientName + " FOUND---\n" + head.data.toString();
        // Recur for remaining list
      return searchClientName(head.next, clientName);
   }

   public static String searchClientID(Node<Client> head, String clientID)
   {
      String s;
        // Base case
      if (head == null)
         return "---Client ID " + clientID +  " NOT FOUND--";

        // If key is present in current node,
        // return client id
      if (head.data.getClientID().equalsIgnoreCase(clientID))

         return "---Client ID  " + clientID + " FOUND---\n" +  head.data.toString();

        // Recur for remaining list
      return searchClientID(head.next, clientID);
   }

   public static String searchDeviceIP(Node<Device> head, String deviceIP)
   {
      String s;
        // Base case
      if (head == null)
         return "---Device IP " + deviceIP +  " NOT FOUND--";

        // If key is present in current node,
        // return client id
      if (head.data.getDeviceIP().equalsIgnoreCase(deviceIP))

         return "---Device IP  " + deviceIP + " FOUND---\n" +  head.data.toString();

        // Recur for remaining list
      return searchDeviceIP(head.next, deviceIP);
   }

   private static class Node<E>
   {
      E data;
      Node<E> next;
      Node(E c)
      {
         data = c;
         next = null;
      }
   }

   static class LinkedList<E>
   {
      Node<E> head;    //Head of list

    //Inserts a new node at the front of the list
      public void push(E c)
      {
        //Allocate new node and putting data
         Node<E> client = new Node<E>(c);

        //Make next of new node as head
         client.next = head;

        //Move the head to point to new Node
         head = client;
      }
   }

}
