package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Day16 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day16ex" + ".txt");
		Map<String, List<String>> rules = new HashMap<>();
		int[] currTicket = null;
		List<String> otherTickets = new ArrayList<>();
		boolean ruleBlockFilled = false;
		boolean currTicketAcquired = false;
		try {
			List<String> inputs = Files.readAllLines(path);
			for (int i = 0; i < inputs.size(); i++) {
				String line = inputs.get(i);
				if (line.isEmpty()) {
					continue;
				}
				if (line.startsWith("your")) {
					ruleBlockFilled = true;
					continue;
				}
				if (line.startsWith("nearby")) {
					currTicketAcquired = true;
					continue;
				}
				if (!ruleBlockFilled) {
					String[] rule = line.split(":");
					rules.put(rule[0], Arrays.asList(rule[1].trim().split(" or ")));
				} else if (!currTicketAcquired) {
					currTicket = extractAsIntArray(line);
				} else {
					otherTickets.add(line);
				}
			}

			System.out.println(rules);
			System.out.println(otherTickets);
			Map<Integer, List<String>> ruleMapTicketFields = new HashMap<>();
			// P1
			int i = 0;
			for (int val : currTicket) {
				List<String> rulesApplicable = getRulesApplicable(val, rules);
				ruleMapTicketFields.put(i, rulesApplicable);
				i++;
			}
			Collection<List<String>> vals = ruleMapTicketFields.values();
			boolean ruleFieldsSet = true;
			for (Entry<Integer, List<String>> ruleField : ruleMapTicketFields.entrySet()) {
				if (ruleField.getValue().size() > 1) {
					int p = 0;
					List<Integer> indicesToRemove = new ArrayList<>();
					for (String rule : ruleField.getValue()) {
						List<String> locallist = new ArrayList<>();
						locallist.add(rule);
						if (vals.contains(locallist)) {
							indicesToRemove.add(p);
						}
						p++;
					}
					for (int index : indicesToRemove) {
						ruleField.getValue().remove(index);
					}
					if (ruleField.getValue().size() > 1) {
						ruleFieldsSet = false;
					}
				}
			}
			
			int k = 0;
			while (!ruleFieldsSet && k < otherTickets.size()) {
				System.out.println(k + "   " + (k== (otherTickets.size() - 1)) + "   " + otherTickets.get(k) );
				int[] ticketVals = extractAsIntArray(otherTickets.get(k));
				int j = 0;
				for (int val : ticketVals) {
					List<String> rulesForIndex = ruleMapTicketFields.get(j);
					if (rulesForIndex.size() == 1) {
						continue;
					}
					List<String> rulesApplicable = getRulesApplicable(val, rules);
					ruleMapTicketFields.put(j, getCommonRules(rulesForIndex, rulesApplicable));
					j++;
				}
				ruleFieldsSet = isTheRuleFieldSet(ruleMapTicketFields);
				k++;
			} 
			System.out.println(ruleMapTicketFields);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static boolean isTheRuleFieldSet(Map<Integer, List<String>> ruleMapTicketFields) {
		for (List<String> val: ruleMapTicketFields.values()) {
			if (val.size() > 1) return false;
		}
		return true;
	}

	static List<String> getCommonRules(List<String> rulesForIndex, List<String> rulesApplicable) {
		List<String> newSetOfRules = new ArrayList<>();
		if (rulesApplicable.containsAll(rulesForIndex)) {
			return rulesApplicable;
		}
		for (String rule: rulesApplicable) {
			if (rulesForIndex.contains(rule)) {
				newSetOfRules.add(rule);
			}
		}
		return newSetOfRules;
	}
	static List<String> getRulesApplicable(int val, Map<String, List<String>> rules) {
		List<String> rulesApplicable = new ArrayList<>();
		for (Entry<String, List<String>> rule : rules.entrySet()) {
			if (isInRange(val, rule.getValue())) {
				rulesApplicable.add(rule.getKey());
			}
		}
		return rulesApplicable;
	}

	static boolean isInRange(int val, List<String> ruleRanges) {
		for (String range : ruleRanges) {
			String[] rangeVals = range.split("-");
			if (Integer.parseInt(rangeVals[0]) <= val && val <= Integer.parseInt(rangeVals[1])) {
				return true;
			}
		}
		return false;
	}

	static int[] extractAsIntArray(String line) {
		String[] vals = line.split(",");
		int[] currTicket = new int[vals.length];
		int j = 0;
		for (String v : vals) {
			currTicket[j] = Integer.parseInt(v);
			j++;
		}
		return currTicket;
	}
}
