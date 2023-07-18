package com.example.exfinal;

public class Plato {
    private String nombre;
    private String precio;
    private String datos;
    private String imageURL;

    public Plato(String nombre, String precio, String datos, String imageURL) {
        this.nombre = nombre;
        this.precio = precio;
        this.datos = datos;
        this.imageURL = imageURL;
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
}
