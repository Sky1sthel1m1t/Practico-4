package Vista;

import Modelo.Lista;
import Modelo.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Panel extends JPanel {

    private int ancho;
    private int alto;
    private JButton btnEliminar = new JButton("Eliminar");
    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private JTable tabla;
    private Lista<Persona> listaPersonas = new Lista<>();
    private String path;
    private String regex = "^\\s*[A-z\\u00C0-\\u017F]+\\s+[A-z\\u00C0-\\u017F]+\\s+[0-9]+\\s*$";
    private Logger logger = LogManager.getRootLogger();

    public Panel(int ancho, int alto, String path) {
        this.ancho = ancho-15;
        this.alto = alto-60;
        this.path = path;
        llenarDatos();
        init1();
    }

    private void llenarDatos(){

        modeloTabla.addColumn("ID_Numero");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Edad");

        File archivo = new File(this.path);

        try {
            Scanner sc = new Scanner(archivo);
            int num = 1;

            while (sc.hasNextLine()) {
                String nombreCompleto = sc.nextLine();

                if (verificarNombre(nombreCompleto)) {
                    Persona persona = new Persona(nombreCompleto, num);
                    this.modeloTabla.addRow(persona.getDatosLimpios());
                    listaPersonas.add(persona);
                    logger.info("Se añadió a " + persona);
                    num++;
                } else {
                    logger.info("Se excluyó a " + nombreCompleto + " por no cumplir el formato");
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Por alguna razon no se encontró el archivo");
            throw new RuntimeException(e);
        }
    }

    private void init1(){
        setSize(ancho,alto);
        setLayout(new BorderLayout());
        setVisible(true);

        this.tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0,0,ancho,alto);
        btnEliminar.setBounds(0,alto, ancho,30);

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            try{
                System.out.println(listaPersonas);
                modeloTabla.removeRow(filaSeleccionada);
                logger.info("Se ha eliminado a: " + listaPersonas.get(filaSeleccionada));
                listaPersonas.eliminar(filaSeleccionada);
            } catch (Exception e1){
                JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna fila para eliminar");
            }
            System.out.println(listaPersonas);
        });

        this.add(btnEliminar, BorderLayout.SOUTH);
        this.add(scroll, BorderLayout.CENTER);

    }

    private boolean verificarNombre(String nombre) {
        Pattern patron = Pattern.compile(this.regex);
        Matcher m = patron.matcher(nombre);
        return m.find();
    }


}
