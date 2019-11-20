import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Christian Wance 012306864
 * 
 *         Makes a TaskList class that extends JFram and can hold a Heap of
 *         Tasks
 */
public class Tasklist extends JFrame {

	/**
	 * @author Christian Wance 012306864
	 * 
	 *         Makes a Panel class that extends JPanel
	 */
	class Panel extends JPanel implements ActionListener {
		private JTextField tNInfo, nameInfo, yearPInfo, monthPInfo, dayPInfo, hourPInfo, minPInfo, yearAInfo,
				monthAInfo, dayAInfo, hourAInfo, minAInfo, addNameInfo, compByInfo;
		private JLabel lblProcess, lblTaskNum, lblTaskName, lblYear, lblMonth, lblDay, lblHour, lblMin, addName,
				lblCompBy;

		private JButton btnPost, btnComp, btnAdd, btnSubPost, btnSubAdd, btnQuit;
		private JPanel pAdd, pPost, pTask, pButton, pQuit;

		private boolean switching;

		private Task task;
		private Heap<Task> heap;

		/**
		 * Constructor
		 * 
		 * @param h Heap h
		 */
		Panel(Heap<Task> h) {
			heap = h;
			JPanel top = new JPanel();
			pTask = new JPanel();
			pButton = new JPanel();

			tNInfo = new JTextField("", 10);
			nameInfo = new JTextField("", 15);

			compByInfo = new JTextField("", 15);

			yearPInfo = new JTextField("", 5);
			monthPInfo = new JTextField("", 5);
			dayPInfo = new JTextField("", 5);
			hourPInfo = new JTextField("", 5);
			minPInfo = new JTextField("", 5);
			nextTask();
			lblProcess = new JLabel("");
			lblTaskNum = new JLabel("Tasks left:");
			lblTaskName = new JLabel("Current Task: ");
			lblCompBy = new JLabel("Complete By: ");
			btnPost = new JButton("Postpone");
			btnComp = new JButton("Completed!");
			btnPost.addActionListener(this);
			btnAdd = new JButton("Add Task");
			btnAdd.addActionListener(this);
			btnSubPost = new JButton("Submit");
			btnSubPost.addActionListener(this);
			btnSubAdd = new JButton("Submit");
			btnSubAdd.addActionListener(this);
			btnComp.addActionListener(this);
			pTask.add(lblTaskNum);
			pTask.add(tNInfo);
			pTask.add(lblTaskName);
			pTask.add(nameInfo);
			pTask.add(lblCompBy);
			pTask.add(compByInfo);
			pTask.setLayout(new GridLayout(0, 2));
			top.add(pTask);
			pButton.add(btnComp);
			pButton.add(btnPost);
			pButton.add(btnAdd);
			pButton.setLayout(new FlowLayout());
			top.add(pButton);
			JPanel title = new JPanel();
			title.add(lblProcess);
			top.add(title);
			pAdd = new JPanel();
			addNameInfo = new JTextField("", 5);
			addName = new JLabel("Task Name: ");
			yearAInfo = new JTextField("", 5);
			lblYear = new JLabel("Year: ");
			monthAInfo = new JTextField("", 5);
			lblMonth = new JLabel("Month: ");
			dayAInfo = new JTextField("", 5);
			lblDay = new JLabel("Day: ");
			hourAInfo = new JTextField("", 5);
			lblHour = new JLabel("Hour: ");
			minAInfo = new JTextField("", 5);
			lblMin = new JLabel("Minute: ");
			pAdd.setLayout(new GridLayout(0, 4));
			pAdd.add(addName);
			pAdd.add(addNameInfo);
			pAdd.add(lblYear);
			pAdd.add(yearAInfo);
			pAdd.add(lblMonth);
			pAdd.add(monthAInfo);
			pAdd.add(lblDay);
			pAdd.add(dayAInfo);
			pAdd.add(lblHour);
			pAdd.add(hourAInfo);
			pAdd.add(lblMin);
			pAdd.add(minAInfo);
			pAdd.add(btnSubPost);

			top.add(pAdd);

			pPost = new JPanel();
			JLabel b1 = new JLabel("");
			JLabel b2 = new JLabel("");
			lblYear = new JLabel("Year: ");
			lblMonth = new JLabel("Month: ");
			lblDay = new JLabel("Day: ");
			lblHour = new JLabel("Hour: ");
			lblMin = new JLabel("Minute: ");

			pPost.add(lblYear);
			pPost.add(yearPInfo);
			pPost.add(lblMonth);
			pPost.add(monthPInfo);
			pPost.add(lblDay);
			pPost.add(dayPInfo);
			pPost.add(lblHour);
			pPost.add(hourPInfo);
			pPost.add(lblMin);
			pPost.add(minPInfo);
			pPost.add(b1);
			pPost.add(b2);
			pPost.add(btnSubAdd);

			pPost.setLayout(new GridLayout(0, 4));
			top.add(pPost);
			top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
			add(top);
			JPanel bottom = new JPanel();
			JPanel empty = new JPanel();
			bottom.setLayout(new GridLayout(0, 2));
			bottom.add(empty);
			pAdd.setVisible(false);
			pPost.setVisible(false);
			btnAdd.setVisible(true);
			btnPost.setVisible(false);
			btnQuit = new JButton("Quit");
			btnQuit.addActionListener(this);
			pQuit = new JPanel();
			pQuit.setLayout(new BorderLayout());
			pQuit.add(btnQuit, BorderLayout.PAGE_END);
			bottom.add(pQuit, BorderLayout.LINE_END);
			add(bottom);

		}

