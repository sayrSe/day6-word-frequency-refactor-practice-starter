import java.util.*;
import java.util.stream.Collectors;

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
                List<String> separatedWords = Arrays.asList(inputStr.split(SPACE_DELIMITER));

                List<WordFrequencyInfo> wordFrequencyInfoList = separatedWords.stream()
                        .map(separatedWord -> new WordFrequencyInfo(separatedWord, 1))
                        .collect(Collectors.toList());

                //get the wordFrequencyMap for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                wordFrequencyInfoList = wordFrequencyMap.entrySet().stream()
                        .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());

                wordFrequencyInfoList.sort((firstWord, secondWord) ->
                        secondWord.getWordCount() - firstWord.getWordCount());

                return wordFrequencyInfoList.stream()
                        .map(WordFrequencyGame::concatWordAndCount)
                        .collect(Collectors.joining(NEW_LINE_DELIMITER));
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private static String concatWordAndCount(WordFrequencyInfo wordFrequencyInfo) {
        return wordFrequencyInfo.getValue() + SPACE_CHAR + wordFrequencyInfo.getWordCount();
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
