package utilityClasses;

import java.util.List;

import edu.byu.ece.rapidSmith.device.Tile;

public class ModifiedTile {
	private Tile tile;
	private List<TileWord> goldenWords, targetWords;
	int numWordsPerTile;
	
	public ModifiedTile(Tile tile, List<TileWord> goldenWords,List<TileWord> targetWords, int numWordsPerTile) {
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
	public List<TileWord> getGoldenWords() {
		return goldenWords;
	}
	public void setGoldenWords(List<TileWord> goldenWords) {
		this.goldenWords = goldenWords;
	}
	public List<TileWord> getTargetWords() {
		return targetWords;
	}
	public void setTargetWords(List<TileWord> targetWords) {
		this.targetWords = targetWords;
	}
	public int getNumWordsPerTile() {
		return numWordsPerTile;
	}
	public void setNumWordsPerTile(int numWordsPerTile) {
		this.numWordsPerTile = numWordsPerTile;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this==obj)
			return true;
		else if( (obj == null) || (obj.getClass() != this.getClass())){
			return false;
		}
		ModifiedTile tile = (ModifiedTile) obj;
		return (tile.getTile().getName() == tile.getTile().getName());
	}
	
	@Override
	public int hashCode(){
		return this.tile.getName().hashCode();
	}
}
