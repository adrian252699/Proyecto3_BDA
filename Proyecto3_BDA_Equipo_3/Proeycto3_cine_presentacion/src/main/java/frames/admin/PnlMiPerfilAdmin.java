/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frames.admin;

import controllers.UsuarioController;
import controllers.factory.FabricaControllers;
import dto.usuarios.ActualizarCorreoDTO;
import dto.usuarios.ActualizarPasswordDTO;
import dto.usuarios.ActualizarUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import excepciones.presentacion.ControllerException;
import java.time.LocalDate;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author jalt2
 */
public class PnlMiPerfilAdmin extends javax.swing.JPanel {
    private final UsuarioController control;
    private UsuarioDTO admin;
    /**
     * Creates new form PnlMiPerfilAdmin
     * @param admin
     */
    public PnlMiPerfilAdmin(UsuarioDTO admin) {
        initComponents();
        this.control = FabricaControllers.getUsuarioController();
        this.admin = admin;
        cargarCamposCliente();
    }
    
    private void actualizarPassword(){
        char[] passwordChars = txtPassword.getPassword();
        String passwordNueva = new String(passwordChars);
        Arrays.fill(passwordChars, '\0');
        
        if (passwordNueva.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "La contraseña nueva no puede estar vacia.",
                "Error",
                JOptionPane.WARNING_MESSAGE
            );  
            return;
        }
        
        String passwordActual = autenticarUsuario();
        
        if (passwordActual==null) {
            return;
        }
        
        //Recuperar la contraseña ingresada para autenticarse
        ActualizarPasswordDTO passwordDTO = new ActualizarPasswordDTO(passwordActual, passwordNueva);
        
