package com.example.exfinal;

public class Plato {
    private String nombre;
    private String precio;
    private String datos;
    private String imageURL;
    private int cantidad;
    private double precioTotal; // Nuevo atributo precioTotal

    public Plato(String nombre, String precio, String datos, String imageURL) {
        this.nombre = nombre;
        this.precio = precio;
        this.datos = datos;
        this.imageURL = imageURL;
        this.cantidad = 0;
        this.precioTotal = 0.0; // Inicializamos el precioTotal a 0.0
    }
    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDatos() {
        return datos;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
