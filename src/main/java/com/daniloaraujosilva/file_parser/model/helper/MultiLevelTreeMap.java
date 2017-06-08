package com.daniloaraujosilva.file_parser.model.helper;

import com.daniloaraujosilva.file_parser.model.utils.NumberUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 *
 */
public class MultiLevelTreeMap extends TreeMap<String, Object>{

	/**
	 *
	 */
	private static final long serialVersionUID = 2464847297254019090L;

	/**
	 *
	 */
	public static final MultiLevelTreeMap EMPTY = new MultiLevelTreeMap();

	/**
	 *
	 */
	private String separator = ".";

	/**
	 *
	 */
	public MultiLevelTreeMap() {
	}

	/**
	 *
	 * @return
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 *
	 * @param separator
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 *
	 * @param keys
	 * @return
	 */
	public boolean containsKey(ArrayList<String> keys) {
		ArrayList<String> localKeys = Cloner.getInstance().deepClone(keys);

		if (CollectionUtils.isEmpty(localKeys)) {
			return false;
		} else if (localKeys.size() == 1) {
			return super.containsKey(localKeys.get(0));
		} else {
			if (super.containsKey(localKeys.get(0))) {
				MultiLevelTreeMap mlhm = (MultiLevelTreeMap) super.get(localKeys.get(0));
				localKeys.remove(0);

				return mlhm.containsKey(localKeys);
			} else {
				return false;
			}
		}
	}

	/**
	 *
	 * @param keys
	 * @return
	 */
	public Object get(ArrayList<String> keys) {
		ArrayList<String> localKeys = Cloner.getInstance().deepClone(keys);

		if (CollectionUtils.isEmpty(localKeys)) {
			return null;
		} else if (!containsKey(localKeys)) {
			return null;
		} else if (localKeys.size() == 1) {
			return super.get(localKeys.get(0));
		} else {
			MultiLevelTreeMap mlhm = (MultiLevelTreeMap) super.get(localKeys.get(0));
			localKeys.remove(0);

			return mlhm.get(localKeys);
		}
	}

	/**
	 *
	 * @param keys
	 * @param value
	 * @return
	 */
	public Object put(ArrayList<String> keys, Object value) {
		ArrayList<String> localKeys = Cloner.getInstance().deepClone(keys);

		if (CollectionUtils.isEmpty(localKeys)) {
			return null;
		} else if (localKeys.size() == 1) {
			return super.put(localKeys.get(0), value);
		} else {
			if (!containsKey(localKeys.get(0))) {
				super.put(localKeys.get(0), Cloner.getInstance().deepClone(EMPTY));
			}

			MultiLevelTreeMap mlhm = (MultiLevelTreeMap) super.get(localKeys.get(0));

			localKeys.remove(0);

			return mlhm.put(localKeys, value);
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Object get(Object key) {
		return get(getKeysList(key));
	}

	/**
	 *
	 * @param key
	 * @param classeDeRetorno
	 * @param <R>
	 * @return
	 */
	public <R> R get(Object key, Class<R> classeDeRetorno) {
		Object value = get(key);
		if (value != null) {
			return classeDeRetorno.cast(value);
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getAsString(Object key) {
		return get(key, String.class);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public Boolean getAsBoolean(Object key) {
		return (Boolean) get(key);

//    	return NumberUtil.parseBoolean(get(key));
    }

	/**
	 *
	 * @param key
	 * @return
	 */
	public Number getAsNumber(Object key) {
		return get(key, Number.class);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public Short getAsShort(Object key) {
		return NumberUtils.parseShort(get(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public Long getAsLong(Object key) {
		return NumberUtils.parseLong(get(key));
	}


	/**
	 *
	 * @param key
	 * @return
	 */
	public Integer getAsInteger(Object key) {
		return NumberUtils.parseInteger(get(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public Double getAsDouble(Object key) {
		return NumberUtils.parseDouble(get(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public BigDecimal getAsBigDecimal(Object key) {
		return NumberUtils.parseBigDecimal(get(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getAsLowerString(Object key) {
    	return getAsString(key).toLowerCase();
    }

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getAsUpperString(Object key) {
    	return getAsString(key).toUpperCase();
    }

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Object put(String key, Object value) {
		return put(getKeysList(key), value);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	@Override
	public boolean containsKey(Object key) {
		return containsKey(getKeysList(key));
	}

	/**
	 *
	 * @param keys
	 * @return
	 */
	public MultiLevelTreeMap getMap(ArrayList<String> keys) {
		return (MultiLevelTreeMap) get(keys);
	}

	/**
	 *
	 * @param keys
	 * @return
	 */
	public MultiLevelTreeMap putMap(ArrayList<String> keys) {
		return (MultiLevelTreeMap) put(keys, Cloner.getInstance().deepClone(EMPTY));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public MultiLevelTreeMap getMap(String key) {
		return getMap(getKeysList(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public MultiLevelTreeMap putMap(String key) {
		return putMap(getKeysList(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	private ArrayList<String> getKeysList(Object key) {
		if (StringUtils.isEmpty(key.toString())) {
			return null;
		}

		return new ArrayList<String>(Arrays.asList(key.toString().split("[" + getSeparator() + "]")));
	}

	/**
	 *
	 * @return
	 */
	public String toJson() throws IOException {
		return Serializer.getInstance().serialize(this);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public String toJson(Object key) throws IOException {
		return Serializer.getInstance().serialize(get(key));
	}
}
