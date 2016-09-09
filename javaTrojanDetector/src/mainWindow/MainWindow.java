package mainWindow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import TrojanDetector.TrojanDetector;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class MainWindow {

	private JFrame frame;
	private TrojanDetector trojanDetector;
	File targetBitFile, goldenBitFile;
	JTextArea trojanTextArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		trojanDetector = new TrojanDetector();
		frame.setBounds(100, 100, 828, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JButton btnBrowse_Golden = new JButton("Browse");
		btnBrowse_Golden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(135, 85, 200, 50);
				String[] fileTypes = {"bit"};
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bit Files", fileTypes);
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new java.io.File("C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles"));
				fileChooser.showOpenDialog(null);
				goldenBitFile = fileChooser.getSelectedFile();
				//JOptionPane.showMessageDialog(null, "You selected: " + fileChooser.getSelectedFile().getName());
			}
		});
		btnBrowse_Golden.setBounds(154, 25, 89, 23);
		frame.getContentPane().add(btnBrowse_Golden);
		
		JButton btnBrowse_Target = new JButton("Browse");
		btnBrowse_Target.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(135, 85, 200, 50);
				String[] fileTypes = {"bit"};
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bit Files", fileTypes);
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new java.io.File("C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles"));
				fileChooser.showOpenDialog(null);
				targetBitFile = fileChooser.getSelectedFile();
			}
		});
		btnBrowse_Target.setBounds(154, 59, 89, 23);
		frame.getContentPane().add(btnBrowse_Target);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 178, 792, 337);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Trojan Detector", null, panel_1, null);
		panel_1.setLayout(null);
		
		trojanTextArea = new JTextArea();
		trojanTextArea.setEditable(false);
		trojanTextArea.setBounds(10, 40, 242, 258);
		trojanTextArea.setLineWrap(true);
		trojanTextArea.setWrapStyleWord(true);
		panel_1.add(trojanTextArea);
		
		JButton btnFindModifiedFrames = new JButton("Find Modified Frames");
		btnFindModifiedFrames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(targetBitFile != null && goldenBitFile != null){
					trojanDetector.performDetection(goldenBitFile, targetBitFile);
				}
				else if(targetBitFile == null && goldenBitFile != null){
					trojanTextArea.setText("Error: Please select a target bit file.");
				}
				else if(targetBitFile != null && goldenBitFile == null){
					trojanTextArea.setText("Error: Please select a golden chip bit file.");
				}
				else{
					trojanTextArea.setText("Error: Please select a target and golden bit file.");
				}
			}
		});
		btnFindModifiedFrames.setBounds(10, 11, 153, 23);
		panel_1.add(btnFindModifiedFrames);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Synthesizer", null, panel, null);
		
		JLabel lblSelectGoldenChip = new JLabel("Select Golden Chip Bit File:");
		lblSelectGoldenChip.setBounds(10, 29, 134, 14);
		frame.getContentPane().add(lblSelectGoldenChip);
		
		JLabel lblSelectTargetBit = new JLabel("Select Target Bit File:");
		lblSelectTargetBit.setBounds(10, 63, 134, 14);
		frame.getContentPane().add(lblSelectTargetBit);
	}
}
