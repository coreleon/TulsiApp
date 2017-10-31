/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.com.quickveggies.misc;

import com.quickveggies.misc.CryptDataHandler;
import com.quickveggies.misc.MySecureCryptDataHandler;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author George Maroulis <tiger@safari>
 */
public class MySecureCryptDataHandlerNGTest
{
    
    public MySecureCryptDataHandlerNGTest()
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
        System.out.println("MySecureCryptDataHandler vs CryptDataHandler");
        String str = "Hello";
        CryptDataHandler insecureInstance = CryptDataHandler.getInstance();
        MySecureCryptDataHandler secureInstance = MySecureCryptDataHandler.getInstance();
        String insecEncry = insecureInstance.encrypt(str);
        String secEncry = secureInstance.encrypt(str);
        System.out.println("CryptDataHandler.encrypt(\"" + str + "\") ==> " + insecEncry);
        System.out.println("MySecureCryptDataHandler.encrypt(\"" + str + "\") ==> " + secEncry);
    }

    /**
     * Test of decrypt method, of class CryptDataHandler.
     */
//    @Test
//    public void testDecrypt()
//    {
//        System.out.println("MySecureCryptDataHandler.decrypt");
//        String str = "12345";
//        MySecureCryptDataHandler instance = MySecureCryptDataHandler.getInstance();
//        String encry = instance.encrypt(str);
//        System.out.println("Encrypt(\"" + str + "\") ==> " + encry);
//        String decry = instance.decrypt(encry);
//        System.out.println("Decrypt(\"" + encry + "\") ==> " + decry);
//        assertEquals(str, decry);
//    }
    
}
