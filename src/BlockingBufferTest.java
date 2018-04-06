// Fig 23.15: BlockingBufferTest.java
// Two threads manipulating a blocking buffer that properly 
// implements the producer/consumer relationship.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingBufferTest
{
   public static void main(String[] args) throws InterruptedException
   {
      // create new thread pool with multiple threads
      ExecutorService executorService = Executors.newCachedThreadPool();

      // create BlockingBuffer to store ints
      Buffer sharedLocation = new BlockingBuffer(5);

      executorService.execute(new Producer("P1", 2, 10, 16, sharedLocation));
      executorService.execute(new Producer("P2", 3, 25, 29, sharedLocation));
      executorService.execute(new Producer("P3", 1, 30, 39, sharedLocation));
      executorService.execute(new Consumer("C1", 3, 1, 9, sharedLocation));
      executorService.execute(new Consumer("C2", 2, 1, 13, sharedLocation));

      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.MINUTES); 
   } 
} // end class BlockingBufferTest