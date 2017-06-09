package com.daniloaraujosilva.file_parser.model.utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class MapUtils {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescendent(Map<K, V> map) {
		return map.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				Map.Entry::getValue,
				(e1, e2) -> e1,
				LinkedHashMap::new
			));
	}
}
