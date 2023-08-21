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
                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfosList(inputStr);

                return wordFrequencyInfoList.stream()
                        .map(WordFrequencyGame::concatWordAndCount)
                        .collect(Collectors.joining(NEW_LINE_DELIMITER));
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordFrequencyInfo> getWordFrequencyInfosList(String inputStr) {
        List<String> separatedWords = Arrays.asList(inputStr.split(SPACE_DELIMITER));
        List<WordFrequencyInfo> wordFrequencyInfoList = mapWordFrequencyInfos(separatedWords);
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = groupWordFrequencyInfo(wordFrequencyInfoList);
        wordFrequencyInfoList = mapWordFrequencyInfosWithCount(wordFrequencyMap);

        wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordFrequencyInfoList;
    }

    private static List<WordFrequencyInfo> mapWordFrequencyInfosWithCount(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private static List<WordFrequencyInfo> mapWordFrequencyInfos(List<String> separatedWords) {
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
}
