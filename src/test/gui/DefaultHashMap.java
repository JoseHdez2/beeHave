package test.gui;

import java.util.HashMap;

public class DefaultHashMap<K,V> extends HashMap<K,V> {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected V defaultValue;
	  public DefaultHashMap(V defaultValue) {
	    this.defaultValue = defaultValue;
	  }
	  @Override
	  public V get(Object k) {
	    return containsKey(k) ? super.get(k) : defaultValue;
	  }
	}