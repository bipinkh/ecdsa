package ecdsa;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author bipin khatiwada
 * github.com/bipinkh
 */
public class BigIntUtils {

    public static BigInteger randomNumberLessThan (BigInteger upperLimit){
        BigInteger r;
        Random rnd = new Random();
        do {
            r = new BigInteger(upperLimit.bitLength(), rnd);
        } while (r.compareTo(upperLimit) >= 0);
        return r;
    }
}
