package Vista;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Ventana extends JFrame {


    private Logger logger = LogManager.getRootLogger();
    private int ancho = 1200;
    private int alto = 700;
    private Panel panel;

    public Ventana() {
        init1();
    }

    public void init1() {
        setSize(ancho,alto);
        setPreferredSize(new Dimension(ancho,alto));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSeleccionar = new JMenuItem("Seleccionar Txt");

        itemSeleccionar.addActionListener(e -> {
            FileNameExtensionFilter filtroTxt = new FileNameExtensionFilter("Documentos TXT", "txt");
            FileNameExtensionFilter filtroDocx = new FileNameExtensionFilter("Documentos WORD", "docx");

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(filtroTxt);
            fc.addChoosableFileFilter(filtroDocx);

            int respuesta = fc.showOpenDialog(null);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                this.panel = new Panel(this.ancho, this.alto, fc.getSelectedFile().getPath());
                this.add(panel, BorderLayout.CENTER);
                this.pack();
            }
        });


        menuArchivo.add(itemSeleccionar);
        menuBar.add(menuArchivo);
        this.setJMenuBar(menuBar);
        this.pack();
    }


}
