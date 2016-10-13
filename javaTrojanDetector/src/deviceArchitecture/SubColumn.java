package deviceArchitecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import utilityClasses.DeviceColumnInfo;
import edu.byu.ece.rapidSmith.bitstreamTools.configurationSpecification.XilinxConfigurationSpecification;
import edu.byu.ece.rapidSmith.device.Tile;
import edu.byu.ece.rapidSmith.device.Utils;

public class SubColumn {
	int column;
	private final DeviceColumnInfo deviceInfo;
	private final XilinxConfigurationSpecification spec;
	String subColumnType, primaryColumnType;
	List<Tile> tiles;

	public SubColumn(final String primaryColumnType, final List<Tile> tiles,
			final int column, final XilinxConfigurationSpecification spec,
			final DeviceColumnInfo deviceInfo) {
		this.tiles = tiles;
		this.primaryColumnType = primaryColumnType;
		this.column = column;
		this.spec = spec;
		this.deviceInfo = deviceInfo;
		this.setConfigurable();
		this.setIsPrimarySeat();
		this.findSubColumnType();
	}

	private void findSubColumnType() {
		final HashMap<String, Integer> map = new HashMap<>();
		for (final Tile t : this.tiles) {
			if (Utils.getColumnSubType(t) != null) {
				final Integer val = map.get(Utils.getColumnSubType(t));
				map.put(Utils.getColumnSubType(t), val == null ? 1 : val + 1);
			}
		}

		Entry<String, Integer> max = null;

		for (final Entry<String, Integer> e : map.entrySet()) {
			if ((max == null) || (e.getValue() > max.getValue())) {
				max = e;
			}
		}
		if (!map.isEmpty()) {
			this.subColumnType = max.getKey();
		}
	}

	public List<Tile> getAffectedTiles(final int totalNumRows, final int rowNum) {
		final List<Tile> ret = new ArrayList<>();
		final int numPrimaryTilesPerRow = this.deviceInfo
				.getNumberOfPrimaryTilesInColumn();
		// Add for clock tile and starting null tile
		final int numRowsPerRow = numPrimaryTilesPerRow + 2;
		for (int i = 0; i < numRowsPerRow; ++i) {
			final Tile currentTile = this.tiles.get(i
					+ (rowNum * numRowsPerRow));
			if (currentTile.isPrimarySeat() && currentTile.isConfigurable()) {
				ret.add(currentTile);
			}
		}
		return ret;
	}

	public String getSubColumnType() {
		return this.subColumnType;
	}

	public List<Tile> getTiles() {
		return this.tiles;
	}

	private void setConfigurable() {
		for (int i = 0; i < this.tiles.size(); ++i) {
			final Tile currentTile = this.tiles.get(i);
			if (Utils.isPrimaryTile(currentTile.getType())
					|| Utils.isHorizontalClockTile(currentTile.getType())) {
				currentTile.setConfigurable(true);
				this.tiles.set(i, currentTile);
			} else {
				currentTile.setConfigurable(false);
				this.tiles.set(i, currentTile);
			}
		}
	}

	// A gate-array is organized into a regular structure.
	// Set a flag for tiles which sit in a primary configurable location in the
	// column.
	private void setIsPrimarySeat() {
		final int totalNumRows = this.spec.getTopNumberOfRows()
				+ this.spec.getBottomNumberOfRows();
		final int numPrimaryTilesPerRow = this.deviceInfo
				.getNumberOfPrimaryTilesInColumn();

		// Add for clock tile and starting null tile
		final int numRowsPerRow = numPrimaryTilesPerRow + 2;
		for (int row = 0; row < totalNumRows; ++row) {
			for (int i = 0; i < numRowsPerRow; ++i) {
				final Tile currentTile = this.tiles.get(i
						+ (row * numRowsPerRow));
				if (i != 0) {
					currentTile.setPrimarySeat(true);
				}
			}
		}
		// Get the very last tile, this isn't necessary but do it to be verbose
		final Tile currentTile = this.tiles.get(this.tiles.size() - 1);
		currentTile.setPrimarySeat(false);

	}

	public void setSubColumnType(final String subColumnType) {
		this.subColumnType = subColumnType;
	}

	public void setTiles(final List<Tile> tiles) {
		this.tiles = tiles;
	}

}
