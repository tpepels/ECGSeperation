/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transforms.math;

/**
 *
 * @author Lukas
 */
public class BlackmanWindow implements Window {
    
    private final double alpha = 0.16;              //by common Convention ;)
    
    private final double a0 = (1-alpha)/2;  
    private final double a1 = 0.5;
    private final double a2 = alpha/2;

    @Override
    public double w(int n, int N) {
        double w = a0 - a1 * Math.cos((2*Math.PI*n)/(N-1)) + a2 * Math.cos((4 * Math.PI * n)/(N - 1));
        return w;
    }
    
    @Override
    public double[] timesW(double[] in) {
        int n = in.length;
        double[] out = new double[n];
        for(int i = 0 ; i < n ; i++) {
            out[i] = in[i]*w(i,n);
        }
        return out;
    }

    @Override
    public double[] inverseW(double[] in) {
        int n = in.length;
        double[] out = new double[n];
        for(int i = 0 ; i < n ; i++) {
            out[i] = in[i]/w(i,n);
        }
        return out;
    }
}
