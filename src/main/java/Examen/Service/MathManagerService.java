package Examen.Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import Examen.Models.*;
import Examen.*;
import Transferencia.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.apache.log4j.Logger;

@Api(value = "/math", description = "Servicio de Operaciones Matemáticas")
@Path("/math")
public class MathManagerService {

    private MathManager mm;
    private static final Logger logger = Logger.getLogger(MathManagerService.class);

    public MathManagerService() {
        this.mm = MathManagerImpl.getInstance();

        if (this.mm.getInstitutesRanking().size() == 0) {
            logger.info("Inicializando datos de prueba...");
            Instituto i1 = new Instituto("1", "EETAC");
            Instituto i2 = new Instituto("2", "ETSETB");

            this.mm.addInstituto(i1);
            this.mm.addInstituto(i2);

            this.mm.addAlumno(new Alumno("1", "Juan", i1));
            this.mm.addAlumno(new Alumno("2", "Maria", i2));
        }
    }


    @POST
    @Path("/instituto")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Añadir un nuevo instituto")
    public Response addInstituto(Instituto i) {
        this.mm.addInstituto(i);
        return Response.status(201).entity(i).build();
    }

    @POST
    @Path("/operation")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Añadir operación (Limpio)")
    public Response addOperation(OperationDTO opDto) {
        this.mm.addOperation(opDto.id, opDto.expresion, opDto.idAlumno);
        return Response.status(201).build();
    }

    @POST
    @Path("/alumno")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Añadir alumno (Limpio)")
    public Response addAlumno(AlumnoDTO aluDto) {
        Instituto i = this.mm.getInstituto(aluDto.idInstituto);

        if (i == null) {
            logger.error("El instituto no existe");
            return Response.status(404).entity("Instituto no existe").build();
        }

        Alumno a = new Alumno(aluDto.id, aluDto.nombre, i);
        this.mm.addAlumno(a);
        return Response.status(201).build();
    }

    @POST
    @Path("/process")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Procesar siguiente operación")
    public Response processOperation() {
        Calculador res = this.mm.processOperation();
        if (res == null) return Response.status(404).entity("Cola vacía").build();
        return Response.status(200).entity(res).build();
    }


    @GET
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Ranking de institutos")
    public Response getRanking() {
        List<Instituto> ranking = this.mm.getInstitutesRanking();
        GenericEntity<List<Instituto>> entity = new GenericEntity<List<Instituto>>(ranking) {};
        return Response.status(200).entity(entity).build();
    }

    @GET
    @Path("/operations/alumno/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Operaciones por alumno")
    public Response getOpsByAlumno(@PathParam("id") String idAlumno) {
        List<Calculador> lista = this.mm.getOperationsByAlumno(idAlumno);
        GenericEntity<List<Calculador>> entity = new GenericEntity<List<Calculador>>(lista) {};
        return Response.status(200).entity(entity).build();
    }
}