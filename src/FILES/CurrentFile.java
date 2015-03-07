package FILES;/*
    Programmers: Alvin Jay Cosare, Gabriel Lagmay, Raphael Tugasan
    Exercise 2: Atchups Bolivia COMPILER.Compiler, Date Due: February 27, 2015
*/

/**
 * Created by alvinjay on 3/2/15.
 */

/**
 * Description:
 *  - class responsible for representing the currently opened file in the IDE
 */
public class CurrentFile {
    String filename, content;
    boolean modified = false;

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
