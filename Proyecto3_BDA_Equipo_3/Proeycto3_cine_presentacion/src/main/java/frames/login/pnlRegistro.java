/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frames.login;

import controllers.UsuarioController;
import controllers.factory.FabricaControllers;
import dto.usuarios.RegistroUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import excepciones.presentacion.ControllerException;
import java.awt.Color;
import java.time.LocalDate;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author jalt2
 */
public class PnlRegistro extends javax.swing.JPanel {
    private final FrmLogin frmLogin;
    private final UsuarioController control;
    private final String REGEX_TELEFONO = "^[0-9]{10}$";
    private final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{8,}$";
    
    /**
     * Creates new form pnlRegistro
     * @param frmLogin
     */
    public PnlRegistro(FrmLogin frmLogin) {
        initComponents();
        this.control = FabricaControllers.getUsuarioController();
        limpiarCampos();
        limpiarBordes();
        this.frmLogin = frmLogin;
        
    }
    
    private void registrarUsuario(){
        try {
            limpiarBordes();
            
            String nombre = this.txtNombre.getText();
            String apellidoPaterno = this.txtApellidoPaterno.getText();
            String apellidoMaterno = this.txtApellidoMaterno.getText();
            LocalDate fchNacimiento = this.dateFechaNacimiento.getDate();
            String correo = this.txtEmail.getText();
            String telefono = this.txtTelefono.getText();
            String password = this.txtPassword.getText();
            char[] confirmPasswordChars = txtConfirmarPassword.getPassword();
            String confirmarPassword = new String(confirmPasswordChars);
            
            //Validar si todos los campos son invalidos
            if (
                nombre.trim().isEmpty() && 
                apellidoPaterno.trim().isEmpty() && 
                correo.trim().isEmpty() &&
                telefono.trim().isEmpty() &&
                password.trim().isEmpty() &&
                confirmarPassword.trim().isEmpty()
            ){
                JOptionPane.showMessageDialog(
                        this,
                        "Rellene los campos de color rojo.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                pintarBordesRojo();
                return;
            }
            
            //Validar nombre 
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario que ingrese un nombre.",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtNombre.setBorder(
                    BorderFactory
                            .createLineBorder(Color.RED,2)
                );
                return;  
            }
            
            //Validar apellido Paterno
            if (apellidoPaterno.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario que ingrese un apellido paterno.",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtApellidoPaterno.setBorder(
                    BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            //Validar apellido Materno
            if (apellidoMaterno.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario que ingrese un apellido materno.",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtApellidoMaterno.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            //Validar fechaNacimiento
            if (fchNacimiento.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(
                                this,
                                "La fecha de nacimiento no puede ser mayor a la actual.",
                                "Facha no valida",
                                JOptionPane.WARNING_MESSAGE
                        );
                dateFechaNacimiento.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return;
            }
            
            //Validar correo
            if (correo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario que ingrese un correo",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtEmail.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            //Validar telefono
            if (telefono.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario que ingrese un telefono",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtTelefono.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            if (!telefono.trim().matches(REGEX_TELEFONO)) {
                JOptionPane.showMessageDialog(
                        this,
                        "El teléfono debe contener exactamente 10 dígitos numéricos",
                        "Telefono no valido",
                        JOptionPane.WARNING_MESSAGE
                );
                txtTelefono.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            //Validar contrseña
            if (password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario que ingrese una contraseña",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtPassword.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            //Regex password
            if (!password.trim().matches(REGEX_PASSWORD)) {
                JOptionPane.showMessageDialog(
                        this,
                        """
                            La contraseña debe tener:
                            - mínimo 8 caracteres
                            - una mayúscula
                            - una minúscula
                            - un número
                            - un carácter especial
                        """,
                        "Contraseña no segura",
                        JOptionPane.WARNING_MESSAGE
                );
                txtPassword.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            //Confirmar contraseña
            if (confirmarPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Campo confirmar contraseña obligatorio.",
                        "Dato faltante",
                        JOptionPane.WARNING_MESSAGE
                );
                txtConfirmarPassword.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            if (!password.equals(confirmarPassword)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Las contraseñas no coinciden",
                        "Contraseñas",
                        JOptionPane.WARNING_MESSAGE
                );
                
                txtPassword.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                
                txtConfirmarPassword.setBorder(BorderFactory.createLineBorder(
                        Color.RED,
                        2
                    )
                );
                return; 
            }
            
            
            //Registrar despues de validar
            RegistroUsuarioDTO usuarioRegistro = new RegistroUsuarioDTO(nombre, apellidoMaterno, apellidoPaterno, correo, confirmarPassword, telefono, fchNacimiento);
            
            Arrays.fill(confirmPasswordChars, '\0');
            
            UsuarioDTO usuarioGuardado = control.guardarUsuario(usuarioRegistro);

            if (usuarioGuardado != null) {
                JOptionPane.showMessageDialog(this, "Te registraste correctamente.");
                limpiarCampos();
                
                txtNombre.requestFocus();
            }else{
                JOptionPane.showMessageDialog(
                    this,
                    "Ocurrio un error al registrarte",
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
        }
    }
    
    private void limpiarCampos(){
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        dateFechaNacimiento.setDate(LocalDate.of(2008, 1, 1));
        txtEmail.setText("");
        txtTelefono.setText("");
        txtPassword.setText("");
        txtConfirmarPassword.setText("");
    }
    
    private void pintarBordesRojo(){
        txtNombre.setBorder(
            BorderFactory.createLineBorder(
                    Color.RED,
                    2
            )
        );
        
        txtApellidoPaterno.setBorder(
            BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        txtApellidoMaterno.setBorder(
            BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );

        txtEmail.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        txtTelefono.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        txtPassword.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
        
        txtConfirmarPassword.setBorder(BorderFactory.createLineBorder(
                Color.RED,
                2
            )
        );
    }

    private void limpiarBordes(){
        txtNombre.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );
        
        txtApellidoPaterno.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );
        
        txtApellidoMaterno.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );
        
        dateFechaNacimiento.setBorder(
                UIManager.getBorder(
                        "DatePicker.border"
                )
        );

        txtEmail.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );

        txtTelefono.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );
        
        txtPassword.setBorder(
                UIManager.getBorder(
                        "TextField.border"
                )
        );
        
        txtConfirmarPassword.setBorder(
                UIManager.getBorder(
                        "TextField.border"
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

        pnlContenidoRegistro = new javax.swing.JPanel();
        pnlContenido = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dateFechaNacimiento = new com.github.lgooddatepicker.components.DatePicker();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtConfirmarPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();
        btnYaTengoUnaCuenta = new javax.swing.JButton();

        pnlContenidoRegistro.setBackground(new java.awt.Color(9, 79, 138));
        pnlContenidoRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContenido.setBackground(new java.awt.Color(9, 79, 138));
        pnlContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlContenidoRegistro.add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 459, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO DE USUARIOS");
        pnlContenidoRegistro.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jPanel1.setBackground(new java.awt.Color(12, 93, 140));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre Completo y Fecha de Nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre/s:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 200, 30));

        txtApellidoPaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        jPanel1.add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 200, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido Paterno:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        txtApellidoMaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        jPanel1.add(txtApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 200, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido Materno (Opcional):");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        dateFechaNacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 58, 87), 2));
        jPanel1.add(dateFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        pnlContenidoRegistro.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 510, 200));

        jPanel2.setBackground(new java.awt.Color(12, 93, 140));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contacto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(7, 58, 87), 2, true));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("E-mail:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Telefono (10 digitos):");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        txtTelefono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(7, 58, 87), 2, true));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 200, 30));

        pnlContenidoRegistro.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 510, 180));

        jPanel4.setBackground(new java.awt.Color(12, 93, 140));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contraseña", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtConfirmarPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(7, 58, 87), 2, true));
        jPanel4.add(txtConfirmarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 200, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Confirmar Contraseña:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(7, 58, 87), 2, true));
        jPanel4.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        pnlContenidoRegistro.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 510, 170));

        btnRegistrarse.setText("REGISTRARSE");
        btnRegistrarse.setBackground(new java.awt.Color(12, 93, 140));
        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        pnlContenidoRegistro.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 730, -1, 40));

        btnYaTengoUnaCuenta.setText("YA TENGO UNA CUENTA");
        btnYaTengoUnaCuenta.setBackground(new java.awt.Color(12, 93, 140));
        btnYaTengoUnaCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnYaTengoUnaCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnYaTengoUnaCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYaTengoUnaCuentaActionPerformed(evt);
            }
        });
        pnlContenidoRegistro.add(btnYaTengoUnaCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 790, 250, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenidoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenidoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnYaTengoUnaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYaTengoUnaCuentaActionPerformed
        // TODO add your handling code here:
        frmLogin.mostrarPanelLogin();
    }//GEN-LAST:event_btnYaTengoUnaCuentaActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        // TODO add your handling code here:
        registrarUsuario();
    }//GEN-LAST:event_btnRegistrarseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JButton btnYaTengoUnaCuenta;
    private com.github.lgooddatepicker.components.DatePicker dateFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlContenidoRegistro;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JPasswordField txtConfirmarPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
