package word.frequency;

public class WordFrequencyInfo {

    private final String word;
    private final int count;

    public WordFrequencyInfo(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }
}