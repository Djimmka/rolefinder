public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private static final String[] hate = {"=(", ":|", ":("};

    NegativeTextAnalyzer() {
    }

    @Override
    protected String[] getKeywords() {
        return hate;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
