import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

public class LXChain {
	
	public static List<Block> blockchain = new ArrayList<Block>();
	
	public static int difficulty = 8;
	
	public static void main(String[] args) {
		
		blockchain.add(new Block("체인 생성, 첫번째 블록", "0"));
		System.out.println("mine block 1...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("두번째 블록", blockchain.get(blockchain.size()-1).hash));
		System.out.println("mine block 2...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("세번째 블록", blockchain.get(blockchain.size()-1).hash));
		System.out.println("mine block 3...");
		blockchain.get(2).mineBlock(difficulty);
		
		blockchain.add(new Block("네번째 블록", blockchain.get(blockchain.size()-1).hash));
		System.out.println("mine block 4...");
		blockchain.get(3).mineBlock(difficulty);
		
		System.out.println();
		System.out.println("isValid? : " + isChainVaild());
		
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		
		System.out.println();
		System.out.println("chain : " +blockchainJson);
	}
	
	public static Boolean isChainVaild() {
		Block currentBlock;
		Block previousBlock;
		
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) return false;
			if (!previousBlock.hash.equals(currentBlock.previousHash)) return false;
			
		}
		return true;
	}

}
