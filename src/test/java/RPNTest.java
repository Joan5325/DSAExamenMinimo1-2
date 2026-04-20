import Examen.ReversePolishNotation;
import Examen.ReversePolishNotationImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RPNTest {
    private ReversePolishNotation rpn;

    @Before
    public void setUp() {
        this.rpn = new ReversePolishNotationImpl();
    }

    @Test
    public void testSimpleOperations() {
        Assert.assertEquals(7.0, rpn.calculate("5 2 +"), 0.001);
        Assert.assertEquals(6.0, rpn.calculate("10 4 -"), 0.001);
    }

    @Test
    public void testComplexOperations() {
        Assert.assertEquals(14.0, rpn.calculate("5 1 2 + 4 * + 3 -"), 0.001);
    }

    @Test(expected = Exception.class)
    public void testInvalidExpression() {
        rpn.calculate("5 +");
    }
}