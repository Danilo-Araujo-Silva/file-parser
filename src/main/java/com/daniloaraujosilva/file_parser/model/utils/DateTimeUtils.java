package com.daniloaraujosilva.file_parser.model.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class DateTimeUtils {

	/**
	 *
	 */
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 *
	 */
	public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 *
	 */
	public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	/**
	 *
	 */
	public static final DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

	/**
	 *
	 */
	public static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");

	/**
	 *
	 */
	public static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

	/**
	 *
	 */
	public static final DateTimeFormatter brazillianDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	/**
	 *
	 */
	public static final DateTimeFormatter brazillianDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 *
	 * @param input
	 * @return
	 */
	public static LocalDateTime getDateTimeFromString(String input) {
		return getDateTimeFromString(input, null);
	}

	/**
	 *
	 */
	public static LocalDateTime getDateTimeFromString(String input, DateTimeFormatter dateTimeFormatter) {
		if (input == null) {
			return null;
		}

		if (dateTimeFormatter == null) {
			dateTimeFormatter = DateTimeUtils.dateTimeFormatter;
		}

		try {
			return LocalDateTime.parse(input, dateTimeFormatter);
		} catch (Exception exception) {
			return null;
		}
	}
}
