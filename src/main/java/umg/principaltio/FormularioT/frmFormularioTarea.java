package umg.principaltio.FormularioT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmFormularioTarea {


    private JPanel PEGRILOSO;
    private JButton TABLA_DATOSButton;
    private JButton TABLA_USUARIOSButton;
    private JButton TABLA_CHAMPIONSButton;
    private JButton FISHI_HIMButton;
    private JLabel TA;

    public JPanel getPEGRILOSO() {
        return PEGRILOSO;
    }

    public frmFormularioTarea() {
        TABLA_DATOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDATOS.main(new String[]{});
            }
        });

        TABLA_USUARIOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tb_usuarios.main(new String[]{});
            }
        });

        TABLA_CHAMPIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JChampions.main(new String[]{});
            }
        });

        FISHI_HIMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }





    public static void main(String[] args) {
        JFrame frame = new JFrame("frmFormularioTarea");
        frame.setContentPane(new frmFormularioTarea().PEGRILOSO);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
