package COMPILER;

import COMPILER.ANALYZERS.LexicalAnalyzer;
import COMPILER.ANALYZERS.SemanticAnalyzer;
import COMPILER.ANALYZERS.SyntaxAnalyzer;
import COMPILER.DATA.Variable;
import FILES.CurrentFile;

import java.util.HashMap;

/**
 * Created by alvinjay on 3/7/15.
 */
public class Compiler {

    private LexicalAnalyzer lex;
    private SyntaxAnalyzer syn;
    private SemanticAnalyzer sem;

    private CurrentFile currentFile;

    private HashMap<String, String> lexemesTokens;
    private HashMap<String, Variable> variables;


    public Compiler(CurrentFile currentFile) {
        this.currentFile = currentFile;

        lexemesTokens = new HashMap<String, String>();

        lex = new LexicalAnalyzer(lexemesTokens, variables);
        syn = new SyntaxAnalyzer();
        sem =  new SemanticAnalyzer();
    }

    public void start() {
        String code = currentFile.getContent();

        lex.analyze(code);
        //syn.analyze(code, lexemesTokens) //TODO syntax analysis
        //sem.analyze(code, lexemeTokens)//TODO semantic analysis
    }

    public HashMap<String, String> getLexemesTokens() {
        return lexemesTokens;
    }
}
