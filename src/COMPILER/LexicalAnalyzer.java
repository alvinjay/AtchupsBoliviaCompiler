package COMPILER;

/**
 * Created by alvinjay on 3/5/15.
 */
public class LexicalAnalyzer {
    private String input;
    private String[] lexemes;

    public LexicalAnalyzer(String input) {
        this.input = input;
    }

    public void analyze() {
        input = input.replaceAll("\\s+", " ").replaceAll("\n", " ");
        lexemes = input.split(" ");
    }

    public String[] getLexemes() {
        return lexemes;
    }
}
