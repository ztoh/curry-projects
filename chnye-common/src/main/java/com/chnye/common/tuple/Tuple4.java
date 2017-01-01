package com.chnye.common.tuple;


import org.apache.commons.lang3.builder.HashCodeBuilder;



public class Tuple4<V1, V2, V3, V4> implements Tuple {

  private final V1 first;
  private final V2 second;
  private final V3 third;
  private final V4 fourth;

  public static <A, B, C, D> Tuple4<A, B, C, D> of(A a, B b, C c, D d) {
    return new Tuple4<A, B, C, D>(a, b, c, d);
  }

  public Tuple4(V1 first, V2 second, V3 third, V4 fourth) {
    this.first = first;
    this.second = second;
    this.third = third;
    this.fourth = fourth;
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

  public V4 fourth() {
    return fourth;
  }

  public Object get(int index) {
    switch (index) {
    case 0:
      return first;
    case 1:
      return second;
    case 2:
      return third;
    case 3:
      return fourth;
    default:
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  public int size() {
    return 4;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder hcb = new HashCodeBuilder();
    return hcb.append(first).append(second).append(third)
    	.append(fourth).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tuple4 other = (Tuple4) obj;
    return (first == other.first || (first != null && first.equals(other.first))) &&
    	(second == other.second || (second != null && second.equals(other.second))) &&
    	(third == other.third || (third != null && third.equals(other.third))) &&
    	(fourth == other.fourth || (fourth != null && fourth.equals(other.fourth)));
  }

  @Override
  public String toString() {
	StringBuilder sb = new StringBuilder("Tuple4[");
	sb.append(first).append(",").append(second).append(",").append(third);
	return sb.append(",").append(fourth).append("]").toString();
  }
}