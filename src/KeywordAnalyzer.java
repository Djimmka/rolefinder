public abstract class KeywordAnalyzer implements TextAnalyzer {

    public Label processText(String text) {
        String[] wrong = this.getKeywords();
        for (String str : wrong) {
            if (text.contains(str)) {
                return getLabel();
            }
        }
        return Label.OK;
    }

    protected abstract String[] getKeywords();

    protected abstract Label getLabel();
}
