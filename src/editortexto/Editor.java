/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package editortexto;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.undo.UndoManager;

/**
 *
 * @author 2B
 */
public class Editor extends javax.swing.JFrame implements Printable{

    String titulo;
    File archivoGuardado;
    private UndoManager undoManager;
    private float zoomFactor = 1.0f; //Factor de zoom predeterminado
    private JCheckBoxMenuItem zoomInMenuItem;
    private JCheckBoxMenuItem zoomOutMenuItem;
    private JCheckBoxMenuItem barraEstadoMenuItem;
    private boolean mostrarBarraEstado = true;
    private int lastIndex = 0;

    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        GuardarComo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarComoActionPerformed(evt);
            }
        });
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        AjusteLinea.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Deshacer.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        undoManager = new UndoManager();
        jTextArea1.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undoManager.addEdit(evt.getEdit());
            }
        });
        jTextArea1.setComponentPopupMenu(createPopupMenu());

        zoomInMenuItem = new JCheckBoxMenuItem("Zoom In");
        zoomOutMenuItem = new JCheckBoxMenuItem("Zoom Out");
        barraEstadoMenuItem = new JCheckBoxMenuItem("Barra de Estado");

        zoomInMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para aumentar el zoom
                zoomFactor *= 1.2f;

            }
        });
        zoomOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para disminuir el zoom
                zoomFactor /= 1.2f;

            }
        });
        barraEstadoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar/ocultar la barra de estado
                // Verificar si el estado del menú ha cambiado
                if (((AbstractButton) e.getSource()).isSelected()) {
                    // Lógica para mostrar la barra de estado
                    mostrarBarraEstado = true;
                    actualizarVista();
                } else {
                    // Lógica para ocultar la barra de estado
                    mostrarBarraEstado = false;
                    actualizarVista();
                }
            }
        });

        // Crear el menú Zoom y agregar elementos
        JMenu zoomMenu = new JMenu("Zoom");
        zoomMenu.add(zoomInMenuItem);
        zoomMenu.add(zoomOutMenuItem);

// Crear el menú Ver y agregar elementos
        JMenu verMenu = new JMenu("Ver");
        verMenu.add(zoomMenu);
        verMenu.add(barraEstadoMenuItem);

