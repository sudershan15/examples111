package sud.exmp1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> results = new ArrayList<List<String>>();
		HashSet<String> set = new HashSet<String>(wordList);
		set.remove(beginWord);
		Queue<List<String>> queue = new LinkedList<List<String>>();
		List<String> list = new ArrayList<String>();
		list.add(beginWord);
		queue.add(list);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<String> removeList = new ArrayList<String>();
			for (int i = 0; i < size; i++) {
				List<String> thisList = new ArrayList<String>(queue.poll());
				String thisWord = thisList.get(thisList.size() - 1);
				if (thisWord.equals(endWord)) {
					results.add(new ArrayList<String>(thisList));
				} else {
					for (int j = 0; j < thisWord.length(); j++) {
						char[] word = thisWord.toCharArray();
						for (char ch = 'a'; ch <= 'z'; ch++) {
							word[j] = ch;
							String nextWord = new String(word);

							if (!nextWord.equals(thisWord) && set.contains(nextWord) && !thisList.contains(nextWord)) {

								removeList.add(nextWord);
								thisList.add(nextWord);
								queue.offer(new ArrayList<String>(thisList));
								thisList.remove(thisList.size() - 1);
							}
						}
					}
				}
			}
			for (int r = 0; r < removeList.size(); r++) {

				set.remove(removeList.get(r));
			}
			if (!results.isEmpty())
				break;
		}
		return results;
	}
}
