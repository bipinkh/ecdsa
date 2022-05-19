package ecdsa;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * @author bipin khatiwada
 * github.com/bipinkh
 */
public class Signature {

    public static BigInteger[] messageSign(String msg, BigInteger n, BigInteger[] G, BigInteger a, BigInteger privateKey) throws NoSuchAlgorithmException {

        BigInteger k, kInv, r, e, s, z;
        BigInteger[] kG;

        do {
            do {
                k = BigIntUtils.randomNumberLessThan(n);
                kG = EcOperations.pointMultiply(G, n, a, k);
                r = kG[0].mod(n);
            } while (r.compareTo(BigInteger.ZERO) == 0);

            kInv = k.modInverse(n);
            e = new BigInteger(SHAsum(msg.getBytes()), 16);
            z = e.shiftRight(e.bitLength() - n.bitLength());
            s = (kInv.multiply(z.add(privateKey.multiply(r)))).mod(n);
        } while (s.compareTo(BigInteger.ZERO) == 0);

        kG[0] = r;
        kG[1] = s;
        return kG;
    }

    public static boolean messageVerify(String msg, BigInteger[] sign, BigInteger n, BigInteger[] G, BigInteger a, BigInteger[] pbkQ) throws NoSuchAlgorithmException {

        BigInteger r = sign[0];
        BigInteger s = sign[1];

        if (r.compareTo(BigInteger.ONE) < 0 || r.compareTo(n) >= 0) {
            System.out.println(" r : Message NOT VERIFIED");
            return false;
        }
        if (s.compareTo(BigInteger.ONE) < 0 || s.compareTo(n) >= 0) {
            System.out.println(" s :Message NOT VERIFIED");
            return false;
        }

        BigInteger e = new BigInteger(SHAsum(msg.getBytes()), 16);
        BigInteger z = z = e.shiftRight(e.bitLength() - n.bitLength());
        BigInteger w = s.modInverse(n);

        BigInteger u1 = (z.multiply(w)).mod(n);
        BigInteger u2 = (r.multiply(w)).mod(n);

        BigInteger[] X = EcOperations.pointAddition(EcOperations.pointMultiply(G, n, a, u1), EcOperations.pointMultiply(pbkQ, n, a, u2), n);

        if(X[0].equals(BigInteger.ZERO) || X[1].equals(BigInteger.ZERO) ){
            System.out.println("Invalid !");
        }
        BigInteger v = X[0].mod(n);

        if (v.compareTo(r) == 0) {
            System.out.println("Message VERIFIED");
            return true;
        }

        System.out.println("Message NOT VERIFIED");
        return false;
    }



    public static String SHAsum(byte[] convertme) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return byteArray2Hex(md.digest(convertme));
    }


    private static String byteArray2Hex(final byte[] hash) {
        Formatter formatter = new Formatter();
        try{
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }finally {
            formatter.close();
        }

    }

}
