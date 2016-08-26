class SleepingThread extends Thread
{
    public SleepingThread()
    {
        setDaemon(true);
    }

    public void run()
    {
        try
        {
            // sleep for 5 minutes
            sleep(300000);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}

public class ThreadTest
{
    public static void main(String[] args) throws InterruptedException
    {
        int threadCount = Integer.parseInt(args[0]);

        for (int i = 0; i < threadCount; ++i)
        {
            SleepingThread thread = new SleepingThread();
            thread.start();

            System.out.format("Threads created: %d\n", i);
        }
        Thread.currentThread().sleep(300000);
    }
}