/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frames.admin;

import controllers.PeliculaController;
import controllers.factory.FabricaControllers;
import dtos.PeliculaDTO;
import excepciones.presentacion.ControllerException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import tables.ModeloTablaPeliculas;

/**
 *
 * @author jalt2
 */
public class PnlPeliculas extends javax.swing.JPanel {
    
    private final PeliculaController control;
    private String idPeliculaSeleccionada;
    
    /**
     * Creates new form PnlPeliculas
     */
    public PnlPeliculas() {
        initComponents();
        this.setPreferredSize(new Dimension(1250,683));
        this.setLayout(new BorderLayout());
        this.add(pnlPeliculas, BorderLayout.CENTER);
        this.spnDuracion.setValue(1);
        this.control = FabricaControllers.getPeliculaController();
        
        tblPeliculas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPeliculaSeleccionada();
            }
        });
        
        try {
            llenarTablaPeliculas();
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
    }
    
    private List<String> obtenerGenerosSeleccionados(){
        List<String> generos = new ArrayList<>();

        if (chkAccion.isSelected()) {
            generos.add("Acción");
        }

        if (chkAventura.isSelected()) {
            generos.add("Aventura");
        }

        if (chkDrama.isSelected()) {
            generos.add("Drama");
        }

        if (chkTerror.isSelected()) {
            generos.add("Terror");
        }

        if (chkCienciaFiccion.isSelected()) {
            generos.add("Ciencia ficción");
        }

        if (chkComedia.isSelected()) {
            generos.add("Comedia");
        }

        return generos;
    }
    
    private void guardarPelicula(){
        try {
            limpiarBordes();
            
            String titulo = this.txtTitulo.getText();
            List<String> generos = this.obtenerGenerosSeleccionados();
            String clasificacion = this.cmbClasificacion.getSelectedItem().toString();
            Double duracion = ((Number) spnDuracion.getValue()).doubleValue();
            
            //Validar si todos los campos son invalidos
            if (titulo.trim().isEmpty() && generos.isEmpty() && clasificacion.equals("Seleccione una opcion")&&duracion <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos son obligatorios.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                pintarBordesRojo();
                return;
            }
            
            if (titulo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                                this,
                                "El titulo de la pelicula es obligatorio.",
                                "Datos faltantes",
                                JOptionPane.WARNING_MESSAGE
                        );
                txtTitulo.setBorder(
                    BorderFactory
                            .createLineBorder(Color.RED,2)
                );
//                return;  
            }

            if (generos.isEmpty()) {
                JOptionPane.showMessageDialog(
                                this,
                                "La pelicula debe tener al menos un genero.",
                                "Datos faltantes",
                                JOptionPane.WARNING_MESSAGE
                        );
                pnlGeneros.setBorder(
                    BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
//                return; 
            }

            if (clasificacion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                                this,
                                "La pelicula debe tener una clasificacion.",
                                "Datos faltantes",
                                JOptionPane.WARNING_MESSAGE
                        );
                cmbClasificacion.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
//                return; 
            }else if (clasificacion.equals("Seleccione una opcion")) {
                JOptionPane.showMessageDialog(
                                this,
                                "Debe seleccionar una clasificacion.",
                                "Datos faltantes",
                                JOptionPane.WARNING_MESSAGE
                        );
                cmbClasificacion.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    ));
