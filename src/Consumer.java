// Fig. 23.11: Consumer.java
// Consumer with a run method that loops, reading 10 values from buffer.
import java.security.SecureRandom;

public class Consumer implements Runnable
{ 
   private static final SecureRandom generator = new SecureRandom();
   private final Buffer sharedLocation; // reference to shared object
   String name = "";
   int sleepTime = 0;
   int startConsuming = 0;
   int stopConsuming = 0;

   // constructor
   public Consumer(String name, int sleepTime, int startConsuming, int stopConsuming, Buffer sharedLocation)
   {
	   this.name = name;
	   this.sleepTime = sleepTime;
	   this.startConsuming = startConsuming;
	   this.stopConsuming = stopConsuming;
	   this.sharedLocation = sharedLocation;
   }

   // read sharedLocation's value 10 times and sum the values
   public void run()                                           
   {
      int sum = 0;

      for (int count = startConsuming; count <= stopConsuming; count++) 
      {
         // sleep 0 to 3 seconds, read value from buffer and add to sum
         try 
         {
            Thread.sleep(generator.nextInt(sleepTime * 1000));
            sum += sharedLocation.blockingGet(name);
            System.out.printf("\t\t\t%2d%n", sum);
         } 
         catch (InterruptedException exception) 
         {
            Thread.currentThread().interrupt(); 
         } 
      } 

      System.out.printf("%n%s %s %s %d%n%s %s%n", 
         "Consumer", name, "read values totaling", sum, "Terminating Consumer", name);
   } 
} // end class Consumer