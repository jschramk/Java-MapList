// Author: Jacob Schramkowski

import java.util.List;
import java.util.Map;

/**
 * An interface detailing additional methods added to the
 * Map interface to allow for more advanced functionality
 * @param <K> the key class type
 * @param <V> the value class type
 */
public interface MapList<K, V extends Comparable<V>> extends Map<K, V> {

 /**
  * Returns the index of a given key
  * @param key the key to get the index of
  * @return the index of the specified key
  */
 int indexOfKey(K key);

 /**
  * Gets the key at a given index
  * @param index the index of the desired key
  * @return the key at the specified index
  */
 K keyAt(int index);

 /**
  * Gets the value at a given index
  * @param index the index of the desired value
  * @return the value at the specified index
  */
 V valueAt(int index);

 /**
  * Removes the item at a given index
  * @param index the index of the entry to remove
  * @return the value that was at the specified index before removal
  */
 V removeEntryAt(int index);

 /**
  * Prepares the MapList to generate a MoveReport for the next entry modification
  * @throws IllegalStateException if prepReport() was already called
  */
 void prepReport();

 /**
  * Gets the MoveReport for the last entry modification as long as prepReport() was called
  * @return a MoveReport object containing information about the movement
  * @throws IllegalStateException if prepReport() was not called
  */
 MoveReport<K, V> getReport();

 /**
  * Gets a list of keys in the MapList ordered by their associated values
  * @return a sorted List containing the keys in the MapList
  */
 List<K> keyList();

}
