package TrojanDetector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import utilityClasses.ModifiedInstance;
import utilityClasses.ModifiedTile;
import edu.byu.ece.rapidSmith.design.Attribute;
import edu.byu.ece.rapidSmith.design.Design;
import edu.byu.ece.rapidSmith.design.Instance;
import edu.byu.ece.rapidSmith.design.Net;
import edu.byu.ece.rapidSmith.device.PrimitiveSite;
import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.device.TileType;
import edu.byu.ece.rapidSmith.device.Utils;

public class Trojan {
	private HashSet<Net> affectedNets;
	private HashMap<String, ModifiedTile> affectedTileMapByName;
	private HashSet<ModifiedTile> affectedTiles;
	private Design goldenDesign;

	public Trojan() {
		this.affectedTiles = new HashSet<>();
	}

	public void addTiles(final List<ModifiedTile> newTiles) {
		this.affectedTiles.addAll(newTiles);
	}

	public boolean doesTrojanModifyCLB() {
		for (final ModifiedTile i : this.affectedTiles) {
			if (Utils.isCLB(i.getTile().getType())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesTrojanModifyClockGrid() {
		for (final ModifiedTile i : this.affectedTiles) {
			if (Utils.isCLK(i.getTile().getType())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesTrojanModifyDSP() {
		for (final ModifiedTile i : this.affectedTiles) {
			if (Utils.isDSP(i.getTile().getType())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesTrojanModifyIOB() {
		for (final ModifiedTile i : this.affectedTiles) {
			if (Utils.isIOB(i.getTile().getType())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesTrojanModifyMemory() {
		for (final ModifiedTile i : this.affectedTiles) {
			if (Utils.isBRAM(i.getTile().getType())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesTrojanModifyPower() {
		if (this.affectedTileMapByName == null) {
			this.makeAffectedTileHashMap();
		}
		for (final ModifiedTile i : this.affectedTiles) {
			final HashSet<ModifiedInstance> instances = i.getModifiedInstances();
			for (final ModifiedInstance j : instances) {
				final Instance inst = j.getGoldenInstance();
				new ArrayList<Attribute>(inst.getAttributes());
				if (inst.hasAttribute("_VCC_SOURCE") && i.wasTileDeactivated()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean doesTrojanModifyRouting() {
		if (this.affectedNets.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public HashSet<Net> getAffectedNets() {
		return this.affectedNets;
	}

	public List<ModifiedTile> getAffectedTiles() {
		return new ArrayList<ModifiedTile>(this.affectedTiles);
	}

	public List<ModifiedTile> getAffectedTilesByType(final TileType t) {
		final List<ModifiedTile> ret = new ArrayList<>();
		for (final ModifiedTile i : this.affectedTiles) {
			if (i.getTile().getType() == t) {
				ret.add(i);
			}
		}
		return ret;
	}

	public HashSet<ModifiedInstance> getAllInstancesThatHaveBeenDeactivated() {
		final HashSet<ModifiedInstance> ret = new HashSet<>();
		for (final ModifiedTile i : this.affectedTiles) {
			if (i.isGoldenConfigured() && !i.isTargetConfigured()) {
				ret.addAll(i.getModifiedInstances());
			}
		}
		return ret;
	}

	public List<PrimitiveSite> getAllPrimitiveSitesThatHaveBeenActivated() {
		final HashSet<PrimitiveSite> ret = new HashSet<PrimitiveSite>();
		for (final ModifiedTile i : this.affectedTiles) {
			if (!i.isGoldenConfigured() && i.isTargetConfigured()) {
				ret.addAll(i.getPrimitiveSites());
			}
		}
		return new ArrayList<PrimitiveSite>(ret);
	}

	public List<PrimitiveSite> getAllPrimitiveSitesThatHaveBeenDeactivated() {
		final HashSet<PrimitiveSite> ret = new HashSet<PrimitiveSite>();
		for (final ModifiedTile i : this.affectedTiles) {
			if (i.isGoldenConfigured() && !i.isTargetConfigured()) {
				ret.addAll(i.getPrimitiveSites());
			}
		}
		return new ArrayList<PrimitiveSite>(ret);
	}

	public HashSet<Tile> getAllTilesThatHaveBeenActivated() {
		final HashSet<Tile> ret = new HashSet<Tile>();
		for (final ModifiedTile i : this.affectedTiles) {
			if (!i.isGoldenConfigured() && i.isTargetConfigured()) {
				ret.add(i.getTile());
			}
		}
		return ret;
	}

	public HashSet<Tile> getAllTilesThatHaveBeenDeactivated() {
		final HashSet<Tile> ret = new HashSet<Tile>();
		for (final ModifiedTile i : this.affectedTiles) {
			if (i.isGoldenConfigured() && !i.isTargetConfigured()) {
				ret.add(i.getTile());
			}
		}
		return ret;
	}

	public int getAverageTrojanManhattenDistance() {
		int sum = 0, count = 0;
		if (this.goldenDesign == null) {
			return 0;
		}
		for (final ModifiedTile i : this.affectedTiles) {
			for (final ModifiedTile j : this.affectedTiles) {
				sum += i.getTile().getManhattanDistance(j.getTile());
				count++;
			}
		}
		return sum / count;
	}

	public Design getGoldenDesign() {
		return this.goldenDesign;
	}

	public int getLargestTrojanManhattenDistance() {
		int largest = 0;
		if (this.goldenDesign == null) {
			return 0;
		}
		for (final ModifiedTile i : this.affectedTiles) {
			for (final ModifiedTile j : this.affectedTiles) {
				final int y = i.getTile().getManhattanDistance(j.getTile());
				if (y > largest) {
					largest = y;
				}
			}
		}
		return largest;
	}

	public int getSmallestTrojanManhattenDistance() {
		int largest = Integer.MAX_VALUE;
		if (this.goldenDesign == null) {
			return Integer.MAX_VALUE;
		}
		for (final ModifiedTile i : this.affectedTiles) {
			for (final ModifiedTile j : this.affectedTiles) {
				final int y = i.getTile().getManhattanDistance(j.getTile());
				if (y < largest) {
					largest = y;
				}
			}
		}
		return largest;
	}

	public void makeAffectedTileHashMap() {
		this.affectedTileMapByName = new HashMap<>();
		for (final ModifiedTile m : this.affectedTiles) {
			this.affectedTileMapByName.put(m.getTile().getName(), m);
		}
	}

	public void printAffectedNets() {
		System.out.println("Affected Nets");
		for (final Net n : this.affectedNets) {
			System.out.println(n.getName());
		}
	}

	public void printTileNames() {
		System.out.println("Affected Tiles");
		for (final ModifiedTile m : this.affectedTiles) {
			System.out.println(m.getTile().getName());
		}
	}

	public void setAffectedNets(final HashSet<Net> affectedNets) {
		this.affectedNets = affectedNets;
	}

	public void setAffectedTiles(final HashSet<ModifiedTile> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}

	public void setAffectedTiles(final List<ModifiedTile> affectedTiles) {
		this.affectedTiles = new HashSet<ModifiedTile>(affectedTiles);
	}

	public void setGoldenDesign(final Design goldenDesign) {
		this.goldenDesign = goldenDesign;
	}

	public boolean wasTileActivatedByTrojan(final Tile tile) {
		for (final ModifiedTile i : this.affectedTiles) {
			if (i.getTile() == tile) {
				return i.wasTileActivated();
			}
		}
		return false;
	}

	public boolean wasTileDeactivatedByTrojan(final Tile tile) {
		for (final ModifiedTile i : this.affectedTiles) {
			if (i.getTile() == tile) {
				return i.wasTileDeactivated();
			}
		}
		return false;
	}

}
