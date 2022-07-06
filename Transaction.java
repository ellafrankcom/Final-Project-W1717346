import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> input = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> output = new ArrayList<TransactionOutput>();

    private static int sequence = 0;

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> input){

        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.input = input;
    }

    private String calculateHash(){
        sequence++;
        return StringUtil.applySha256(StringUtil.getStringFromKey(sender) +
                                            StringUtil.getStringFromKey(recipient) +
                                            Float.toString(value) + sequence);
    }

    public void generateSignature(PrivateKey privateKey){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature(){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
    }

    public boolean processTransaction(){
        if(verifySignature() == false){
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        for (TransactionInput i : input){
            i.UTXO = BlockChain.UTXOs.get(i.transactionOutputId);
        }

        if(getInputsValue() < BlockChain.minimumTransaction){
            System.out.println("#Transaction inputs to small: " + getInputsValue());
            return false;
        }
        float leftOver = getInputsValue() - value;
        transactionId = calculateHash();
        output.add(new TransactionOutput(this.recipient, value, transactionId));
        output.add(new TransactionOutput(this.sender, leftOver, transactionId));

        for(TransactionOutput o : output){
            BlockChain.UTXOs.put(o.id, o);
        }
        for(TransactionInput i : input){
            if(i.UTXO == null) continue;
            BlockChain.UTXOs.remove(i.UTXO.id);
        }
        return true;
    }

    public float getInputsValue(){
        float total = 0;
        for(TransactionInput i: input){
            if(i.UTXO == null) continue;
            total += i.UTXO.value;
        }
        return total;
    }

    public float getOutputsValue(){
        float total = 0;
        for(TransactionOutput o : output){
            total += o.value;
        }
        return total;
    }
}
