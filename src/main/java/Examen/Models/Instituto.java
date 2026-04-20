package Examen.Models;

import java.util.ArrayList;
import java.util.List;

public class Instituto {
    private String id;
    private String nombre;
    private List<Calculador> operacionesRealizadas;

    public Instituto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.operacionesRealizadas = new ArrayList<>();
    }
    public Instituto() {
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Calculador> getOperacionesRealizadas() {
        return operacionesRealizadas;
    }

    public int getNumOperaciones() {
        return operacionesRealizadas.size();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void registrarOperacion(Calculador op) {
        this.operacionesRealizadas.add(op);
    }
}