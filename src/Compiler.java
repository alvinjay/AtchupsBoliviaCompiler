import ANALYZERS.LexicalAnalyzer;
import ANALYZERS.SemanticAnalyzer;
import ANALYZERS.SyntaxAnalyzer;

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

    public Compiler(CurrentFile currentFile) {
        this.currentFile = currentFile;
        lex = new LexicalAnalyzer();
        syn = new SyntaxAnalyzer();
        sem =  new SemanticAnalyzer();
    }

    public void start() {
        lexemesTokens = new HashMap<String, String>();

        String code = currentFile.getContent();

        lexemesTokens = lex.analyze(code);
        //syn.analyze(code, lexemesTokens) //TODO syntax analysis
        //sem.analyze(code, lexemeTokens)//TODO semantic analysis
    }

    public HashMap<String, String> getLexemesTokens() {
        return lexemesTokens;
    }
}
