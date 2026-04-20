package Examen.Models;

import java.util.ArrayList;
import java.util.List;
public class Alumno {
    private String id;
    private String nombre;
    private Instituto instituto;
    private List<Calculador> operacion;

    public Alumno(String id, String name, Instituto institute) {
        this.id = id;
        this.nombre  = name;
        this.instituto = institute;
        this.operacion = new ArrayList<>();
    }
    public Alumno() {
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return nombre;
    }

    public Instituto getInstitute() {
        return instituto;
    }

    public List<Calculador> getOperations() {
        return operacion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public void setInstitute(Instituto institute) {
        this.instituto = institute;
    }


    public void addOperation(Calculador op) {
        this.operacion.add(op);
    }
}
