package Examen;
import Examen.Models.*;
import java.util.List;

public interface MathManager {
     void addOperation(String idOp, String expresion, String idAlumno);
     Calculador processOperation();
     List<Calculador> getOperationsByInstitute(String idInstituto);
     List<Calculador> getOperationsByAlumno(String idAlumno);
     List<Instituto> getInstitutesRanking();
     void addAlumno(Alumno a);
     void addInstituto(Instituto i);
    Instituto getInstituto(String id);
}