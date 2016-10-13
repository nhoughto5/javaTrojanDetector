package utilityClasses;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import edu.byu.ece.rapidSmith.device.PrimitiveSite;
import edu.byu.ece.rapidSmith.device.Tile;

public class ModifiedTile {
	private Tile tile;
	private List<TileWord> goldenWords, targetWords;
	HashSet<ModifiedInstance> modifiedInstances;
	int numWordsPerTile;
	
	public ModifiedTile(Tile tile, List<TileWord> goldenWords,List<TileWord> targetWords, int numWordsPerTile) {
		this.tile = tile;
		this.goldenWords = goldenWords;
		this.targetWords = targetWords;
		this.numWordsPerTile = numWordsPerTile;
		this.modifiedInstances = new HashSet<>();
	}
	
	public List<PrimitiveSite> getPrimitiveSites(){
		return Arrays.asList(this.tile.getPrimitiveSites());
	}
	
	public boolean isGoldenConfigured(){
		for(TileWord tW : goldenWords){
			if(tW.getValue() != 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean isTargetConfigured(){
		for(TileWord tW : targetWords){
			if(tW.getValue() != 0){
				return true;
			}
		}
		return false;
	}
	
	public void addInstance(ModifiedInstance mI){
		modifiedInstances.add(mI);
	}
	
	public HashSet<ModifiedInstance> getModifiedInstances() {
		return modifiedInstances;
	}
	public void setModifiedInstances(HashSet<ModifiedInstance> modifiedInstances) {
		this.modifiedInstances = modifiedInstances;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goldenWords == null) ? 0 : goldenWords.hashCode());
		result = prime
				* result
				+ ((modifiedInstances == null) ? 0 : modifiedInstances
						.hashCode());
		result = prime * result + numWordsPerTile;
		result = prime * result
				+ ((targetWords == null) ? 0 : targetWords.hashCode());
		result = prime * result + ((tile == null) ? 0 : tile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModifiedTile other = (ModifiedTile) obj;
		if (goldenWords == null) {
			if (other.goldenWords != null)
				return false;
		} else if (!goldenWords.equals(other.goldenWords))
			return false;
		if (modifiedInstances == null) {
			if (other.modifiedInstances != null)
				return false;
		} else if (!modifiedInstances.equals(other.modifiedInstances))
			return false;
		if (numWordsPerTile != other.numWordsPerTile)
			return false;
		if (targetWords == null) {
			if (other.targetWords != null)
				return false;
		} else if (!targetWords.equals(other.targetWords))
			return false;
		if (tile == null) {
			if (other.tile != null)
				return false;
		} else if (!tile.equals(other.tile))
			return false;
		return true;
	}
	

}
