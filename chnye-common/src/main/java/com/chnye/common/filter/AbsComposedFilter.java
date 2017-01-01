package com.chnye.common.filter;

import java.util.HashSet;
import java.util.Set;

public abstract class AbsComposedFilter<T> implements IFilter<T> {

	protected Set<IFilter<T>> filters = null;
	
	AbsComposedFilter(){
		filters = new HashSet<IFilter<T>>();
	}

	AbsComposedFilter(IFilter<T>... filters ){
		this();
		addFilter( filters );
	}
	
	public void addFilter(IFilter<T>... filtersToAdd ){
		for( IFilter<T> filter : filtersToAdd ){
			filters.add(  filter );
		}
	}
	
	public void removeFilter(IFilter<T>... filtersToRemove ){
		for( IFilter<T> filter : filtersToRemove ){
			filters.remove( filter );
		}
	}
	
	public Set<IFilter<T>> getFilters(){
		return new HashSet( filters );
	}
}
