package com.quickveggies.misc;

import java.util.Random;

import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.User;

public class MySecureCryptDataHandler
{
    /**
     * The length of the alphabet in bytes.
     */
    private int alphabet_length;
    /**
     * The number of symbols needed to encode all values 
     * created from multiplier×alphabet_char.
     */
    private int encodingLength_BASE16;
    private int multiplier;
    private int numberOfRandomSymbols;

    public MySecureCryptDataHandler(int alphabet_length, int multiplier, int numberOfRandomSymbols)
    {
        this.alphabet_length = alphabet_length;
        this.multiplier = multiplier;
        this.numberOfRandomSymbols = numberOfRandomSymbols;
        
        int encodingBitsNeeded = (int)Math.ceil(Math.log10(Math.pow(2, this.alphabet_length)*this.multiplier)/Math.log10(2));
        
        if(encodingBitsNeeded%4 != 0)
            encodingBitsNeeded = (encodingBitsNeeded/4 + 1)*4;
        
        this.encodingLength_BASE16 = encodingBitsNeeded/4;
        
        this.encodingLength_BASE16 = encodingLength_BASE16;
    }
    
    public static MySecureCryptDataHandler instance = new MySecureCryptDataHandler(8, 5, 1);

    public static MySecureCryptDataHandler getInstance()
    {
        if(instance==null)
        {
            return new MySecureCryptDataHandler(8, 5, 1);
        }
        return instance;
    }

    private String toPaddedHexString(int numValue)
    {
        String hex = Integer.toHexString(numValue);
        
        for(int i = 0; i<this.encodingLength_BASE16-hex.length(); i++)
            hex = "0" + hex;
        return hex;
    }

    private char randomSymbol()
    {
        Random r = new Random(999999999);
        String randomizerAlphabet = "0123456789abcdef";
        return randomizerAlphabet.charAt(r.nextInt(randomizerAlphabet.length()));
    }

    public String encrypt(String str)
    {
        String result = "";
        for(int ind = 0; ind<str.length(); ind++)
        {
            int numVal = (int) ((str.charAt(ind))*5);
            if(numVal<10)
            {
                result += 0;
                result += toPaddedHexString(numVal);
            }
            else
            {
                result += toPaddedHexString(numVal);
            }
            result += randomSymbol();
        }
        return result;
    }

    public String decrypt(String str)
    {
        String result = "";
        String selection = null;
        int blockLength = this.encodingLength_BASE16 + this.numberOfRandomSymbols;
        int blocks = str.length()/blockLength;
        
        for(int ind = 0; ind<blocks; ind++)
        {
            selection = str.substring(blockLength*ind, blockLength*ind+this.encodingLength_BASE16);
            char charVal = (char) (Integer.parseInt(selection, 16)/this.multiplier);
            result += charVal;
        }
        return result;
    }

    public static void main(String[] args) throws Exception
    {
        DatabaseClient dbclient = DatabaseClient.getInstance();
        User user = dbclient.getUserByName("demo");
        System.out.println(MySecureCryptDataHandler.getInstance().decrypt(user.getPassword()));
    }
}
