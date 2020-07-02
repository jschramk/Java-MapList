// Author: Jacob Schramkowski

/**
 * A class containing information about a MapList entry modification
 * @param <K> the key class type
 * @param <V> the value class type
 */
public class MoveReport<K, V extends Comparable<V>> {

  // enum for type of modification outcome
  public enum Type {
    INSERT,
    MOVE,
    UPDATE,
    REMOVE,
    NONE
  }

  private int prevIndex, newIndex;
  private K key;
  private V value;

  public MoveReport(int prevIndex, int newIndex, K key, V value){
    this.prevIndex = prevIndex;
    this.newIndex = newIndex;
    this.key = key;
    this.value = value;
  }

  public Type type(){

    if(prevIndex != -1){

      if(newIndex == -1){
        return Type.REMOVE;
      } else if(newIndex != prevIndex){
        return Type.MOVE;
      } else {
        return Type.UPDATE;
      }

    } else {

      if(newIndex == -1){
        return Type.NONE;
      } else {
        return Type.INSERT;
      }

    }

  }

  /**
   * Gets the index of the entry before the operation
   * @return the previous index of the modified entry
   */
  public int from() {
    return prevIndex;
  }

  /**
   * Gets the index of the entry after the operation
   * @return the new index of the modified entry
   */
  public int to() {
    return newIndex;
  }

  /**
   * Gets the key of the modified entry
   * @return the key of the entry
   */
  public K key(){
    return key;
  }

  /**
   * Gets the value of the modified entry
   * @return the value of the entry
   */
  public V value() {
    return value;
  }

  @Override public String toString() {

    switch (type()) {

      case INSERT: {
        return String.format("Inserted key \"%s\" at index %d", key, newIndex);
      }

      case UPDATE: {
        return String.format("Updated key \"%s\" at index %d", key, newIndex);
      }

      case MOVE: {
        return String.format("Moved key \"%s\" from index %d to index %d", key, prevIndex, newIndex);
      }

      case REMOVE: {
        return String.format("Removed key \"%s\" from index %d", key, prevIndex);
      }

      case NONE: {
        return String.format("Key \"%s\" not contained", key);
      }

    }

    throw new IllegalStateException("Move type not properly set");

  }

}
