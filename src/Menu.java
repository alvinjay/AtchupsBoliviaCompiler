/*
    Programmers: Alvin Jay Cosare, Gabriel Lagmay, Raphael Tugasan
    Exercise 2: Atchups Bolivia Compiler, Date Due: February 27, 2015
*/

import javax.swing.*;

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
    public JMenuItem compileRun, runOnly;

    private GUI gui;

    public Menu(GUI gui, JMenuBar mbar) {
        this.gui = gui;
        this.mbar = mbar;
        initMenu();
    }

    /**
     * Initialize menu bar GUI components
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

        run.setMnemonic('E');

        compileRun = new JMenuItem("Compile and Run");
        runOnly = new JMenuItem("Run");

//        runOnly.setEnabled(false);
//        compileRun.setEnabled(false);

        run.add(compileRun);
        run.add(runOnly);

        compileRun.addActionListener(gui);
        runOnly.addActionListener(gui);

        mbar.add(run);
    }

}
