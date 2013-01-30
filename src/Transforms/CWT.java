package Transforms;

public class CWT {

    private double m;
    private double fb;
    private double fc;
    private final double a = 1;
    /**
     * Creates a CWT object with specified parameters of the complex frequency B-spline wavelet
     * @param M integer order parameter
     * @param Fb bandwith parameter
     * @param Fc wavelets central frequency
     */
    public CWT(double M, double Fb, double Fc) {
        m = M;
        fb = Fb;
        fc = Fc;
    }
    /**
     * Performs a complex Wavelet Transform on a given input signal using a complex frequency B-spline wavelet with parameters given in the class constructor
     * @param input the input signal
     * @return the transformed signal
     */
    public Complex[] complexTransform(double[] input) {
        Complex[] cwt = new Complex[input.length];
        int n = input.length;
        Complex[] in = new Complex[n];
        for (int i = 0; i < n; i++) {
            in[i] = new Complex(input[i], 0); // Complex representation of the input signal.
        }
        double weight = 1 / Math.sqrt(Math.abs(a));
        for (int b = 0; b < input.length; b++) {
            Complex integral = new Complex(0, 0);
            for (int t = 0; t < input.length; t++) {
                Complex conjW = fbsp((t - b) / a).conj();
                integral = integral.plus(in[t].times(conjW));
            }
            cwt[b] = new Complex(weight, 0).times(integral);
//            System.out.println("CWT at " + b + ": " + cwt[b].toString());
        }
        return cwt;
    }
    /**
     * The complex frequency B-spline wavelet
     * @param n the time index
     * @return the value of the wavelet at the given time index
     */
    public Complex fbsp(double n) {
        double sincPart = fb * n / m;
        double second = 1;
        if (sincPart != 0) {
            second = (Math.sin(sincPart * Math.PI)) / (sincPart * Math.PI); //Sinc function
        }
        second = Math.pow(second, m);
        double first = Math.sqrt(fb);
        Complex e = new Complex(0, 2 * Math.PI * fc * n);
        Complex third = e.exp();
        double firstPart = first * second;
        return new Complex(firstPart * third.real(), firstPart * third.imag());
    }
}
