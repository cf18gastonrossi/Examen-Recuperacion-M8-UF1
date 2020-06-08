package com.example.recuperacion.Model;

public class Reserva {

    private String fecha;
    private int personas;
    private String comentarios;
    private String nombre;
    private String telefono;

    public Reserva() {
    }

    public Reserva(String fecha, int personas, String comentarios, String nombre, String telefono) {
        this.fecha = fecha;
        this.personas = personas;
        this.comentarios = comentarios;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
