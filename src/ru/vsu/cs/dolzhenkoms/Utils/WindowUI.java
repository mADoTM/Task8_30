package ru.vsu.cs.dolzhenkoms.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class WindowUI extends JFrame {
    private JTable inputArrayTable;
    private JTable outputArrayTable;

    private JButton openButton;
    private JButton saveButton;
    private JButton executeMathOperationButton;
    private JButton addRowButton;
    private JButton addColumnButton;
    private JButton removeRowButton;
    private JButton removeColumnButton;
    private JButton fillRandomButton;


    private Object[][] defaultArray = new Integer[][]  {{1,2,3,4},
                                                        {3,2,2,0},
                                                        {1,2,1,0}};

    public WindowUI() {
        super("WindowUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initButtons();
        initMarkup();

        setSize(600, 600);
        setVisible(true);
    }

    private void initButtons(){
        openButton = new JButton("Открыть файл");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(new JFrame());

                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    System.out.println("File Selected: " + file.getName());

                    try {
                        int[][] arrayFromFile = ArrayUtils.readArray2FromLines(ArrayUtils.toPrimitive(FileManager.readAllLinesFromFile(file.getPath())));
                        ArrayUtils.printArray2(arrayFromFile);
                        ArrayUtils.fillTableModelBy2Array((DefaultTableModel) inputArrayTable.getModel(),arrayFromFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    System.out.println("Open command canceled");
                }
            }
        });

        executeMathOperationButton = new JButton("Сформировать новый массив");
        executeMathOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel inputModel = (DefaultTableModel) inputArrayTable.getModel();
                int[][] newArray = ArrayUtils.get2ArrayFromTable(inputModel);
                newArray = MathOperation.getNewDoubleArrayByRule(newArray);
                ArrayUtils.printArray2(newArray);

                DefaultTableModel outputModel = (DefaultTableModel) outputArrayTable.getModel();
                ArrayUtils.fillTableModelBy2Array(outputModel, newArray);
            }
        });

        saveButton = new JButton("Сохранить файл");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(parentFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());

                    int[][] arrayFromOutputTable = ArrayUtils.get2ArrayFromTable((DefaultTableModel) outputArrayTable.getModel());
                    try {
                        FileManager.createFileWithText(fileToSave.getPath(), ArrayUtils.convertArray2ToString(arrayFromOutputTable));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        addColumnButton = new JButton("+");
        addColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputArrayTable != null) {
                    DefaultTableModel model = (DefaultTableModel) inputArrayTable.getModel();
                    model.setColumnCount(model.getColumnCount() + 1);
                }
            }
        });

        removeColumnButton = new JButton("-");
        removeColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputArrayTable != null) {
                    DefaultTableModel model = (DefaultTableModel) inputArrayTable.getModel();
                    model.setColumnCount(model.getColumnCount() - 1);
                }
            }
        });

        addRowButton = new JButton("+");
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputArrayTable != null) {
                    DefaultTableModel model = (DefaultTableModel) inputArrayTable.getModel();
                    model.addRow(new Integer[model.getRowCount()]);
                }
            }
        });

        removeRowButton = new JButton("-");
        removeRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputArrayTable != null) {
                    DefaultTableModel model = (DefaultTableModel) inputArrayTable.getModel();
                    model.removeRow(model.getRowCount() - 1);
                }
            }
        });

        fillRandomButton = new JButton("Случайные числа");
        fillRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputArrayTable != null) {
                    DefaultTableModel model = (DefaultTableModel) inputArrayTable.getModel();

                    int rowCount = model.getRowCount();
                    int columnCount = model.getColumnCount();

                    for(int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }

                    for(int i = 0; i < rowCount; i++) {
                        Integer[] array = ArrayUtils.toObject(ArrayUtils.fillRandomValuesInArray(new int[columnCount]));
                        model.addRow(array);
                    }
                }
            }
        });
    }

    private void initMarkup() {
        DefaultTableModel model = new DefaultTableModel(defaultArray, new String[defaultArray[0].length]);

        inputArrayTable = new JTable(model);
        inputArrayTable.setForeground(Color.red);
        inputArrayTable.setRowHeight(30);

        Box inputBoxWorkplace = new Box(BoxLayout.Y_AXIS);

        Box columnButtons = new Box(BoxLayout.Y_AXIS);
        columnButtons.add(addColumnButton);
        columnButtons.add(removeColumnButton);

        JPanel jPanel = new JPanel();
        jPanel.add(inputArrayTable);
        jPanel.add(columnButtons);

        JPanel rowButtons = new JPanel();
        rowButtons.add(addRowButton);
        rowButtons.add(removeRowButton);

        inputBoxWorkplace.add(jPanel);
        inputBoxWorkplace.add(rowButtons);

        JPanel mainButtons = new JPanel();
        mainButtons.add(fillRandomButton);
        mainButtons.add(executeMathOperationButton);
        mainButtons.add(openButton);
        mainButtons.add(saveButton);

        outputArrayTable = new JTable(new DefaultTableModel(3,4));
        outputArrayTable.setRowHeight(30);

        Box windowBox = new Box(BoxLayout.Y_AXIS);
        windowBox.add(inputBoxWorkplace);
        windowBox.add(mainButtons);
        windowBox.add(outputArrayTable);

        getContentPane().add(windowBox);
    }
}
