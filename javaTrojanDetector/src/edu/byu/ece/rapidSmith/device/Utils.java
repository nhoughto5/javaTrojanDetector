/*
 * Copyright (c) 2010 Brigham Young University
 * 
 * This file is part of the BYU RapidSmith Tools.
 * 
 * BYU RapidSmith Tools is free software: you may redistribute it
 * and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * BYU RapidSmith Tools is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License is included with the BYU
 * RapidSmith Tools. It can be found at doc/gpl2.txt. You may also 
 * get a copy of the license at <http://www.gnu.org/licenses/>.
 * 
 */
package edu.byu.ece.rapidSmith.device;

import java.util.HashSet;

import utilityClasses.Error;

/**
 * This is a helper class for creating PrimitiveTypes and TileTypes
 * as well as helping to categorize TileTypes. 
 */
public class Utils{

	private static HashSet<TileType> clbs;
	
	private static HashSet<TileType> dsps;
	
	private static HashSet<TileType> brams;
	
	private static HashSet<TileType> ints;
	
	private static HashSet<TileType> cnfgs;
	
	private static HashSet<TileType> iob;
	
	private static HashSet<TileType> clk;
	
	private static HashSet<TileType> bufs;
	
	private static HashSet<TileType> interfaces;
	
	private static HashSet<TileType> gtp;
	
	private static HashSet<TileType> logic_Overhead;
	
	private static HashSet<TileType> horizontalClock;
	
	private static HashSet<TileType> ignores;
	
	private static HashSet<TileType> miscellaneous;
	/**
	 * Returns a PrimitiveType enum based on the given string. If such
	 * an enum does not exist, it will return null.
	 * @param s The string to be converted to an enum type
	 * @return The PrimitiveType corresponding to the string s, null if none exists.
	 */
	public static PrimitiveType createPrimitiveType(String s){
		return PrimitiveType.valueOf(s.toUpperCase());
	}

	/**
	 * Returns a TileType enum based on the given string s.  If such an enum
	 * does not exist, it will return null
	 * @param s The string to be converted to an enum type
	 * @return The TileType corresponding to String s, null if none exists.
	 */
	public static TileType createTileType(String s){
		return TileType.valueOf(s.toUpperCase());
	}
	
	/**
	 * Determines if the provided tile type contains SLICE primitive sites
	 * of any type.
	 * @param type The tile type to test for.
	 * @return True if this tile type has SLICE (any kind) primitive sites.
	 */
	public static boolean isCLB(TileType type){
		return clbs.contains(type);
	}
	
	/**
	 * Determines if the provided tile type contains DSP primitive sites
	 * of any type.
	 * @param type The tile type to test for.
	 * @return True if this tile type has DSP (any kind) primitive sites.
	 */
	public static boolean isDSP(TileType type){
		return dsps.contains(type);
	}
	
	/**
	 * Determines if the provided tile type contains BRAM primitive sites
	 * of any type.
	 * @param type The tile type to test for.
	 * @return True if this tile type has BRAM (any kind) primitive sites.
	 */
	public static boolean isBRAM(TileType type){
		return brams.contains(type);
	}
	
	/**
	 * Determines if the provided tile type contains BRAM primitive sites
	 * of any type.
	 * @param type The tile type to test for.
	 * @return True if this tile type has BRAM (any kind) primitive sites.
	 */
	public static boolean isSwitchBox(TileType type){
		return ints.contains(type);
	}
	
	public static boolean isCNFG(TileType type){
		return cnfgs.contains(type);
	}
	
	public static boolean isIOB(TileType type){
		return iob.contains(type);
	}
	
	public static boolean isCLK(TileType type){
		return clk.contains(type);
	}
	
	public static boolean isBUFS(TileType type){
		return bufs.contains(type);
	}
	
	public static boolean isIGNORE(TileType type){
		return ignores.contains(type);
	}
	
