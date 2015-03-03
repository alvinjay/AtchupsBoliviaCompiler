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

    JMenuBar mbar;
    JMenu file, edit;
    JMenuItem newFile, exit, open, save, saveAs;
    JMenuItem cut , copy, paste, selall, del;

    GUI gui;

    public Menu(GUI gui, JMenuBar mbar) {
        this.gui = gui;
        this.mbar = mbar;
        initMenu();
    }

    /**
     * Initialize menu bar GUI components
     */
    public void initMenu() {
        file = new JMenu("File");
        edit = new JMenu("Edit");

        file.setMnemonic('F');
        edit.setMnemonic('E');

        newFile = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As...");
        exit = new JMenuItem("Exit");

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selall = new JMenuItem("Select all");
        del = new JMenuItem("Delete");

        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();
        edit.add(selall);
        edit.add(del);

        mbar.add(file);
        mbar.add(edit);

        newFile.addActionListener(gui);
        open.addActionListener(gui);
        save.addActionListener(gui);
        saveAs.addActionListener(gui);
        exit.addActionListener(gui);

        cut.addActionListener(gui);
        copy.addActionListener(gui);
        paste.addActionListener(gui);
        selall.addActionListener(gui);
        del.addActionListener(gui);

        KeyStroke k ;

        k = KeyStroke.getKeyStroke('N', java.awt.event.InputEvent.CTRL_DOWN_MASK);
        newFile.setAccelerator(k);

        k = KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.CTRL_DOWN_MASK);
        open.setAccelerator(k);

        k = KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK);
        save.setAccelerator(k);

        k = KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK);
        saveAs.setAccelerator(k);

        gui.setJMenuBar(mbar);
        gui.setVisible(true);
    }


}
