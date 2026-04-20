package Examen;

import Examen.Models.*;
import java.util.*;
import org.apache.log4j.Logger;

public class MathManagerImpl implements MathManager {
    private static MathManager instance;
    private static final Logger logger = Logger.getLogger(MathManagerImpl.class);
    private Queue<Calculador> colaOperaciones;
    private Map<String, Alumno> alumnos;
    private Map<String, Instituto> institutos;
    private ReversePolishNotation motorRPN;

    public MathManagerImpl() {
        this.colaOperaciones = new LinkedList<>();
        this.alumnos = new HashMap<>();
        this.institutos = new HashMap<>();
        this.motorRPN = new ReversePolishNotationImpl();
    }

    @Override
    public void addOperation(String idOp, String expresion, String idAlumno) {
        logger.info("Inicio addOperation: idOp=" + idOp + ", expr=" + expresion + ", alumno=" + idAlumno);

        try {
            Alumno alumno = alumnos.get(idAlumno);
            if (alumno != null) {
                Calculador op = new Calculador(idOp, expresion, alumno);
                colaOperaciones.add(op);
                alumno.addOperation(op);
                logger.info("Final addOperation: Operación añadida correctamente");
            } else {
                logger.error("Error addOperation: Alumno " + idAlumno + " no encontrado");
            }
        } catch (Exception e) {
            logger.fatal("Fallo crítico en addOperation", e);
        }
    }

    @Override
    public Calculador processOperation() {
        logger.info("Inicio processOperation");

        Calculador op = colaOperaciones.poll();
        if (op != null) {
            try {
                double res = motorRPN.calculate(op.getExpresion());
                op.setResultado(res);
                op.getAutor().getInstitute().registrarOperacion(op);
                logger.info("Final processOperation: Resultado=" + res);
            } catch (Exception e) {
                logger.error("Error al procesar cálculo RPN", e);
            }
        } else {
            logger.warn("Se intentó procesar pero la cola está vacía");
        }
        return op;
    }

    @Override
    public List<Calculador> getOperationsByInstitute(String idInstituto) {
        logger.info("Inicio getOperationsByInstitute: " + idInstituto);
        List<Calculador> opsDelInstituto = new ArrayList<>();
        try {
            for (Alumno a : alumnos.values()) {
                if (a.getInstitute() != null && a.getInstitute().getId().equals(idInstituto)) {
                    opsDelInstituto.addAll(a.getOperations());
                }
            }
            logger.info("Final getOperationsByInstitute: total ops=" + opsDelInstituto.size());
        } catch (Exception e) {
            logger.error("Error al obtener operaciones del instituto " + idInstituto, e);
        }
        return opsDelInstituto;
    }

    @Override
    public List<Calculador> getOperationsByAlumno(String idAlumno) {
        logger.info("Inicio getOperationsByAlumno: " + idAlumno);
        Alumno a = alumnos.get(idAlumno);
        if (a != null) {
            logger.info("Final getOperationsByAlumno: total ops=" + a.getOperations().size());
            return a.getOperations();
        } else {
            logger.error("Error getOperationsByAlumno: Alumno no encontrado");
            return new ArrayList<>();
        }
    }
    @Override
    public List<Instituto> getInstitutesRanking() {
        logger.info("Inicio getInstitutesRanking");

        List<Instituto> listaRanking = new ArrayList<>(institutos.values());
        listaRanking.sort((i1, i2) -> Integer.compare(i2.getNumOperaciones(), i1.getNumOperaciones()));

        logger.info("Final getInstitutesRanking: Institutos retornados=" + listaRanking.size());
        return listaRanking;
    }

    public void addAlumno(Alumno a) {
        logger.info("Añadiendo alumno: " + a.getName());
        this.alumnos.put(a.getId(), a);
    }

    public void addInstituto(Instituto i) {
        logger.info("Añadiendo instituto: " + i.getNombre());
        this.institutos.put(i.getId(), i);
    }
    public static MathManager getInstance() {
        if (instance == null) instance = new MathManagerImpl();
        return instance;
    }
    @Override
    public Instituto getInstituto(String id) {
        return this.institutos.get(id);
    }
}