// Agregar el menú Ver a tu barra de menú
        mnuView.add(verMenu);
    }

    // Método para aplicar la fuente al JTextArea
    public void aplicarFuente(Font nuevaFuente) {
        if (nuevaFuente != null) {
            // Aplicar la nueva fuente al JTextArea en la clase Editor
            jTextArea1.setFont(nuevaFuente);
        }
    }

    public JTextArea getJTextArea() {
        return jTextArea1;
    }

    private void actualizarVista() {
        // Configurar la visibilidad de la barra de estado
        barraEstadoMenuItem.setVisible(mostrarBarraEstado);

        // Configurar el zoom
        if (zoomFactor != 1.0f) {
            // Aplicar el zoom al JTextArea
            jTextArea1.setFont(jTextArea1.getFont().deriveFont(zoomFactor * jTextArea1.getFont().getSize()));
        }

        // Otras actualizaciones de la interfaz según sea necesario
        // ...
        // Repintar la interfaz para reflejar los cambios
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        Nuevo = new javax.swing.JMenuItem();
        VentanaNueva = new javax.swing.JMenuItem();
        Abrir = new javax.swing.JMenuItem();
        Guardar = new javax.swing.JMenuItem();
        GuardarComo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        ConfigPage = new javax.swing.JMenuItem();
        Imprimir = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Exit = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        Deshacer = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        Copiar = new javax.swing.JMenuItem();
        Cortar = new javax.swing.JMenuItem();
        Pegar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        Bing = new javax.swing.JMenuItem();
        Buscar = new javax.swing.JMenuItem();
        BuscarNext = new javax.swing.JMenuItem();
        BuscarLast = new javax.swing.JMenuItem();
        Reemplazar = new javax.swing.JMenuItem();
        IrA = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        SelectAll = new javax.swing.JMenuItem();
        Date = new javax.swing.JMenuItem();
        mnuFormat = new javax.swing.JMenu();
        AjusteLinea = new javax.swing.JMenuItem();
        Fuente = new javax.swing.JMenuItem();
        mnuView = new javax.swing.JMenu();
        mnuHelp = new javax.swing.JMenu();
        Ayuda = new javax.swing.JMenuItem();
        SendComents = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        AcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        mnuFile.setText("Archivo");

        Nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Nuevo.setText("Nuevo");
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        mnuFile.add(Nuevo);

        VentanaNueva.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        VentanaNueva.setText("Ventana nueva");
        VentanaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaNuevaActionPerformed(evt);
            }
        });
        mnuFile.add(VentanaNueva);

        Abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Abrir.setText("Abrir...");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        mnuFile.add(Abrir);

        Guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        mnuFile.add(Guardar);

        GuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        GuardarComo.setText("Guardar como");
        GuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarComoActionPerformed(evt);
            }
        });
        mnuFile.add(GuardarComo);
        mnuFile.add(jSeparator1);

        ConfigPage.setText("Configurar pagina");
        ConfigPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfigPageActionPerformed(evt);
            }
        });
        mnuFile.add(ConfigPage);

        Imprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Imprimir.setText("Imprimir...");
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });
        mnuFile.add(Imprimir);
        mnuFile.add(jSeparator2);

        Exit.setText("Salir");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        mnuFile.add(Exit);

        jMenuBar1.add(mnuFile);

        mnuEdit.setText("Edicion");

        Deshacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Deshacer.setText("Deshacer");
        Deshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeshacerActionPerformed(evt);
            }
        });
        mnuEdit.add(Deshacer);
        mnuEdit.add(jSeparator3);

        Copiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Copiar.setText("Copiar");
        Copiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiarActionPerformed(evt);
            }
        });
        mnuEdit.add(Copiar);

        Cortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Cortar.setText("Cortar");
        Cortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CortarActionPerformed(evt);
            }
        });
        mnuEdit.add(Cortar);

        Pegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Pegar.setText("Pegar");
        Pegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PegarActionPerformed(evt);
            }
        });
        mnuEdit.add(Pegar);

        Eliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        mnuEdit.add(Eliminar);
        mnuEdit.add(jSeparator4);

        Bing.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Bing.setText("Busqueda con bing...");
        Bing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BingActionPerformed(evt);
            }
        });
        mnuEdit.add(Bing);

        Buscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        mnuEdit.add(Buscar);

        BuscarNext.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        BuscarNext.setText("Buscar siguiente");
        BuscarNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarNextActionPerformed(evt);
            }
        });
        mnuEdit.add(BuscarNext);

        BuscarLast.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        BuscarLast.setText("Buscar anterior");
        BuscarLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarLastActionPerformed(evt);
            }
        });
        mnuEdit.add(BuscarLast);

        Reemplazar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Reemplazar.setText("Reemplazar");
        Reemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReemplazarActionPerformed(evt);
            }
        });
        mnuEdit.add(Reemplazar);

        IrA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        IrA.setText("Ir a...");
        IrA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IrAActionPerformed(evt);
            }
        });
        mnuEdit.add(IrA);
        mnuEdit.add(jSeparator5);

        SelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SelectAll.setText("Seleccionar todo");
        SelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectAllActionPerformed(evt);
            }
        });
        mnuEdit.add(SelectAll);

        Date.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        Date.setText("Hora y fecha");
        Date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateActionPerformed(evt);
            }
        });
        mnuEdit.add(Date);

        jMenuBar1.add(mnuEdit);

        mnuFormat.setText("Formato");

        AjusteLinea.setText("Ajuste de linea");
        AjusteLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteLineaActionPerformed(evt);
            }
        });
        mnuFormat.add(AjusteLinea);

        Fuente.setText("Fuente...");
        Fuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuenteActionPerformed(evt);
            }
        });
        mnuFormat.add(Fuente);

        jMenuBar1.add(mnuFormat);

        mnuView.setText("Ver");
        jMenuBar1.add(mnuView);

        mnuHelp.setText("Ayuda");

        Ayuda.setText("Ver la ayuda");
        Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AyudaActionPerformed(evt);
            }
        });
        mnuHelp.add(Ayuda);

        SendComents.setText("Enviar comentarios");
        SendComents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendComentsActionPerformed(evt);
            }
        });
        mnuHelp.add(SendComents);
        mnuHelp.add(jSeparator6);

        AcercaDe.setText("Acerca del bloc de notas");
        AcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaDeActionPerformed(evt);
            }
        });
        mnuHelp.add(AcercaDe);

        jMenuBar1.add(mnuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PegarActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(this);
        String textoPegado;
        try {
            textoPegado = (String) contents.getTransferData(DataFlavor.stringFlavor);
            jTextArea1.replaceSelection(textoPegado);
        } catch (UnsupportedFlavorException | IOException e) {
        }
    }//GEN-LAST:event_PegarActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();

            String filePath = selectedFile.getAbsolutePath();
            jTextArea1.setText(filePath);
        }
    }//GEN-LAST:event_NuevoActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed

        if (archivoGuardado == null) {
            // Si no se ha guardado anteriormente, realiza la acción de "Guardar Como"
            GuardarComoActionPerformed(evt);
        } else {
            // Actualiza el contenido en el archivo existente
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoGuardado))) {
                writer.write(jTextArea1.getText());
                JOptionPane.showMessageDialog(this, "Contenido guardado exitosamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                jTextArea1.setText(contenido.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_AbrirActionPerformed

    private void AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AyudaActionPerformed
        // Abre el navegador web con la página de ayuda
        try {
            URI uri = new URI("https://www.bing.com/search"); // Reemplaza con la URL de tu página de ayuda
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException ex) {
            // Maneja cualquier excepción que pueda ocurrir al intentar abrir el navegador web
            ex.printStackTrace();
        }
    }//GEN-LAST:event_AyudaActionPerformed

    private void GuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarComoActionPerformed
        // TODO add your handling code here:
        // Verificar si el área de texto está vacía antes de intentar guardar
        if (jTextArea1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El área de texto está vacía. No hay contenido.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return; // Sale de la función si no hay contenido para guardar
        }

        // Crea un nuevo objeto JFileChooser para permitir al usuario seleccionar la ubicación y el nombre del archivo
        JFileChooser fileChooser = new JFileChooser();

        // Filtra para que solo se puedan seleccionar archivos de texto con extensión .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        // Muestra el diálogo de guardar archivo y espera a que el usuario seleccione una opción
        int result = fileChooser.showSaveDialog(this);

        // Verifica si el usuario hizo clic en "Guardar"
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtiene el archivo seleccionado por el usuario
            File selectedFile = fileChooser.getSelectedFile();

            // Asegura que el archivo tenga la extensión .txt
            if (!selectedFile.getName().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }

            // Intenta escribir el contenido del área de texto en el archivo seleccionado
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(jTextArea1.getText());

                // Actualiza el título de la ventana con el nombre del archivo guardado
                archivoGuardado = selectedFile;
                titulo = archivoGuardado.getName();
                setTitle(titulo);

                // Muestra un mensaje de éxito al usuario
                JOptionPane.showMessageDialog(this, "Contenido guardado exitosamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace(); // Imprime detalles del error en la consola en caso de un problema al guardar
            }
        }

    }//GEN-LAST:event_GuardarComoActionPerformed

    private void VentanaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentanaNuevaActionPerformed
        Editor nuevaVentana = new Editor();
        nuevaVentana.setVisible(true);
    }//GEN-LAST:event_VentanaNuevaActionPerformed

    private void ConfigPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfigPageActionPerformed
        // Obtener la impresora predeterminada
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Obtener la configuración actual de la página
        PageFormat pageFormat = printerJob.pageDialog(printerJob.defaultPage());

        // Puedes acceder a varias propiedades de la página, como el tamaño del papel, la orientación y los márgenes
        double width = pageFormat.getWidth();
        double height = pageFormat.getHeight();
        int orientation = pageFormat.getOrientation();
        double marginLeft = pageFormat.getImageableX();
        double marginTop = pageFormat.getImageableY();
    }//GEN-LAST:event_ConfigPageActionPerformed

    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed

        PrinterJob job = PrinterJob.getPrinterJob();
        
    }//GEN-LAST:event_ImprimirActionPerformed


    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void FuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuenteActionPerformed
        Fuente ventanaFuente = new Fuente(this); // Pasar la fuente actual como argumento

        // Hacer visible la ventana Fuente
        ventanaFuente.setVisible(true);


    }//GEN-LAST:event_FuenteActionPerformed

    private void DeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeshacerActionPerformed
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }//GEN-LAST:event_DeshacerActionPerformed

    private void CopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiarActionPerformed
        String seleccion = jTextArea1.getSelectedText();
        if (seleccion != null) {
            StringSelection seleccionClipboard = new StringSelection(seleccion);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(seleccionClipboard, null);
        }
    }//GEN-LAST:event_CopiarActionPerformed

    private void CortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CortarActionPerformed
        CopiarActionPerformed(evt);  // Primero copia la selección al portapapeles
        jTextArea1.replaceSelection("");  // Luego elimina la selección
    }//GEN-LAST:event_CortarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        jTextArea1.replaceSelection("");  // Simplemente elimina la selección
    }//GEN-LAST:event_EliminarActionPerformed

    private void BingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BingActionPerformed

        // Obtén el texto seleccionado en el bloc de notas (reemplaza "text" con tu componente de texto real)
        String selectedText = jTextArea1.getSelectedText();

        if (selectedText != null && !selectedText.isEmpty()) {
            // Realiza la búsqueda en Bing con el texto seleccionado
            try {
                String encodedQuery = URLEncoder.encode(selectedText, "UTF-8");
                String bingSearchUrl = "https://www.bing.com/search?q=" + encodedQuery;

                // Abre el navegador web con la búsqueda en Bing
                Desktop.getDesktop().browse(new URI(bingSearchUrl));
            } catch (IOException | URISyntaxException ex) {
                // Maneja cualquier excepción que pueda ocurrir al intentar abrir el navegador web
                ex.printStackTrace();
            }
        } else {
            // Mensaje o lógica adicional si no hay texto seleccionado
        }
    }//GEN-LAST:event_BingActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed

    }//GEN-LAST:event_BuscarActionPerformed

    private void DateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateActionPerformed
        Date now = new Date();//hora y fecha actual

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss   yyyy/MM/dd");//foramto

        String formatteDate = dateFormat.format(now);//farmato en cadena

        jTextArea1.append(formatteDate + "\n");
    }//GEN-LAST:event_DateActionPerformed

    private void SelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectAllActionPerformed
        jTextArea1.selectAll();
    }//GEN-LAST:event_SelectAllActionPerformed

    private void IrAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IrAActionPerformed
        JTextArea jTextArea1 = new JTextArea();

        // Puedes abrir un cuadro de diálogo para que el usuario ingrese la línea a la que quiere ir.
        String input = JOptionPane.showInputDialog("Ingrese el número de línea:");

        try {
            // Intenta convertir la entrada a un número de línea.
            int lineNumber = Integer.parseInt(input);

            // Verifica si el número de línea está en un rango válido.
            if (lineNumber > 0 && lineNumber <= jTextArea1.getLineCount()) {
                // Calcula la posición del inicio de la línea.
                int startOffset = jTextArea1.getLineStartOffset(lineNumber - 1);

                // Establece el cursor en esa posición.
                jTextArea1.setCaretPosition(startOffset);
            } else {
                // Muestra un mensaje de error si el número de línea no es válido.
                JOptionPane.showMessageDialog(null, "Número de línea no válido");
            }
        } catch (NumberFormatException | BadLocationException e) {
            // Captura excepciones si la entrada no es un número o hay problemas con la posición de la línea.
            JOptionPane.showMessageDialog(null, "Ingrese un número válido");
        }
    }//GEN-LAST:event_IrAActionPerformed

    private void ReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReemplazarActionPerformed
        // Mostrar un cuadro de diálogo para obtener el texto a buscar y el texto de reemplazo
        String buscar = JOptionPane.showInputDialog(this, "Buscar:");
        if (buscar == null) {
            // El usuario canceló la operación
            return;
        }

        String reemplazar = JOptionPane.showInputDialog(this, "Reemplazar con:");
        if (reemplazar == null) {
            // El usuario canceló la operación
            return;
        }

        // Obtener el texto actual del JTextArea
        String textoActual = jTextArea1.getText();

        // Realizar el reemplazo
        String textoNuevo = textoActual.replace(buscar, reemplazar);

        // Establecer el nuevo texto en el JTextArea
        jTextArea1.setText(textoNuevo);
    }//GEN-LAST:event_ReemplazarActionPerformed

    private void BuscarLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarLastActionPerformed
        buscarTexto(false);
    }//GEN-LAST:event_BuscarLastActionPerformed

    private void BuscarNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarNextActionPerformed
        buscarTexto(true);
    }//GEN-LAST:event_BuscarNextActionPerformed

    private void buscarTexto(boolean buscarSiguiente) {

        String textoBusqueda = jTextArea1.getSelectedText();

        if (textoBusqueda != null && !textoBusqueda.isEmpty()) {
            // Obtén el documento del componente de texto
            javax.swing.text.Document doc = jTextArea1.getDocument();
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.YELLOW);
            Highlighter highlighter = jTextArea1.getHighlighter();

            try {
                // Elimina resaltes anteriores
                highlighter.removeAllHighlights();

                // Realiza la búsqueda en el documento
                String contenido = doc.getText(0, doc.getLength());
                int startIndex = buscarSiguiente ? lastIndex + 1 : lastIndex - 1;
                int index = contenido.indexOf(textoBusqueda, startIndex);

                if (index != -1) {
                    // Resalta la ocurrencia encontrada
                    highlighter.addHighlight(index, index + textoBusqueda.length(), highlightPainter);

                    // Almacena el índice actual para la próxima búsqueda
                    lastIndex = index;
                } else {
                    // Mensaje o lógica adicional si no se encuentra más texto
                }
            } catch (BadLocationException ex) {
                // Maneja cualquier excepción que pueda ocurrir al manipular el documento del componente de texto
                ex.printStackTrace();
            }
        } else {
            // Mensaje o lógica adicional si no hay texto seleccionado
        }
    }
    private void AjusteLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteLineaActionPerformed
        // Obtén el estado actual del ajuste de línea
        boolean ajusteLineaActivado = jTextArea1.getLineWrap();

        // Invierte el estado
        jTextArea1.setLineWrap(!ajusteLineaActivado);
        jTextArea1.setWrapStyleWord(!ajusteLineaActivado);  // ajustar también por palabras

    }//GEN-LAST:event_AjusteLineaActionPerformed

    private void SendComentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendComentsActionPerformed
        try {
            //
            String mailto = "mailto:soporte@example.com?subject=Comentarios%20o%20Quejas";
            Desktop.getDesktop().browse(new URI(mailto));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_SendComentsActionPerformed

    private void AcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaDeActionPerformed
        JOptionPane.showMessageDialog(this, "Bloc de Notas\nVersión 1.0\n\nDesarrollado por: Microsoft", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AcercaDeActionPerformed

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem pegarItem = new JMenuItem("Pegar");
        JMenuItem copiarItem = new JMenuItem("Copiar");
        JMenuItem cortarItem = new JMenuItem("Cortar");
        JMenuItem eliminarItem = new JMenuItem("Eliminar");

        pegarItem.addActionListener((ActionEvent e) -> {
            PegarActionPerformed(e);
        });

        copiarItem.addActionListener((ActionEvent e) -> {
            CopiarActionPerformed(e);
        });

        cortarItem.addActionListener((ActionEvent e) -> {
            CortarActionPerformed(e);
        });

        eliminarItem.addActionListener((ActionEvent e) -> {
            EliminarActionPerformed(e);
        });

        popupMenu.add(pegarItem);
        popupMenu.add(copiarItem);
        popupMenu.add(cortarItem);
        popupMenu.add(eliminarItem);

        return popupMenu;
    }

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
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenuItem AcercaDe;
    private javax.swing.JMenuItem AjusteLinea;
    private javax.swing.JMenuItem Ayuda;
    private javax.swing.JMenuItem Bing;
    private javax.swing.JMenuItem Buscar;
    private javax.swing.JMenuItem BuscarLast;
    private javax.swing.JMenuItem BuscarNext;
    private javax.swing.JMenuItem ConfigPage;
    private javax.swing.JMenuItem Copiar;
    private javax.swing.JMenuItem Cortar;
    private javax.swing.JMenuItem Date;
    private javax.swing.JMenuItem Deshacer;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Fuente;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JMenuItem GuardarComo;
    private javax.swing.JMenuItem Imprimir;
    private javax.swing.JMenuItem IrA;
    private javax.swing.JMenuItem Nuevo;
    private javax.swing.JMenuItem Pegar;
    private javax.swing.JMenuItem Reemplazar;
    private javax.swing.JMenuItem SelectAll;
    private javax.swing.JMenuItem SendComents;
    private javax.swing.JMenuItem VentanaNueva;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuFormat;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenu mnuView;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
