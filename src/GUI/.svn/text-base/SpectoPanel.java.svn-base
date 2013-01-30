package GUI;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Tom Pepels
 */
public class SpectoPanel extends javax.swing.JPanel {

    /* ChartPanel chartPanel;
    JFreeChart chart;
    XYPlot xyPlot;
    int datasets = 1; */
    double[][] data;
    double max, min, crange;
    int yHeight, xWidth, overlap;

    /** Creates new form GraphPanel */
    public SpectoPanel() {
        initComponents();
    }

    /*
    public void drawSpecto(double[][] data, int overlap, int window) {
    this.removeAll();
    
    XYSeries series = new XYSeries("Spectogram");
    
    for (int j = 0; j < data.length; j++) {
    int offset = j*(window-overlap);
    System.out.println("[" + j + "] Start at: " + offset + " end at: " + (offset + window));
    for (int i = 0; i < window; i++) {
    series.add(offset + i, data[j][i]);
    }
    }
    
    XYDataset plotData = new XYSeriesCollection(series);
    
    chart = ChartFactory.createScatterPlot("", "", "", plotData, 
    PlotOrientation.VERTICAL, true, true, true);
    XYDotRenderer renderer = new XYDotRenderer();
    xyPlot = chart.getXYPlot();
    xyPlot.setRenderer(renderer);
    xyPlot.setBackgroundPaint(Color.black);
    xyPlot.getRangeAxis().setAutoRange(true);
    
    chartPanel = new ChartPanel(chart);
    chartPanel.setSize(this.getWidth(), this.getHeight());
    chartPanel.setLocation(0, 0);
    add(chartPanel);
    repaint();
    revalidate();
    } */
    public void drawSpecto(double[][] data, int overlap) {
        max = Double.MIN_VALUE;
        min = Double.MAX_VALUE;
        this.overlap = overlap;
        this.data = data;
        // Find the max value
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] > max) {
                    max = data[i][j];
                }
                if (data[i][j] < min) {
                    min = data[i][j];
                }
            }
        }

        xWidth = this.getWidth() / data.length; // Size of a single value on y axis
        yHeight = this.getHeight() / data[0].length;    // Size of a single value on y axis
        if (yHeight == 0) {
            yHeight = 1;
        }
        if (xWidth == 0) {
            xWidth = 1;
        }
        System.out.println("W: " + xWidth + " H: " + yHeight);
        double maxSig = Math.max(Math.abs(max), Math.abs(min));
        crange = (3 * 255) / (maxSig);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data != null) {
            // Find the max value
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {

                    double val = Math.abs(data[i][j]);
                    double r = Math.abs(val) * crange;

                    Color col = new Color(0, 0, 0);
                    if (r >= (2 * (255))) {
                        col = new Color(255, (int) (r - (2 * 255)), 0);
                    } else if (r >= 255) {
                        col = new Color(0, (int) (r - 255), 255 - (int)(r-255));
                    } else {
                        col = new Color(0, (int) r, 128);
                    }
                    g.setColor(col);
                    g.fillRect(i * xWidth, (j * yHeight), xWidth, yHeight);
                    //g.fillRect(i*1, j*1, 1, 1);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        drawSpecto(data, overlap);
        this.repaint();
    }//GEN-LAST:event_formComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
