/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transforms;

/**
 *
 * @author Lukas
 */
public class DFT {

    public static double[] DiscreteFourier(double data[], int k) {
        int N = data.length;
        double result[] = new double[k];
        double[] r_data = new double[k];
        double[] i_data = new double[k];
        double p;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < N; j++) {
                p = 2 * Math.PI * i * j / N;
                r_data[i] += data[j] * Math.cos(p);
                i_data[i] -= data[j] * Math.sin(p);
            }
            r_data[i] /= N;
            i_data[i] /= N;
            result[i] = Math.sqrt(r_data[i] * r_data[i] + i_data[i] * i_data[i]);
        }
        return result;
    }

    public static void DiscreteFourier(Complex data[]) {
        int N = data.length;
        double p;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                p = 2 * Math.PI * i * j / N;
                data[i] = new Complex(data[i].real() + data[j].real() * Math.cos(p), data[i].imag() - data[j].real() * Math.sin(p));
            }
            data[i] = data[i].div(new Complex(N, 0));
        }
    }
// JWave Implementation
    public static double[] forward(double[] arrTime) {

        int m = arrTime.length;
        double[] arrFreq = new double[m]; // result

        int n = m >> 1; // half of m

        for (int i = 0; i < n; i++) {

            int iR = i * 2;
            int iC = i * 2 + 1;

            arrFreq[ iR] = 0.;
            arrFreq[ iC] = 0.;

            double arg = -2. * Math.PI * (double) i / (double) n;

            for (int k = 0; k < n; k++) {

                int kR = k * 2;
                int kC = k * 2 + 1;

                double cos = Math.cos(k * arg);
                double sin = Math.sin(k * arg);

                arrFreq[ iR] += arrTime[ kR] * cos - arrTime[ kC] * sin;
                arrFreq[ iC] += arrTime[ kR] * sin + arrTime[ kC] * cos;

            } // k

            arrFreq[ iR] /= (double) n;
            arrFreq[ iC] /= (double) n;

        } // i

        return arrFreq;
    } 
 // JWave Implementation
    public static double[] reverse(double[] arrFreq) {

        int m = arrFreq.length;
        double[] arrTime = new double[m]; // result

        int n = m >> 1; // half of m

        for (int i = 0; i < n; i++) {

            int iR = i * 2;
            int iC = i * 2 + 1;

            arrTime[ iR] = 0.;
            arrTime[ iC] = 0.;

            double arg = 2. * Math.PI * (double) i / (double) n;

            for (int k = 0; k < n; k++) {

                int kR = k * 2;
                int kC = k * 2 + 1;

                double cos = Math.cos(k * arg);
                double sin = Math.sin(k * arg);

                arrTime[ iR] += arrFreq[ kR] * cos - arrFreq[ kC] * sin;
                arrTime[ iC] += arrFreq[ kR] * sin + arrFreq[ kC] * cos;

            } // k

        } // i

        return arrTime;
    }
}
