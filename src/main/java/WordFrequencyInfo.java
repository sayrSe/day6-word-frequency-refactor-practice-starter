public class WordFrequencyInfo {

    private final String value;
    private final int count;

    public WordFrequencyInfo(String value, int count) {
        this.value = value;
        this.count = count;
    }

    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }
}