		/**
		 * Checks what action was taken with the button
		 */
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if (s.equals("Add Task")) {

				pPost.setVisible(false);
				pAdd.setVisible(true);
				btnPost.setVisible(true);
				btnAdd.setVisible(false);
				lblProcess.setText("Add Task");
				switching = true;

			} else if (s.equals("Postpone")) {
				if (heap.size() != 0) {
					pPost.setVisible(true);
					pAdd.setVisible(false);
					btnPost.setVisible(false);
					btnAdd.setVisible(true);
					lblProcess.setText("Postpone Task");
					switching = false;
				}
			} else if (s.equals("Submit") && !switching) {
				heap.removeItem();
				heap.addItem(new Task(nameInfo.getText(), yearPInfo.getText(), monthPInfo.getText(), dayPInfo.getText(),
						hourPInfo.getText(), minPInfo.getText()));
				nextTask();

			} else if (s.equals("Submit") && switching) {
				if (heap.size() != 0) {
					heap.removeItem();
					heap.addItem(task);
					heap.addItem(new Task(addNameInfo.getText(), yearAInfo.getText(), monthAInfo.getText(),
							dayAInfo.getText(), hourAInfo.getText(), minAInfo.getText()));
					nextTask();
				} else {
					heap.addItem(new Task(addNameInfo.getText(), yearAInfo.getText(), monthAInfo.getText(),
							dayAInfo.getText(), hourAInfo.getText(), minAInfo.getText()));
					nextTask();

				}

			} else if (s.equals("Completed!")) {
				if (heap.size() != 0) {
					heap.removeItem();
					nextTask();
				}
			} else if (s.equals("Quit")) {
				if (heap.size() != 0) {
					try {
						PrintWriter write = new PrintWriter("taskList.txt");
						System.out.println(task.toString());
						while (heap.size() != 0) {
							write.println(heap.removeItem().toString());
							System.out.println();

						}
						write.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						PrintWriter write = new PrintWriter("taskList.txt");
						write.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				System.exit(0);
			}

		}

		/**
		 * Sets the next task from the heap
		 */
		private void nextTask() {
			if (heap.size() != 0) {
				task = (Task) heap.getCurrent();
				tNInfo.setText("" + heap.size());
				compByInfo.setText(task.getDate().toString());
				nameInfo.setText(task.getName());
				compByInfo.setText(task.getDate().toString());
				yearPInfo.setText(task.getDate().getYear());
				monthPInfo.setText(task.getDate().getMonth());
				dayPInfo.setText(task.getDate().getDay());
				hourPInfo.setText(task.getDate().getHour());
				minPInfo.setText(task.getDate().getMinute());
			} else {
				tNInfo.setText("0");

				nameInfo.setText("");
				compByInfo.setText("");
				yearPInfo.setText("");
				monthPInfo.setText("");
				dayPInfo.setText("");
				hourPInfo.setText("");
				minPInfo.setText("");

			}
		}

	}

	public static void main(String[] args) {
		Heap<Task> heap = new Heap<Task>();
		try {
			Scanner in = new Scanner(new File("taskList.txt"));
			while (in.hasNext()) {
				String[] vars = in.nextLine().split(",");
				Task task = new Task(vars[0], vars[1]);
				heap.addItem(task);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Tasklist t = new Tasklist(heap);
	}

	/**
	 * Constructor
	 * 
	 * @param h Heap h
	 */
	public Tasklist(Heap h) {
		Panel tn = new Panel(h);
		setBounds(100, 100, 500, 500);
		setTitle("Drawing Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(tn);
		setVisible(true);

	}
}