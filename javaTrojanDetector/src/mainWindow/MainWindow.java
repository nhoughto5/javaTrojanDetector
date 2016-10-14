package mainWindow;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import TrojanDetector.TrojanDetector;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainWindow {

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	File targetBitFile, goldenBitFile, xdlFile;
	private TrojanDetector trojanDetector;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		// this.trojanDetector = new TrojanDetector(messageArea);
		this.frame.setBounds(100, 100, 828, 565);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final JButton btnBrowse_Golden = new JButton("Browse");
		btnBrowse_Golden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(135, 85, 200, 50);
				final String[] fileTypes = { "bit" };
				final FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Bit Files", fileTypes);
				fileChooser.setFileFilter(filter);
				fileChooser
						.setCurrentDirectory(new java.io.File(
								"C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles"));
				fileChooser.showOpenDialog(null);
				MainWindow.this.goldenBitFile = fileChooser.getSelectedFile();
				// JOptionPane.showMessageDialog(null, "You selected: " +
				// fileChooser.getSelectedFile().getName());
			}
		});
		btnBrowse_Golden.setBounds(154, 25, 89, 23);
		this.frame.getContentPane().add(btnBrowse_Golden);

		final JButton btnBrowse_Target = new JButton("Browse");
		btnBrowse_Target.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(135, 85, 200, 50);
				final String[] fileTypes = { "bit" };
				final FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Bit Files", fileTypes);
				fileChooser.setFileFilter(filter);
				fileChooser
						.setCurrentDirectory(new java.io.File(
								"C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles"));
				fileChooser.showOpenDialog(null);
				MainWindow.this.targetBitFile = fileChooser.getSelectedFile();
			}
		});
		btnBrowse_Target.setBounds(154, 59, 89, 23);
		this.frame.getContentPane().add(btnBrowse_Target);

		final JLabel lblSelectGoldenChip = new JLabel(
				"Select Golden Chip Bit File:");
		lblSelectGoldenChip.setBounds(10, 29, 134, 14);
		this.frame.getContentPane().add(lblSelectGoldenChip);

		final JLabel lblSelectTargetBit = new JLabel("Select Target Bit File:");
		lblSelectTargetBit.setBounds(10, 63, 134, 14);
		this.frame.getContentPane().add(lblSelectTargetBit);

		final JButton btnBrowese_XDL = new JButton("Browse");
		btnBrowese_XDL.setBounds(154, 93, 89, 23);
		this.frame.getContentPane().add(btnBrowese_XDL);

		final JLabel lblSelectGoldenXdl = new JLabel("Select Golden XDL File:");
		lblSelectGoldenXdl.setBounds(10, 97, 134, 14);
		this.frame.getContentPane().add(lblSelectGoldenXdl);

		final JButton btnMapTilesToDesign = new JButton("Map");
		btnMapTilesToDesign.setBounds(154, 133, 89, 23);
		this.frame.getContentPane().add(btnMapTilesToDesign);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 792, 325);
		frame.getContentPane().add(scrollPane);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setLineWrap(true);
		scrollPane.setViewportView(messageArea);
		this.trojanDetector = new TrojanDetector(messageArea);
		
		btnMapTilesToDesign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				MainWindow.this.goldenBitFile = new File(
						"C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles/aes/aes_T100Clean.bit");
				MainWindow.this.targetBitFile = new File(
						"C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles/aes/aes_T100Trojan.bit");
				MainWindow.this.xdlFile = new File(
						"C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles/aes/aes_128.xdl");
				MainWindow.this.trojanDetector
						.loadDesign(MainWindow.this.xdlFile);
				// goldenBitFile = new
				// File("C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles/itemDefault.bit");
				// targetBitFile = new
				// File("C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles/itemMod.bit");
				MainWindow.this.trojanDetector.performDetection(
						MainWindow.this.goldenBitFile,
						MainWindow.this.targetBitFile);
				// if(targetBitFile != null && goldenBitFile != null){
				// trojanDetector.performDetection(goldenBitFile,
				// targetBitFile);
				// }
				// else if(targetBitFile == null && goldenBitFile != null){
				// trojanTextArea.setText("Error: Please select a target bit file.");
				// }
				// else if(targetBitFile != null && goldenBitFile == null){
				// trojanTextArea.setText("Error: Please select a golden chip bit file.");
				// }
				// else{
				// trojanTextArea.setText("Error: Please select a target and golden bit file.");
				// }

				MainWindow.this.trojanDetector.mapModifiedTilesToDesign();
			}
		});
		btnBrowese_XDL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(135, 85, 200, 50);
				final String[] fileTypes = { "xdl" };
				final FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XDL Files", fileTypes);
				fileChooser.setFileFilter(filter);
				fileChooser
						.setCurrentDirectory(new java.io.File(
								"C:/Users/Nick/Desktop/NickTop/HomeWork/MASc/Virtex5/bitFiles"));
				fileChooser.showOpenDialog(null);
				MainWindow.this.xdlFile = fileChooser.getSelectedFile();
				MainWindow.this.trojanDetector
						.loadDesign(MainWindow.this.xdlFile);
			}
		});
	}
}
