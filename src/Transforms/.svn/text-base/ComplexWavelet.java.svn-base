package Transforms;

import java.util.ArrayList;

public class ComplexWavelet {

    private double[] rawSignal;
    private Complex[] rawMaternal;
    private Complex[] rawFetal;
    private Complex[] cFetal;
    private CWT maternalCWT;
    private CWT fetalCWT;
    private double maternalHeartRate;

    public double getFetalHeartRate() {
        return fetalHeartRate;
    }

    public double getMaternalHeartRate() {
        return maternalHeartRate;
    }
    private double fetalHeartRate;

    public ComplexWavelet() {
    }

    public double[] complexCWT(double[] thorax, double[] abdomen, int length) {
        // Creating all the preprocessed data and methods that need to be used
        fetalCWT = new CWT(2, 1, 0.5);

        // Maternal ECG calculation

        // Fetal ECG calculation
        rawFetal = fetalCWT.complexTransform(abdomen);
        ArrayList<Integer> peaks = getPeaks(thorax, 0.8);
        
        maternalHeartRate = (peaks.size() / 2.0) / (length / 1000.0)*30;
        cFetal = removeMaternalPeaks(rawFetal, peaks);
        
        double[] fetal = new double[cFetal.length];
        for (int i = 0; i < cFetal.length; i++) {
            fetal[i] = cFetal[i].mod();
        }
        ArrayList<Integer> fetalPeaks = getFetalPeaks(fetal);
        System.out.println("Number of fetal heart beats: "+fetalPeaks.size());
        fetalHeartRate = (fetalPeaks.size())/(length/1000.0)*60;
        return fetal;
    }

    public double[] avgSignal(double[][] input) {

        for (int i = 0; i < input.length; i++) {
            input[i] = DFT.forward(input[i]);
        }

        double[] av = new double[input[0].length];
        for (int i = 0; i < av.length; i++) {
            for (int j = 0; j < input.length; j++) {
                av[i] += input[j][i];
            }
            av[i] /= input.length;
        }
        return DFT.reverse(av);
    }

    public ArrayList<Integer> getFetalPeaks(double[] input) {
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        double threshold = 0.6;
        ArrayList<Integer> peaks = new ArrayList<Integer>();
        double peak = 0;
        for (int i = 0; i < input.length - 5; i++) {
            if (input[i] > peak) {
                peak = input[i];
            }
        }
        peak *= threshold;
        for (int i = 0; i < input.length - 5; i++) {
            if (input[i] > peak) {

                while (input[i + 1] > input[i]) {
                    i++;
                }
                candidates.add(i);
                System.out.println("Peak at : " + i);
                while(input[i] > peak) {
                    i++;
                }
            }
        }
        
        return candidates;
    }

    public ArrayList<Integer> getPeaks(double[] input, double threshold) {
        double max = 0;
        double peakThreshold = 0;
        ArrayList<Integer> peaks = new ArrayList<Integer>();
        System.out.println("Start getting the peaks");
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
                System.out.println("new max = " + max);
            }
        }
        System.out.println("Max = " + max);
        peakThreshold = max * threshold;

        for (int i = 0; i < input.length; i++) {
            if (input[i] > peakThreshold) {
                int j = i;

                if (input[i] < input[i + 1]) {
                    while (input[j] < input[j + 1] && j > 0) {
                        j--;
                    }
                    if (!peaks.contains(j)) {
                        peaks.add(j);
                    }
                } else {
                    while (input[j] > input[j + 1]) {
                        j++;
                    }
                    while (input[j] < input[j + 1]) {
                        j++;
                    }
                    if (!peaks.contains(j)) {
                        peaks.add(j);
                    }
                }
            }
        }
        return peaks;
    }

    public Complex[] removeMaternalPeaks(Complex[] input, ArrayList<Integer> peaks) {
        int start;
        int end;
        Complex[] peaksRemoved = input;

        for (int i = 0; i < peaks.size(); i = i + 2) {
            start = peaks.get(i);
            end = peaks.get(i + 1);
            for (int j = start; j < end; j++) {
                peaksRemoved[j] = new Complex(0, 0);
            }
        }
        return peaksRemoved;
    }

    public double[] postProcess(double[] input) {

        for (int i = 1; i < input.length - 1; i++) {
            if ((input[i + 1] - input[i]) > (1.5 * (input[i] - input[i - 1]))) {
                // Possible overlap
            } else if ((input[i + 1] - input[i]) < (1.5 * (input[i] - input[i - 1])) && ((input[i + 1] - input[i]) > (0.45 * (input[i] - input[i - 1])))) {
                // Continue with next peak
            } else if ((input[i + 1] - input[i]) > (0.45 * (input[i] - input[i - 1]))) {
                // Misdetected fetal QRS exists
            }
        }
        return new double[0];
    }
}
