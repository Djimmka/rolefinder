public class SpamAnalyzer extends KeywordAnalyzer {
    private static String[] hate;

    SpamAnalyzer(String[] hate) {
        this.hate = hate;
    }

    @Override
    public String[] getKeywords() {
        return hate;
    }

    @Override
    public Label getLabel() {
        return Label.SPAM;
    }
}
