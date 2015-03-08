package INTERFACE;/*
    Programmers: Alvin Jay Cosare, Gabriel Lagmay, Raphael Tugasan
    Exercise 2: Atchups Bolivia COMPILER.Compiler, Date Due: February 27, 2015
*/

import FILES.FileHandler;
import COMPILER.Compiler;
import FILES.IOLFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alvinjay on 3/2/15.
 */

/**
 * Description:
 *  - includes methods responsible for activating menu bar functionalities
 */
public class Menu {

    private JMenuBar mbar;
    /* Menubar tabs */
    public JMenu file, edit, run;
    /* File Tab options */
    public JMenuItem newFile, exit, open, save, saveAs;
    /* Edit Tab options */
    public JMenuItem cut , copy, paste, selall, del;
    /* Run Tab options */
    public JMenuItem compile, compileExecute, execute;

    private GUI gui;
    private IOLFile currentFile;
    private FileHandler fileHandler;

    private Compiler compiler;

    public Menu(GUI gui, JMenuBar mbar, FileHandler fileHandler) {
        this.gui = gui;
        this.mbar = mbar;
        this.fileHandler = fileHandler;

        compiler = new Compiler();
        initMenu();
    }

    /**
     * Initialize menu bar INTERFACE.GUI components
     */
    private void initMenu() {
        initFileTab();
        initEditTab();
        initRunTab();

        gui.setJMenuBar(mbar);
        gui.setVisible(true);
    }

    /**
     * Initialize "File" tab in menu bar
     */
    private void initFileTab() {
        file = new JMenu("File");

        file.setMnemonic('F');

        newFile = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As...");
        exit = new JMenuItem("Exit");

        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);

        newFile.addActionListener(gui);
        open.addActionListener(gui);
        save.addActionListener(gui);
        saveAs.addActionListener(gui);
        exit.addActionListener(gui);

        KeyStroke k ;

        k = KeyStroke.getKeyStroke('N', java.awt.event.InputEvent.CTRL_DOWN_MASK);
        newFile.setAccelerator(k);

        k = KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.CTRL_DOWN_MASK);
        open.setAccelerator(k);

        k = KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK);
        save.setAccelerator(k);

        k = KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK);
        saveAs.setAccelerator(k);

        mbar.add(file);
    }

    /**
     * Initialize "Edit" tab in menu bar
     */
    private void initEditTab() {
        edit = new JMenu("Edit");

        edit.setMnemonic('E');

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selall = new JMenuItem("Select all");
        del = new JMenuItem("Delete");

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();
        edit.add(selall);
        edit.add(del);

        cut.addActionListener(gui);
        copy.addActionListener(gui);
        paste.addActionListener(gui);
        selall.addActionListener(gui);
        del.addActionListener(gui);

        mbar.add(edit);
    }

    /**
     * Initialize "Run" tab in menu bar
     */
    private void initRunTab() {
        run = new JMenu("Run");

        run.setMnemonic('R');

        compile = new JMenuItem("Compile");
        compileExecute = new JMenuItem("Compile and Execute");
        execute = new JMenuItem("Execute");

//        runOnly.setEnabled(false);
//        compileRun.setEnabled(false);

        run.add(compile);
        run.add(compileExecute);
        run.add(execute);

        compile.addActionListener(gui);
        compileExecute.addActionListener(gui);
        execute.addActionListener(gui);

        mbar.add(run);
    }

    public boolean fileTabActionListener(ActionEvent e) {
        if(e.getSource().equals(newFile))
        {
            currentFile = new IOLFile(gui.IOLFiles.size(), "", "", ""); //create an empty IOL File
            addCurrentFile(currentFile);
            prepareCurrentFile(currentFile);
        }
        else if(e.getSource().equals(open))
        {
            currentFile = fileHandler.fileOpen();
            //check if error occured
            if (!currentFile.equals(null)) {
                if (!gui.IOLFiles.contains(currentFile))
                    addCurrentFile(currentFile);
                prepareCurrentFile(currentFile);
            }
        }
        else if(e.getSource().equals(save))
        {
            IOLFile savedAsFile = fileHandler.fileSave();

           try {
               savedAsFile.equals(null); //check if null, throws exception to catch if null
               addCurrentFile(savedAsFile);
               prepareCurrentFile(savedAsFile);
               setCurrentFile(savedAsFile);
            }
            catch(NullPointerException exception) {} finally {
               gui.jTabbedPane.setTitleAt(currentFile.getIndex(), currentFile.getFilename());
           }
        }
        else if(e.getSource().equals(saveAs))
        {
            IOLFile savedAsFile = fileHandler.fileSaveAs();
            try {
                savedAsFile.equals(null); //check if null, throws exception to catch if null
                addCurrentFile(savedAsFile);
                prepareCurrentFile(savedAsFile);
                setCurrentFile(savedAsFile);
                gui.jTabbedPane.setTitleAt(currentFile.getIndex(), currentFile.getFilename());
            }
            catch(NullPointerException exception) {}
        }
        else if(e.getSource().equals(exit))
        {
            fileHandler.fileExit();
        } else {
            return false;
        }

        return true;
    }

    public boolean editTabActionListener(ActionEvent e) {
        if(e.getSource().equals(cut) )
        {
            gui.textEditor.cut();
        }
        else if(e.getSource().equals(copy))
        {
            gui.textEditor.copy();
        }
        else if(e.getSource().equals(paste))
        {
            gui.textEditor.paste();
        }
        else if(e.getSource().equals(selall))
        {
            gui.textEditor.selectAll();
        }
        else if(e.getSource().equals(del))
        {
            gui.textEditor.replaceSelection("");
        } else {
            return false;
        }

        return true;
    }

    public void runTabActionListener(ActionEvent e) {
        if (e.getSource().equals(compile)) {
            //save file if modified
            if (currentFile.isModified())
                save.doClick();

            //check if is new/empty file
            if (!currentFile.getFilename().equals("New File")) {
                compiler.setFile(currentFile);
                compiler.start();
                gui.setLexemesTokens(compiler.getLexemesTokens());
            }

        } else if (e.getSource().equals(compileExecute)) {
            //save file if modified
            if (currentFile.isModified())
                fileHandler.fileSave();

            //check if is new/empty file
            if (!currentFile.getFilename().equals(null)) {
                compiler.start();
                gui.setLexemesTokens(compiler.getLexemesTokens());
            }
            //TODO execute function
        } else if (e.getSource().equals(execute)) {

        }
    }

    private void prepareCurrentFile(IOLFile currentFile) {
        currentFile.textEditor.addKeyListener(gui); //set keylistener to gui keylisteners
        gui.jTabbedPane.setSelectedIndex(currentFile.getIndex()); //focus newly created/opened file file
    }

    private void addCurrentFile(IOLFile currentFile) {
        gui.IOLFiles.add(currentFile); //add new IOLFile to IOLFiles arraylist
        gui.jTabbedPane.add(currentFile.getFilename(), currentFile.getTextEditor()); //add text editor to tabs
    }

    public void setCurrentFile(IOLFile currentFile) {
        System.out.println(currentFile.getIndex());
        this.currentFile = currentFile;
        fileHandler.setCurrentFile(currentFile);
        compiler.setFile(currentFile);
    }
}