	public static boolean isMiscellaneousPrimaryType(TileType type){
		return miscellaneous.contains(type);
	}
	
	public static boolean isInterconnect(TileType type){
		return interfaces.contains(type);
	}
	
	public static boolean isGTP(TileType type){
		return gtp.contains(type);
	}
	
	public static boolean isLogicOverhead(TileType type){
		return logic_Overhead.contains(type);
	}
	
	public static boolean isHorizontalClockTile(TileType type){
		return horizontalClock.contains(type);
	}

	public static boolean isPrimaryTile(TileType type) {
		if (isCLB(type) || isBRAM(type) || isDSP(type) || isSwitchBox(type)
				|| isCNFG(type) || isIOB(type) || isCLK(type) || isBUFS(type)
				|| isMiscellaneousPrimaryType(type) || isGTP(type)
				|| isLogicOverhead(type)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getColumnSubType(Tile tile){
		String tileType = null;
		if(isCLB(tile.getType())){
			tileType = "CLB";
		}
		else if(isBRAM(tile.getType())){
			tileType = "BRAMINTERCONNECT";
		}
		else if(isDSP(tile.getType())){
			tileType = "DSP";
		}
		else if(isSwitchBox(tile.getType())){
			tileType = "INTERCONNECT";
		}
		else if(isCNFG(tile.getType())){
			tileType = "CFG";
		}
		else if(isIOB(tile.getType())){
			tileType = "IOB";
		}
		else if(isCLK(tile.getType())){
			tileType = "CLK";
		}
		else if(isBUFS(tile.getType())){
			tileType = "BUFS";
		}
		else if(isMiscellaneousPrimaryType(tile.getType())){
			tileType = "miscellaneous";
		}
		else if(isInterconnect(tile.getType())){
			tileType = "INTERFACE";
		}
		else if(isGTP(tile.getType())){
			tileType = "GTP";
		}
		else if(isLogicOverhead(tile.getType())){
			tileType = "LOGIC_OVERHEAD";
		}
		else if(isHorizontalClockTile(tile.getType())){
			tileType = "HORIZONTAL_CLOCK";
		}
		else if (isIGNORE(tile.getType())){
			tileType = null;
		}
		else{
			//System.out.println("Unexpected Tile Type, Add: ignores.add(TileType." + tile.getType()+");  Name: " + tile.getName());
			Error.printError("Unexpected Tile Type, Add: ignores.add(TileType." + tile.getType()+");  Name: " + tile.getName(), new Exception().getStackTrace()[0]);
		}
		return tileType;
	}
	
	static{
		clbs = new HashSet<TileType>();
		clbs.add(TileType.CLB);
		clbs.add(TileType.CLBLL);
		clbs.add(TileType.CLBLM);
		clbs.add(TileType.CLEXL);
		clbs.add(TileType.CLEXM);
		clbs.add(TileType.CLBLL_L);
		clbs.add(TileType.CLBLL_R);
		clbs.add(TileType.CLBLM_L);
		clbs.add(TileType.CLBLM_R);
		
		dsps = new HashSet<TileType>();
		dsps.add(TileType.DSP);
		dsps.add(TileType.DSP_L);
		dsps.add(TileType.DSP_R);
		dsps.add(TileType.MACCSITE2);
		dsps.add(TileType.MACCSITE2_BRK);
		dsps.add(TileType.BRAMSITE);
		dsps.add(TileType.BRAMSITE2);
		dsps.add(TileType.BRAMSITE2_BRK);
		
		brams = new HashSet<TileType>();
		brams.add(TileType.BRAM);
		brams.add(TileType.BRAM_L);
		brams.add(TileType.BRAM_R);
		brams.add(TileType.LBRAM);
		brams.add(TileType.RBRAM);
		brams.add(TileType.BRAMSITE);
		brams.add(TileType.BRAMSITE2);
		brams.add(TileType.BRAMSITE2_3M);
		brams.add(TileType.BRAMSITE2_3M_BRK);
		brams.add(TileType.BRAMSITE2_BRK);
		brams.add(TileType.MBRAM);

		ints = new HashSet<TileType>();
		ints.add(TileType.INT);
		ints.add(TileType.INT_L);
		ints.add(TileType.INT_R);
		ints.add(TileType.INT_SO);
		ints.add(TileType.INT_SO_DCM0);
		ints.add(TileType.INT_BRAM);
		ints.add(TileType.INT_BRAM_BRK);
		ints.add(TileType.INT_BRK);
		ints.add(TileType.INT_GCLK);
		ints.add(TileType.IOI_INT);
		ints.add(TileType.INT_TERM);
		ints.add(TileType.INT_TERM_BRK);
		ints.add(TileType.LIOI_INT);
		ints.add(TileType.LIOI_INT_BRK);
		
		cnfgs = new HashSet<TileType>();
		cnfgs.add(TileType.CFG_VBRK);
		
		iob = new HashSet<TileType>();
		iob.add(TileType.BIOB);
		iob.add(TileType.CIOB);
		iob.add(TileType.LIOB);
		iob.add(TileType.RIOB);
		
		interfaces = new HashSet<TileType>();
		interfaces.add(TileType.INT_INTERFACE);
		
		clk = new HashSet<TileType>();
		clk.add(TileType.CLKV);
		clk.add(TileType.CLKV_MC);
		clk.add(TileType.CLK_HROW);
		clk.add(TileType.CLK_MGT_TOP);
		clk.add(TileType.CLK_IOB_T);
		clk.add(TileType.CLK_MGT_BOT);
		
		bufs = new HashSet<TileType>();
		bufs.add(TileType.INT_BUFS_L);
		bufs.add(TileType.INT_BUFS_R);
		
		gtp = new HashSet<TileType>();
		gtp.add(TileType.GTP_INT_INTERFACE);
		
		logic_Overhead = new HashSet<TileType>();
		logic_Overhead.add(TileType.R_TERM_INT);
		logic_Overhead.add(TileType.R_TERM_INT_D);
		
		miscellaneous = new HashSet<TileType>();
		miscellaneous.add(TileType.PCIE_INT_INTERFACE);
		miscellaneous.add(TileType.EMAC_INT_INTERFACE);
		miscellaneous.add(TileType.EMAC);
		miscellaneous.add(TileType.PCIE_T);
		miscellaneous.add(TileType.PCIE_B);
		miscellaneous.add(TileType.IOI);
		miscellaneous.add(TileType.L_TERM_INT);
		miscellaneous.add(TileType.R_TERM_INT);
		miscellaneous.add(TileType.CMT_BOT);
		miscellaneous.add(TileType.CMT_TOP);
		
		horizontalClock = new HashSet<TileType>();
		horizontalClock.add(TileType.HCLK);
		horizontalClock.add(TileType.HCLK_BRAM);
		horizontalClock.add(TileType.HCLK_BRAM_FEEDTHRU);
		horizontalClock.add(TileType.HCLK_BRAM_FEEDTHRU_FOLD);
		horizontalClock.add(TileType.HCLK_BRAM_FX);
		horizontalClock.add(TileType.HCLK_BRAM_MGT);
		horizontalClock.add(TileType.HCLK_BRAM_MGT_LEFT);
		horizontalClock.add(TileType.HCLK_CENTER);
		horizontalClock.add(TileType.HCLK_CENTER_ABOVE_CFG);
		horizontalClock.add(TileType.HCLK_CLB);
		horizontalClock.add(TileType.HCLK_CLBLL);
		horizontalClock.add(TileType.HCLK_CLBLM);
		horizontalClock.add(TileType.HCLK_CLBLM_MGT);
		horizontalClock.add(TileType.HCLK_CLBLM_MGT_LEFT);
		horizontalClock.add(TileType.HCLK_CLB_XL_CLE);
		horizontalClock.add(TileType.HCLK_CLB_XL_CLE_FOLD);
		horizontalClock.add(TileType.HCLK_CLB_XL_INT);
		horizontalClock.add(TileType.HCLK_CLB_XL_INT_FOLD);
		horizontalClock.add(TileType.HCLK_CLB_XM_CLE);
		horizontalClock.add(TileType.HCLK_CLB_XM_CLE_FOLD);
		horizontalClock.add(TileType.HCLK_CLB_XM_INT);
		horizontalClock.add(TileType.HCLK_CLB_XM_INT_FOLD);
		horizontalClock.add(TileType.HCLK_CMT_BOT);
		horizontalClock.add(TileType.HCLK_CMT_CMT);
		horizontalClock.add(TileType.HCLK_CMT_CMT_MGT);
		horizontalClock.add(TileType.HCLK_CMT_IOI);
		horizontalClock.add(TileType.HCLK_CMT_TOP);
		horizontalClock.add(TileType.HCLK_DCM);
		horizontalClock.add(TileType.HCLK_DCMIOB);
		horizontalClock.add(TileType.HCLK_DSP);
		horizontalClock.add(TileType.HCLK_FT);
		horizontalClock.add(TileType.HCLK_GT3);
		horizontalClock.add(TileType.HCLK_GTH);
		horizontalClock.add(TileType.HCLK_GTH_LEFT);
		horizontalClock.add(TileType.HCLK_GTX);
		horizontalClock.add(TileType.HCLK_GTX_DUMMY);
		horizontalClock.add(TileType.HCLK_GTX_LEFT);
		horizontalClock.add(TileType.HCLK_GT_EMP);
		horizontalClock.add(TileType.HCLK_INNER_IOI);
		horizontalClock.add(TileType.HCLK_INT_INTERFACE);
		horizontalClock.add(TileType.HCLK_IOB);
		horizontalClock.add(TileType.HCLK_IOBDCM);
		horizontalClock.add(TileType.HCLK_IOB_CMT_BOT);
		horizontalClock.add(TileType.HCLK_IOB_CMT_BOT_MGT);
		horizontalClock.add(TileType.HCLK_IOB_CMT_MID);
		horizontalClock.add(TileType.HCLK_IOB_CMT_MID_MGT);
		horizontalClock.add(TileType.HCLK_IOB_CMT_TOP);
		horizontalClock.add(TileType.HCLK_IOB_CMT_TOP_MGT);
		horizontalClock.add(TileType.HCLK_IOI);
		horizontalClock.add(TileType.HCLK_IOIL_BOT_DN);
		horizontalClock.add(TileType.HCLK_IOIL_BOT_SPLIT);
		horizontalClock.add(TileType.HCLK_IOIL_BOT_UP);
		horizontalClock.add(TileType.HCLK_IOIL_EMP);
		horizontalClock.add(TileType.HCLK_IOIL_INT);
		horizontalClock.add(TileType.HCLK_IOIL_INT_FOLD);
		horizontalClock.add(TileType.HCLK_IOIL_TOP_DN);
		horizontalClock.add(TileType.HCLK_IOIL_TOP_SPLIT);
		horizontalClock.add(TileType.HCLK_IOIL_TOP_UP);
		horizontalClock.add(TileType.HCLK_IOIR_BOT_DN);
		horizontalClock.add(TileType.HCLK_IOIR_BOT_SPLIT);
		horizontalClock.add(TileType.HCLK_IOIR_BOT_UP);
		horizontalClock.add(TileType.HCLK_IOIR_EMP);
		horizontalClock.add(TileType.HCLK_IOIR_INT);
		horizontalClock.add(TileType.HCLK_IOIR_INT_FOLD);
		horizontalClock.add(TileType.HCLK_IOIR_TOP_DN);
		horizontalClock.add(TileType.HCLK_IOIR_TOP_SPLIT);
		horizontalClock.add(TileType.HCLK_IOIR_TOP_UP);
		horizontalClock.add(TileType.HCLK_IOIS_DCI);
		horizontalClock.add(TileType.HCLK_IOIS_LVDS);
		horizontalClock.add(TileType.HCLK_IOI_BOTCEN);
		horizontalClock.add(TileType.HCLK_IOI_BOTCEN_MGT);
		horizontalClock.add(TileType.HCLK_IOI_CENTER);
		horizontalClock.add(TileType.HCLK_IOI_CMT);
		horizontalClock.add(TileType.HCLK_IOI_CMT_MGT);
		horizontalClock.add(TileType.HCLK_IOI_LTERM);
		horizontalClock.add(TileType.HCLK_IOI_LTERM_BOT25);
		horizontalClock.add(TileType.HCLK_IOI_RTERM);
		horizontalClock.add(TileType.HCLK_IOI_RTERM_BOT25);
		horizontalClock.add(TileType.HCLK_IOI_TOPCEN);
		horizontalClock.add(TileType.HCLK_IOI_TOPCEN_MGT);
		horizontalClock.add(TileType.HCLK_LIOB);
		horizontalClock.add(TileType.HCLK_MGT);
		horizontalClock.add(TileType.HCLK_OUTER_IOI);
		horizontalClock.add(TileType.HCLK_PCIE_BRAM);
		horizontalClock.add(TileType.HCLK_PPC);
		horizontalClock.add(TileType.HCLK_PPC_TERM);
		horizontalClock.add(TileType.HCLK_QBUF_L);
		horizontalClock.add(TileType.HCLK_QBUF_R);
		horizontalClock.add(TileType.HCLK_TERM);
		horizontalClock.add(TileType.HCLK_TERM_L);
		horizontalClock.add(TileType.HCLK_TERM_R);
		horizontalClock.add(TileType.HCLK_VBRK);
		horizontalClock.add(TileType.HCLK_VBRK_R);
		horizontalClock.add(TileType.HCLK_VFRAME);
		
		ignores = new HashSet<TileType>();
		ignores.add(TileType.NULL);
		ignores.add(TileType.CLK_CMT_TOP);
		ignores.add(TileType.BRKH);
		ignores.add(TileType.T_TERM_INT);
		ignores.add(TileType.T_TERM_INT_D);
		ignores.add(TileType.B_TERM_INT_D);
		ignores.add(TileType.CFG_HCLK_INTERFACE);
		ignores.add(TileType.BRKH_IOI);
		ignores.add(TileType.BRKH_CLB);
		ignores.add(TileType.BRKH_BRAM);
		ignores.add(TileType.BRKH_DSP);
		ignores.add(TileType.INT_HCLK_BUFS);
		ignores.add(TileType.SITE_FEEDTHRU);
		ignores.add(TileType.CMT_BOT);
		ignores.add(TileType.CFG_CENTER);
		ignores.add(TileType.CLK_TERM_TOP);
		ignores.add(TileType.CLKV_MC);
		ignores.add(TileType.CLK_HROW);
		ignores.add(TileType.CLK_BUFGMUX);
		ignores.add(TileType.CLK_HROW);
		ignores.add(TileType.CLKV_MC);
		ignores.add(TileType.GBRKC);
		ignores.add(TileType.CLK_IOB_B);
		ignores.add(TileType.CLK_HROW);
		ignores.add(TileType.CLK_CMT_BOT);
		ignores.add(TileType.CLK_TERM_BOT);
		ignores.add(TileType.PCIE_BRAM);
		ignores.add(TileType.PCIE_BRAM);
		ignores.add(TileType.GT3);
		ignores.add(TileType.BRKH_GT3);
		ignores.add(TileType.PCIE_BRKH);
	}
}
