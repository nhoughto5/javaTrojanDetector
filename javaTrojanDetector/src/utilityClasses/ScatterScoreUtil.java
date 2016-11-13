package utilityClasses;

import java.util.List;

import TrojanDetector.Trojan;
import edu.byu.ece.rapidSmith.device.Tile;

public class ScatterScoreUtil {
	private static final double SMALL_TROJAN_THRESHOLD = 5.0;
	private static final double CLUSTERED_TROJAN_THRESHOLD = 15.0;

	private List<Tile> goldenConfiguredTiles, targetConfiguredTiles;
	private PositionMedian goldenPositionMedian, targetPositionMedian;
	private ScatterScore goldenScatterScore, targetScatterScore;
	private Trojan trojan;

	public ScatterScoreUtil(Trojan trojan) {
		this.trojan = trojan;
		this.goldenConfiguredTiles = this.trojan.getAllGoldenTiles();
		this.targetConfiguredTiles = this.trojan.getAllTargetTiles();

		this.goldenPositionMedian = new PositionMedian(
				this.averageX(this.goldenConfiguredTiles),
				this.averageY(this.goldenConfiguredTiles));
		this.targetPositionMedian = new PositionMedian(
				this.averageX(this.targetConfiguredTiles),
				this.averageY(this.targetConfiguredTiles));

		this.goldenScatterScore = new ScatterScore(
				this.standardDeviationX(this.goldenConfiguredTiles),
				this.standardDeviationY(this.goldenConfiguredTiles));
		this.targetScatterScore = new ScatterScore(
				this.standardDeviationX(this.targetConfiguredTiles),
				this.standardDeviationY(this.targetConfiguredTiles));
	}

	public boolean isSmall() {
		int numTilesInGolden = this.goldenConfiguredTiles.size();
		int numReconfigured = this.trojan.getAffectedTiles().size();
		double percent = ((numReconfigured * 1.0) / numTilesInGolden) * 100.0;
		if (percent <= SMALL_TROJAN_THRESHOLD) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAugmented() {
		if (this.trojan.getAllTilesThatHaveBeenActivated().size() == 0
				&& this.trojan.getAllTilesThatHaveBeenDeactivated().size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isDistributed() {
		if ((this.targetScatterScore.getDeltaX() * 100.0) > CLUSTERED_TROJAN_THRESHOLD
				|| (this.targetScatterScore.getDeltaY() * 100.0) > CLUSTERED_TROJAN_THRESHOLD) {
			return false;
		}
		else{
			return true;
		}
	}

	public double averageX(final List<Tile> tiles) {
		int sum = 0;
		for (final Tile t : tiles) {
			sum += t.getTileXCoordinate();
		}
		return (sum * 1.0) / tiles.size();
	}

	public double averageY(final List<Tile> tiles) {
		int sum = 0;
		for (final Tile t : tiles) {
			sum += t.getTileYCoordinate();
		}
		return (sum * 1.0) / tiles.size();
	}

	public List<Tile> getGoldenConfiguredTiles() {
		return this.goldenConfiguredTiles;
	}

	public PositionMedian getGoldenPositionMedian() {
		return this.goldenPositionMedian;
	}

	public ScatterScore getGoldenScatterScore() {
		return this.goldenScatterScore;
	}

	public List<Tile> getTargetConfiguredTiles() {
		return this.targetConfiguredTiles;
	}

	public PositionMedian getTargetPositionMedian() {
		return this.targetPositionMedian;
	}

	public ScatterScore getTargetScatterScore() {
		return this.targetScatterScore;
	}

	public void setGoldenConfiguredTiles(final List<Tile> goldenConfiguredTiles) {
		this.goldenConfiguredTiles = goldenConfiguredTiles;
	}

	public void setGoldenPositionMedian(
			final PositionMedian goldenPositionMedian) {
		this.goldenPositionMedian = goldenPositionMedian;
	}

	public void setGoldenScatterScore(final ScatterScore goldenScatterScore) {
		this.goldenScatterScore = goldenScatterScore;
	}

	public void setTargetConfiguredTiles(final List<Tile> targetConfiguredTiles) {
		this.targetConfiguredTiles = targetConfiguredTiles;
	}

	public void setTargetPositionMedian(
			final PositionMedian targetPositionMedian) {
		this.targetPositionMedian = targetPositionMedian;
	}

	public void setTargetScatterScore(final ScatterScore targetScatterScore) {
		this.targetScatterScore = targetScatterScore;
	}

	public double standardDeviationX(final List<Tile> tiles) {
		final double average = this.averageX(tiles);
		double diffSumSqr = 0;
		for (final Tile t : tiles) {
			diffSumSqr += Math.pow((t.getTileXCoordinate() - average), 2);
		}
		return Math.sqrt(diffSumSqr / tiles.size());
	}

	public double standardDeviationY(final List<Tile> tiles) {
		final double average = this.averageY(tiles);
		double diffSumSqr = 0;
		for (final Tile t : tiles) {
			diffSumSqr += Math.pow((t.getTileYCoordinate() - average), 2);
		}
		return Math.sqrt(diffSumSqr / tiles.size());
	}

}
