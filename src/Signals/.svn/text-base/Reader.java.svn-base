package Signals;

import Transforms.math.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

/**
 *
 * @author Tom Pepels - 23 sept 2011
 */
public class Reader {

    private static JNative openEDFFile;
    private static JNative closeEDFFile;
    private static JNative noOfSignals;
    private static JNative readSamples;
    private static JNative signalName;
    private static JNative doubleSize;
    private static JNative physicalDimension;
    private static boolean fileOpen = false;
    private static int sdouble = -1;
    
        /**
     * Returns the dimension (y axis name) for a signal
     * @return int signal index
     */
    public static String physicalDimension(int signal) {
        try {
            physicalDimension = new JNative("ReadSignals.dll", "physicalDimension");
            physicalDimension.setRetVal(Type.STRING);
            physicalDimension.setParameter(0, signal);
            physicalDimension.invoke();
            return physicalDimension.getRetVal();
        } catch (Exception ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    /**
     * Returns the size of a native double. Since this will differ from java's size.
     * @return double size
     */
    private static int doubleSize() {
        try {
            doubleSize = new JNative("ReadSignals.dll", "doubleSize");
            doubleSize.setRetVal(Type.INT);
            doubleSize.invoke();
            return doubleSize.getRetValAsInt();
        } catch (Exception ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 8; // The double size on my 64 bit pc. *tom*
    }

    public static double[] readSamples(int signal, int samples) {
        if (fileOpen) {
            try {
                if (sdouble < 1) {
                    sdouble = doubleSize();
                }

                Pointer p = new Pointer(MemoryBlockFactory.createMemoryBlock(samples * sdouble));
                readSamples = new JNative("ReadSignals.dll", "readSamples");
                readSamples.setRetVal(Type.INT);
                readSamples.setParameter(0, signal);
                readSamples.setParameter(1, samples);
                readSamples.setParameter(2, p);
                readSamples.invoke();

                int samplesRead = readSamples.getRetValAsInt();

                if (samplesRead > 0) {
                    double[] smp = new double[samplesRead];
                    for (int i = 0; i < samplesRead; i++) {
                        smp[i] = p.getAsDouble(i * sdouble);
                    }
                    return Vector.center(smp);
                }
            } catch (Exception ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Gets the name of the signal at given index
     * @param signalIndex The index of the signal for which the name is to be retreived
     * @return null on failure, else the name of the signal.
     */
    public static String signalName(int signalIndex) {
        if (fileOpen) {
            try {
                signalName = new JNative("ReadSignals.dll", "signalName");
                signalName.setParameter(0, signalIndex);
                signalName.setRetVal(Type.STRING);
                signalName.invoke();
                return signalName.getRetVal();
            } catch (Exception ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    /**
     * Opens an EDF file for reading.
     * @param filePath Path to the EDF File to open for reading.
     * @return 0 on success, else check edflib.h
     */
    public static int openEDFFile(String filePath) {
        if (!fileOpen) {
            try {
                openEDFFile = new JNative("ReadSignals.dll", "openEDFFile");
                openEDFFile.setRetVal(Type.INT);
                openEDFFile.setParameter(0, filePath);
                openEDFFile.invoke();
                fileOpen = true;
                return openEDFFile.getRetValAsInt();
            } catch (Exception ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    /**
     * Closes the currently opened EDF file
     * @return 0 if success, else check edflib.h
     */
    public static int closeEDFFile() {
        if (fileOpen) {
            try {
                closeEDFFile = new JNative("ReadSignals.dll", "closeEDFFile");
                closeEDFFile.setRetVal(Type.INT);
                closeEDFFile.invoke();
                fileOpen = false;
                return closeEDFFile.getRetValAsInt();
            } catch (Exception ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    /**
     * Returns the number of signals in the currently opened EDF file.
     * @return -1 on fail, otherwise the amount of signals in the file.
     */
    public static int noOfSignals() {
        if (fileOpen) {
            try {
                noOfSignals = new JNative("ReadSignals.dll", "noOfSignals");
                noOfSignals.setRetVal(Type.INT);
                noOfSignals.invoke();
                return noOfSignals.getRetValAsInt();
            } catch (Exception ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
}