//                return;
            }

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(
                                this,
                                "La duracion debe ser mayor a 0.",
                                "Datos faltantes",
                                JOptionPane.WARNING_MESSAGE
                        );
                spnDuracion.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return;
            }
            
            PeliculaDTO peliculaGuardada = new PeliculaDTO(titulo, generos, duracion, clasificacion);

            peliculaGuardada = control.guardarPelicula(peliculaGuardada);

            if (peliculaGuardada != null) {
                JOptionPane.showMessageDialog(this, "Pelicula guardada correctamente.");
                limpiarCampos();
                llenarTablaPeliculas();
                txtTitulo.requestFocus();
            }else{
                JOptionPane.showMessageDialog(
                    this,
                    "Ocurrio un error al guardar la pelicula",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "La duración es inválida.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } 
    }
    
    private boolean actualizar(){
        try {
            limpiarBordes();
            
            String id = idPeliculaSeleccionada;
            String titulo = this.txtTitulo.getText().trim();
            List<String> generos = this.obtenerGenerosSeleccionados();
            String clasificacion = this.cmbClasificacion.getSelectedItem().toString();
            Double duracion = ((Number) spnDuracion.getValue()).doubleValue();
            
            if (id == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione una película.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
            
            if (titulo.isEmpty()) {
                txtTitulo.setBorder(
                BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );     

                JOptionPane.showMessageDialog(
                        this,
                        "El título es obligatorio.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }
            
             if (generos.isEmpty()) {

                pnlGeneros.setBorder(
                BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                ); 

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione al menos un género.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }
             
            if (clasificacion.equals("Seleccione una opcion")) {
                cmbClasificacion.setBorder(
                BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione una clasificacion.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }
             
            if (duracion <= 0) {

                spnDuracion.setBorder(
                    BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );

                JOptionPane.showMessageDialog(
                        this,
                        "La duración debe ser mayor a 0.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }
            
            PeliculaDTO peliculaSeleccionada = new PeliculaDTO(id, titulo, generos, duracion, clasificacion);
            
            PeliculaDTO peliculaActualizada = control.actualizarPelicula(peliculaSeleccionada);
            
            if (peliculaActualizada != null) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Película actualizada correctamente."
                );

                llenarTablaPeliculas();

                limpiarCampos();

                limpiarSeleccion();

                return true;
            }
            
            return false;
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }    
    }
    
    private void cargarPeliculaSeleccionada(){
        int fila =
            tblPeliculas.getSelectedRow();

        if (fila == -1) {
            return;
        }

        ModeloTablaPeliculas modelo =(ModeloTablaPeliculas)tblPeliculas.getModel();

        PeliculaDTO pelicula = modelo.obtenerPelicula(fila);

        // CARGAR DATOS
        idPeliculaSeleccionada = pelicula.getId();

        txtTitulo.setText(pelicula.getTitulo());

        spnDuracion.setValue(pelicula.getDuracion());

        cmbClasificacion.setSelectedItem(pelicula.getClasificacion());

        cargarGeneros(pelicula.getGeneros());
        
        btnGuardar.setEnabled(false);

        btnEditar.setEnabled(true);

        btnEliminar.setEnabled(true);
    }
    
    private void llenarTablaPeliculas() throws ControllerException{
        try {
            List<PeliculaDTO> peliculas = control.listarPeliculas();
        
            ModeloTablaPeliculas modelo = new ModeloTablaPeliculas(peliculas);

            tblPeliculas.setModel(modelo);
            
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
        
        
    }
    
    private void cargarGeneros(List<String> generos) {

        // LIMPIAR TODOS

        chkAccion.setSelected(false);
        chkDrama.setSelected(false);
        chkTerror.setSelected(false);
        chkAventura.setSelected(false);
        chkCienciaFiccion.setSelected(false);
        chkComedia.setSelected(false);

        // MARCAR LOS NECESARIOS

        for (String genero : generos) {

            switch (genero) {

                case "Accion":

                    chkAccion.setSelected(true);

                    break;

                case "Drama":

                    chkDrama.setSelected(true);

                    break;

                case "Terror":

                    chkTerror.setSelected(true);

                    break;

                case "Aventura":

                    chkAventura.setSelected(true);

                    break;
                    
                case "Ciencia Ficcion":

                    chkAventura.setSelected(true);

                    break;
                    
                case "Comedia":

                    chkAventura.setSelected(true);

                    break;
            }
        }
    }
    
    private void limpiarCampos(){
        txtTitulo.setText("");

        spnDuracion.setValue(1);

        cmbClasificacion.setSelectedIndex(0);

        chkAccion.setSelected(false);
        chkDrama.setSelected(false);
        chkAventura.setSelected(false);
        chkTerror.setSelected(false);
        chkCienciaFiccion.setSelected(false);
        chkComedia.setSelected(false);
        
        btnEliminar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }
    
    private void limpiarSeleccion() {

        idPeliculaSeleccionada = null;

        tblPeliculas.clearSelection();

        btnEliminar.setEnabled(false);

        btnEditar.setEnabled(false);

        btnGuardar.setEnabled(true);
    }
    
    private void limpiarBordes() {

        txtTitulo.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );

        cmbClasificacion.setBorder(
                UIManager.getBorder(
                        "ComboBox.border"
                )
        );

        spnDuracion.setBorder(
                UIManager.getBorder(
                        "Spinner.border"
                )
        );
        
        pnlGeneros.setBorder(
                UIManager.getBorder(
                        "Spinner.border"
                )
        );
    }
    
    private void pintarBordesRojo(){
        txtTitulo.setBorder(
            BorderFactory.createLineBorder(
                    Color.RED,
                    2
            )
        );
        
        pnlGeneros.setBorder(
            BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        cmbClasificacion.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        spnDuracion.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlPeliculas = new javax.swing.JPanel();
        scrPnlTablaPeliculas = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblGeneros = new javax.swing.JLabel();
        lblClasificacion = new javax.swing.JLabel();
        cmbClasificacion = new javax.swing.JComboBox<>();
        lblDuracion = new javax.swing.JLabel();
        spnDuracion = new javax.swing.JSpinner();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnlGeneros = new javax.swing.JPanel();
        chkComedia = new javax.swing.JCheckBox();
        chkCienciaFiccion = new javax.swing.JCheckBox();
        chkTerror = new javax.swing.JCheckBox();
        chkDrama = new javax.swing.JCheckBox();
        chkAventura = new javax.swing.JCheckBox();
        chkAccion = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(1250, 683));

        pnlPeliculas.setBackground(new java.awt.Color(9, 79, 138));
        pnlPeliculas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrPnlTablaPeliculas.setViewportView(tblPeliculas);

        pnlPeliculas.add(scrPnlTablaPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 810, 470));

        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Titulo:");
        pnlPeliculas.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        txtTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(0, 0, 0));
        txtTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        pnlPeliculas.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 280, 40));

        lblGeneros.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblGeneros.setForeground(new java.awt.Color(255, 255, 255));
        lblGeneros.setText("Generos:");
        pnlPeliculas.add(lblGeneros, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        lblClasificacion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblClasificacion.setForeground(new java.awt.Color(255, 255, 255));
        lblClasificacion.setText("Clasificacion:");
        pnlPeliculas.add(lblClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));

        cmbClasificacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmbClasificacion.setForeground(new java.awt.Color(0, 0, 0));
        cmbClasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "AA", "A", "B", "B15", "C", "D" }));
        pnlPeliculas.add(cmbClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 280, 40));

        lblDuracion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblDuracion.setForeground(new java.awt.Color(255, 255, 255));
        lblDuracion.setText("Duracion (Minutos):");
        pnlPeliculas.add(lblDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        spnDuracion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnlPeliculas.add(spnDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 280, 40));

        btnGuardar.setBackground(new java.awt.Color(12, 93, 140));
        btnGuardar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlPeliculas.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, -1, -1));

        btnEditar.setBackground(new java.awt.Color(12, 93, 140));
        btnEditar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlPeliculas.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 110, -1));

        btnEliminar.setBackground(new java.awt.Color(12, 93, 140));
        btnEliminar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        pnlPeliculas.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 120, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestionar Peliculas");
        pnlPeliculas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        pnlGeneros.setBackground(new java.awt.Color(9, 79, 138));
        pnlGeneros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        pnlGeneros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkComedia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkComedia.setForeground(new java.awt.Color(255, 255, 255));
        chkComedia.setText("Comedia");
        pnlGeneros.add(chkComedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        chkComedia.getAccessibleContext().setAccessibleParent(pnlGeneros);

        chkCienciaFiccion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkCienciaFiccion.setForeground(new java.awt.Color(255, 255, 255));
        chkCienciaFiccion.setText("Ciencia Ficcion");
        pnlGeneros.add(chkCienciaFiccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));
        chkCienciaFiccion.getAccessibleContext().setAccessibleParent(pnlGeneros);

        chkTerror.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkTerror.setForeground(new java.awt.Color(255, 255, 255));
        chkTerror.setText("Terror");
        pnlGeneros.add(chkTerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        chkTerror.getAccessibleContext().setAccessibleParent(pnlGeneros);

        chkDrama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkDrama.setForeground(new java.awt.Color(255, 255, 255));
        chkDrama.setText("Drama");
        pnlGeneros.add(chkDrama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        chkDrama.getAccessibleContext().setAccessibleParent(pnlGeneros);

        chkAventura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkAventura.setForeground(new java.awt.Color(255, 255, 255));
        chkAventura.setText("Aventura");
        pnlGeneros.add(chkAventura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        chkAventura.getAccessibleContext().setAccessibleParent(pnlGeneros);

        chkAccion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkAccion.setForeground(new java.awt.Color(255, 255, 255));
        chkAccion.setText("Accion");
        pnlGeneros.add(chkAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        chkAccion.getAccessibleContext().setAccessibleParent(pnlGeneros);

        pnlPeliculas.add(pnlGeneros, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 160, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPeliculas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPeliculas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarPelicula();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkAccion;
    private javax.swing.JCheckBox chkAventura;
    private javax.swing.JCheckBox chkCienciaFiccion;
    private javax.swing.JCheckBox chkComedia;
    private javax.swing.JCheckBox chkDrama;
    private javax.swing.JCheckBox chkTerror;
    private javax.swing.JComboBox<String> cmbClasificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblClasificacion;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblGeneros;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlGeneros;
    private javax.swing.JPanel pnlPeliculas;
    private javax.swing.JScrollPane scrPnlTablaPeliculas;
    private javax.swing.JSpinner spnDuracion;
    private javax.swing.JTable tblPeliculas;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
