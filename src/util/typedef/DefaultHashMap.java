package util.typedef;

import java.util.HashMap;

/**
 * http://stackoverflow.com/a/7519422/3399416
 * 
 * HashMap that returns a default value if none was set for that key.
 */
public class DefaultHashMap<K,V> extends HashMap<K,V> {
    
    protected V defaultValue;   // Default value that will be given.
    
    public DefaultHashMap(V defaultValue) {
      this.defaultValue = defaultValue;
    }
    
    @Override
    public V get(Object k) {
      // Return value if it exists for given key, otherwise return default value.
      return containsKey(k) ? super.get(k) : defaultValue;
    }
  }
