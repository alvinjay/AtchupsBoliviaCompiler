package FILES;

import javax.swing.*;

/**
 * Created by alvinjay on 3/8/15.
 */
public class IOLFile {
    private int index;
    private String path, filename, content;
    private boolean modified;
    public JTextArea textEditor;

    public IOLFile() {
        path = "";
        filename = "";
        content = "";
        modified = false;
    }

    public IOLFile(int index, String path, String filename, String content) {
        this.index = index;
        this.path = path;
        this.filename = filename;
        this.content = content;
        modified = false;
        textEditor = new JTextArea(content);
    }

    public String getPath() {
        return path;
    }

    public JTextArea getTextEditor() {
        return textEditor;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public String getFilename() {
        if (filename.equals(""))
            filename = "New File";
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

    public int getIndex() {
        return index;
    }
}
