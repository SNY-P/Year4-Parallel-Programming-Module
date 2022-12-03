public class ParallelPi extends Thread {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis(); //new code
        // long startTime = System.nanoTime();

        // quad-core threading
        // ParallelPi thread1 = new ParallelPi();
        // thread1.begin = 0 ;
        // thread1.end = numSteps / 4 ; // 0% - 25%

        // ParallelPi thread2 = new ParallelPi();
        // thread2.begin = numSteps / 4 ;
        // thread2.end = numSteps / 2 ; // 25% - 50%

        // ParallelPi thread3 = new ParallelPi();
        // thread3.begin = numSteps / 2 ;
        // thread3.end = (numSteps / 4) * 3; // 50% - 75%

        // ParallelPi thread4 = new ParallelPi();
        // thread4.begin = (numSteps / 4) * 3 ;
        // thread4.end = numSteps ; // 75% - 100%

        // dual-core threading
        ParallelPi thread1 = new ParallelPi();
        thread1.begin = 0;
        thread1.end = numSteps / 2; // 0% - 50%

        ParallelPi thread2 = new ParallelPi();
        thread2.begin = numSteps / 2;
        thread2.end = numSteps;

        thread1.start();
        thread2.start();
        // thread3.start();
        // thread4.start();
      
        thread1.join();
        thread2.join();
        // thread3.join();
        // thread4.join();

        long endTime = System.currentTimeMillis(); //new code
        //long endTime = System.nanoTime();

        // double pi = step * (thread1.sum + thread2.sum + thread3.sum + thread4.sum) ; // quad-core sum
        double pi = step * (thread1.sum + thread2.sum) ; // dual-core sum
      
        System.out.println("Value of pi: " + pi);

        System.out.println("Calculated in " + //new code
                           (endTime - startTime) + " nanoseconds");
    }

    static int numSteps = 1000000000;
    
    static double step = 1.0 / (double) numSteps;

    double sum ;  
    int begin, end ;

    public void run() {
        sum = 0.0 ;

        for(int i = begin ; i < end ; i++) {
            double x = (i + 0.5) * step ;
            sum += 4.0 / (1.0 + x * x);
        }
    }   
}
