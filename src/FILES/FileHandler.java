package FILES;/*
    Programmers: Alvin Jay Cosare, Gabriel Lagmay, Raphael Tugasan
    Exercise 2: Atchups Bolivia COMPILER.Compiler, Date Due: February 27, 2015
*/
import INTERFACE.GUI;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by alvinjay on 3/2/15.
 */

/**
 * Description:
 *  - includes methods for handling file operations (open, save, write, exit)
 */
public class FileHandler {

    private ArrayList<IOLFile> IOLFiles;
    private IOLFile openedFile, currentFile;
    private GUI gui;

    public FileHandler(GUI gui, ArrayList<IOLFile> IOLFiles) {
        this.gui = gui;
        this.IOLFiles = IOLFiles;
        openedFile = new IOLFile();
    }

    /**
     * Method called when user wants to open a file
     */
    public IOLFile fileOpen()
    {
        currentFile = new IOLFile();
        
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(jfc.FILES_ONLY);

        IMPORTS.FileFilter filter = new IMPORTS.FileFilter();
        filter.addExtension("iol");
        filter.setDescription("Input Files");
        jfc.setFileFilter(filter);

        int res = jfc.showOpenDialog(gui);
        if(res == jfc.APPROVE_OPTION)
        {
            File f = jfc.getSelectedFile();

            //check if file selected is already loaded
            int result = fileExistsInArrayList(f.getAbsolutePath());
            if(result != -1) { //if already exists then
                return IOLFiles.get(result); //return the existing file instead
            }
            try
            {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                //read input string from file
                String data = "", line;
                while( (line=br.readLine()) != null)
                {
                    data += line + "\n";
                }

                openedFile = new IOLFile(IOLFiles.size(), f.getAbsolutePath(), f.getName(), data);

                br.close();
                fr.close();

                return openedFile;
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog
                        (
                                gui , e.getMessage() , "File Open Error",
                                JOptionPane.ERROR_MESSAGE
                        );
            }
        }

        return null;
    }

    /**
     * Method called when user decides to save changes done to currently opened file
     */
    public IOLFile fileSave()
    {
        if(currentFile.getFilename().equals("New File"))
        {
            return fileSaveAs();
        }
        else {
            fileWrite();
        }

        currentFile.setContent(currentFile.textEditor.getText());

        return null;
    }

    public IOLFile fileSaveAs() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY);

        int res = jfc.showSaveDialog(gui);
        if(res == jfc.APPROVE_OPTION)
        {
            File f = jfc.getSelectedFile();
            currentFile = new IOLFile(IOLFiles.size(), f.getAbsolutePath(), f.getName(), currentFile.textEditor.getText());
            fileWrite();
            return currentFile;
        }

        return null;
    }

    /**
     * Method called within fileSave(), overwrites the existing file in file directory
     */
    public void fileWrite()
    {
        try
        {
            FileWriter fw = new FileWriter(currentFile.getPath());

            fw.write(currentFile.textEditor.getText());
            fw.flush();
            fw.close();
            currentFile.setModified(false);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog
                    (
                            gui , e.getMessage() , "File Save Error",
                            JOptionPane.ERROR_MESSAGE
                    );
        }

    }

    /**
     * Method called when user decides to exit IDE
     */
    public void fileExit()
    {
        if(currentFile.isModified())
        {
            int res;
            res = JOptionPane.showConfirmDialog(gui, "Do You Want to Save Changes", "File Exit", JOptionPane.YES_NO_CANCEL_OPTION);
            if(res == JOptionPane.YES_OPTION)
            {
                fileSave();
            }
            else if(res == JOptionPane.CANCEL_OPTION)
            {
                return;
            }
        }
        System.exit(0);
    }

    /**
     * Checks if the filename of the file newly opened is already loaded in IDE
     * @param filePath - location of file in file directory
     * @return index of file if already loaded, else return -1
     */
    private int fileExistsInArrayList(String filePath) {
        for (int i = 0; i < IOLFiles.size(); i++) {
            if (IOLFiles.get(i).getPath().equals(filePath))
                return i;
        }
        return -1;
    }

    public void setCurrentFile(IOLFile currentFile) {
        this.currentFile = currentFile;
    }
}
