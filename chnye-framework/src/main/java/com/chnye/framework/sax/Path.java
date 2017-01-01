package com.chnye.framework.sax;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.ssg.tools/JSONXML/1.1/com/ssg/tools/jsonxml/utils/Path.java?av=f

import java.util.ArrayList;
import java.util.List;

public class Path {

	public static final String SEPARATOR = "/";
	public static final String ARRAY_OPEN = "[";
	public static final String ARRAY_CLOSE = "]";
	public static final int NOT_INDEXED = -1;
	public static final int NON_NUMBERIC_INDEX = -2;
	public static final String INIT_INDEX = "1";
	
	protected List<String>  _path = new ArrayList<String>();
	protected int _depth;
	
	protected String preLeafName ;
	protected int preIndex = 1;
	
	private boolean bShowRootSeparator = true;
	
	private Path(){
	}
	
	public Path( String path ){
		add( path );
	}
	
	public Path( String path, int index ){
		add( path, index );
	}
	
	public Path( Path parent, String path, int index ){
		copyPath( parent );
		add( path, index );
	}
	
	public void add( String path, int index ){
		add( path,  (index > NOT_INDEXED) ? ""+index : null );
	}
	
	public void add( String path, String index ){
		if( SEPARATOR.equals( path ) || path == null || path.equals("") ){
			return;
		}
		path = path.trim();
		//智能判断含斜杠
		//如果path中带斜杠，则自动分割
		if( path.indexOf( SEPARATOR ) > -1 ){
			String[] items = path.split( SEPARATOR );
			if( items != null ){
				for( int i = 0; i < items.length; i++ ){
					doAdd( items[i], null );
				}
			}
			return;
		}
		//智能判断index
		if( index == null ){
			//在解析过程中，如果上次移除的节点与当前加入的节点同名，则认为其数组节点
			if( path.equals( preLeafName ) ){
				index = String.valueOf( preIndex + 1 );
			} else {
				resetPreIndex();
			}
		}
		
		doAdd( path, index );
	}
	
	private void doAdd( String path, String index ){
		if( SEPARATOR.equals( path ) || path == null || path.equals("") ){
			return;
		}
		path = path.trim();
		_depth++;
		_path.add( SEPARATOR );
		_path.add( path );
		addIndex( index );
	}
	
	private void addIndex( String index ){
		if( index != null ){
			_path.add( ARRAY_OPEN );
			_path.add( index );
			_path.add( ARRAY_CLOSE );
		}
	}
	
	public void add( String path ){
		add( path, NOT_INDEXED );
	}
	
	public String remove(){
		removIndex();
		return removeLast();
	}
	
	private void removIndex(){
		if( isArray() ){
			preIndex = getIndexInt();
			removeLastItem();	//移除最后一个元素
			removeLastItem();
			removeLastItem();
		}
	}
	
	private String removeLast(){
		if( _path.size() > 0 ){
			preLeafName = removeLastItem();
			if( _path.size() > 0 && SEPARATOR.equals( getLastItem() ) ){
				removeLastItem();
			}
			_depth--;
		}
		return preLeafName;
	}
	
	@Override
	public String toString(){
		return toString( false );
	}
	
	public String toString( boolean noArrays ){
		StringBuilder sb = new StringBuilder();
		int count = 0;
		int skip = 0;
		for( String str : _path ){
			if( !bShowRootSeparator && count == 0 ){
				count++;
				continue;
			}
			if( noArrays && ARRAY_OPEN.equals( str ) ){
				skip = 3;
			}
			if( skip == 0 ){
				sb.append( str );
			}
			if( skip > 0 ){
				skip--;
			}
			count++;
		}//end for 
		return sb.toString();
	}
	
	public Path getPathBForLevel( int level ){
		if( level < 0 ){
			return null;
		}
		Path p = new Path(null);
		p.copyPath( this );
		while( p.getDepth() > level ){
			p.remove();
		}
		return p;
	}
	
	public Path getParent(){
		if( getDepth() == 0 ){
			return null;
		}
		Path p = new Path();
		p.copyPath( this );
		p.remove();
		return p;
	}
	
	public void copyPath( Path from ){
		for( int i = 0; i < from._path.size(); i++ ){
			_path.add( from._path.get(i) );
		}
		_depth += from.getDepth();
	}
	
	public boolean isArray(){
		if( _path.isEmpty() || _path.size() < 3 ){
			return false;
		}
		if( ARRAY_CLOSE.equals( getLastItem() ) && ARRAY_OPEN.equals( _path.get( _path.size() - 3 ) ) ){
			return true;
		} else {
			return false;
		}
	}
	
	public int getIndexInt(){
		if( isArray() ){
			try{
				return Integer.parseInt( getIndexString() );
			} catch ( NumberFormatException e ){
				return NON_NUMBERIC_INDEX;
			}
		} else {
			return NOT_INDEXED;
		}
	}
	
//	private String getNextIndexInt(){
//		int idx = getIndexInt();
//		if( idx >= 0 ){
//			return  String.valueOf( idx + 1 );
//		}
//		return null;
//	}
	
	public String getIndexString(){
		if( isArray() ){
			return _path.get( _path.size() - 2 );
		} else {
			return null;
		}
	}
	
	public int getDepth(){
		return _depth;
	}
	
	public void setIndex( String index ){
		if( isArray() ){
			if( index == null ){	//deleteIndex	
				removeLastItem();
				removeLastItem();
				removeLastItem();
			} else {
				_path.set( _path.size() - 2 , index );
			}
		} else if( index != null ){
			_path.add( ARRAY_OPEN );
			_path.add( index );
			_path.add( ARRAY_CLOSE );
		}
	}
	
	public String getLeafName(){
		if( _path.isEmpty() ){
			return "";
		}
		if( isArray() ){
			return _path.get( _path.size() - 4 );
		} else {
			return getLastItem();
		}
	}
	
	public boolean isRoot(){
		return _path.isEmpty();
	}
	
	
	private String getLastItem(){
		return _path.get( _path.size() - 1 );
	}
	
	private String removeLastItem(){
		return _path.remove( _path.size() - 1 );
	}
	
	private void resetPreIndex(){
		preIndex = 0;
	}
	
	public static Path parse( String path ){
		Path p = new Path();
		if( path != null ){
			String[] items = path.split( SEPARATOR );
			for( String item : items ){
				if( item.endsWith( ARRAY_CLOSE) && item.indexOf( ARRAY_OPEN ) > 0 ){
					int idx = item.indexOf( ARRAY_OPEN );
					p.add( item.substring(0, idx), item.substring(idx+1, item.length()-1) );
				} else {
					if( item.length() > 0 ){
						p.add( item );
					}
				}
			}//end for
		}
		return p;
	}
	
	public static Path createFor( Path parent, String subpath, String index ){
		Path p = new Path();
		if( parent != null ){
			p.copyPath( parent );
		}
		p.add( subpath, index );
		return p;
	}
	
}
