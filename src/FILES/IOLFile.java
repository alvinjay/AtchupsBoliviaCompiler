package FILES;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alvinjay on 3/8/15.
 */
public class IOLFile {
/* file index in the IOLFiles ArrayList and file's textEditor's index in GUI tabbed pane  */
    private int index;
/* file attributes */
    private String path, filename, content;
/* flag for determining edited/modified status */
    private boolean modified;
/* file's own textEditor, is the one put in tabbed pane */
    public JTextArea textEditor;
/* default style for text editor font */
    private Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 12);

/* empty file constructor */
    public IOLFile() {
        path = "";
        filename = "";
        content = "";
        modified = false;
    }

/* file constructor */
    public IOLFile(int index, String path, String filename, String content) {
        this.index = index;
        this.path = path;
        this.filename = filename;
        this.content = content;
        modified = false;
        textEditor = new JTextArea(content);
        textEditor.setFont(DEFAULT_FONT);
    }

    public String getPath() {
        return path;
    }

    public JTextArea getTextEditor() {
        return textEditor;
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
