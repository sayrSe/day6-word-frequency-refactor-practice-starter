package word.frequency;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
//TODO can change the name into more meaningful description and remove the if else
    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfosList(inputStr);
                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordFrequencyInfo> getWordFrequencyInfosList(String inputStr) {
        List<String> separatedWords = Arrays.asList(inputStr.split(SPACE_DELIMITER));
        List<WordFrequencyInfo> wordFrequencyInfoList = mapWordFrequencyInfoList(separatedWords);
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = groupWordFrequencyInfo(wordFrequencyInfoList);
        wordFrequencyInfoList = mapWordFrequencyInfoWithCountList(wordFrequencyMap);

        wordFrequencyInfoList.sort(WordFrequencyGame::compareWordCount);
        return wordFrequencyInfoList;
    }

    private static int compareWordCount(WordFrequencyInfo firstWord, WordFrequencyInfo secondWord) {
        return secondWord.getWordCount() - firstWord.getWordCount();
    }

    private static List<WordFrequencyInfo> mapWordFrequencyInfoWithCountList(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }
//TODO not entirely sure but can be counted using distinct and collections.frequency
    private static List<WordFrequencyInfo> mapWordFrequencyInfoList(List<String> separatedWords) {
        return separatedWords.stream()
                .map(separatedWord -> new WordFrequencyInfo(separatedWord, 0))
                .collect(Collectors.toList());
    }

    private static String concatWordAndCount(WordFrequencyInfo wordFrequencyInfo) {
        return wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount();
    }

    private Map<String, List<WordFrequencyInfo>> groupWordFrequencyInfo(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream().collect(Collectors.groupingBy(WordFrequencyInfo::getWord));
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(WordFrequencyGame::concatWordAndCount)
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }
}
