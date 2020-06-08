package com.example.recuperacion.Model;

public class Plato {

    private String nombre_plato;
    private String ingredientes;
    private int precio;

    public Plato(String nombre_plato, String ingredientes, int precio) {
        this.nombre_plato = nombre_plato;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
