import java.util.*;

public class HashMapList<K, V extends Comparable<V>> implements MapList<K, V> {
  
  private List<K> keys = new ArrayList<>();
  private Map<K, V> values = new HashMap<>();

  private Comparator<K> keyComparator = Comparator.comparing(k -> values.get(k));

  private boolean prepped = false, sorted = true;
  private int prevIndex;
  private K moveKey;

  private void prepIfNeeded(K key){
    if(prepped){
      prevIndex = indexOfKey(key);
      moveKey = key;
    }
  }

  private void sortIfNeeded(){
    if(!sorted){
      keys.sort(keyComparator);
      sorted = true;
    }
  }

  @Override public V removeItemAt(int index) {
    sortIfNeeded();

    K key = keyAt(index);

    prepIfNeeded(key);

    keys.remove(index);

    return values.remove(key);
  }

  @Override public int indexOfKey(K key) {
    sortIfNeeded();
    return keys.indexOf(key);
  }

  @Override public K keyAt(int index) {
    sortIfNeeded();
    return keys.get(index);
  }

  @Override public void prepReport() {
    if(prepped){
      throw new IllegalStateException("Already prepped");
    }
    prepped = true;
  }

  @Override public MoveReport<K, V> getReport() {
    if(!prepped){
      throw new IllegalStateException("Must call prepReport() first");
    }
    prepped = false;
    return new MoveReport<>(prevIndex, indexOfKey(moveKey), moveKey, values.get(moveKey));
  }

  @Override public List<K> keyList() {
    sortIfNeeded();
    return keys;
  }





  @Override public int size() {
    return values.size();
  }

  @Override public boolean isEmpty() {
    return values.isEmpty();
  }

  @Override public boolean containsKey(Object o) {
    return values.containsKey(o);
  }

  @Override public boolean containsValue(Object o) {
    return values.containsValue(o);
  }

  @Override public V get(Object o) {
    return values.get(o);
  }

  @Override public V put(K k, V v) {
    prepIfNeeded(k);
    if(!values.containsKey(k)){
      keys.add(k);
    }
    sorted = false;
    return values.put(k, v);
  }

  @Override public V remove(Object o) {
    K k = (K) o;

    prepIfNeeded(k);

    if(values.containsKey(k)){
      keys.remove(k);
    } else {
      return null;
    }

    return values.remove(o);
  }

  @Override public void putAll(Map<? extends K, ? extends V> map) {
    prepped = false;
    for(K key : map.keySet()){
      put(key, map.get(key));
    }
  }

  @Override public void clear() {
    keys.clear();
    values.clear();
  }

  @Override public Set<K> keySet() {
    return values.keySet();
  }

  @Override public Collection<V> values() {
    return values.values();
  }

  @Override public Set<Entry<K, V>> entrySet() {
    return values.entrySet();
  }

  @Override public String toString() {

    sortIfNeeded();

    StringBuilder s = new StringBuilder();

    s.append("{");

    for(int i = 0; i < keys.size(); i++){

      K key = keys.get(i);

      if(i > 0) s.append(", ");
      s.append(key);
      s.append("=");
      s.append(values.get(key));

    }

    s.append("}");

    return s.toString();
  }
}
