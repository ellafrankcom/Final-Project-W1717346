import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>(); //can use key to find a value

    public static int difficulty = 3;
    public static float minimumTransaction = 0.1f; //used as minimum value a transaction can have to proceed
    public static Wallet walletA;
    public static Wallet walletB;
    public static Wallet walletC;
    public static Transaction genesisTransaction;  //first trans of the block

    //change to be called chain
    public BlockChain() {
        //setup bouncy castle security provider - add blocks to blockchain ArrayList
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //Create new wallets
        walletA = new Wallet();
        walletB = new Wallet();  //wallet A = exPrisoner's key pair, //wallet B = vendor keypair
        walletC = new Wallet();
        Wallet coinbase = new Wallet();

        //create genesis trans, sends 100  to walletA
        genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 34110f, null);
        genesisTransaction.generateSignature(coinbase.privateKey); //manually sign transaction
        genesisTransaction.transactionId = "0"; //manually set transaction ID
        //manually add transaction output
        genesisTransaction.output.add(new TransactionOutput(genesisTransaction.recipient, genesisTransaction.value, genesisTransaction.transactionId));
        UTXOs.put(genesisTransaction.output.get(0).id, genesisTransaction.output.get(0)); //store first transaction in UXTOs list

        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        Block block1 = new Block(genesis.hash);
        System.out.println("\ninvestedMoney's balance is: " + walletA.getBalance());
        System.out.println("\ninvestedMoney is attempting to send £10300 to loanedMoney");
        block1.addTransaction(walletA.sendFunds(walletB.publicKey, 10300f)); //sends 40  to walletA
        addBlock(block1);
        System.out.println("\ninvestedMoney's balance is: " + walletA.getBalance());

        Block block2 = new Block(block1.hash);
        System.out.println("\nloanedMoney's balance is: " + walletB.getBalance());
        System.out.println("\nloanedMoney is attempting to send £7800 to vendMoney");
        block2.addTransaction(walletB.sendFunds(walletC.publicKey, 7800f));
        addBlock(block2);
        System.out.println("\nloanedMoney's balance is: " + walletB.getBalance());
        System.out.println("vendMoney's balance is: " + walletC.getBalance());
        //test end
        isChainValid();

        System.out.println(walletA.publicKey);
        System.out.println(walletA.privateKey);

    }

    //check integrity of blockchain
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
