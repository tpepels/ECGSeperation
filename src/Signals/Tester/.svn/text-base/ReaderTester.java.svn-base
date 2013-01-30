package Signals.Tester;

import Signals.Reader;

/**
 * Test class for the native EDF reader
 * @author Tom Pepels - 24 september 2011
 */
public class ReaderTester {

    public static void main(String args[]) {      
        if (Reader.openEDFFile("C:\\ECG.edf") == 0) {
            System.out.println("Open file successful");
        }
        
        if (Reader.noOfSignals() > -1) {
            System.out.println("File has " + Reader.noOfSignals() + " signals.");
        } else {
            System.out.println("Reading number of signals failed.");
        }
        
        String sigName = Reader.signalName(0);
        if (sigName != null) {
            System.out.println("The name of signal 0 is: " + sigName);
        } else {
            System.out.println("Retreiving signal name failed.");
        }
        
        double[] samples = Reader.readSamples(0, 10);
        if (samples.length > 0) {
            System.out.println(samples.length + " samples read.");
            for (int i = 0; i < samples.length; i++) {
                System.out.println("Sample " + i + ": " + samples[i]);
            }
        }

        if (Reader.closeEDFFile() == 0) {
            System.out.println("Close file successful");
        }
        
        if (Reader.noOfSignals() == -1) {
            System.out.println("This should fail :)");
        } else {
            System.out.println("This should have failed.");
        }
    }
}
