package com.daniloaraujosilva.file_parser.model.utils;

import java.math.BigDecimal;

/**
 *
 */
public class NumberUtils {

	/**
	 *
	 * @param input
	 * @return
	 */
	public static BigDecimal parseBigDecimal(Object input) {
		try {
			return new BigDecimal(input.toString().trim());
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static Boolean parseBoolean(Object input) {
		try {
			return Boolean.parseBoolean(input.toString().trim());
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static Double parseDouble(Object input) {
		try {
			return Double.parseDouble(input.toString().trim());
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static Integer parseInteger(Object input) {
		try {
			return Integer.parseInt(input.toString().trim());
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static Short parseShort(Object input) {
		try {
			return Short.parseShort(input.toString().trim());
		} catch (Exception exception) {
			return null;
		}
	}
}
