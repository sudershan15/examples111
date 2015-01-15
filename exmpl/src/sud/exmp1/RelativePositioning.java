package sud.exmp1;

import java.util.*;
public class RelativePositioning {
	
	Set<String> getValue(String key, HashMap<String, Set<String>> hashMap) {
		Set<String> value = hashMap.get(key);
		if(value == null) {
			value = new HashSet<String>();
		}
		return value;
	}
	
	boolean isValueRepeated(String key, ArrayList<String> keyList, HashSet<String> visitedKeys, HashMap<String, Set<String>> hashMap) {
		if(visitedKeys.contains(key)) return false;
		keyList.add(key);
		Set<String> valuesSet = hashMap.get(key);
		for(String value : valuesSet) {
			if(keyList.contains(value)) return true;
			@SuppressWarnings("unchecked")
			ArrayList<String> keyListClone = (ArrayList<String>)keyList.clone();
			if(isValueRepeated(value, keyListClone, visitedKeys, hashMap)) return true;
		}
		visitedKeys.add(key);
		return false;
	}
	
	boolean checkForLoops(HashMap<String, Set<String>> hashMap) {
		Set<String> keys = hashMap.keySet();
		HashSet<String> visitedKeys = new HashSet<String>();
		for(String key: keys) {
			ArrayList<String> keyList = new ArrayList<String>();
			if(isValueRepeated(key, keyList, visitedKeys, hashMap)) return true;
		}
		return false;
	}
	
	boolean fitInAGraph(String[] positions) {
		HashMap<String, Set<String>> xHashMap = new HashMap<String, Set<String>>();
		HashMap<String, Set<String>> yHashMap = new HashMap<String, Set<String>>();
		for(String position: positions) {
			String[] positionParts = position.split(" ");
			String val1 = positionParts[0];
			String val2 = positionParts[2];
			String relation = positionParts[1];
			
			Set<String> val1ArrayX = getValue(val1, xHashMap);
			Set<String> val2ArrayX = getValue(val2, xHashMap);
			Set<String> val1ArrayY = getValue(val1, yHashMap);
			Set<String> val2ArrayY = getValue(val2, yHashMap);
			
			if(relation.equals("S")) {
				val1ArrayX.addAll(val2ArrayX);
				val2ArrayX = val1ArrayX;
				val2ArrayY.add(val1);
			} else if(relation.equals("W")) {
				val1ArrayY.addAll(val2ArrayY);
				val2ArrayY = val1ArrayY;
				val2ArrayX.add(val1);
			} else if(relation.equals("N")) {
				val1ArrayX.addAll(val2ArrayX);
				val2ArrayX = val1ArrayX;
				val1ArrayY.add(val2);
			} else if(relation.equals("E")) {
				val1ArrayY.addAll(val2ArrayY);
				val2ArrayY = val1ArrayY;
				val1ArrayX.add(val2);
			} else if(relation.equals("SW")) {
				val2ArrayX.add(val1);
				val2ArrayY.add(val1);
			} else if(relation.equals("NW")) {
				val1ArrayY.add(val2);
				val2ArrayX.add(val1);
			} else if(relation.equals("SE")) {
				val2ArrayY.add(val1);
				val1ArrayX.add(val2);
			} else if(relation.equals("NE")) {
				val1ArrayX.add(val2);
				val1ArrayY.add(val2);
			}
			xHashMap.put(val1, val1ArrayX);
			xHashMap.put(val2, val2ArrayX);
			yHashMap.put(val1, val1ArrayY);
			yHashMap.put(val2, val2ArrayY);
			
		}
		return !checkForLoops(xHashMap) && !checkForLoops(yHashMap);
	}

	public static void  main(String[] inputs) {
		RelativePositioning rp = new RelativePositioning();
		String[] positions = new String[10];
		positions[0] = "p1 N p3";
		positions[1] = "p1 NW p5";
		positions[2] = "p1 NW p2";
		positions[3] = "p2 SW p6";
		positions[4] = "p4 SW p6";
		positions[5] = "p3 W p2";
		positions[6] = "p4 S p1";
		positions[7] = "p5 E p4";
		positions[8] = "p1 W p6";
		positions[9] = "p6 NE p5";
		System.out.println(rp.fitInAGraph(positions));
		/*above test: 
		  ________________
		  | p1 |    | p6 |
		  _________________
		  | p3 | p2 |    |
		  ________________
		  | p4 | p5 |    |
		  ________________
		*/
		
	}
}