import java.util.List;
import java.util.Map;

public interface MapList<K, V extends Comparable<V>> extends Map<K, V> {

 int indexOfKey(K key);

 K keyAt(int index);

 V removeItemAt(int index);

 void prepReport();

 MoveReport<K, V> getReport();

 List<K> keyList();

}
