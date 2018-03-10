package ecdsa;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * @author bipin khatiwada
 * github.com/bipinkh
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //message to sign
        String msg = "this is the formula of coca cola";
        //get a random key pair
        KeyPair kp = new KeyPair(Constants.xyG, Constants.n, Constants.a);

        //sign the message and get signature
        BigInteger[] signature = Signature.messageSign(msg, Constants.n, Constants.xyG, Constants.a, kp.getPrivateKey());

        //verify the signature
        boolean verified = Signature.messageVerify(msg,signature,Constants.n,Constants.xyG,Constants.a,kp.getPublicKey());
        System.out.println("Signature Verification Status :: "+verified);
    }
}
