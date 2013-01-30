package GUI;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Tom Pepels - 25-9-2011
 */
public class GraphPanel extends javax.swing.JPanel {

    ChartPanel chartPanel;
    JFreeChart chart;
    XYPlot xyPlot;
    int datasets = 1;

    /** Creates new form GraphPanel */
    public GraphPanel() {
        initComponents();
    }

    /**
     * Adds a graph to the existing graphpanel.
     * @param data Data to add
     * @param signalName Name of the data
     */
    public void addGraph(double[] data, String signalName) {
        datasets++;
        XYSeries series1 = new XYSeries(signalName);
        for (int i = 0; i < data.length; i++) {
            series1.add(i, data[i]);
        }
        
        XYDataset plotData = new XYSeriesCollection(series1);        
        xyPlot.setDataset(datasets, plotData);
        xyPlot.setRenderer(datasets, new StandardXYItemRenderer());
    }

    /**
     * Draw the initial graph.
     * @param data
     * @param yAxis
     * @param signalName 
     */
    public void drawGraph(double[] data, String yAxis, String signalName) {
        this.removeAll();

        XYSeries series1 = new XYSeries(signalName);
        XYSeries zeroLine = new XYSeries("");
        for (int i = 0; i < data.length; i++) {
            series1.add(i, data[i]);
            zeroLine.add(i, 0);
        }

        XYDataset plotData = new XYSeriesCollection(series1);
        XYDataset zero = new XYSeriesCollection(zeroLine);

        chart = ChartFactory.createXYLineChart(signalName, "", yAxis,
                plotData, PlotOrientation.VERTICAL, true,
                true, true);

        chart.setAntiAlias(true);
        
        xyPlot = chart.getXYPlot();
        xyPlot.setBackgroundPaint(Color.white);
        xyPlot.setDomainGridlinePaint(Color.gray);
        xyPlot.setRangeGridlinePaint(Color.gray);
        xyPlot.setRangeZeroBaselinePaint(Color.DARK_GRAY);
        xyPlot.getRangeAxis().setAutoRange(true);
        xyPlot.setRangeZeroBaselineVisible(true);

        chartPanel = new ChartPanel(chart);        
        chartPanel.setSize(this.getWidth(), this.getHeight());
        chartPanel.setLocation(0, 0);
        add(chartPanel);
        repaint();
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        if (chartPanel != null) {
            chartPanel.setSize(this.getWidth(), this.getHeight());
        }
    }//GEN-LAST:event_formComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
