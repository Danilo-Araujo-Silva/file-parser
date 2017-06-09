package com.daniloaraujosilva.file_parser.model.utils;

/**
 *
 */
public class ObjectUtils {

	/**
	 *
	 * @param input
	 * @param defaultOutput
	 * @param returningClass
	 * @param <R>
	 * @return
	 */
	public static <R> R coalesce(R input, Object defaultOutput, Class<R> returningClass) {
		if (input == null) {
			return returningClass.cast(defaultOutput);
		}

		return input;
	}

	/**
	 *
	 * @param input
	 * @param defaultOutput
	 * @return
	 */
	public static Object coalesce(Object input, Object defaultOutput) {
		if (input == null) {
			return defaultOutput;
		}

		return input;
	}
}
