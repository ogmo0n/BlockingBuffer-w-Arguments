// Fig. 23.14: BlockingBuffer.java
// Creating a synchronized buffer using the ArrayBlockingQueue class.
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer
{
   private final ArrayBlockingQueue<Integer> buffer; // shared buffer

   public BlockingBuffer(int num)
   {
      buffer = new ArrayBlockingQueue<Integer>(num);
   }
   
   // place value into buffer
   public void blockingPut(String name, int value) throws InterruptedException
   {
      buffer.put(value); // place value in buffer
      System.out.printf("%s%s%s%2d\t%s%d%n", "Producer ", name, " writes ", value,
         "Buffer cells occupied: ", buffer.size());
   } 

   // return value from buffer
   public int blockingGet(String name) throws InterruptedException
   {
      int readValue = buffer.take(); // remove value from buffer
      System.out.printf("%s%s%s%2d\t%s%d%n", "Consumer ", name, " reads ", 
         readValue, "Buffer cells occupied: ", buffer.size());

      return readValue;
   } 
} // end class BlockingBuffer