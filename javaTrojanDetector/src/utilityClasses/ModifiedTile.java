package utilityClasses;

import java.util.List;

import edu.byu.ece.rapidSmith.device.Tile;

public class ModifiedTile {
	private Tile tile;
	private List<Integer> goldenWords, targetWords;
	int numWordsPerTile;
	
	
	
	public ModifiedTile(Tile tile, List<Integer> goldenWords,List<Integer> targetWords, int numWordsPerTile) {
		this.tile = tile;
		this.goldenWords = goldenWords;
		this.targetWords = targetWords;
		this.numWordsPerTile = numWordsPerTile;
	}
	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	public List<Integer> getGoldenWords() {
		return goldenWords;
	}
	public void setGoldenWords(List<Integer> goldenWords) {
		this.goldenWords = goldenWords;
	}
	public List<Integer> getTargetWords() {
		return targetWords;
	}
	public void setTargetWords(List<Integer> targetWords) {
		this.targetWords = targetWords;
	}
	public int getNumWordsPerTile() {
		return numWordsPerTile;
	}
	public void setNumWordsPerTile(int numWordsPerTile) {
		this.numWordsPerTile = numWordsPerTile;
	}
	
}
