package COMPILER;

import COMPILER.ANALYZERS.LexicalAnalyzer;
import COMPILER.ANALYZERS.SemanticAnalyzer;
import COMPILER.ANALYZERS.SyntaxAnalyzer;
import COMPILER.DATA.Variable;
import FILES.IOLFile;

import java.util.HashMap;

/**
 * Created by alvinjay on 3/7/15.
 */
public class Compiler {

    private LexicalAnalyzer lex;
    private SyntaxAnalyzer syn;
    private SemanticAnalyzer sem;

    private IOLFile file;

    private HashMap<String, String> lexemesTokens;
    private HashMap<String, Variable> variables;


    public Compiler() {
        lexemesTokens = new HashMap<String, String>();

        lex = new LexicalAnalyzer(lexemesTokens, variables);
        syn = new SyntaxAnalyzer();
        sem =  new SemanticAnalyzer();
    }

    public void start() {
        String code = file.getContent();

        lex.analyze(code);
        //syn.analyze(code, lexemesTokens) //TODO syntax analysis
        //sem.analyze(code, lexemeTokens)//TODO semantic analysis
    }

    public HashMap<String, String> getLexemesTokens() {
        return lexemesTokens;
    }

    public void setFile(IOLFile currentFile) {
        this.file = currentFile;
        lexemesTokens.clear();
    }
}
