package sample.model;

import sample.Main;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static sample.model.Gloabal.allTripValue;

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


    //  !!!!!!!!!!!
    public float getBalance() {
        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : Main.UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            if (UTXO.isMine(publicKey)) { //if output belongs to me ( if coins belong to me )
                UTXOs.put(UTXO.id, UTXO); //add it to our list of unspent transactions.
                total += UTXO.value;
            }
        }
        return total;
    }

    // !!!!!!!!!!!!
    public Transaction sendMileage(PublicKey _recipient, float resourceSendingValue) {
        if (getBalance() < resourceSendingValue) {
            System.out.println("#Not Enough funds to send transaction. Transaction Discarded.");
            return null;
        }
        ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();

        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            total += UTXO.value;
            inputs.add(new TransactionInput(UTXO.id));
            if (total > resourceSendingValue) break;
        }

        Transaction newTransaction = new Transaction(publicKey, _recipient, resourceSendingValue, inputs);
        newTransaction.generateSignature(privateKey);

        for (TransactionInput input : inputs) {
            UTXOs.remove(input.transactionOutputId);
        }

        return newTransaction;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public HashMap<String, TransactionOutput> getUTXOs() {
        return UTXOs;
    }

    public void setUTXOs(HashMap<String, TransactionOutput> UTXOs) {
        this.UTXOs = UTXOs;
    }

}
