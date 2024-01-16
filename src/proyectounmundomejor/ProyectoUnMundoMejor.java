package proyectounmundomejor;
import com.mysql.cj.protocol.Resultset;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import javax.crypto.Cipher;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import static java.util.Objects.hash;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ProyectoUnMundoMejor {
    
    private static final String urlBD = "jdbc:mysql://localhost:3306/proyectounmundomejor";
    private static final String usuarioBD = "Alvaro_MReyes";
    private static final String contraseñaBD = "cAxF[i1VlxcqXfPv";
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        
        
        JPanel ventanaRegistro = new JPanel(new GridBagLayout());
        
        // Crear un objeto JFrame (ventana)
        JFrame frame = new JFrame("Gestor de contraseñas");
        
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Termina la aplicación al cerrar la ventana
            }
        });
        

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana

        // Crear un objeto JPanel (panel) con GridBagLayout
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

        // Crear un objeto JLabel (etiqueta) y JTextField (campo de texto) para Usuario Inicio
        JLabel labelUsuarioInicio = new JLabel("Usuario Inicio");
        JTextField textFieldUsuarioInicio = new JTextField("Usuario inicio");

        // Crear un objeto JLabel (etiqueta) y JTextField (campo de texto) para Contraseña Inicio
        JLabel labelContraseñaInicio = new JLabel("Contraseña inicio");
        JTextField textFieldContraseñaInicio = new JTextField("Contraseña ");

        JButton botonIniciador = new JButton("Inicio de sesión");

        // Configurar GridBagConstraints y añadir componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        ventanaRegistro.add(labelUsuarioInicio, gbc);

        gbc.gridx = 1;
        ventanaRegistro.add(textFieldUsuarioInicio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        ventanaRegistro.add(labelContraseñaInicio, gbc);

        gbc.gridx = 1;
        ventanaRegistro.add(textFieldContraseñaInicio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        ventanaRegistro.add(botonIniciador, gbc);

        // Añadir el panel al frame
        frame.add(ventanaRegistro);

        // Hacer visible la ventana
        frame.setVisible(true);
            
        

        JPanel panel = new JPanel();
        panel.setVisible(false);
        panel.setVisible(false);

        botonIniciador.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                boolean booleanUserInicio = usuarioBD.equals(textFieldUsuarioInicio.getText());
                boolean booleanContraseñaInicio = contraseñaBD.equals(textFieldContraseñaInicio.getText());
                if (booleanUserInicio && booleanContraseñaInicio) {
                    panel.setVisible(true);
                    ventanaRegistro.setVisible(false);
                    JLabel Acceso = new JLabel("¡Felicidades has accedido a esta base de datos!");
                    panel.add(Acceso);
                    panel.setVisible(true);

                } else {
                    JLabel falloInicio = new JLabel("El usuario o la contraseña son incorrectos");
                    panel.setVisible(false);
                    ventanaRegistro.add(falloInicio);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
        });
                
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection(urlBD,usuarioBD,contraseñaBD);
        
        // Crear un objeto JButton (botón)
        JButton botonIntroducirDatosBD = new JButton("Introducir Datos BD");
        

        // Crear un objeto JLabel (etiqueta)
        JLabel label = new JLabel("Usuario ");
        JTextField usuario = new JTextField("Usuario ");      
  
        JLabel contraseñaEtiqueta = new JLabel("Contraseña");
        JTextField contraseña = new JTextField("Contraseña ");

        JLabel correoelectronicoEtiqueta = new JLabel("Correo electrónico");
        JTextField correoelectronico = new JTextField("Correo electrónico");

        JLabel telefonoEtiqueta = new JLabel("Telefono");
        JTextField telefono = new JTextField("Teléfono");

       
        JLabel etiqueta = new JLabel(" ");

        // Crear un objeto JPanel (panel) para organizar componentes
        

        // Agregar componentes al panel
        panel.add(label);
        panel.add(usuario);
        panel.add(contraseñaEtiqueta);
        panel.add(contraseña);
        panel.add(correoelectronicoEtiqueta);
        panel.add(correoelectronico);
        panel.add(telefonoEtiqueta);
        panel.add(telefono);
        panel.add(botonIntroducirDatosBD);
        panel.add(etiqueta);
        

        // Configurar el diseño del panel (FlowLayout en este caso)
        panel.setLayout(new FlowLayout());

        // Agregar el panel a la ventana
        frame.getContentPane().add(panel);

        botonIntroducirDatosBD.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                
               try {
            Statement sentencia = conexion.createStatement();

            // Obtener los valores introducidos por el usuario
            String usuarioIntroducido = usuario.getText();
            String contraseñaIntroducida = contraseña.getText();
            String correoelectronicoIntroducido = correoelectronico.getText();
            String telefonoIntroducido = telefono.getText();
            int telefonoIntroducidoINT = Integer.parseInt(telefonoIntroducido);
            //String[] datosUsuario = {usuario.getText(), contraseña.getText(),correoelectronico.getText(),telefono.getText()};
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            //byte[] hashUsuarioIntroducido = digest.digest(usuarioIntroducido.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash a una cadena hexadecimal para una visualización más fácil
           StringBuilder hexStringUsuario = new StringBuilder();
           
           byte[] hash = digest.digest(usuarioIntroducido.getBytes(StandardCharsets.UTF_8));
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexStringUsuario.append('0');
                hexStringUsuario.append(hex);
            } 
            
            
            byte[] hashContraseñaIntroducido = digest.digest(contraseñaIntroducida.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash a una cadena hexadecimal para una visualización más fácil
           StringBuilder hexStringContraseña = new StringBuilder();
           byte[] hex = digest.digest(contraseñaIntroducida.getBytes(StandardCharsets.UTF_8));
            for (byte b : hex) {
                String hex3 = Integer.toHexString(0xff & b);
                if (hex3.length() == 1) hexStringContraseña.append('0');
                hexStringContraseña.append(hex3);
            } 
            
          byte[] hashCorreoElectronicoIntroducido = digest.digest(correoelectronicoIntroducido.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash a una cadena hexadecimal para una visualización más fácil
           StringBuilder hexStringCorreoElectronico = new StringBuilder();
           byte[] hex5 = digest.digest(correoelectronicoIntroducido.getBytes(StandardCharsets.UTF_8));
            for (byte b : hex5) {
                String hex6 = Integer.toHexString(0xff & b);
                if (hex6.length() == 1) hexStringCorreoElectronico.append('0');
                hexStringCorreoElectronico.append(hex6);
            } 
           byte[] hashTelefonoIntroducido = digest.digest(telefonoIntroducido.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash a una cadena hexadecimal para una visualización más fácil
           StringBuilder hexStringTelefonoIntroducido = new StringBuilder();
           byte[] hex3 = digest.digest(contraseñaIntroducida.getBytes(StandardCharsets.UTF_8));
            for (byte b : hex3) {
                String hex4 = Integer.toHexString(0xff & b);
                if (hex4.length() == 1) hexStringTelefonoIntroducido.append('0');
                hexStringTelefonoIntroducido.append(hex4);
                
            } 
        // Generar el hash
        /*for(String dato : datosUsuario){
           byte[] hash = digest.digest(dato.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash a una cadena hexadecimal para una visualización más fácil
           StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            } 
            System.out.println(hexString.toString());
            System.out.println(dato);
           
        } */   
        
            // Crear y ejecutar la sentencia SQL
            String sentenciasql = "INSERT INTO registros VALUES(NULL, '" + usuarioIntroducido + "', '"
                    + contraseñaIntroducida + "', '" + correoelectronicoIntroducido + "', '" + telefonoIntroducido + "')";
            //int filasAfectadas = sentencia.executeUpdate(sentenciasql);
            
            String sentenciasql2 = "INSERT INTO registroscifrados VALUES(NULL, '" + hexStringUsuario.toString() + "', '"
                    + hexStringContraseña.toString() + "', '" + hexStringCorreoElectronico + "', '" + hexStringTelefonoIntroducido + "')";
            //int filasAfectadas2 = sentencia.executeUpdate(sentenciasql2);
            JLabel datos = new JLabel("¡LOS DATOS HAN SIDO INTRODUCIDOS CORRECTAMENTE!");
            panel.add(datos);
            datos.setVisible(true);
            
            
            } catch (SQLException ex) {
            ex.printStackTrace(); // Manejar la excepción apropiadamente en una aplicación real
        }       catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ProyectoUnMundoMejor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                /*boolean booleanUserInicio = usuarioBD.equals(textFieldUsuarioInicio.getText());
                boolean booleanContraseñaInicio = contraseñaBD.equals(textFieldContraseñaInicio.getText());
                if (booleanUserInicio && booleanContraseñaInicio) {
                    panel.setVisible(true);
                    ventanaRegistro.setVisible(false);
                    JLabel Acceso = new JLabel("¡Felicidades has accedido a esta base de datos!");
                    panel.add(Acceso);
                    panel.setVisible(true);

                } else {
                    JLabel falloInicio = new JLabel("El usuario o la contraseña son incorrectos");
                    panel.setVisible(false);
                    ventanaRegistro.add(falloInicio);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }*/
            });
        });
            
       
        
    }
        };