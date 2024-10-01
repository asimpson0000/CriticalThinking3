import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.io.Serializable;

public class CriticalThinking extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private Random random;

	public CriticalThinking() {
		random = new Random();

		setTitle("Critical Thinking CSC372");
		setSize(400, 200);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);

		textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");

		JMenuItem dateTimeItem = new JMenuItem("Show Date & Time");
		dateTimeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDateTime();
			}
		});

		JMenuItem logToFileItem = new JMenuItem("Log to File");
		logToFileItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logToFile();
			}
		});

		JMenuItem changeColorItem = new JMenuItem("Change Background Color");
		changeColorItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackgroundColor();
			}
		});

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(dateTimeItem);
		menu.add(logToFileItem);
		menu.add(changeColorItem);
		menu.add(exitItem);
		menuBar.add(menu);

		setJMenuBar(menuBar);
	}

	private void showDateTime() {
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		textArea.setText(currentTime);
	}

	private void logToFile() {
		String content = textArea.getText().trim();
		if (!content.isEmpty()) {
			try (FileWriter writer = new FileWriter("log.txt", true)) {
				writer.write(content + "\n");
				JOptionPane.showMessageDialog(this, "Content written to log.txt");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this, "Text area is empty.");
		}
	}

	private void changeBackgroundColor() {
		System.out.println("Changing the Background color to...");

		int greenHue = random.nextInt(256);
		int blue = random.nextInt(128);
		Color color = new Color(0, greenHue, blue);
		getContentPane().setBackground(color);

		System.out.println("New Color: " + color);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CriticalThinking ui = new CriticalThinking();
			ui.setVisible(true);
		});
	}
}
