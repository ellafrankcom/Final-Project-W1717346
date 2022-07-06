import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor
    public Block(String previousHash ) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculatedHash();
    }

    public String calculatedHash(){
        String calculatedHash = StringUtil.applySha256(previousHash + Long.toString(timeStamp)
                +Integer.toString(nonce)
                + merkleRoot);
        return calculatedHash;
    }
    //increases nonce until hash target reached
    public void merkle(int difficulty){
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDifficultyString(difficulty);
        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash= calculatedHash();
        }
        System.out.println("Block Added to the network: " + hash);
    }
    //add transaction to block
    public boolean addTransaction(Transaction transaction){
       //process, check if valid
        //if block is genesis block, ignore
        if(transaction == null) return false;
        if(previousHash != "0"){
            if(transaction.processTransaction() != true){
                System.out.println("Transaction failed.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successful");
        return true; //only returns true if transaction successfully added
    }

}


