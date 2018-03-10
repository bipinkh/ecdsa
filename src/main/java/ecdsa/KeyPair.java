package ecdsa;

import java.math.BigInteger;

/**
 * @author bipin khatiwada
 * github.com/bipinkh
 * @reference https://github.com/Kalyanranjan/ecdsa
 */

public class KeyPair {
    public BigInteger privateKey;
    public BigInteger[] publicKey;


    public KeyPair(BigInteger[] point, BigInteger n, BigInteger a) {
        privateKey = BigIntUtils.randomNumberLessThan(n);
        publicKey = EcOperations.pointMultiply(point, n, a, privateKey);
    }

    public BigInteger[] getPublicKey() {
        return this.publicKey;
    }

    public BigInteger getPrivateKey() {
        return this.privateKey;
    }
}