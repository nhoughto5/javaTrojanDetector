package mainWindow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class main {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 828, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMessage = new JButton("message");
		btnMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(135, 85, 200, 50);
				String[] fileTypes = {"bit"};
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bit Files", fileTypes);
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new java.io.File("C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles"));
				fileChooser.showOpenDialog(null);
				JOptionPane.showMessageDialog(null, "You selected: " + fileChooser.getSelectedFile().getName());
			}
		});
		btnMessage.setBounds(333, 259, 89, 23);
		frame.getContentPane().add(btnMessage);
		

		//frame.getContentPane().add(fileChooser);
	}
}
