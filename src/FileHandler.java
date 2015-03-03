/*
    Programmers: Alvin Jay Cosare, Gabriel Lagmay, Raphael Tugasan
    Exercise 2: Atchups Bolivia Compiler, Date Due: February 27, 2015
*/
import javax.swing.*;
import java.io.*;

/**
 * Created by alvinjay on 3/2/15.
 */

/**
 * Description:
 *  - includes methods for handling file operations (open, save, write, exit)
 */
public class FileHandler {

    private CurrentFile currentFile;
    private GUI gui;

    public FileHandler(GUI gui, CurrentFile currentFile) {
        this.gui = gui;
        this.currentFile = currentFile;
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
     * Method called when user wants to open a file
     */
    public void fileOpen()
    {
        gui.jta.setEnabled(true);

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
            try
            {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String data;
                gui.jta.setText("");

                while( (data=br.readLine()) != null)
                {
                    gui.jta.append(data);
                }

                currentFile.setFilename(f.getAbsolutePath());
                br.close();
                fr.close();

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
    }

    /**
     * Method called when user decides to save changes done to currently opened file
     */
    public void fileSave()
    {
        if(currentFile.getFilename().equals(""))
        {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY);

            int res = jfc.showSaveDialog(gui);
            if(res == jfc.APPROVE_OPTION)
            {
                File f = jfc.getSelectedFile();
                currentFile.setFilename(f.getAbsolutePath());
                fileWrite();
            }
        }
        else
            fileWrite();
    }

    /**
     * Method called within fileSave(), overwrites the existing file in file directory
     */
    public void fileWrite()
    {
        try
        {
            FileWriter fw = new FileWriter(currentFile.getFilename());

            fw.write(gui.jta.getText());
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
}