        try {
            //Actualizar password
            UsuarioDTO usuarioActualizado = control.actualizarPassword(admin.getId(), passwordDTO);
            
            this.admin = usuarioActualizado;
            
            txtPassword.setText("");
            
            JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.");
            
        } catch (ControllerException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            ); 
        }
    }
    
    private void actualizarCorreo(){
        try {
            String correo = txtEmail.getText().trim(); 
            String correoNuevo = txtEmailNuevo.getText().trim();

            //Validar
            if (correoNuevo == null || correoNuevo.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "El correo nuevo no puede estar vacio.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );  
                return;
            }

            if (correo.equals(correoNuevo)) {
                JOptionPane.showMessageDialog(
                        this,
                        "El correo nuevo no puede ser igual al correo actual.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );  
                return;
            }
            
            String passwordActual = autenticarUsuario();
            
            if (passwordActual==null) {
                return;
            }
            
            ActualizarCorreoDTO correoDTO = new ActualizarCorreoDTO(correoNuevo, passwordActual);
            
            
            
            UsuarioDTO usuarioCorreoActualizado = control.actualizarCorreo(admin.getId(), correoDTO);
            
            this.admin = usuarioCorreoActualizado;
            
            cargarCamposCliente();
            
            txtEmailNuevo.setText("");
            
            JOptionPane.showMessageDialog(this, "Correo actualizado correctamente.");
            
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );    
        }
    }
    
    private void cargarCamposCliente(){
        //Datos Personales
        txtNombre.setText(admin.getNombre());
        txtApellidoMaterno.setText(admin.getApellidoMaterno());
        txtApellidoPaterno.setText(admin.getApellidoPaterno());
        datePickerFchNacimiento.setDate(admin.getFechaNacimiento());
        txtTelefono.setText(admin.getTelefono());
        
        //Datos Acceso
        txtEmail.setText(admin.getEmail()); 
    }
    
    private void actualizarDatosPersonales(){
        try {
            //Guardar texto de los campos despues de habilitarlos
            String nombre = txtNombre.getText().trim();
            String apellidoPaterno = txtApellidoPaterno.getText().trim();
            String apellidoMaterno = txtApellidoMaterno.getText().trim();
            String telefono = txtTelefono.getText().trim();
            LocalDate fchNacimiento = this.datePickerFchNacimiento.getDate();

            if (nombre.isEmpty()||apellidoPaterno.isEmpty()||telefono.isEmpty()||fchNacimiento==null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ningun campo obligatorio debe ir vacio.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );  
                return;
            }
            
            String passwordActual = autenticarUsuario();
            
            if (passwordActual==null) {
                return;
            }
            
            //Guardar nuevos datos
            ActualizarUsuarioDTO datosActualizar = new ActualizarUsuarioDTO(admin.getId(), nombre, apellidoMaterno, apellidoPaterno, telefono, fchNacimiento, passwordActual);
            UsuarioDTO usuarioActualizado = control.actualizarUsuario(datosActualizar);
            this.admin = usuarioActualizado;
            
            cargarCamposCliente();
            
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
 
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );  
            
        }
    }
    
    private String autenticarUsuario() {

        JPasswordField passwordField = new JPasswordField();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                passwordField,
                "Ingrese su contraseña actual",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }

        String password = new String(passwordField.getPassword());

        if (password.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "La contraseña es obligatoria.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }

        return password;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMiPerfil = new javax.swing.JPanel();
        pnlDatosPersonales = new javax.swing.JPanel();
        txtApellidoPaterno = new javax.swing.JTextField();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        lblApellidoMaterno = new javax.swing.JLabel();
        datePickerFchNacimiento = new com.github.lgooddatepicker.components.DatePicker();
        lblFechaNacimiento = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        btnActualizarDatosPersonales = new javax.swing.JButton();
        pnlDatosAcesso = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        btnActualizarCorreo = new javax.swing.JButton();
        btnActualizarPassword = new javax.swing.JButton();
        lblEmailNuevo = new javax.swing.JLabel();
        txtEmailNuevo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        pnlMiPerfil.setBackground(new java.awt.Color(9, 79, 138));
        pnlMiPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDatosPersonales.setBackground(new java.awt.Color(9, 79, 138));
        pnlDatosPersonales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDatosPersonales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtApellidoPaterno.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosPersonales.add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 260, 30));

        lblApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblApellidoPaterno.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoPaterno.setText("Apellido Paterno:");
        pnlDatosPersonales.add(lblApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosPersonales.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 260, 30));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre:");
        pnlDatosPersonales.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtApellidoMaterno.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosPersonales.add(txtApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 270, 30));

        lblApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblApellidoMaterno.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoMaterno.setText("Apellido Materno:");
        pnlDatosPersonales.add(lblApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, -1, -1));

        datePickerFchNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        datePickerFchNacimiento.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosPersonales.add(datePickerFchNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 260, 30));

        lblFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaNacimiento.setText("Fecha Nacimiento:");
        pnlDatosPersonales.add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosPersonales.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 270, 30));

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("Telefono (10 digitos):");
        pnlDatosPersonales.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        btnActualizarDatosPersonales.setText("Editar Datos Personales");
        btnActualizarDatosPersonales.setBackground(new java.awt.Color(12, 93, 140));
        btnActualizarDatosPersonales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizarDatosPersonales.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarDatosPersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDatosPersonalesActionPerformed(evt);
            }
        });
        pnlDatosPersonales.add(btnActualizarDatosPersonales, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, -1, 30));

        pnlMiPerfil.add(pnlDatosPersonales, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 970, 330));

        pnlDatosAcesso.setBackground(new java.awt.Color(9, 79, 138));
        pnlDatosAcesso.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Acesso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDatosAcesso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosAcesso.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 260, 30));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("E-mail:");
        pnlDatosAcesso.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosAcesso.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 280, 30));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Contraseña nueva:");
        pnlDatosAcesso.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, -1, -1));

        btnActualizarCorreo.setText("Actualizar Correo");
        btnActualizarCorreo.setBackground(new java.awt.Color(12, 93, 140));
        btnActualizarCorreo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizarCorreo.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCorreoActionPerformed(evt);
            }
        });
        pnlDatosAcesso.add(btnActualizarCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 30));

        btnActualizarPassword.setText("Cambiar Contraseña");
        btnActualizarPassword.setBackground(new java.awt.Color(12, 93, 140));
        btnActualizarPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizarPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPasswordActionPerformed(evt);
            }
        });
        pnlDatosAcesso.add(btnActualizarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, -1, 30));

        lblEmailNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmailNuevo.setForeground(new java.awt.Color(255, 255, 255));
        lblEmailNuevo.setText("E-mail nuevo:");
        pnlDatosAcesso.add(lblEmailNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        txtEmailNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmailNuevo.setForeground(new java.awt.Color(0, 0, 0));
        pnlDatosAcesso.add(txtEmailNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 260, 30));

        pnlMiPerfil.add(pnlDatosAcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 970, 180));

        jLabel1.setText("Mi Perfil");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        pnlMiPerfil.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMiPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 1430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMiPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarDatosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarDatosPersonalesActionPerformed
        // TODO add your handling code here:
        actualizarDatosPersonales();
    }//GEN-LAST:event_btnActualizarDatosPersonalesActionPerformed

    private void btnActualizarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCorreoActionPerformed
        // TODO add your handling code here:
        actualizarCorreo();
    }//GEN-LAST:event_btnActualizarCorreoActionPerformed

    private void btnActualizarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPasswordActionPerformed
        // TODO add your handling code here:
        actualizarPassword();
    }//GEN-LAST:event_btnActualizarPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCorreo;
    private javax.swing.JButton btnActualizarDatosPersonales;
    private javax.swing.JButton btnActualizarPassword;
    private com.github.lgooddatepicker.components.DatePicker datePickerFchNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailNuevo;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel pnlDatosAcesso;
    private javax.swing.JPanel pnlDatosPersonales;
    private javax.swing.JPanel pnlMiPerfil;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailNuevo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
