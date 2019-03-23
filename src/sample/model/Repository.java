package sample.model;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;

public class Repository {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public float numberOfComponents = 8;
    public float resourceSendingValue = allTripValue / numberOfComponents; //don't used resourceSendingValue

    public HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public Repository() {
        generateKeyPair();
    }

    public Repository(PrivateKey privateKey, PublicKey publicKey, HashMap<String, TransactionOutput> UTXOs) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.UTXOs = UTXOs;
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random); //256
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
