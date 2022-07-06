import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>(); //can use key to find a value

    public static int difficulty = 5;
    public static Wallet investedMoney, vendMoney, loanedMoney;
    public static Transaction genesisTransaction;

    public static void main(String args[]) throws IOException {

        //setup bouncy castle security provider - add blocks to blockchain ArrayList
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //Create new wallets
        Wallet coinbase = new Wallet();
        investedMoney = new Wallet();
        loanedMoney = new Wallet();
        vendMoney = new Wallet();

        float invested = Float.parseFloat(connectDB.getTotalInvested().toString());
        float loaned = Float.parseFloat(connectDB.getTotalLoaned().toString());
        float distributed = Float.parseFloat(connectDB.getTotalToVendors().toString());

        //create genesis trans
        genesisTransaction = new Transaction(coinbase.publicKey, investedMoney.publicKey, invested, null);
        genesisTransaction.generateSignature(coinbase.privateKey); //manually sign transaction
        genesisTransaction.transactionId = "0"; //manually set transaction ID
        //manually add transaction output
        genesisTransaction.output.add(new TransactionOutput(genesisTransaction.recipient, genesisTransaction.value, genesisTransaction.transactionId));
        UTXOs.put(genesisTransaction.output.get(0).id, genesisTransaction.output.get(0)); //store first transaction in UXTOs list


        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        System.out.println("\ninvestedMoney's balance is: " + investedMoney.getBalance());
        System.out.println("\ninvestedMoney is attempting to send £10300 to loanedMoney");
        Block block1 = new Block(genesis.hash);
        block1.addTransaction(investedMoney.sendFunds(loanedMoney.publicKey, loaned));
        addBlock(block1);
        System.out.println("\ninvestedMoney's balance is: " + investedMoney.getBalance());

        System.out.println("\nloanedMoney's balance is: " + loanedMoney.getBalance());
        System.out.println("\nloanedMoney is attempting to send £7800 to vendMoney");
        Block block2 = new Block(block1.hash);
        block2.addTransaction(loanedMoney.sendFunds(vendMoney.publicKey, distributed));
        addBlock(block2);
        System.out.println("\nloanedMoney's balance is: " + loanedMoney.getBalance());
        System.out.println("vendMoney's balance is: " + vendMoney.getBalance());

        isChainValid();

        LogIn.logInGUI();

    }

    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); //a temporary list of unspent transactions at a given block state.
        tempUTXOs.put(genesisTransaction.output.get(0).id, genesisTransaction.output.get(0));

        //loop through blockchain, check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculatedHash()) ){
                System.out.println("#Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("#Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("#This block hasn't been mined");
                return false;
            }

            //loop through blockchains transactions:
            TransactionOutput tempOutput;
            for(int t=0; t <currentBlock.transactions.size(); t++) {
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if(!currentTransaction.verifySignature()) {
                    System.out.println("#Signature on Transaction(" + t + ") is Invalid");
                    return false;
                }
                if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
                    System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
                    return false;
                }

                for(TransactionInput input: currentTransaction.input) {
                    tempOutput = tempUTXOs.get(input.transactionOutputId);

                    if(tempOutput == null) {
                        System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
                        return false;
                    }

                    if(input.UTXO.value != tempOutput.value) {
                        System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
                        return false;
                    }

                    tempUTXOs.remove(input.transactionOutputId);
                }

                for(TransactionOutput output: currentTransaction.output) {
                    tempUTXOs.put(output.id, output);
                }

                if( currentTransaction.output.get(0).recipient != currentTransaction.recipient) {
                    System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
                    return false;
                }
                if( currentTransaction.output.get(1).recipient != currentTransaction.sender) {
                    System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
                    return false;
                }

            }

        }
        System.out.println("Blockchain is valid");
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.merkle(difficulty);
        blockchain.add(newBlock);
    }
}
