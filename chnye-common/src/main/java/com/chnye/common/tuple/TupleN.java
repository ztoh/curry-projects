package com.chnye.common.tuple;

import java.util.Arrays;

import org.apache.commons.lang3.builder.HashCodeBuilder;



public class TupleN implements Tuple {

  private final Object values[];

  public TupleN(Object... values) {
    this.values = new Object[values.length];
    System.arraycopy(values, 0, this.values, 0, values.length);
  }

  public Object get(int index) {
    return values[index];
  }

  public int size() {
    return values.length;
  }

  @Override
  public int hashCode() {
  	HashCodeBuilder hcb = new HashCodeBuilder();
  	for (Object v : values) {
  	  hcb.append(v);
  	}
  	return hcb.toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TupleN other = (TupleN) obj;
    return Arrays.equals(this.values, other.values);
  }

  @Override
  public String toString() {
    return Arrays.toString(values);
  }
}







