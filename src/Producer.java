// Fig. 23.10: Producer.java
// Producer with a run method that inserts the values 1 to 10 in buffer.
import java.security.SecureRandom;

public class Producer implements Runnable
{
   private static final SecureRandom generator = new SecureRandom();
   private final Buffer sharedLocation; // reference to shared object
   String name = "";
   int sleepTime = 0;
   int startProducing = 0;
   int stopProducing = 0;

   // constructor
   public Producer(String name, int sleepTime, int startProducing, int stopProducing, Buffer sharedLocation)
   {
	   this.name = name;
	   this.sleepTime = sleepTime;
	   this.startProducing = startProducing;
	   this.stopProducing = stopProducing;
	   this.sharedLocation = sharedLocation;
   } 

   // store values from 1 to 10 in sharedLocation
   public void run()                             
   {
      int sum = 0;

      for (int count = startProducing; count <= stopProducing; count++)
      {
         try // sleep 0 to 3 seconds, then place value in Buffer
         {
            Thread.sleep(generator.nextInt(sleepTime * 1000)); // random sleep
            sharedLocation.blockingPut(name, count); // set value in buffer
            sum += count; // increment sum of values
            System.out.printf("\t%2d%n", sum);
         } 
         catch (InterruptedException exception) 
         {
            Thread.currentThread().interrupt(); 
         } 
      } 

      System.out.printf(
         "Producer %s produced values totaling %d%nTerminating Producer %s%n", name, sum, name);
   } 
} // end class Producer