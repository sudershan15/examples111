package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day19 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day19" + ".txt");
		Map<Integer, String> rules = new TreeMap<>();
		List<String> stringsToCompare = new ArrayList<>();
		try {
			List<String> inputs = Files.readAllLines(path);
			inputs.forEach(line -> {
				if (line.contains(":")) {
					String[] parts = line.split(": ");
					rules.put(Integer.parseInt(parts[0]), parts[1]);
				} else if (!line.isEmpty()) {
					stringsToCompare.add(line);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		//List<String> possibleStrings = getPossibleStrings(rules, 0);
		//System.out.println(possibleStrings.size());

		String regex = "^" + getRegexP2(0, rules) + "$";
		System.out.println(regex);

		int cnt = 0;
		for (String s : stringsToCompare) {
			if (s.matches(regex))
				cnt++;
		}
		System.out.println(cnt);
	}

	private static Map<Integer, String> memo = new HashMap<>();

	public static String getRegexP2(int rule, Map<Integer, String> rules) {
        if (memo.containsKey(rule)) { return memo.get(rule); }

        if (rule == 8) {
            String rv = "(" + getRegexP2(42, rules) + "+)";
            memo.put(8, rv);
            return rv;
        }

        if (rule == 11) {
            String r42 = getRegexP2(42, rules);
            String r31 = getRegexP2(31, rules);
            StringBuilder sb = new StringBuilder("(");
            for (int i = 1; i < 10; i++) {
                if (i > 1) { sb.append('|'); }
                sb.append('(');
                for (int k = 0; k < i; k++) {
                    sb.append(r42);
                }
                for (int k = 0; k < i; k++) {
                    sb.append(r31);
                }
                sb.append(')');
            }
            sb.append(')');

            memo.put(11, sb.toString());
            return sb.toString();
        }

        String ruleS = rules.get(rule);
        if (ruleS.contains("\"")) { return ruleS.replaceAll("\"", ""); }

        StringBuilder sb = new StringBuilder("(");
        String[] parts = ruleS.split(" ");
        for (String part : parts) {
            if (Character.isDigit(part.charAt(0))) {
                sb.append(getRegexP2(Integer.parseInt(part), rules));
            } else if (part.equals("|")) {
                sb.append('|');
            } else {
                throw new IllegalArgumentException(part);
            }
        }
        sb.append(")");

        memo.put(rule, sb.toString());
        return sb.toString();
    } 
	
	public static String getRegex(int rule, Map<Integer, String> rules) {
		if (memo.containsKey(rule)) {
			return memo.get(rule);
		}

		String ruleS = rules.get(rule);
		if (ruleS.contains("\"")) {
			return ruleS.replaceAll("\"", "");
		}

		StringBuilder sb = new StringBuilder("(");
		String[] parts = ruleS.split(" ");
		for (String part : parts) {
			if (Character.isDigit(part.charAt(0))) {
				sb.append(getRegex(Integer.parseInt(part), rules));
			} else if (part.equals("|")) {
				sb.append('|');
			} else {
				throw new RuntimeException(part);
			}
		}
		sb.append(')');

		memo.put(rule, sb.toString());
		return sb.toString();
	}

	static List<String> getPossibleStrings(Map<Integer, String> rules, int ruleId) {
		List<String> response = new ArrayList<>();
		String ruleForId = rules.get(ruleId);
		if (ruleForId.startsWith("\"")) {
			ruleForId.replaceAll("\"", "");
			response.add(ruleForId);
			return response;
		}
		String[] rulesSplitted = ruleForId.split(" \\| ");
		List<Integer> ruleIdSplitRule1 = getRuleIdList(rulesSplitted[0]);
		List<String> possibleStringForRule1 = combinations(getPossibleStrings(rules, ruleIdSplitRule1.get(0)),
				ruleIdSplitRule1.size() > 1 ? getPossibleStrings(rules, ruleIdSplitRule1.get(1)) : new ArrayList<>());
		System.out.println(ruleId + "   1. " + possibleStringForRule1);
		response.addAll(possibleStringForRule1);
		if (rulesSplitted.length > 1) {
			List<Integer> ruleIdSplitRule2 = getRuleIdList(rulesSplitted[1]);
			List<String> possibleStringForRule2 = combinations(getPossibleStrings(rules, ruleIdSplitRule2.get(0)),
					ruleIdSplitRule2.size() > 1 ? getPossibleStrings(rules, ruleIdSplitRule2.get(1))
							: new ArrayList<>());
			response.addAll(possibleStringForRule2);
			System.out.println(ruleId + "   2. " + possibleStringForRule2);
		}

		return response;
	}

	static List<String> combinations(List<String> pos1, List<String> pos2) {
		List<String> response = new ArrayList<>();
		for (String s1 : pos1) {
			for (String s2 : pos2) {
				response.add(s1.replaceAll("\"", "") + s2.replaceAll("\"", ""));
			}
		}
		return response;
	}

	static List<Integer> getRuleIdList(String rule) {
		String[] splitted = rule.split(" ");
		List<Integer> rules = new ArrayList<>();
		for (String split : splitted) {
			rules.add(Integer.parseInt(split));
		}
		return rules;
	}
}
