/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.com.quickveggies.misc;

import com.quickveggies.misc.CryptDataHandler;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author George Maroulis <tiger@safari>
 */
public class CryptDataHandlerNGTest
{
    
    public CryptDataHandlerNGTest()
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

    /**
     * Test of encrypt method, of class CryptDataHandler.
     */
    @Test
    public void testEncrypt()
    {
        System.out.println("encrypt");
        String str = "12345";
        CryptDataHandler instance = CryptDataHandler.getInstance();
        String encry = instance.encrypt(str);
        assertNotEquals(encry, "");
    }

    /**
     * Test of decrypt method, of class CryptDataHandler.
     */
    @Test
    public void testDecrypt()
    {
        System.out.println("decrypt");
        String str = "12345";
        CryptDataHandler instance = CryptDataHandler.getInstance();
        String encry = instance.encrypt(str);
        System.out.println("Encrypt(\"" + str + "\") ==> " + encry);
        String decry = instance.decrypt(encry);
        System.out.println("Decrypt(\"" + encry + "\") ==> " + decry);
        assertEquals(str, decry);
    }
    
}
