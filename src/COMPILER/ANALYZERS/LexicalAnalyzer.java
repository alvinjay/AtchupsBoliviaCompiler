package COMPILER.ANALYZERS;

import COMPILER.DATA.Variable;
import java.util.HashMap;
/**
 * Created by alvinjay on 3/5/15.
 */
public class LexicalAnalyzer {
    private String input;
    private String[] lexemes;
    private HashMap<String, String> lexemesTokens;
    private HashMap<String, Variable> variables;

    public LexicalAnalyzer(HashMap<String, String> lexemesTokens, HashMap<String, Variable> variables) {
        this.lexemesTokens = lexemesTokens;
        this.variables = variables;
    }

    public void analyze(String code) {
        input = sanitizeCode(code);
        lexemes = extractLexemes(input);
        tokenize(lexemes);
    }

    private String sanitizeCode(String code) {
        return code.replaceAll("\\s+", " ").replaceAll("\n", " ");
    }

    private String[] extractLexemes(String input) {
        return input.split(" ");
    }

    private void tokenize(String[] lexemes) {
        for (int i = 0; i < lexemes.length; i++) {

            //TODO for raph
            //TODO determine what kind of token for lexeme
                //sample lexeme -> token: var_name -> <id>(Identifier)
            //TODO put lexeme as KEY and token as VALUE in lexemesTokens HashMap
                lexemesTokens.put(lexemes[i], null); // temporary, replace null with actual token

            //TODO for gab
            //TODO determine if lexeme is a variable, if yes then determine what type of variable
            //TODO put variable to variables hash map
                // sample: variables.put(lexemes[i], new Variable(lexemes[i], <type>, <initial value>))
            //note: variable type is lexemes[i-1] and initial value is lexemes[i+2]
            //                  i-1   i      i+2
            //sample statement: BOOL temp IS true

        }
    }

    public String[] getLexemes() {
        return lexemes;
    }
}
