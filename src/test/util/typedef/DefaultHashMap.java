package test.util.typedef;

import java.awt.Point;
import java.util.HashMap;

/**
 * Hash that is initialized with a default value for keys that haven't been written on.
 * (Matrix implementation)
 */
public class DefaultHashMap<K,V> extends HashMap<K,V> {

	private static final long serialVersionUID = 1L;
	protected V defaultValue;
	  public DefaultHashMap(V defaultValue, int height, int width) {
	    super();
		this.defaultValue = defaultValue;
	    for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.put((K) new Point(i,j), getDefaultValue());
			}
		}
	  }
	  @Override
	  public V get(Object k) {
	    return containsKey(k) ? super.get(k) : defaultValue;
	  }
	  
	  public void putDefault(K k){
		  this.put(k,(V) getDefaultValue() );
	  }
	/**
	 * @return the defaultValue
	 */
	public V getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(V defaultValue) {
		this.defaultValue = defaultValue;
	}
	}