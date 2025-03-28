package ui;

import model.DnaNucleotide;
import model.DnaSequence;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a DNA calculator
// Notes:
// - SimpleDrawingPlayer was used as a guide
// - dnaImage.png source: LxcasUni, CC BY-SA 4.0 <https://creativecommons.org/licenses/by-sa/4.0>, via Wikimedia Commons
public class DnaCalculator extends JFrame {
    private DnaSequence sequence;

    private JPanel loadFromFilePanel;
    private JPanel enterSequencePanel;
    private JPanel currentSequencePanel;
    private JTextArea currentSequenceTextArea;
    private JPanel addToSequencePanel;
    private JPanel complementPanel;
    private JPanel transcribePanel;
    private JPanel savePanel;

    private ImageIcon dnaImageIcon = new ImageIcon("data/dnaImage.png");
    public static final String JSON_STORE = "./data/sequence.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    // EFFECTS: constructs a DNA calculator with all fields initialized and scales icon
    public DnaCalculator() {
        super("DNA Calculator");
        sequence = new DnaSequence();
        loadFromFilePanel = new JPanel();
        enterSequencePanel = new JPanel();
        currentSequencePanel = new JPanel();
        currentSequenceTextArea = new JTextArea();
        addToSequencePanel = new JPanel();
        complementPanel = new JPanel();
        transcribePanel = new JPanel();
        savePanel = new JPanel();

        Image dnaImage = dnaImageIcon.getImage();
        dnaImageIcon = new ImageIcon(dnaImage.getScaledInstance(100, 80, Image.SCALE_SMOOTH));

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDnaCalculator();
    }

    // MODIFIES: this
    // EFFECTS: runs DNA calculator
    private void runDnaCalculator() {
        setUpWindow();
        startWindow();
    }

    // MODIFIES: this
    // EFFECTS: sets up JFrame window
    private void setUpWindow() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printEventLog();
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds panels that prompt the user to load sequence from file or enter a new sequence
    private void startWindow() {
        addLoadFromFilePanel();
        addEnterSequencePanel();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to load sequence from file
    private void addLoadFromFilePanel() {
        JButton loadFromFileButton = new JButton("Load from file");
        loadFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    sequence = jsonReader.read();
                    JOptionPane.showMessageDialog(null, "Loaded sequence from: " + JSON_STORE,
                            "DNA Calculator", JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
                    mainWindow();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to read from file: " + JSON_STORE, "DNA Calculator",
                            JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
                }
            }
        });

        loadFromFilePanel.add(loadFromFileButton);

        add(loadFromFilePanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to enter a new sequence
    private void addEnterSequencePanel() {
        JTextArea enterSequenceTextArea = new JTextArea();
        enterSequenceTextArea.setRows(25);
        enterSequenceTextArea.setColumns(50);

        JScrollPane enterSequenceScrollPlane = new JScrollPane(enterSequenceTextArea);

        JButton enterSequenceButton = new JButton("Enter new sequence");
        enterSequenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterSequenceTextArea.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "No sequence entered",
                            "DNA Calculator", JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
                } else {
                    sequence = toDnaSequence(enterSequenceTextArea.getText());
                    mainWindow();
                }
            }
        });

        enterSequencePanel.add(enterSequenceScrollPlane, BorderLayout.EAST);
        enterSequencePanel.add(enterSequenceButton, BorderLayout.WEST);

        add(enterSequencePanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds panels that allow user to do the following user stories:
    //          - view the current sequence
    //          - add to the sequence
    //          - open a popup window that contains the complementary sequence
    //          - open a popup window that contains the transcribed sequence
    //          - save the current sequence to file
    private void mainWindow() {
        remove(loadFromFilePanel);
        remove(enterSequencePanel);
        repaint();
        addCurrentSequencePanel();
        addAddToSequencePanel();
        addComplementPanel();
        addTranscribePanel();
        addSavePanel();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to view the current sequence
    private void addCurrentSequencePanel() {
        currentSequenceTextArea.setText("Current sequence: " + sequence.toString());
        currentSequenceTextArea.setRows(30);
        currentSequenceTextArea.setColumns(25);
        currentSequenceTextArea.setEditable(false);

        JScrollPane currentSequenceScrollPane = new JScrollPane(currentSequenceTextArea);

        currentSequencePanel.add(currentSequenceScrollPane);

        add(currentSequencePanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to enter a sequence to add to the end of the current sequence
    private void addAddToSequencePanel() {
        JTextArea addToSequenceTextArea = new JTextArea();
        addToSequenceTextArea.setRows(30);
        addToSequenceTextArea.setColumns(25);

        JScrollPane addToSequenceScrollPane = new JScrollPane(addToSequenceTextArea);

        JButton addToSequenceButton = new JButton("Add to sequence");
        addToSequenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (addToSequenceTextArea.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "No sequence entered",
                            "DNA Calculator", JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
                } else {
                    sequence.addSequenceToSequence(toDnaSequence(addToSequenceTextArea.getText()));
                    currentSequenceTextArea.setText("Current sequence: " + sequence.toString());
                }
            }
        });

        addToSequencePanel.add(addToSequenceButton, BorderLayout.NORTH); // being overridden by JFrame layout? !!!
        addToSequencePanel.add(addToSequenceScrollPane, BorderLayout.SOUTH); // ^

        add(addToSequencePanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to open a popup window containing the complement of the current sequence
    private void addComplementPanel() {
        JButton complementButton = new JButton("Complement");
        complementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,
                        "Complement: " + sequence.complement().toString(), "DNA Calculator",
                        JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
            }
        });

        complementPanel.add(complementButton);

        add(complementPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to open a popup window containing the transcribed sequence
    private void addTranscribePanel() {
        JButton transcribeButton = new JButton("Transcribe");
        transcribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,
                        "Transcribed sequence: " + sequence.transcribe().toString(), "DNA Calculator",
                        JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
            }
        });

        transcribePanel.add(transcribeButton);

        add(transcribeButton, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: adds panel that allows user to save sequence to file
    private void addSavePanel() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(sequence);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null,
                            "Saved sequence to: " + JSON_STORE, "DNA Calculator",
                            JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to write to file: " + JSON_STORE, "DNA Calculator",
                            JOptionPane.PLAIN_MESSAGE, dnaImageIcon);
                }
            }
        });

        savePanel.add(saveButton);

        add(savePanel);
    }

    // EFFECTS: prints EventLog to console
    private void printEventLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    // EFFECTS: returns a DNA sequence corresponding to inputted string; ignores invalid characters
    //          (characters other than A/a, C/c, G/g, or T/t)
    private static DnaSequence toDnaSequence(String stringSequence) {
        stringSequence = stringSequence.toUpperCase();
        char[] charArraySequence = stringSequence.toCharArray();
        DnaSequence dnaSequence = new DnaSequence();
        for (Character character : charArraySequence) {
            if (character.equals('A')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.A);
            }
            if (character.equals('C')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.C);
            }
            if (character.equals('G')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.G);
            }
            if (character.equals('T')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.T);
            }
        }

        return dnaSequence;
    }
}
