public class Analization {

    public static Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer an : analyzers) {
            Label lb = an.processText(text);
            if (!(lb.equals(Label.OK))) {
                return lb;
            }
        }
        return Label.OK;
    }

    public static class MyNewException extends Exception{
        public MyNewException (String message) {
            System.out.println(message);
        }
    }


    public static void main(String[] args)throws MyNewException {
        String[] s = {"Фу ыдаодофывра", "щфыроа Бе фыщвоаывоа", "фва =( афвафца", "офывралорф влыраор фвыаfdhjgfbjkkbfbjdvjdbjnfjjfjgbd", " фывцуцукт длао =)"};
        String[] hate = {"Фу", "Бе"};
        if (true) {
            throw new MyNewException("") ;
        }

        TextAnalyzer[] b = {new SpamAnalyzer(hate), new NegativeTextAnalyzer(), new TooLongTextAnalyzer(20)};
        for (String str :
                s) {
            Label ou = checkLabels(b, str);
            System.out.println(ou);
        }
    }
}
