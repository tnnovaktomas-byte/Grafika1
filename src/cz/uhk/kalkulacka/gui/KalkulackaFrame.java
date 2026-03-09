package cz.uhk.kalkulacka.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class KalkulackaFrame extends JFrame {

    private JTextField tfPrvnicislo;
    private JTextField tfDruhyicislo;
    private JLabel lbVysledek;

    public KalkulackaFrame() {
//        super("Kalkulacka"); jina moznosz
        setTitle("Priklad ony s poctni operaci");

        setSize(640, 480);

        initGui();

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGui() {
        tfPrvnicislo = new JTextField("0", 8); // cisla jsou informace pro layout
        tfDruhyicislo = new JTextField("0", 8);
        lbVysledek = new JLabel ("0");
        JPanel pnlSever = new JPanel();

        //naskladani do layoutu
        pnlSever.add(tfPrvnicislo);
        pnlSever.add(new JLabel("+"));
        pnlSever.add(tfDruhyicislo);
        JButton btRovnaSe = new JButton(" = ");
        pnlSever.add(btRovnaSe);
        pnlSever.add(lbVysledek);

        //dame panel na sever okna
        add(pnlSever, BorderLayout.NORTH);

        // pridame obsluhu/listener tlacitak
        btRovnaSe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prvni = tfPrvnicislo.getText();
                String druhy = tfDruhyicislo.getText();
                Double prvniDouble;
                Double druhyDouble;

                try {
                    prvniDouble = Double.parseDouble(prvni);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(KalkulackaFrame.this,
                            "Chybny foramt prvniho cisla",
                            "Chyba",
                                JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    druhyDouble = Double.parseDouble(druhy);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(KalkulackaFrame.this,
                            "Chybny foramt prvniho cisla",
                            "Chyba",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                var vysledek = prvniDouble + druhyDouble;

                lbVysledek.setText(String.valueOf(vysledek));
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() { //uz implementuje "MouseMotionListener"
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = (Graphics2D) getGraphics();
                g.fillOval(e.getX(), e.getY(), 5, 5);
            }
        });
    }

    static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KalkulackaFrame().setVisible(true);
            }
        });
    }
}
