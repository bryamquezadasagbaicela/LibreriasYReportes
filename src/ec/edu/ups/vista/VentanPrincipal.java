/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.controlador.BaseDeDatos;
import ec.edu.ups.controlador.ControladorPersona;
import ec.edu.ups.controlador.ControladorPersonadb;
import ec.edu.ups.modelo.Persona;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author QuezadaBryam
 */
public class VentanPrincipal extends javax.swing.JFrame {

    String url = "jdbc:postgresql://localhost:5432/MiBaseDeDatos";
    String user = "postgres";
    String password = "2999";
    Set<Persona> listaPersonas;

    int cont = 0;
    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;
    int cont4 = 0;
    int conp1 = 0;
    int conp2 = 0;
    int conp3 = 0;
    int conp4 = 0;
    int conp5 = 0;
    int conp6 = 0;
    int conp7 = 0;
    int conp8 = 0;
    Double suma1 = 0.00;
    Double suma2 = 0.00;
    Double suma3 = 0.00;
    Double suma4 = 0.00;
    Double suma5 = 0.00;
    Double suma6 = 0.00;
    Double suma7 = 0.00;
    Double suma8 = 0.00;
    double[] sumas;
    int[] contadores;
    double[] acumulador;

    public VentanPrincipal() throws Exception {
        initComponents();

        ControladorPersonadb controlador = new ControladorPersonadb(url, user, password);

        sumas = new double[38];
        contadores = new int[38];
        acumulador = new double[38];
        listaPersonas = controlador.Listar();
        for (Persona persona : listaPersonas) {
            if (persona.getEdad() >= 16 && persona.getEdad() <= 20) {
                // System.out.println(persona.toString());
                if (persona.getGenero().equals("MASCULINO")) {
                    conp1++;
                    suma1 = suma1 + persona.getSalario();
                }
                if (persona.getGenero().equals("FEMENINO")) {
                    conp2++;
                    suma2 = suma2 + persona.getSalario();
                }
                cont1++;

            }
            if (persona.getEdad() >= 21 && persona.getEdad() <= 30) {
                //System.out.println(persona.toString());

                if (persona.getGenero().equals("MASCULINO")) {
                    conp3++;
                    suma3 = suma3 + persona.getSalario();
                }
                if (persona.getGenero().equals("FEMENINO")) {
                    conp4++;
                    suma4 = suma4 + persona.getSalario();
                }
                cont2++;

            }
            if (persona.getEdad() >= 31 && persona.getEdad() <= 40) {
                //System.out.println(persona.toString());
                if (persona.getGenero().equals("MASCULINO")) {
                    conp5++;
                    suma5 = suma5 + persona.getSalario();
                }
                if (persona.getGenero().equals("FEMENINO")) {
                    conp6++;
                    suma6 = suma6 + persona.getSalario();
                }
                cont3++;

            }
            if (persona.getEdad() >= 41) {
                //System.out.println(persona.toString());
                if (persona.getGenero().equals("MASCULINO")) {
                    conp7++;
                    suma7 = suma7 + persona.getSalario();
                }
                if (persona.getGenero().equals("FEMENINO")) {
                    conp8++;
                    suma8 = suma8 + persona.getSalario();
                }
                cont4++;

            }
            cont++;
        }

        int i = 0;
        for (int ed = 16; ed <= 53; ed++) {

            for (Persona persona : listaPersonas) {
                if (persona.getEdad() == ed) {
                    sumas[i] = sumas[i] + persona.getSalario();
                    contadores[i]++;
                }
            }
            if (contadores[i] == 0) {
                acumulador[i] = 0;
            } else {
                acumulador[i] = sumas[i] / contadores[i];
            }
            i++;
        }
        grafico1();
        grafico2();
        grafico3();

    }

    public void grafico1() {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Entre 16-20", cont1);
        data.setValue("Entre 21-30", cont2);
        data.setValue("Entre 31-40", cont3);
        data.setValue("Mayores a 40", cont4);

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
                "PROMEDIO EDADES",
                data,
                true,
                true,
                false);

