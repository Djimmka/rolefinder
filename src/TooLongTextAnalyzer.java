public class TooLongTextAnalyzer implements TextAnalyzer {
    private static int maxLength;

    TooLongTextAnalyzer(int i) {
        this.maxLength = i;
    }

    @Override
    public Label processText(String text) {
        if (text.length() >= maxLength) {
            return Label.TOO_LONG;
        }
        return Label.OK;
    }
}
