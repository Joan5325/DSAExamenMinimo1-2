package Examen.Models;

public class Calculador {
    private String id;
    private String expresion;
    private double resultado;
    private Alumno autor;

    public Calculador(String id, String expresion, Alumno autor) {
        this.id = id;
        this.expresion = expresion;
        this.autor = autor;
        this.resultado = 0;
    }
    public Calculador() {
    }
    public String getId() {
        return id;
    }

    public String getExpresion() {
        return expresion;
    }

    public double getResultado() {
        return resultado;
    }

    public Alumno getAutor() {
        return autor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public void setAutor(Alumno autor) {
        this.autor = autor;
    }
}