        // Mostrar Grafico
        ChartPanel frame = new ChartPanel(chart);
        visualizacion1.setLayout(new java.awt.BorderLayout());
        visualizacion1.add(frame, BorderLayout.CENTER);
        visualizacion1.validate();

    }

    public void grafico2() {
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue((suma2 / conp2), "Mujeres", "16-20");
        dataset.setValue((suma1 / conp1), "Hombres", "16-20");
        dataset.setValue((suma4 / conp4), "Mujeres", "21-30");
        dataset.setValue((suma3 / conp3), "Hombres", "21-30");
        dataset.setValue((suma6 / conp6), "Mujeres", "31-40");
        dataset.setValue((suma5 / conp5), "Hombres", "31-40");
        dataset.setValue(0, "Mujeres", "Mayores 40");
        dataset.setValue((suma7 / conp7), "Hombres", "Mayores 40");

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D("PROMEDIO SALARIO POR GENERO", "Genero", "Salario",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        visualizacion2.setLayout(new java.awt.BorderLayout());
        visualizacion2.add(chartPanel, BorderLayout.CENTER);
        visualizacion2.validate();
    }

    public void grafico3() {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        int ed = 16;
        for (int i = 0; i < sumas.length; i++) {
            String edad = String.valueOf(ed);
            line_chart_dataset.addValue(acumulador[i], "salario", edad);
            ed++;
        }

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("PROMEDIO EDAD",
                "Edades", "Salario", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        // Mostrar Grafico
        ChartPanel chartPanel1 = new ChartPanel(chart);
        visualizacion3.setLayout(new java.awt.BorderLayout());
        visualizacion3.add(chartPanel1, BorderLayout.CENTER);
        visualizacion3.validate();
    }

    /**
     * Creates new form VentanPrincipal
     */
    public void generarPDF() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        visualizacion1 = new javax.swing.JPanel();
        visualizacion2 = new javax.swing.JPanel();
        visualizacion3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("PDF REGISTRO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        visualizacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout visualizacion1Layout = new javax.swing.GroupLayout(visualizacion1);
        visualizacion1.setLayout(visualizacion1Layout);
        visualizacion1Layout.setHorizontalGroup(
            visualizacion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        visualizacion1Layout.setVerticalGroup(
            visualizacion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        visualizacion2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout visualizacion2Layout = new javax.swing.GroupLayout(visualizacion2);
        visualizacion2.setLayout(visualizacion2Layout);
        visualizacion2Layout.setHorizontalGroup(
            visualizacion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        visualizacion2Layout.setVerticalGroup(
            visualizacion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        visualizacion3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout visualizacion3Layout = new javax.swing.GroupLayout(visualizacion3);
        visualizacion3.setLayout(visualizacion3Layout);
        visualizacion3Layout.setHorizontalGroup(
            visualizacion3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );
        visualizacion3Layout.setVerticalGroup(
            visualizacion3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        jButton2.setText("PDF DIRECCIONES");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jButton2)
                                .addGap(0, 70, Short.MAX_VALUE))
                            .addComponent(visualizacion1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(visualizacion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(visualizacion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(27, 27, 27)
                        .addComponent(visualizacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(visualizacion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(visualizacion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String url = "jdbc:postgresql://localhost:5432/MiBaseDeDatos";
        String user = "postgres";
        String password = "2999";
        try {

            BaseDeDatos base = new BaseDeDatos(url, user, password);
            System.out.println("---------------------------------------------");
            base.conectar();
            System.out.println("---------------------------------------------");
            File reporteArchivo = new File("src/ec/edu/ups/reporte/reportePersona1.jasper");

            JasperReport reporteJasper = (JasperReport) JRLoader.loadObject(reporteArchivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, null, base.getConexionDb());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Reporte.pdf");

            JasperViewer.viewReport(jasperPrint,false);
            System.out.println("---------------------------------------------");
            base.desconectar();
            System.out.println("---------------------------------------------");
        } catch (JRException ex) {
            System.out.println(ex);
        }
        generarPDF();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String url = "jdbc:postgresql://localhost:5432/MiBaseDeDatos";
        String user = "postgres";
        String password = "2999";
        try {

            String parametro = JOptionPane.showInputDialog("INGRESE CEDULA: ");
            Map parametros = new HashMap();
            parametros.put("DIR_CEDULA", parametro);

            BaseDeDatos base = new BaseDeDatos(url, user, password);
            System.out.println("---------------------------------------------");
            base.conectar();
            System.out.println("---------------------------------------------");
            File reporteArchivo = new File("src/ec/edu/ups/reporte/reporteDireccion.jasper");

            JasperReport reporteJasper = (JasperReport) JRLoader.loadObject(reporteArchivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, parametros, base.getConexionDb());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Reporte1.pdf");

            JasperViewer.viewReport(jasperPrint, false);
            System.out.println("---------------------------------------------");
            base.desconectar();
            System.out.println("---------------------------------------------");
        } catch (JRException ex) {
            System.out.println(ex);
        }
        generarPDF();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VentanPrincipal().setVisible(true);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel visualizacion1;
    private javax.swing.JPanel visualizacion2;
    private javax.swing.JPanel visualizacion3;
    // End of variables declaration//GEN-END:variables
}
