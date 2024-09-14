package umg.principaltio.FormularioT;

import umg.principaltio.BaseDeDatos.Model.EquipoChampions;
import umg.principaltio.BaseDeDatos.Services.EquipoChampionsService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JChampions {
    private JPanel Panelc;
    private JTextField IDTextField;
    private JTextField NOMBREtextField;
    private JTextField PAISTextField;
    private JTextField CIUDADTextField;
    private JTextField ESTADIOTextField;
    private JTextField FUNDACIONTextField;
    private JTextField ENTRENADORTextField;
    private JTextField WEBTextField;
    private JTextField FACEBOOKTextField;
    private JTextField TWITTERTextField;
    private JTextField INSTAGRAMTextField;
    private JTextField PATROCINADORTextField;
    private JTextField CREADOTextField;
    private JButton CREARButton;
    private JButton CARGARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private JButton REGRESARButton;



private EquipoChampions EquipoChampions;
private EquipoChampionsService EquipoChampionsService;

    public JChampions() {

        EquipoChampionsService = new EquipoChampionsService();
        EquipoChampions = new EquipoChampions(0, "", "", "", "",0 , "", "", "", "", "", "",  null);


        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fundacion = Integer.parseInt(FUNDACIONTextField.getText());
                    if (fundacion < 1801 || fundacion > 2155) { // Rango válido para el tipo YEAR en MySQL
                        throw new NumberFormatException("El año de fundación debe estar entre 1801 y 2155");
                    }
                    EquipoChampions.setFundacion(fundacion);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: llenar el campo de fundación con un año válido");
                    return;
                }

                // Asignar los valores de los campos de texto
                EquipoChampions.setNombre(NOMBREtextField.getText());
                EquipoChampions.setPais(PAISTextField.getText());
                EquipoChampions.setCiudad(CIUDADTextField.getText());
                EquipoChampions.setEstadio(ESTADIOTextField.getText());
                EquipoChampions.setEntrenador(ENTRENADORTextField.getText());
                EquipoChampions.setWebOficial(WEBTextField.getText());
                EquipoChampions.setFacebook(FACEBOOKTextField.getText());
                EquipoChampions.setTwitter(TWITTERTextField.getText());
                EquipoChampions.setInstagram(INSTAGRAMTextField.getText());
                EquipoChampions.setPatrocinadorPrincipal(PATROCINADORTextField.getText());

                try {
                    // Llamar al servicio para agregar el equipo
                    EquipoChampionsService equipoService = new EquipoChampionsService();
                    equipoService.agregarNuevoEquipo(EquipoChampions);

                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Equipo ingresado exitosamente");
                } catch (Exception ex) {
                    // Mostrar mensaje de error
                    JOptionPane.showMessageDialog(null, "Error al ingresar el equipo: " + ex.getMessage());
                }
            }
        });

        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar el año de fundación
                    int fundacion = Integer.parseInt(FUNDACIONTextField.getText());
                    if (fundacion < 1801 || fundacion > 2155) {
                        throw new NumberFormatException("El año de fundación debe estar entre 1801 y 2155");
                    }
                    EquipoChampions.setFundacion(fundacion);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: llenar el campo de fundación con un año válido");
                    return;
                }

                // Asignar los valores de los campos de texto
                EquipoChampions.setNombre(NOMBREtextField.getText());
                EquipoChampions.setPais(PAISTextField.getText());
                EquipoChampions.setCiudad(CIUDADTextField.getText());
                EquipoChampions.setEstadio(ESTADIOTextField.getText());
                EquipoChampions.setEntrenador(ENTRENADORTextField.getText());
                EquipoChampions.setWebOficial(WEBTextField.getText());
                EquipoChampions.setFacebook(FACEBOOKTextField.getText());
                EquipoChampions.setTwitter(TWITTERTextField.getText());
                EquipoChampions.setInstagram(INSTAGRAMTextField.getText());
                EquipoChampions.setPatrocinadorPrincipal(PATROCINADORTextField.getText());

                try {
                    // Llamar al servicio para actualizar el equipo
                    EquipoChampionsService equipoService = new EquipoChampionsService();
                    equipoService.actualizarEquipo(EquipoChampions); // Debes implementar este método en el servicio

                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente");
                } catch (Exception ex) {
                    // Mostrar mensaje de error
                    JOptionPane.showMessageDialog(null, "Error al actualizar el equipo: " + ex.getMessage());
                }
            }
        });

        CARGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(IDTextField.getText()); // Suponiendo que tienes un campo para ingresar el ID

                    EquipoChampionsService equipoService = new EquipoChampionsService();
                    EquipoChampions equipo = equipoService.cargarEquipoPorId(idEquipo); // Debes haber implementado este método

                    if (equipo != null) {
                        // Cargar los datos del equipo en los campos de texto
                        NOMBREtextField.setText(equipo.getNombre());
                        PAISTextField.setText(equipo.getPais());
                        CIUDADTextField.setText(equipo.getCiudad());
                        ESTADIOTextField.setText(equipo.getEstadio());
                        FUNDACIONTextField.setText(String.valueOf(equipo.getFundacion()));
                        ENTRENADORTextField.setText(equipo.getEntrenador());
                        WEBTextField.setText(equipo.getWebOficial());
                        FACEBOOKTextField.setText(equipo.getFacebook());
                        TWITTERTextField.setText(equipo.getTwitter());
                        INSTAGRAMTextField.setText(equipo.getInstagram());
                        PATROCINADORTextField.setText(equipo.getPatrocinadorPrincipal());
                    } else {
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: ingresar un ID válido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar el equipo: " + ex.getMessage());
                }
            }
        });


        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(IDTextField.getText()); // Suponiendo que tienes un campo para ingresar el ID

                    int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el equipo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        EquipoChampionsService equipoService = new EquipoChampionsService();
                        equipoService.eliminarEquipo(idEquipo); // Debes implementar este método en el servicio

                        // Limpiar los campos después de eliminar
                        NOMBREtextField.setText("");
                        PAISTextField.setText("");
                        CIUDADTextField.setText("");
                        ESTADIOTextField.setText("");
                        FUNDACIONTextField.setText("");
                        ENTRENADORTextField.setText("");
                        WEBTextField.setText("");
                        FACEBOOKTextField.setText("");
                        TWITTERTextField.setText("");
                        INSTAGRAMTextField.setText("");
                        PATROCINADORTextField.setText("");

                        // Mostrar mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: ingresar un ID válido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el equipo: " + ex.getMessage());
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
                SwingUtilities.getWindowAncestor(Panelc).dispose();
            }
        });



    }















    public static void main(String[] args) {
        JFrame frame = new JFrame("JChampions");
        frame.setContentPane(new JChampions().Panelc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
