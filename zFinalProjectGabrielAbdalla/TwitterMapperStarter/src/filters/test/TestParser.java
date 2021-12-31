package filters.test;

import com.sun.org.apache.xpath.internal.operations.Or;
import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void notFilter() throws SyntaxError {
        Filter f = new Parser("not trump").parse();
        assertTrue(f instanceof NotFilter);
        assertTrue(((NotFilter)f).toString().equals("not trump"));
    }

    @Test
    public void orFilter() throws SyntaxError {
        Filter f = new Parser("red or blue").parse();
        assertTrue(f instanceof OrFilter);
        assertTrue(((OrFilter)f).toString().equals("(red or blue)"));
    }

    @Test
    public void andFilter() throws SyntaxError {
        Filter f = new Parser("red and blue").parse();
        assertTrue(f instanceof AndFilter);
        assertTrue(((AndFilter)f).toString().equals("(red and blue)"));
    }



    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));

    }
}
