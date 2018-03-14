import org.junit.Before;
import org.junit.Test;
import sysc4806.Main;

import static org.junit.Assert.*;

/**
 * Created by CraigBook on 2018-03-06.
 */
public class mainTest {
    Main main;
    @Before
    public void setUp() throws Exception {
        main  = new Main();
    }
    @Test
    public void testReturn0() {
        assertFalse(main.return0());
    }



}