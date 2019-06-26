#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;

public class JEncrypt {

	public static String charsetName = "UTF8";
    public static String algorithm = "DES";
    public static String key = "ECO654RDS033FGV1";
    
    public static String encrypt(String data) {
 
    	if (key == null || data == null)
            return null;
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            byte[] dataBytes = data.getBytes(charsetName);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String s = new String(Base64.encodeBase64(cipher.doFinal(dataBytes)));
//            LoggingUtil.DebugInfo("ENC:::" + s);
            System.out.println("Data-Enc:::" + s);
            return s;
        } catch (Exception e) {
//        	LoggingUtil.DebugInfo(e.toString());
        	return null;
        }
    }

    public static String decrypt( String data) {
        
    	//LoggingUtil.logDebugInfo("DDData:::" + data);
    	if (key == null || data == null)
            return null;
        try {
            byte[] dataBytes = Base64.decodeBase64((data.getBytes()));
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
            String s = new String(dataBytesDecrypted);
            //LoggingUtil.logDebugInfo("ENC:::" + s);
            return s;
        } catch (Exception e) {
//        	LoggingUtil.DebugInfo(e.toString());
        	e.printStackTrace();
            return null;
        }
    }
    
    public static boolean ValidateTransactionToken(String scode, String amount, String acc, String requestID,String token)
    {
    	boolean isValid = false;
    	try
    	{
//    		LoggingUtil.DebugInfo("SCODE::" + scode + " " + token + " " + amount + "  " + requestID + "  " + acc);
			String pwd = Settings.getInstance("").getProperty(scode + "_TOKEN");
			//LoggingUtil.logDebugInfo("SCODE::" + pwd);
		String spwd = JEncrypt.decrypt(pwd);
		String data = "";
		data = acc +  requestID + scode + spwd;
		if(scode.equals("INTERAFRICA"))
			data = requestID + scode + spwd;
		else
			data = acc +  requestID + scode + spwd;
			
//		LoggingUtil.DebugInfo("STOKEN::" + data);
//		LoggingUtil.DebugInfo("TOKEN::" + token);
		
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    // ** NOTE all bytes that are retrieved from the data string must be done so using UTF-8 Character Set.
	    byte[] hashBytes = data.getBytes("UTF-8");
	    //Create the hash bytes from the data
	    byte[] messageDigest = digest.digest(hashBytes);
	    //Create a HEX string from the hashed data
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < messageDigest.length; i++)
	    {
	        String h = Integer.toHexString(0xFF & messageDigest[i]);
	        while(h.length() < 2)
	            h = "0" + h;
	        sb.append(h);
	    }
//	    LoggingUtil.DebugInfo("SBB-TOKEN:" + sb.toString());
	    if( sb.toString().equals(token))
	    {
//	    	LoggingUtil.DebugInfo("SBB-TOKEN VALID:");
	    	sb = null;
	    	isValid = true;
	    	return true;
	    }
    		
    	}
    	catch(Exception ex)
    	{
//    		LoggingUtil.DebugInfo(ex.toString());
    	}
    	
    	return isValid;
    	
    }
    
    public static boolean ValidateTokenSourcePassReq(String scode, String requestID,String token)
    {
    	boolean isValid = false;
    	try
    	{
//    		LoggingUtil.DebugInfo("SCODE::" + scode + " " + token + " " +  "  " + requestID + "  " );
			String pwd = Settings.getInstance("").getProperty(scode + "_TOKEN");
//			LoggingUtil.DebugInfo("SCODE::" + pwd);
		String spwd = JEncrypt.decrypt(pwd);
		String data = requestID + scode + spwd;
//		LoggingUtil.DebugInfo("STOKEN::" + data);
//		LoggingUtil.DebugInfo("TOKEN::" + token);
		
		
		String validateToken = Settings.getInstance("").getProperty("VALIDATE_TOKEN");
		
		if (validateToken.equals("N"))
			return true;
		
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    // ** NOTE all bytes that are retrieved from the data string must be done so using UTF-8 Character Set.
	    byte[] hashBytes = data.getBytes("UTF-8");
	    //Create the hash bytes from the data
	    byte[] messageDigest = digest.digest(hashBytes);
	    //Create a HEX string from the hashed data
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < messageDigest.length; i++)
	    {
	        String h = Integer.toHexString(0xFF & messageDigest[i]);
	        while(h.length() < 2)
	            h = "0" + h;
	        sb.append(h);
	    }
//	    LoggingUtil.DebugInfo("SBB-TOKEN:" + sb.toString());
	    if( sb.toString().equals(token))
	    {
//	    	LoggingUtil.DebugInfo("SBB-TOKEN VALID:");
	    	sb = null;
	    	isValid = true;
	    	return true;
	    }
    		
    	}
    	catch(Exception ex)
    	{
//    		LoggingUtil.DebugInfo(ex.toString());
    	}
    	
    	return isValid;
    	
    }
	
    public static String Hash512Msg(String data)
    {
    	String isValid = "";
    	try
    	{
    		
		//LoggingUtil.DebugInfo("STOKEN::" + data);
		
		
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    // ** NOTE all bytes that are retrieved from the data string must be done so using UTF-8 Character Set.
	    byte[] hashBytes = data.getBytes("UTF-8");
	    //Create the hash bytes from the data
	    byte[] messageDigest = digest.digest(hashBytes);
	    //Create a HEX string from the hashed data
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < messageDigest.length; i++)
	    {
	        String h = Integer.toHexString(0xFF & messageDigest[i]);
	        while(h.length() < 2)
	            h = "0" + h;
	        sb.append(h);
	    }
	    //LoggingUtil.DebugInfo("SBB-TOKEN:" + sb.toString());
	    isValid = sb.toString();
	    	
    	}
    	catch(Exception ex)
    	{
    		//LoggingUtil.logError(ex);
//    		LoggingUtil.DebugInfo(ex.toString());
    	}
    	
    	return isValid;
    	
    }
}
