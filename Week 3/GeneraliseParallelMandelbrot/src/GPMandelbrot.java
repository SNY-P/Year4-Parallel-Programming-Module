import java.awt.Color ;
import java.awt.image.BufferedImage ;

import java.io.File ;

import javax.imageio.ImageIO;

public class GPMandelbrot extends Thread {
    final static int N = 4096 ;
    final static int CUTOFF = 100 ; 
    // P = no. of threads
    final static int P = 8 ; // new code

    static int [] [] set = new int [N] [N] ;
    public static void main(String [] args) throws Exception {
        // Calculate set
        long startTime = System.currentTimeMillis();

        // GPMandelbrot thread0 = new GPMandelbrot(0) ;
        // GPMandelbrot thread1 = new GPMandelbrot(1) ;

        // thread0.start() ;
        // thread1.start() ;

        // thread0.join() ;
        // thread1.join() ;

        GPMandelbrot [] threads = new GPMandelbrot [P] ;
        for(int me = 0 ; me < P ; me++) {
            threads [me] = new GPMandelbrot(me) ;
            threads [me].start() ;
        }

        for(int me = 0 ; me < P ; me++) {
            threads [me].join() ;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Calculation completed in " +
                           (endTime - startTime) + " milliseconds");

        // Plot image
        BufferedImage img = new BufferedImage(N, N,
                                              BufferedImage.TYPE_INT_ARGB) ;

        // Draw pixels
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {

                int k = set [i] [j] ;

                float level ;
                if(k < CUTOFF) {
                    level = (float) k / CUTOFF ;
                }
                else {
                    level = 0 ;
                }
                Color c = new Color(0, level, 0) ;  // Green
                img.setRGB(i, j, c.getRGB()) ;
            }
        }
        // Print file
        ImageIO.write(img, "PNG", new File("D:/Main/[Edu. ] University Work/4th Year/Parallel Programming/Lab Work/Week 3/GeneraliseParallelMandelbrot/src", 
                                                    "Mandelbrot.png"));
    }

    int me ;

    public GPMandelbrot(int me) {
        this.me = me ;
    }

    public void run() {
        int begin, end ;

        // int b = N/P ;  // block size
        // begin = me * b ;
        // end = begin + b ;

        if (me == 0) {
            begin = 0 ;
            end = N/2 ;
        }
        else {  // me == 1
            begin = N/2 ;
            end = N ;
        }

        for (int i = me ; i < N ; i+=P) {
            for(int j = 0 ; j < N ; j++) {
                double cr = (4.0 * i - 2 * N) / N ;
                double ci = (4.0 * j - 2 * N) / N ;

                double zr = cr, zi = ci ;

                int k = 0 ;
                while (k < CUTOFF && zr * zr + zi * zi < 4.0) {

                    // z = c + z * z
                    double newr = cr + zr * zr - zi * zi ;
                    double newi = ci + 2 * zr * zi ;

                    zr = newr ;
                    zi = newi ;

                    k++ ;
                }

                set [i] [j] = k ;
            }
        }
    }
}