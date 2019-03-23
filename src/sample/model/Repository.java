package sample.model;

import java.security.PrivateKey;
import java.security.PublicKey;
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



}
