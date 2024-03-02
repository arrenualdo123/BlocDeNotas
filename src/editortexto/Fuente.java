/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package editortexto;

import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author 2B
 */
public class Fuente extends javax.swing.JFrame {

    private Font fontSeleccionada;
    private JTextArea jTextArea;
    private Editor editor; // Almacena la instancia de Editor recibida

    /**
     * Creates new form Fuente
     */
    public Fuente() {
        initComponents();
        this.editor = editor;
        this.jTextArea = editor.getJTextArea();
        this.fontSeleccionada = jTextArea.getFont();
        
    }

    private int obtenerEstiloDesdeString(String estilo) {
        return switch (estilo) {
            case "Normal" ->
                Font.PLAIN;
            case "Negrita comprimida" ->
                Font.BOLD;
            case "Oblicua comprimida" ->
                Font.ITALIC;
            case "Oblicua negrita comprimida" ->
                Font.BOLD | Font.ITALIC;
            case "Cursiva" ->
                Font.ITALIC;
            case "Negrita" ->
                Font.BOLD;
            case "Extra comprimida oblicua" ->
                Font.BOLD | Font.ITALIC;
            case "Cursiva negrita" ->
                Font.BOLD | Font.ITALIC;
            case "Oblicua ligera" ->
                Font.ITALIC;
            default ->
                Font.PLAIN;
        };
    }

    // Método para actualizar el ejemplo de texto en Muestratxt
    private void actualizarEjemploTexto() {
        if (fontSeleccionada != null) {
            Muestratxt.setFont(fontSeleccionada);
            Muestratxt.setText("Ejemplo de texto con la fuente seleccionada.");
        }
    }

    public Fuente(Editor editor) {
        this.jTextArea = editor.getJTextArea();
        this.fontSeleccionada = jTextArea.getFont();
        initComponents();
        // Resto de la inicialización...
    }

    private void aplicarCambios() {
        // Obtener la fuente seleccionada, estilo y tamaño
        String fuenteSeleccionada = Fuente.getSelectedValue();
        String estiloSeleccionado = Estilo.getSelectedValue();
        int tamañoSeleccionado = Integer.parseInt(Tamaño.getSelectedValue());

        // Crear una nueva fuente con los cambios
        Font nuevaFuente = new Font(fuenteSeleccionada, obtenerEstilo(estiloSeleccionado), tamañoSeleccionado);

        // Aplicar la nueva fuente al JTextArea en la clase Editor
        jTextArea.setFont(nuevaFuente);
        // Cerrar la ventana de Fuente después de aplicar los cambios
        this.dispose();
    }

    private int obtenerEstilo(String estilo) {
        switch (estilo) {
            case "Negrita":
                return Font.BOLD;
            case "Cursiva":
                return Font.ITALIC;
            case "Negrita cursiva":
                return Font.BOLD | Font.ITALIC;
            default:
                return Font.PLAIN;
        }
    }

    

    // Otros métodos y campos
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Muestratxt = new javax.swing.JTextArea();
        Cancelar = new javax.swing.JButton();
        Aceptar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Fuente = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        Estilo = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tamaño = new javax.swing.JList<>();
        Muestra = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Muestratxt.setColumns(20);
        Muestratxt.setRows(5);
        jScrollPane1.setViewportView(Muestratxt);

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Fuente.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Agency FB", "Algerian", "Arial", "Arial Rounded MT", "Bell MT", "Berlin Sans FB", "Broadway", "Calibri", "Castellar", "Century", "Dubai", "Ebrima", "Times New Roman" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(Fuente);

        Estilo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Normal", "Negrita comprimida", "Oblicua comprimida", "Oblicua negrita comprimida", "Cursiva", "Negrita", "Extra comprimida oblicua", "Cursiva negrita", "Oblicua ligera" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(Estilo);

        Tamaño.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(Tamaño);

        Muestra.setText("Muestra");

        jLabel2.setText("Fuente:");

        jLabel3.setText("Estilo de fuente:");

        jLabel4.setText("Tamaño:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Muestra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(73, 73, 73)
                .addComponent(Muestra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        // Obtener la fuente seleccionada
        String fuenteSeleccionada = Fuente.getSelectedValue();
        // Obtener el estilo seleccionado
        String estiloSeleccionado = Estilo.getSelectedValue();
        // Obtener el tamaño seleccionado
        String tamañoSeleccionado = Tamaño.getSelectedValue();

        // Validar si todos los elementos están seleccionados
        if (fuenteSeleccionada != null && estiloSeleccionado != null && tamañoSeleccionado != null) {
            // Crear un objeto Font con los parámetros seleccionados
            fontSeleccionada = new Font(fuenteSeleccionada, obtenerEstiloDesdeString(estiloSeleccionado), Integer.parseInt(tamañoSeleccionado));

            // Actualizar el ejemplo de texto en Muestratxt
            actualizarEjemploTexto();

            // Cerrar la ventana
            dispose();
        }
        aplicarCambios();
    }//GEN-LAST:event_AceptarActionPerformed

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
            java.util.logging.Logger.getLogger(Fuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Fuente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JList<String> Estilo;
    private javax.swing.JList<String> Fuente;
    private javax.swing.JLabel Muestra;
    private javax.swing.JTextArea Muestratxt;
    private javax.swing.JList<String> Tamaño;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}