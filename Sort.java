import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
public class Sort{
   public static void main(String[] args) {
      LinkedList<Client> c = new LinkedList<Client>();
      c.add(new Client("Palistha"));
      c.add(new Client("Andrew"));
      c.add(new Client("Travis"));

      Collections.sort(c, new compareClientNames());
      System.out.println("---Sorted: Client Name---");
      for(Client e:c){
         System.out.println(e);
      }

      System.out.println("----------------");

      Collections.sort(c, new compareClientIDs());
      System.out.println("---Sorted: Client ID---");
      for(Client e:c){
         System.out.println(e);
      }

   }
}


class compareClientNames implements Comparator<Client>{


  // @Override
   public int compare(Client c1, Client c2) {
      return c1.getClientName().compareTo(c2.getClientName());
   }
}

class compareClientIDs implements Comparator<Client>{

   @Override
   public int compare(Client c1, Client c2) {
      return c1.getClientID().compareTo(c2.getClientID());
   }
}
