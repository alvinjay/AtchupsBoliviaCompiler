package ANALYZERS;

import java.util.HashMap;

/**
 * Created by alvinjay on 3/5/15.
 */
public class LexicalAnalyzer {
    private String input;
    private String[] lexemes;

    public HashMap<String, String> analyze(String code) {
        input = sanitizeCode(code);
        lexemes = extractLexemes(input);
        return assignTokensToLexemes(lexemes);
    }

    private String sanitizeCode(String code) {
        return code.replaceAll("\\s+", " ").replaceAll("\n", " ");
    }

    private String[] extractLexemes(String input) {
        return input.split(" ");
    }

    private HashMap<String, String> assignTokensToLexemes(String[] lexemes) {
        HashMap<String, String> lexemesTokens = new HashMap<String, String>();

        for (int i = 0; i < lexemes.length; i++) {
            //TODO determine what kind of token for lexeme
            //sample lexeme -> token: var_name -> <id>(Identifier)
            lexemesTokens.put(lexemes[i], "aw");
            //TODO put lexeme as KEY and token as VALUE in lexemesTokens HashMap
        }

        return lexemesTokens;
    }

    public String[] getLexemes() {
        return lexemes;
    }
}
