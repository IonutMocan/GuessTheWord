import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GuessTheWord implements ActionListener {

	// ----------------------- Declaration Area
	public static String WordsList[] = { "advanced", "approximately", "atmosphere", // --- A words
			"background", "beautiful", "between", // --- B words
			"circumstance", "country", "commission", // --- C words
			"dangerous", "demonstration", "dramatically", // --- D words
			"edge", "emergency", "entertainment", // --- E words
			"finish", "foundation", "furniture", // --- F words
			"gate", "government", "guarantee", // --- G words
			"habit", "helicopter", "human", // --- H words
			"illegal", "improvement", "intelligence", // --- I words
			// etc..
	}; // ------------- List of words

	String trueWord, HiddenWord = "";

	JFrame SwingWindow;

	JLabel WelcomeText, theWordIs, theWord;

	JMenuBar menuBar;

	JMenu windowMenu;

	JMenuItem menuItem;

	JTextField characterOrWord;

	JButton getTheValue;

	// ----------------------- Declaration Area

	GuessTheWord(int windowSize) {

		SwingWindow = new JFrame("Guess the word game"); // Create the window

		// ------------------------ Welcome text label
		WelcomeText = new JLabel("Welcome to Guess The Word!");
		WelcomeText.setBounds(230, 25, 350, 40);
		WelcomeText.setFont(new Font("Sherif", Font.BOLD, 21));
		SwingWindow.add(WelcomeText);
		// ------------------------ Welcome text label

		// ------------------------ MenuBar
		menuBar = new JMenuBar();
		windowMenu = new JMenu("Help");
		menuItem = new JMenuItem("Game rules");

		menuBar.add(windowMenu);
		windowMenu.add(menuItem);
		menuItem.addActionListener(this);
		windowMenu.addActionListener(this);
		SwingWindow.setJMenuBar(menuBar);
		// ------------------------ MenuBar

		// ----------------------- theWord is JLabel
		theWordIs = new JLabel("The unknown word is: ");
		theWordIs.setBounds(45, 170, 450, 40);
		theWordIs.setFont(new Font("Sherif", Font.BOLD, 40));
		SwingWindow.add(theWordIs);
		// ----------------------- theWord is JLabel

		// ----------------------- theWord JLaberl
		theWord = new JLabel(HiddenWordGenerator());
		theWord.setBounds(470, 170, 450, 45);
		theWord.setFont(new Font("Sherif", Font.ITALIC, 40));
		SwingWindow.add(theWord);
		// ----------------------- theWord JLaberl

		// ----------------------- Text Field
		characterOrWord = new JTextField();
		characterOrWord.setBounds(350, 250, 50, 45);
		SwingWindow.add(characterOrWord);
		// ----------------------- Text Field

		// ----------------------- Check the entered value
		getTheValue = new JButton("Click to enter the value");
		getTheValue.setBounds(273, 300, 200, 45);
		getTheValue.addActionListener(this);
		SwingWindow.add(getTheValue);
		// ----------------------- Check the entered value

		// ------------------------ Window settings
		SwingWindow.setDefaultCloseOperation(SwingWindow.EXIT_ON_CLOSE); // Close the window to X button press
		SwingWindow.setSize(windowSize, (windowSize / 12 * 9)); // Sets the window size on aspect ratio 12 x 9
		SwingWindow.setLocationRelativeTo(null); // Sets the default location of window in center
		SwingWindow.getContentPane().setBackground(new java.awt.Color(255, 145, 0)); // Sets the background color
		SwingWindow.setResizable(false); // Prevents window resizing
		SwingWindow.setLayout(null);
		SwingWindow.setVisible(true); // Sets the window visible
		// ------------------------ Window settings
	}

// --------------------- The visible HiddenWord
	public String HiddenWordGenerator() {
		String Word = WordChooser();
		trueWord = Word;
		for (int i = 0; i < Word.length(); ++i)
			HiddenWord += "*";

		return HiddenWord;

	}
	// --------------------- The visible HiddenWord

	// --------------------- Choose a word random from the list
	public String WordChooser() {
		int randId = RandomWordID();
		return WordsList[randId];
	}
	// --------------------- Choose a word random from the list

	// ---------------------- Random function
	int RandomWordID() {
		Random RandomNumber = new Random();
		int min = 0;
		int max = WordsList.length;
		return RandomNumber.nextInt(max - min) + min; // Generate a random number between min and max
	}
	// ---------------------- Random function

	// ---------------------- Action performing function
	public void actionPerformed(ActionEvent Event) {

		// --------------------- Show help dialog box
		if (Event.getSource() == menuItem) {
			JOptionPane.showMessageDialog(SwingWindow,
					"Game rules:\n" + "\t- introduce one character as a guess or entire word if you think you know it"
							+ "\n\t- based on previous guesses you can asume what's the complet word");
		}
		// --------------------- Show help dialog box

		// ----------------------- Check the entered value
		if (Event.getSource() == getTheValue) {
			char[] enteredChar = characterOrWord.getText().toCharArray(); // Convert strings to char array
			char[] trueChar = trueWord.toCharArray(); // Convert strings to char array
			if (characterOrWord.getText().length() == trueWord.length()) { // Check if we got words with the same length
				int ok = 1;
				for (int i = 0; i < trueChar.length && ok == 1; ++i) { // Check if it's the same word
					if (enteredChar[i] != trueChar[i]) {
						ok = 0;
					}
				}
				if (ok == 1) {
					JOptionPane.showMessageDialog(SwingWindow, "Congrats! You found the word ;)"); // If condition is
																									// true, we found a
																									// word
					characterOrWord.setText(null);
					HiddenWord = "";
					HiddenWord = HiddenWordGenerator();
					theWord.setText(HiddenWord);

				}
			} else if (characterOrWord.getText().length() == 1) { // If we got just one character

				char[] tempHiddenWord = HiddenWord.toCharArray();
				for (int i = 0; i < trueChar.length; ++i) {
					if (characterOrWord.getText().charAt(0) == trueChar[i]) {
						tempHiddenWord[i] = characterOrWord.getText().charAt(0);
						HiddenWord = String.valueOf(tempHiddenWord);
						theWord.setText(HiddenWord);
						if (theWord.getText().equals(trueWord)) {
							JOptionPane.showMessageDialog(SwingWindow, "Congrats! You found the word ;)"); // If
																											// condition
																											// is true,
																											// we found
																											// a word
							characterOrWord.setText(null);
							HiddenWord = "";
							HiddenWord = HiddenWordGenerator();
							theWord.setText(HiddenWord);
						}

					}
				}
				characterOrWord.setText(null);
			} else { // Otherwise, we got an invalid input
				JOptionPane.showMessageDialog(SwingWindow, "Invalid character!");
			}

		}
		// ----------------------- Check the entered value

	}
	// ---------------------- Action performing function

	public static void main(String[] args) {
		new GuessTheWord(800);

	}

}
