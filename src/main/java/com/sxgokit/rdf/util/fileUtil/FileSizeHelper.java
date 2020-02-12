package com.sxgokit.rdf.util.fileUtil;

import java.text.DecimalFormat;

/**
 * 获取文件大小工具类
 */
public class FileSizeHelper {

	public static long ONE_KB = 1024;
	public static long ONE_MB = ONE_KB * 1024;
	public static long ONE_GB = ONE_MB * 1024;
	public static long ONE_TB = ONE_GB * (long)1024;
	public static long ONE_PB = ONE_TB * (long)1024;

	public static String getHumanReadableFileSize(Long fileSize) {
		if(fileSize == null) return null;
		return getHumanReadableFileSize(fileSize.longValue());
	}

	/**
	 * 获取文件对应单位大小
	 * @param fileSize 文件大小
	 * @return
	 */
	public static String getHumanReadableFileSize(long fileSize) {
		if(fileSize < 0) {
			return String.valueOf(fileSize);
		}
		String result = getHumanReadableFileSize(fileSize, ONE_PB, "PB");
		if(result != null) {
			return result;
		}

		result = getHumanReadableFileSize(fileSize, ONE_TB, "TB");
		if(result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_GB, "GB");
		if(result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_MB, "MB");
		if(result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_KB, "KB");
		if(result != null) {
			return result;
		}
		return String.valueOf(fileSize)+"B";
	}

	/**
	 * 转换为对应单位格式的大小
	 * @param fileSize 文件大小
	 * @param unit 单位
	 * @param unitName 单位名称
	 * @return
	 */
	private static String getHumanReadableFileSize(long fileSize, long unit, String unitName) {
		if(fileSize == 0) return "0";

		if(fileSize / unit >= 1) {
			double value = fileSize / (double)unit;
			DecimalFormat df = new DecimalFormat("######.##"+unitName);
			return df.format(value);
		}
		return null;
	}
}
