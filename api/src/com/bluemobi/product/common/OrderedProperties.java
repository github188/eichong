package com.bluemobi.product.common;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

/**
 * OrderedProperties
 * 
 * @author Unmi
 * @date 2012-12-07
 */
public class OrderedProperties extends Properties {

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 5930433123184201234L;
	private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

	public Enumeration<Object> keys() {
		return Collections.<Object> enumeration(keys);
	}

	public Object put(Object key, Object value) {
		keys.add(key);
		return super.put(key, value);
	}

	public Set<Object> keySet() {
		return keys;
	}

	public Set<String> stringPropertyNames() {
		Set<String> set = new LinkedHashSet<String>();

		for (Object key : this.keys) {
			set.add((String) key);
		}

		return set;
	}
}
