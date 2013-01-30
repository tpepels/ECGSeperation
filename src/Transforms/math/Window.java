/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transforms.math;

/**
 *
 * @author Lukas
 */
public interface Window {
    /**
     * Calculates the value of the window function at a certain index
     * 
     * @param n the index
     * @param N the total length of the window
     * @return the value
     */
    public double w(int n, int N);
    /**
     * multiplies a vector with the window fucntion
     * 
     * @param in the vector
     * @return the resulting vector after multiplying with the window
     */
    public double[] timesW(double[] in);

    
    public double[] inverseW(double[] in);
}
