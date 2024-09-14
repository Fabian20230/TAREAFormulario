package umg.principaltio.FormularioT;

import umg.principaltio.BaseDeDatos.Model.Datos;
import umg.principaltio.BaseDeDatos.Model.Usuario;
import umg.principaltio.BaseDeDatos.Services.DatosService;
import umg.principaltio.BaseDeDatos.Services.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tb_usuarios {
    private JTextField textFieldId;
    private JTextField textFieldCarne;
    private JTextField textFieldNombre;
    private JTextField textFieldCorreo;
    private JTextField textFieldSeccion;
    private JTextField textFieldTelegram;
    private JTextField textFieldActivo;
    private JPanel USUARIOSS;
    private JButton CREARButton;
    private JButton CARGARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private JButton REGRESARButton;

    private Usuario Usuario;
    private UsuarioService UsuarioService;

    public Tb_usuarios() {

        UsuarioService = new UsuarioService();
        Usuario = new Usuario(0, "", "", "", "", 0, "");

        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario.setCarne(textFieldCarne.getText());
                Usuario.setNombre(textFieldNombre.getText());
                Usuario.setCorreo(textFieldCorreo.getText());
                Usuario.setSeccion(textFieldSeccion.getText());

                try {
                    String telegramText = textFieldTelegram.getText();
                    if (telegramText.isEmpty()) {
                        throw new NumberFormatException("El campo de Telegram no puede estar vacío");
                    }
                    Usuario.setTelegramId(Long.parseLong(telegramText));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    return;
                }

                Usuario.setActivo(textFieldActivo.getText());

                try {
                    UsuarioService.addUsuario(Usuario);
                    JOptionPane.showMessageDialog(null, "Datos ingresados exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar los datos: " + ex.getMessage());
                }
            }
        });

        CARGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUsuario = textFieldId.getText().isEmpty() ? 0 : Integer.parseInt(textFieldId.getText());

                try {
                    Usuario UsuarioEncontrado = UsuarioService.getUsuarioById(idUsuario);
                    if (UsuarioEncontrado != null) {
                        textFieldId.setText(String.valueOf(UsuarioEncontrado.getIdUsuario()));
                        textFieldCarne.setText(UsuarioEncontrado.getCarne());
                        textFieldNombre.setText(UsuarioEncontrado.getNombre());
                        textFieldCorreo.setText(UsuarioEncontrado.getCorreo());
                        textFieldSeccion.setText(UsuarioEncontrado.getSeccion());
                        textFieldTelegram.setText(String.valueOf(UsuarioEncontrado.getTelegramId()));
                        textFieldActivo.setText(UsuarioEncontrado.getActivo());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el ID: " + idUsuario);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + ex.getMessage());
                }
            }
        });

        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUsuario = textFieldId.getText().isEmpty() ? 0 : Integer.parseInt(textFieldId.getText());
                Usuario.setCarne(textFieldCarne.getText());
                Usuario.setNombre(textFieldNombre.getText());
                Usuario.setCorreo(textFieldCorreo.getText());
                Usuario.setSeccion(textFieldSeccion.getText());

                try {
                    String telegramText = textFieldTelegram.getText();
                    if (telegramText.isEmpty()) {
                        throw new NumberFormatException("El campo de Telegram no puede estar vacío");
                    }
                    Usuario.setTelegramId(Long.parseLong(telegramText));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    return;
                }

                Usuario.setActivo(textFieldActivo.getText());
                Usuario.setIdUsuario(idUsuario);

                try {
                    UsuarioService.updateUsuario(Usuario);
                    JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + ex.getMessage());
                }
            }
        });

        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUsuario = textFieldId.getText().isEmpty() ? 0 : Integer.parseInt(textFieldId.getText());

                try {
                    UsuarioService.deleteUsuario(idUsuario);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
                }
            }
        });

        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("frmFormularioTarea");
                frame.setContentPane(new frmFormularioTarea().getPEGRILOSO());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(USUARIOSS).dispose();
            }
        });


    }






        public static void main(String[] args) {
        JFrame frame = new JFrame("Tb_usuarios");
        frame.setContentPane(new Tb_usuarios().USUARIOSS);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
