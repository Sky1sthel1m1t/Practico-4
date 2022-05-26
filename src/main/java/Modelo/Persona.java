package Modelo;

import java.util.Locale;

public class Persona {

    private String datosSucios;
    private String nombre;
    private String apellido;
    private int edad;
    private String[] datosLimpios;
    private int numLista;

    public Persona(String datosSucios, int numLista) {
        this.datosSucios = datosSucios;
        this.numLista = numLista;
        limpiarDatos();
    }

    public void limpiarDatos() {
        String regex = "\s+";
        String datosLimpios = datosSucios.replaceAll(regex, " ");

        if (datosLimpios.charAt(datosLimpios.length() - 1) == ' ') {
            datosLimpios = datosLimpios.substring(0, datosLimpios.length() - 1);
        }

        if (datosLimpios.charAt(0) == ' ') {
            datosLimpios = datosLimpios.substring(1);
        }

        String[] datos = datosLimpios.split(" ");

        this.nombre = datos[0].substring(0,1).toUpperCase() + datos[0].substring(1).toLowerCase(Locale.ROOT) ;
        this.apellido = datos[1].substring(0,1).toUpperCase() + datos[1].substring(1).toLowerCase(Locale.ROOT);
        this.edad = Integer.parseInt(datos[2]);
        this.datosLimpios = new String[]{numLista + "", nombre, apellido, edad + ""};
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String[] getDatosLimpios() {
        return datosLimpios;
    }

    public void setDatosLimpios(String[] datosLimpios) {
        this.datosLimpios = datosLimpios;
    }

    @Override
    public String toString() {
        return "Persona: " + nombre + " " + apellido + " " + edad;
    }
}
