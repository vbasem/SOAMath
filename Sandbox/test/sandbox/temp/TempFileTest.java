/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.temp;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Basem
 */
public class TempFileTest
{
    
    public TempFileTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testGetTempFolderName()
    {

        Handler h = null;
        String tempPath = System.getProperty("java.io.tmpdir") + "java_log"  + File.separator;
        System.out.print(tempPath);
        try
        {
            File f = new File(tempPath);
            if (!f.exists())
            {
                System.out.print("here");
                f.mkdir();
            }
            h = new FileHandler(System.getProperty("java.io.tmpdir") + "java_log" + File.separator + df.format(d) + ".txt");
        } catch (IOException ex)
        {
            Logger.getLogger(TempFileTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex)
        {
            Logger.getLogger(TempFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(h.getFormatter().getClass().getName());
        Formatter form = new SimpleFormatter();
        h.setFormatter(form);
        Logger.getLogger("gi").addHandler(h);
        Logger.getLogger("gi").log(Level.INFO, "testing");
        Logger.getLogger("gi").log(Level.WARNING, "2testing");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
