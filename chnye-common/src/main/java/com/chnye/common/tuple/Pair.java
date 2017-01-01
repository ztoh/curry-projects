package com.chnye.common.tuple;

//import org.apache.commons.lang3.builder.HashCodeBuilder;


public class Pair<K, V> implements Tuple, Comparable<Pair<K, V>> {

  private final K first;
  private final V second;

  public static <T, U> Pair<T, U> of(T first, U second) {
    return new Pair<T, U>(first, second);
  }

  public Pair(K first, V second) {
    this.first = first;
    this.second = second;
  }

  public K first() {
    return first;
  }

  public V second() {
    return second;
  }

  public Object get(int index) {
    switch (index) {
    case 0:
      return first;
    case 1:
      return second;
    default:
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  public int size() {
    return 2;
  }

  @Override
  public int hashCode() {
//    HashCodeBuilder hcb = new HashCodeBuilder();
//    return hcb.append(first).append(second).toHashCode();
	  int result = first != null ? first.hashCode() : 0;
	  result = 31 * result + ( second != null ? second.hashCode() : 0 );
	  return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj){
      return true;
    }
    if (obj == null || getClass() != obj.getClass() ){
      return false;
    }

    final Pair<?, ?> other = (Pair<?, ?>) obj;
    if( first != null ? !first.equals(other.first) : other.first != null ){
    	return false;
    }
    if( second != null ? !second.equals(other.second) : other.second != null ){
    	return false;
    }
    return true;
//    return (first == other.first || (first != null && first.equals(other.first))) &&
//    	(second == other.second || (second != null && second.equals(other.second)));
  }

  @Override
  public String toString() {
	StringBuilder sb = new StringBuilder("Pair{first=");
	sb.append(first).append(", second=").append(second).append("}");
	return sb.toString();
  }

  private int cmp(Object lhs, Object rhs) {
    if (lhs == rhs) {
      return 0;
    } else if (lhs != null && Comparable.class.isAssignableFrom(lhs.getClass())) {
      return ((Comparable) lhs).compareTo(rhs);
    }
    return (lhs == null ? 0 : lhs.hashCode()) - (rhs == null ? 0 : rhs.hashCode());
  }

  @Override
  public int compareTo(Pair<K, V> o) {
    int diff = cmp(first, o.first);
    if (diff == 0) {
      diff = cmp(second, o.second);
    }
    return diff;
  }
}