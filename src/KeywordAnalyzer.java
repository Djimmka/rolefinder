public abstract class KeywordAnalyzer implements TextAnalyzer {
    abstract void getKeywords () {

    }
    public Label getLabel () {
        return Label.OK;
    }
}
