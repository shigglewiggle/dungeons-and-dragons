import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TimeKeeper extends JFrame {

	private JPanel monthPanel;
	private JPanel selectedMonthPanel;
	private JPanel buttonPanel;
	private JPanel radioPanel;
	private JList monthList;
	private JList selectedMonthList;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JButton selectButton;
	private JButton addButton;
	private JButton decButton;
	private JRadioButton turnButton;
	private JRadioButton roundButton;
	private ButtonGroup radioGroup;
	private JMenuBar menuBar; // Menu bar
	private JMenu fileMenu, optionMenu; // Menus
	private JMenuItem exit, delItem, helpItem, editItem;
	private boolean isTurns = true;

	private ArrayList<Item> items = new ArrayList<Item>();

	public TimeKeeper() {
		setTitle("Time Keeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLayout(new BorderLayout());
		buildSelectedMonthsPanel();
		buildButtonPanel();
		buildRadioPanel();
		BuildMenuBar();

		add(selectedMonthPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		add(radioPanel, BorderLayout.EAST);

		pack();

		setLocationRelativeTo(null);
		setSize(500, 200);
		setVisible(true);

	}// end constructor

	private void buildSelectedMonthsPanel() {
		selectedMonthPanel = new JPanel();
		selectedMonthList = new JList();
		selectedMonthList.setVisibleRowCount(6);
	//	selectedMonthList.setSize(800,100);
		scrollPane2 = new JScrollPane(selectedMonthList);
		selectedMonthPanel.add(scrollPane2);

	}

	private void buildButtonPanel() {

		buttonPanel = new JPanel();
		addButton = new JButton("Add stuff");
		decButton = new JButton("Decrease");

		addButton.addActionListener(new ButtonListener());
		decButton.addActionListener(new ButtonListener());

		buttonPanel.add(addButton);
		buttonPanel.add(decButton);
	}

	private void buildRadioPanel() {
		radioPanel = new JPanel();
		turnButton = new JRadioButton("Turns", true);
		roundButton = new JRadioButton("Rounds");

		radioGroup = new ButtonGroup();
		radioGroup.add(turnButton);
		radioGroup.add(roundButton);
		turnButton.addActionListener(new RadioButtonListener());
		roundButton.addActionListener(new RadioButtonListener());

		radioPanel.add(turnButton);
		radioPanel.add(roundButton);

	}

	public void BuildMenuBar() {

		menuBar = new JMenuBar();

		buildFileMenu();
		buildOptionMenu();
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		setJMenuBar(menuBar);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String actionThing = e.getActionCommand();

			switch (actionThing) {

			case "Add stuff": {

				// add item with turns
				if (isTurns == true) {
					String n, t;

					// dialogue boxes
					n = JOptionPane.showInputDialog("Enter buff/item name");
					t = JOptionPane.showInputDialog("Enter # of turns");

					// construct new item/buff
					int turns = Integer.parseInt(t);
					System.out.println(n + " " + turns + " " + 0);
					Item it = new Item(n, turns, 0);
					items.add(it);

				}// end is turns True

				// add item with rounds
				if (isTurns == false) {
					String n, r;

					// dialogue boxes
					n = JOptionPane.showInputDialog("Enter buff/item name");
					r = JOptionPane.showInputDialog("Enter # of rounds");

					// construct new item/buff
					int rounds = Integer.parseInt(r);
					System.out.println(n + " " + 0 + " " + rounds);
					Item it = new Item(n, 0, rounds);
					items.add(it);
				}// end isturns false

				// add it to array
				String itemList[] = new String[items.size()];

				// add to array
				for (int i = 0; i < items.size(); i++) {
					String placeholder = items.get(i).toString();
					itemList[i] = placeholder;
				}
				// set monthList to temp array
				selectedMonthList.setListData(itemList);
				break;
			}// end case add stuff

			case "Decrease": {

				// check if list is empty
				if (items.isEmpty() == false) {

					// check if turn button is checked
					if (isTurns == true) {

						// check for items to remove
						for (int i = 0; i < items.size(); i++) {
							if ((items.get(i).getTurns() == 0)
									&& (items.get(i).getRounds() <= 10)) {
								System.out.println(" FINAL turns = "
										+ items.get(i).getTurns() + " "
										+ "rounds = "
										+ items.get(i).getRounds());
								items.remove(i);
							}// end if
						}// end for

						// decrements counters
						for (Item it : items) {
							int t = it.getTurns();
							int r = it.getRounds();

							if (t == 0 & r > 10) {
								r -= 10;
								it.setRounds(r);
							}
							if (t > 0) {
								t -= 1;
								it.setTurns(t);
							}

						}// end for
							// check for items to remove
						for (int i = 0; i < items.size(); i++) {
							if ((items.get(i).getTurns() == 0)
									&& (items.get(i).getRounds() == 0)) {
								System.out.println(" FINAL turns = "
										+ items.get(i).getTurns() + " "
										+ "rounds = "
										+ items.get(i).getRounds());
								items.remove(i);
							}// end if
						}// end for
					}// end isTurns = true

					if (isTurns == false) {
						boolean isTurnt = false;
						// check for items to remove
						for (int i = 0; i < items.size(); i++) {
							if (items.get(i).getRounds() == 0
									& items.get(i).getTurns() > 0) {

								int turns = items.get(i).getTurns();
								items.get(i).setRounds(9);
								turns -= 1;
								items.get(i).setTurns(turns);
								System.out.println("turns = " + turns + " "
										+ "rounds = "
										+ items.get(i).getRounds());
								isTurnt = true;
								System.out.println("IsTurn = " + isTurnt);
							}

							if (items.get(i).getRounds() <= 0
									& items.get(i).getTurns() <= 0) {
								items.remove(i);
							}// end if
						}// end for

						// decrement counters
						for (Item it : items) {
							int t = it.getTurns();
							int r = it.getRounds();

							if (t > 0 & (r == 0)) {
								t -= 1;
								it.setTurns(t);
							}// end if

							if (isTurnt == false) {
								r -= 1;
								it.setRounds(r);
								System.out.println("turns = " + t + " "
										+ "rounds = " + r);

							}
							isTurnt = false;
						}// end for
						System.out.println("IsTurn3 = " + isTurnt);

					}// end isTurns = false

				}// end empty test
					// update main panel

				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).getRounds() <= 0
							& items.get(i).getTurns() <= 0) {
						items.remove(i);
					}// end if

				}
				listUpdate();
				break;
			}// end case decrease
			}// end switch case actionthing
		}// end action performed
	}// end buttonlistener

	private class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent penis) {
			String radioAction = penis.getActionCommand();

			// radiobuttons that toggle whether you want rounds or turns
			switch (radioAction) {

			case "Turns": {
				isTurns = true;
				System.out.println("isTurns = " + isTurns);
				break;
			}
			case "Rounds": {
				isTurns = false;
				System.out.println("isTurns = " + isTurns);
				break;
			}
			}// end switch structure
		}// end action statement

	}// end actionperformed

	private void listUpdate() {
		// make temp array
		String detailList[] = new String[items.size()];

		// use selected indices to make temp matrix
		for (int k = 0; k < items.size(); k++) {
			detailList[k] = items.get(k).toString();
		}// end for

		// insert temp array into selectedMonthList
		selectedMonthList.setListData(detailList);
	}

	void buildFileMenu() {
		// make exit item
		exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuListener());
		fileMenu = new JMenu("File");
		fileMenu.add(exit);

		// makes jmenu object
		fileMenu = new JMenu("File");

		// add exit to menus
		fileMenu.add(exit);
	}

	void buildOptionMenu() {
		// make time editing items
		delItem = new JMenuItem("Delete");
		delItem.addActionListener(new MenuListener());
		helpItem = new JMenuItem("Help");
		helpItem.addActionListener(new MenuListener());
		editItem = new JMenuItem("Edit");
		editItem.addActionListener(new MenuListener());
		// make menu objects
		optionMenu = new JMenu("Options");

		// add shit to menu
		optionMenu.add(delItem);
		optionMenu.add(helpItem);
		optionMenu.add(editItem);
		// optionMenu.add(decrementTest);
	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent titties) {
			String tittyAction = titties.getActionCommand();

			// radiobuttons that toggle whether you want rounds or turns
			switch (tittyAction) {
			case "Exit": {
				System.exit(0);
				break;
			}// end exit case
			case "Delete": {
				int selections = (int) selectedMonthList.getSelectedIndex();
				// System.out.println("Index is: " + selections);
				items.remove(selections);
				listUpdate();
				break;
			}// end del case

			case "Help": {
				// end help case
				JOptionPane
						.showMessageDialog(
								null,
								"Select either 'turns' or 'rounds'. Once the desired time measurement is selected, \n click 'add stuff' to enter the name of the buff or item you are tracking.\n Once the name is entered, you will be prompted for the duration of the item or buff. \n When added, you can decrement the buff by either a turn or a round, or delete it from the list.");
				break;
			}// end help
			case "Edit": {

				// remove old entry
				int selections = (int) selectedMonthList.getSelectedIndex();

				// set net info
				String name, t, r;
				name = JOptionPane.showInputDialog("Enter Item Name");
				t = JOptionPane.showInputDialog("Enter # of turns");
				int turns = Integer.parseInt(t);
				r = JOptionPane.showInputDialog("Enter # of rounds");
				int rounds = Integer.parseInt(r);

				items.get(selections).setName(name);
				items.get(selections).setTurns(turns);
				items.get(selections).setRounds(rounds);

				listUpdate();

				break;
			}// end edit
			}// end switch titty

		}// end actionperformed

	}// end class

	public static void main(String[] args) {
		new TimeKeeper();
	}
}