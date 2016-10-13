package TrojanDetector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.byu.ece.rapidSmith.design.Instance;
import edu.byu.ece.rapidSmith.design.Net;
import edu.byu.ece.rapidSmith.device.PrimitiveSite;
import edu.byu.ece.rapidSmith.device.PrimitiveType;
import edu.byu.ece.rapidSmith.device.Tile;
import utilityClasses.ModifiedInstance;
import utilityClasses.ModifiedTile;

public class Trojan {
	public HashSet<ModifiedTile> affectedTiles;
	public HashSet<Net> affectedNets;
	public Trojan() {
		affectedTiles = new HashSet<>();
	}

	public void printTileNames(){
		System.out.println("Affected Tiles");
		for(ModifiedTile m : affectedTiles){
			System.out.println(m.getTile().getName());
		}
	}
	
	public List<PrimitiveSite> getAllPrimitiveSitesDeactivated(){
		HashSet<PrimitiveSite> ret = new HashSet<PrimitiveSite>();
		for(ModifiedTile i : affectedTiles){
			if(i.isGoldenConfigured() && !i.isTargetConfigured()){
				ret.addAll(i.getPrimitiveSites());
			}
		}
		return new ArrayList<PrimitiveSite>(ret);
	}
	
	public List<PrimitiveSite> getAllPrimitiveSitesActivated(){
		HashSet<PrimitiveSite> ret = new HashSet<PrimitiveSite>();
		for(ModifiedTile i : affectedTiles){
			if(!i.isGoldenConfigured() && i.isTargetConfigured()){
				ret.addAll(i.getPrimitiveSites());
			}
		}
		return new ArrayList<PrimitiveSite>(ret);
	}
	
	public HashSet<Tile> getAllTilesThatHaveBeenActivated(){
		HashSet<Tile> ret = new HashSet<Tile>();
		for(ModifiedTile i : affectedTiles){
			if(!i.isGoldenConfigured() && i.isTargetConfigured()){
				ret.add(i.getTile());
			}
		}
		return ret;
	}
	
	public HashSet<Tile> getAllTilesThatHaveBeenDeactivated(){
		HashSet<Tile> ret = new HashSet<Tile>();
		for(ModifiedTile i : affectedTiles){
			if(i.isGoldenConfigured() && !i.isTargetConfigured()){
				ret.add(i.getTile());
			}
		}
		return ret;
	}
	
	public HashSet<ModifiedInstance> getAllInstancesThatHaveBeenDeactivated(){
		HashSet<ModifiedInstance> ret = new HashSet<>();
		for(ModifiedTile i : affectedTiles){
			if(i.isGoldenConfigured() && !i.isTargetConfigured()){
				ret.addAll(i.getModifiedInstances());
			}
		}
		return ret;
	}
	
	public void printAffectedNets(){
		System.out.println("Affected Nets");
		for(Net n : affectedNets){
			System.out.println(n.getName());
		}
	}
	
	public List<ModifiedTile> getAffectedTiles() {
		return new ArrayList<ModifiedTile>(affectedTiles);
	}

	public void setAffectedTiles(List<ModifiedTile> affectedTiles) {
		this.affectedTiles = new HashSet<ModifiedTile>(affectedTiles);
	}
	
	public void addTiles(List<ModifiedTile> newTiles){
		affectedTiles.addAll(newTiles);
	}

	public HashSet<Net> getAffectedNets() {
		return affectedNets;
	}

	public void setAffectedNets(HashSet<Net> affectedNets) {
		this.affectedNets = affectedNets;
	}

	public void setAffectedTiles(HashSet<ModifiedTile> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}
	
}
