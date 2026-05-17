/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frames.admin;

import controllers.FuncionController;
import controllers.factory.FabricaControllers;
import dto.funciones.FuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import dtos.PeliculaDTO;
import excepciones.presentacion.ControllerException;
import java.awt.Color;
import java.awt.Frame;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import tables.ModeloTablaFunciones;

/**
 *
 * @author jalt2
 */
public class PnlGestionFunciones extends javax.swing.JPanel {
    private final FuncionController control;
    private PeliculaDTO peliculaSeleccionada;
    
    /**
     * Creates new form PnlGestionFunciones
     */
    public PnlGestionFunciones() {
        initComponents();
        this.control = FabricaControllers.getFuncionController();
        try {
            llenarTablaFunciones();
        } catch (ControllerException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        limpiarCampos();
    }
    
    private void registrarFuncion(){ 
        limpiarBordes();
        
        Object salaSeleccionada = cmbSala.getSelectedItem();
        LocalDate fecha = dateFechaFuncion.getDate();
        LocalTime hora = timeFuncion.getTime();
        
        //Validar si todos los campos son invalidos
        // Validar campos vacíos
        if (peliculaSeleccionada == null && cmbSala.getSelectedIndex() == 0 && fecha == null && hora == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );

            pintarBordesRojo();

            return;
        }
        
        //Validaciones
        if (peliculaSeleccionada == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "La película es obligatoria.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPelicula.setBorder(
                    BorderFactory.createLineBorder(
                            Color.RED,
                            2
                    )
            );

            return;
        }
        
        if (txtPelicula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "La película es obligatoria.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPelicula.setBorder(
                    BorderFactory.createLineBorder(
                            Color.RED,
                            2
                    )
            );
            
            peliculaSeleccionada=null;
            
            return;
        }
        
        if (cmbSala.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debes seleccionar una sala.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
            );

            cmbSala.setBorder(
                    BorderFactory.createLineBorder(
                            Color.RED,
                            2
                    )
            );

            return;
        }
        
        if (fecha==null) {
            JOptionPane.showMessageDialog(
                    this,
                    "La fecha es obligatoria.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
                );
            dateFechaFuncion.setBorder(
                    BorderFactory.createLineBorder(Color.RED,2)
            );
            return;
        }
        
        if (hora==null) {
            JOptionPane.showMessageDialog(
                    this,
                    "La hora es obligatoria.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
                );
            timeFuncion.setBorder(
                    BorderFactory.createLineBorder(Color.RED,2)
            );
            return;
        }
        
        try {
            Integer numSala = Integer.valueOf(salaSeleccionada.toString());
            
            RegistrarFuncionDTO funcionDTO = new RegistrarFuncionDTO(peliculaSeleccionada.getId(),peliculaSeleccionada.getTitulo(), numSala, fecha, hora);

            FuncionDTO funcionGuardada = control.guardarFuncion(funcionDTO);


            if (funcionGuardada != null) {
                JOptionPane.showMessageDialog(this, "Funcion guardada correctamente.");
                limpiarCampos();
                llenarTablaFunciones();
            }
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }   
    }
    
    private void llenarTablaFunciones() throws ControllerException{
        try {
            List<FuncionDTO> funciones = control.listarFunciones();
        
            ModeloTablaFunciones modelo = new ModeloTablaFunciones(funciones);

            tblFunciones.setModel(modelo);
            
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
    
    private void limpiarCampos(){
        txtPelicula.setText("");
        cmbSala.setSelectedIndex(0);
        dateFechaFuncion.setDate(LocalDate.now());
        timeFuncion.setTime(LocalTime.of(12, 0));
        
    }   
    
    private void limpiarBordes() {

        txtPelicula.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );

        cmbSala.setBorder(
                UIManager.getBorder(
                        "ComboBox.border"
                )
        );

        dateFechaFuncion.setBorder(
                UIManager.getBorder(
                        "Spinner.border"
                )
        );
        
        timeFuncion.setBorder(
                UIManager.getBorder(
                        "Spinner.border"
                )
        );
    }
    
    private void pintarBordesRojo(){
        txtPelicula.setBorder(
            BorderFactory.createLineBorder(
                    Color.RED,
                    2
            )
        );
        
        cmbSala.setBorder(
            BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        dateFechaFuncion.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        timeFuncion.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
    }
    
    private void abrirDialogPeliculas() {

        DialogSeleccionarPelicula dialog = new DialogSeleccionarPelicula(
                (Frame) SwingUtilities.getWindowAncestor(this),
                true
            );
        
        dialog.setVisible(true);

        PeliculaDTO pelicula = dialog.getPeliculaSeleccionada();

        if (pelicula != null) {

            this.peliculaSeleccionada = pelicula;

            txtPelicula.setText(
                    pelicula.getTitulo()
            );
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFunciones = new javax.swing.JPanel();
        scrPaneFunciones = new javax.swing.JScrollPane();
        tblFunciones = new javax.swing.JTable();
        lblGestionar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateFechaFuncion = new com.github.lgooddatepicker.components.DatePicker();
        timeFuncion = new com.github.lgooddatepicker.components.TimePicker();
        txtPelicula = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        cmbSala = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();

        pnlFunciones.setBackground(new java.awt.Color(9, 79, 138));
        pnlFunciones.setMinimumSize(new java.awt.Dimension(1430, 683));
        pnlFunciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrPaneFunciones.setViewportView(tblFunciones);

        pnlFunciones.add(scrPaneFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 870, 470));

        lblGestionar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblGestionar.setForeground(new java.awt.Color(255, 255, 255));
        lblGestionar.setText("Gestionar Funciones");
        pnlFunciones.add(lblGestionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pelicula:");
        pnlFunciones.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sala:");
        pnlFunciones.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha:");
        pnlFunciones.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hora:");
        pnlFunciones.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        dateFechaFuncion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        dateFechaFuncion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnlFunciones.add(dateFechaFuncion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 240, 30));

        timeFuncion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        timeFuncion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnlFunciones.add(timeFuncion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 240, 30));

        txtPelicula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPelicula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        pnlFunciones.add(txtPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 240, 30));

        btnSeleccionar.setBackground(new java.awt.Color(12, 93, 140));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        pnlFunciones.add(btnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 110, 30));

        cmbSala.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmbSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una sala", "1", "2", "3", "4", "5" }));
        cmbSala.setToolTipText("");
        cmbSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        pnlFunciones.add(cmbSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 240, 30));

        btnRegistrar.setBackground(new java.awt.Color(12, 93, 140));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlFunciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 120, 30));

        btnEditar.setBackground(new java.awt.Color(12, 93, 140));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        pnlFunciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 120, 30));

        btnDesactivar.setBackground(new java.awt.Color(12, 93, 140));
        btnDesactivar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDesactivar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesactivar.setText("DESACTIVAR");
        pnlFunciones.add(btnDesactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 520, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFunciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFunciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        registrarFuncion();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        abrirDialogPeliculas();
    }//GEN-LAST:event_btnSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cmbSala;
    private com.github.lgooddatepicker.components.DatePicker dateFechaFuncion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblGestionar;
    private javax.swing.JPanel pnlFunciones;
    private javax.swing.JScrollPane scrPaneFunciones;
    private javax.swing.JTable tblFunciones;
    private com.github.lgooddatepicker.components.TimePicker timeFuncion;
    private javax.swing.JTextField txtPelicula;
    // End of variables declaration//GEN-END:variables
}
