import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by CraigBook on 2018-03-06.
 */
public class mainTest {
    main main;
    @Before
    public void setUp() throws Exception {
        main  = new main();
    }
    @Test
    public void testReturn0() {
        assertFalse(main.return0());
    }



}