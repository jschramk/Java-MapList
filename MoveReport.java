

public class MoveReport<K, V extends Comparable<V>> {

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

  public int from() {
    return prevIndex;
  }

  public int to() {
    return newIndex;
  }

  public K key(){
    return key;
  }

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
        return String.format("Moved key \"%s\" from %d to index %d", key, prevIndex, newIndex);
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
