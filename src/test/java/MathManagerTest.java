import Examen.MathManager;
import Examen.MathManagerImpl;
import Examen.Models.Alumno;
import Examen.Models.Calculador;
import Examen.Models.Instituto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathManagerTest {
    private MathManager manager;

    @Before
    public void setUp() {
        this.manager = MathManagerImpl.getInstance();
        Instituto i1 = new Instituto("EETAC", "Escola d'Enginyeria");
        Alumno a1 = new Alumno("A1", "Juan", i1);
        ((MathManagerImpl)manager).addInstituto(i1);
        ((MathManagerImpl)manager).addAlumno(a1);
    }

    @Test
    public void testSingleton() {
        MathManager instance2 = MathManagerImpl.getInstance();
        Assert.assertSame("Deberían ser la misma instancia (Singleton)", manager, instance2);
    }

    @Test
    public void testFullProcess() {
        manager.addOperation("OP-001", "5 2 +", "A1");
        Calculador res = manager.processOperation();
        Assert.assertNotNull(res);
        Assert.assertEquals(7.0, res.getResultado(), 0.001);
        Assert.assertEquals(1, manager.getInstitutesRanking().get(0).getNumOperaciones());
    }
}