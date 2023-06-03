public class App {

    public static void main(String[] args) throws Exception {
        // sequential();
        sequentialBenchmark();
    }

    public static void sequential() {
        int numSteps = 10000000;

        double step = 1.0 / (double) numSteps;

        double sum = 0.0;

        for(int i = 0 ; i < numSteps ; i++) {
            double x = (i + 0.5) * step ;
            sum += 4.0 / (1.0 + x * x);
        }

        double pi = step * sum ;

        System.out.println("Value of pi: " + pi);
    }

    public static void sequentialBenchmark() {
        long startTime = System.currentTimeMillis(); // new code

        int numSteps = 1000000000;

        double step = 1.0 / (double) numSteps;

        double sum = 0.0;

        for(int i = 0 ; i < numSteps ; i++){
            double x = (i + 0.5) * step ;
            sum += 4.0 / (1.0 + x * x);
        }

        double pi = step * sum ;

        long endTime = System.currentTimeMillis(); // new code

        System.out.println("Value of pi: " + pi);

        System.out.println("Calculated in " + // new code
                            (endTime - startTime) + " milliseconds");
    }
}
