package com.chnye.common.predicate;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.chnye.common.function.Function;
import com.chnye.common.function.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;


public class TestPredicates {


	private  void assertIn(){
		List<String> lists = ImmutableList.of( "123", "456", "4", "9", "34" );
		Predicate<String>  predicateIn = Predicates.in( lists );
		System.out.println( "predicate in :" + predicateIn.evaluate( "9" ) );
		System.out.println( "predicate in :" + predicateIn.evaluate( "25" ) );
		
	}
	
	private void assertEverySome(){
		//某个区号集合
		List<String> lists = ImmutableList.of( "027", "0718", "021", "025" );
		
		//判断某个区号是否为湖北内区号
//		Predicate<String> predicateHubei = new Predicate<String>(){
//			private final List<String> hubeiRegions = ImmutableList.of( "027", "0711", "0712", "0713", "0714", "0715", "0716", "0717", "0718", "0719", "0728" );
//			@Override
//			public boolean evaluate(String object) {
//				// TODO Auto-generated method stub
//				return hubeiRegions.contains( object );
//			}
//		};
		
		List<String> hubeiRegions = ImmutableList.of( "027", "0711", "0712", "0713", "0714", "0715", "0716", "0717", "0718", "0719", "0728" );
		Predicate<String>  predicateIn = Predicates.in( hubeiRegions );
		
		boolean bEvery = Predicates.every( predicateIn, lists );
		System.out.println( "predicate every :"  + bEvery );
		
		boolean bSome = Predicates.some( predicateIn, lists );
		System.out.println( "predicate some :"  + bSome );
		
	}
	
	private void assertFilter(){
		//某个区号集合
		List<String> lists = ImmutableList.of( "027", "0718", "021", "025" );
		
		//判断某个区号是否为湖北内区号
		List<String> hubeiRegions = ImmutableList.of( "027", "0711", "0712", "0713", "0714", "0715", "0716", "0717", "0718", "0719", "0728" );
		Predicate<String>  predicateIn = Predicates.in( hubeiRegions );
		
		List<String> subRegions = Predicates.filter(predicateIn, lists );
		System.out.println( "subRegions size:" + subRegions.size() );
		StringBuilder sb = new StringBuilder();
		for( String subRegion : subRegions ){
			sb.append( subRegion ).append( ",");
		}
		System.out.println( "subRegions :" + sb.toString() );
	}
	
	private void assertCompose(){
		//某个区号的集合
		List<String> lists = ImmutableList.of( "天门", "黄石", "孝感", "重庆" );
		
		//判断某个区号是否为湖北内区号
		List<String> hubeiRegions = ImmutableList.of( "027", "0711", "0712", "0713", "0714", "0715", "0716", "0717", "0718", "0719", "0728" );
		Predicate<String>  predicateIn = Predicates.in( hubeiRegions );
		
		//需求：这个时候要判断区号是否为湖北内区号
		//     但我们只有区号的中文，需要先翻译成区号的数字表示形式
		ImmutableMap.Builder<String,String> mapBuilder = ImmutableMap.builder();
		mapBuilder.put( "武汉", "027"  );
		mapBuilder.put( "黄石", "0714"  );
		mapBuilder.put( "襄樊", "0710"  );
		mapBuilder.put( "随州", "0722"  );
		mapBuilder.put( "宜昌", "0717"  );
		mapBuilder.put( "荆门", "0724"  );
		mapBuilder.put( "十堰", "0719"  );
		mapBuilder.put( "鄂州", "0711"  );
		mapBuilder.put( "孝感", "0712"  );
		mapBuilder.put( "荆州", "0716"  );
		mapBuilder.put( "沙市", "0716"  );
		mapBuilder.put( "荆沙", "0716"  );
		mapBuilder.put( "黄冈", "0713"  );
		mapBuilder.put( "恩施", "0718"  );
		mapBuilder.put( "咸宁", "0715"  );
		mapBuilder.put( "仙桃", "0728"  );
		mapBuilder.put( "潜江", "0728"  );
		mapBuilder.put( "天门", "0728"  );
		Map<String,String> REGION_MAP = mapBuilder.build();
		Function<String, String>  regionMapFunc = Functions.mapGetFunc( REGION_MAP );
		
		
		//现在把predicateIn复合成了，能接受中文区域的断言了。
		Predicate<String>  predicateInCN = Predicates.compose( predicateIn,  regionMapFunc );
		
		boolean bEvery = Predicates.every( predicateInCN, lists );
		System.out.println( "predicate compose every :"  + bEvery );
		
		boolean bSome = Predicates.some( predicateInCN, lists );
		System.out.println( "predicate compose some :"  + bSome );
		
	}
	
	@Test
	public void testToDo(){
		assertIn();
	}
	
	@Test
	public void testToDo2(){
		assertEverySome();
	}
	
	@Test
	public void testToDo3(){
		assertFilter();
	}
	
	@Test
	public void testToDo4(){
		assertCompose();
	}
	
}
