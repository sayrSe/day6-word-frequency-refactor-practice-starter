import java.util.*;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {

        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {

            try {
                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String s : arr) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(s, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }

                //get the wordFrequencyMap for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = list;

                wordFrequencyInfoList.sort((firstWord, secondWord) ->
                        secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordFrequencyInfo w : wordFrequencyInfoList) {
                    String s = w.getValue() + SPACE_CHAR + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequencyInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getValue(), arr);
            } else {
                map.get(wordFrequencyInfo.getValue()).add(wordFrequencyInfo);
            }
        }
        return map;
    }
}
