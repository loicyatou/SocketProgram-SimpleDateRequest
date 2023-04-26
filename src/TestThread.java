class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    RunnableDemo( String name) { //a construcrtor that assaigns a name to the thread
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() { //5. this is where your logic for the threads are written. Here it creates a count down from 4 and prints the name of the thread that passes through and forces it to sleep for 50ms
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () { //3. creates a new thread using the runnable instantiation as the first argument and the name of the thread as the second
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start (); //4. calling the start method then triggers the .run() method
        }
    }
}

public class TestThread {

    //1. Instantiate a runnable interface. This interaface allows you to create a thread per instantiation (for multi threading)
    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start(); //2. The start method is called

        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }
}