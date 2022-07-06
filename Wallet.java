import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public Wallet() {
        generateKeyPair();
    }

    public void generateKeyPair() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //generates key pair using eliptic curve digital signature
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            //provides crypto strong random number generator
            //PRNG - pseudo-random number generator
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            //sets the parameters
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            //initialize key generator and create pair
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            //set public and priv keys
            privateKey = keyPair.getPrivate(); //store this in database
            publicKey = keyPair.getPublic();  //store this in database


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //returns balance, stores UTXO's owned by this wallet
    public float getBalance() {
        float total = 0;

        for (Map.Entry<String, TransactionOutput> item : BlockChain.UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            if (UTXO.isMine(publicKey)) {
                UTXOs.put(UTXO.id, UTXO); //add to list of unspent transactions
                total += UTXO.value;
            }
        }
        return total;
    }

    //generates and returns a new transaction from this wallet
    public Transaction sendFunds(PublicKey _recipient, float value) {
        if (getBalance() < value) {//check funds
            System.out.println("#Not enough funds to send transaction. Transaction cancelled");
            return null;
        }
        //create array list of inputs
        ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();

        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            total += UTXO.value;
            inputs.add(new TransactionInput(UTXO.id));
            if (total > value) break;
        }

        Transaction newTransaction = new Transaction(publicKey, _recipient, value, inputs);
        newTransaction.generateSignature(privateKey);
        for (TransactionInput input : inputs) {
            UTXOs.remove(input.transactionOutputId);
        }
        return newTransaction;
    }

}
