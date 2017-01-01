package com.chnye.common.tuple;

//import org.apache.commons.lang3.builder.HashCodeBuilder;


public class Tuple3<V1, V2, V3> implements Tuple {

  private final V1 first;
  private final V2 second;
  private final V3 third;

  public static <A, B, C> Tuple3<A, B, C> of(A a, B b, C c) {
    return new Tuple3<A, B, C>(a, b, c);
  }

  public Tuple3(V1 first, V2 second, V3 third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  public V1 first() {
    return first;
  }

  public V2 second() {
    return second;
  }

  public V3 third() {
    return third;
  }

  public Object get(int index) {
    switch (index) {
    case 0:
      return first;
    case 1:
      return second;
    case 2:
      return third;
    default:
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  public int size() {
    return 3;
  }

  @Override
  public int hashCode() {
//    HashCodeBuilder hcb = new HashCodeBuilder();
//    return hcb.append(first).append(second).append(third).toHashCode();
	  int result = first != null ? first.hashCode() : 0;
	  result = 31 * result + ( second != null ? second.hashCode() : 0);
	  result = 31 * result + ( third != null ? third.hashCode() : 0);
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

    Tuple3 other = (Tuple3) obj;
    if( first != null ? !first.equals(other.first) : other.first != null ){
    	return false;
    }
    if( second != null ? !second.equals(other.second) : other.second != null ){
    	return false;
    }
    if( third != null ? !third.equals(other.third) : other.third != null ){
    	return false;
    }
    return true;
//    return (first == other.first || (first != null && first.equals(other.first))) &&
//    	(second == other.second || (second != null && second.equals(other.second))) &&
//    	(third == other.third || (third != null && third.equals(other.third)));
  }

  @Override
  public String toString() {
	StringBuilder sb = new StringBuilder("Tuple3{first=");
	sb.append(first).append(", second=").append(second)
		.append(", third=").append(third).append("}");
	return sb.toString();
  }
}




