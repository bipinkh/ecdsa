package ecdsa;

import java.math.BigInteger;

/**
 * @author bipin khatiwada
 * github.com/bipinkh
 */
public class Constants {

    // for curve p192
    public static String Curve = "p192";
    public static BigInteger n = new BigInteger("6277101735386680763835789423176059013767194773182842284081");
    public static BigInteger xG = new BigInteger("188da80eb03090f67cbf20eb43a18800f4ff0afd82ff1012", 16);
    public static BigInteger yG = new BigInteger("07192b95ffc8da78631011ed6b24cdd573f977a11e794811", 16);
    public static BigInteger[] xyG = {xG,yG};
    public static BigInteger a = BigInteger.valueOf(-3); //variable a of the EC equation
}
