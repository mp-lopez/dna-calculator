# DNA Calculator

This application allows the user to input a DNA sequence, add to it, and request various pieces of information at any
point of the growing sequence (e.g., complementary DNA sequence). 

The application is designed for anyone working with DNA sequences and calculating different properties.

It is of personal interest because I am currently taking a molecular biology lab and use similar programs. I would like
to explore how these features can be implemented in OOD and even add features that I wish were in other programs.

## User Stories
As a user, I want to be able to...
- Input an initial DNA sequence
- Add to the sequence
- Request its complement
- Request its corresponding RNA sequence
- Be able to save the current sequence to file
- Be able to reload the sequence from file 

## Phase 3: Instructions for Grader

- To reload a DNA sequence from file:
  - Run main
  - Click "Load from file"
- To complement the current DNA sequence
  - Run main
  - Click "Load from file" or enter a new sequence into the text area and then click "Enter new sequence" 
  - Click "Complement"
  - A popup window containing the complementary sequence should open.
- To transcribe the current DNA sequence
  - Run main
  - Click "Load from file" or enter a new sequence into the text area and then click "Enter new sequence"
  - Click "Transcribe"
  - A popup window containing the transcribed sequence should open.
- To add a single DNA nucleotide or another sequence to the end of the current sequence:
  - Run main
  - Click "Load from file" or enter a new sequence into the text area and then click "Enter new sequence"
  - Enter a single DNA nucleotide or another sequence into the text area beside "Add to sequence"
  - Click "Add to sequence"
  - The added nucleotide/s should appear in the text area displaying "Current sequence:..."
- To save the current DNA sequence to file:
  - Run main
  - Click "Load from file" or enter a new sequence into the text area and then click "Enter new sequence"
  - Add to sequence if desired
  - Click "Save"
- To locate the visual component:
  - Open any popup window as follows and see the DNA icon on the bottom left:
    - Attempt to "Load from file" when the file cannot be found
    - Attempt to "Enter new sequence" when the text area is empty or only contains whitespace characters
    - Click "Complement"
    - Click "Transcribe"
    - Click "Save"
    - Attempt to "Add to sequence" when the text area is empty or only contains whitespace characters

## Phase 4 Task 2
Thu Nov 30 11:25:28 PST 2023\
Loaded sequence from ./data/sequence.json\
Thu Nov 30 11:25:36 PST 2023\
Transcribed sequence\
Thu Nov 30 11:25:47 PST 2023\
Added AAA to sequence\
Thu Nov 30 11:25:49 PST 2023\
Complemented sequence\
Thu Nov 30 11:25:51 PST 2023\
Saved sequence to ./data/sequence.json\
Thu Nov 30 11:25:54 PST 2023\
Transcribed sequence

## Phase 4 Task 3
One idea for refactoring would be to make a Nucleotide interface/abstract class that DnaNucleotide and RnaNucleotide
implement/extend, and a Sequence interface/abstract class that DnaSequence and RnaSequence implement/extend. That way,
existing shared behaviour and future shared behaviour only need to be edited/added in Nucleotide or Sequence. An example
of existing shared behaviour are the methods DnaSequence.addNucleotideToSequence(DnaNucleotide dnaNucleotide) and 
RnaSequence.addToSequence(RnaNucleotide rnaNucleotide). 
So, Sequence could have a method called addNucleotideToSequence(Nucleotide nucleotide). An example of future shared 
behaviour could be a method called nucleotideCount() which returns the number of nucleotides in a sequence.

Another idea for refactoring would be to separate DnaCalculator into two classes: one for calculation (e.g., complementing)
and the other to manage the JFrame. The calculation class could have static methods (e.g., a method that takes a DnaSequence
and returns the complement as a String) that are then called in the JFrame manager, which would be the one instantiated
in Main.

## Notes for Self:
- Additional user stories
  - Nucleotide count
  - Translate
  - Sequence grouper (e.g., 10's)
  - Graphical illustration of double-stranded DNA
- Add nucleotideCount() method to DnaSequence and RnaSequence and refactor tests?
- GUI: have multiple panels that are continually updating with complement, transcribed sequence, etc.?
- Consider changing all "JOptionPane.showMessageDialog()" to JDialogs instead
- Consider changing addPanel methods to just make the panels and then add them in mainWindow()
- Change currentSequencePanel from JTextArea to JTextPane, so it can be labelled "Current sequence" (if that's how it works)
- Consider throwing exception for illegal characters that is caught in ActionListener of enterSequenceButton and ActionListener of addToSequenceButton
