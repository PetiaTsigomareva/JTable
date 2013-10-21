package presentation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.GoodsTableModel;

import com.itextpdf.text.DocumentException;

public class GoodsFrame extends JFrame implements TableModelListener {
	private static final String DEFAULT_FILE_EXTENSION = "txt";

	private static final long serialVersionUID = 1L;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private JButton addButton = null;

	private JPanel buttonPanel = null;

	private JButton deleteButton = null;

	private JButton saveToFileButton = null;

	private JButton loadFromFileButton = null;

	private JButton exportToPDFButton = null;

	private JButton printButton = null;

	private GoodsTableModel tableModel = null;

	/**
	 * This is the default constructor
	 */
	public GoodsFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 320);
		this.setTitle("Goods Table");
		this.setLayout(new GridLayout(2, 1));

		this.add(getJScrollPane());
		this.add(getButtonPanel());
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(getTableModel());

			JTableColorRenderer leftRenderer = new JTableColorRenderer();
			leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

			JTableColorRenderer centerRenderer = new JTableColorRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

			JTableColorRenderer rightRenderer = new JTableColorRenderer();
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

			jTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
			jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			jTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		}

		return jTable;
	}

	/**
	 * This method initializes addButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton("Add");
			addButton.setSize(150, 20);
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showAddGoodDialog();
				}
			});
		}
		return addButton;
	}

	/**
	 * This method initializes buttonPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(25);

			buttonPanel = new JPanel();

			buttonPanel.setLayout(flowLayout);
			buttonPanel.setPreferredSize(new Dimension(100, 35));
			buttonPanel.add(getAddButton());
			buttonPanel.add(getDeleteButton());
			buttonPanel.add(getSaveToFileButton());
			buttonPanel.add(getLoadFromFileButton());
			buttonPanel.add(getExportToPDFButton());
			buttonPanel.add(getPrintButton());
		}

		return buttonPanel;
	}

	/**
	 * This method initializes deleteButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton("Delete");
			deleteButton.setSize(150, 20);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jTable.getSelectedRow() != -1) {
						getTableModel().deleteGood(jTable.getSelectedRow());
					}
				}
			});
		}
		return deleteButton;
	}

	/**
	 * This method initializes saveToFileButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSaveToFileButton() {
		if (saveToFileButton == null) {
			saveToFileButton = new JButton("Save");
			saveToFileButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					File renamedFile;

					int selectedOption = chooser.showSaveDialog(null);

					if (selectedOption == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();

						// add default file extension when is not provided
						String fileName = file.getName();
						int i = fileName.lastIndexOf('.');
						// no file extention
						if (i < 0) {
							renamedFile = new File(file.getParent(), file.getName() + "." + DEFAULT_FILE_EXTENSION);
							// no file extention but dot provided
						} else if (i > 0 && "".equals(fileName.substring(i + 1))) {
							renamedFile = new File(file.getParent(), file.getName() + DEFAULT_FILE_EXTENSION);
						} else {
							renamedFile = file;
						}

						try {
							getTableModel().storeToFile(renamedFile);
						} catch (FileNotFoundException fnfe) {
							JOptionPane.showMessageDialog(new JPanel(), "File not found caused by: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ioe) {
							JOptionPane.showMessageDialog(new JPanel(), ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}

					}
				}

			});
		}
		return saveToFileButton;
	}

	/**
	 * This method initializes loadFromFileButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getLoadFromFileButton() {
		if (loadFromFileButton == null) {
			loadFromFileButton = new JButton("Load");
			loadFromFileButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();

					int selectedOption = chooser.showOpenDialog(null);

					if (selectedOption == JFileChooser.APPROVE_OPTION) {
						File chosenFile = chooser.getSelectedFile();
						try {
							getTableModel().loadFromFile(chosenFile);
						} catch (FileNotFoundException fnfe) {
							JOptionPane.showMessageDialog(new JPanel(), "File not found caused by: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ioe) {
							JOptionPane.showMessageDialog(new JPanel(), ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(new JPanel(), "The file that you try to open is with incorrect format: " + nfe.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});
		}
		return loadFromFileButton;
	}

	/**
	 * This method initializes exportToPDFButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getExportToPDFButton() {
		if (exportToPDFButton == null) {
			exportToPDFButton = new JButton("Export to PDF");
			exportToPDFButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();

					int selectedOption = chooser.showSaveDialog(null);

					if (selectedOption == JFileChooser.APPROVE_OPTION) {
						File chosenFile = chooser.getSelectedFile();

						try {
							getTableModel().exportToPdf(chosenFile);
						} catch (FileNotFoundException fnfe) {
							JOptionPane.showMessageDialog(new JPanel(), "File not found caused by: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (DocumentException de) {
							JOptionPane.showMessageDialog(new JPanel(), "Error occured in the document writing: " + de.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
		return exportToPDFButton;
	}

	/**
	 * This method initializes printButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPrintButton() {
		if (printButton == null) {
			printButton = new JButton("Print");
			printButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						boolean complete = jTable.print();
						if (complete) {
							JOptionPane.showMessageDialog(new JPanel(), "Sent to Printer", "Info", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(new JPanel(), "Printing Canceled", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (PrinterException pe) {
						JOptionPane.showMessageDialog(new JPanel(), "Printing Failed caused by: " + pe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return printButton;
	}

	/**
	 * This method initializes GoodsTableModel
	 * 
	 * @return {@link GoodsTableModel}
	 */
	public GoodsTableModel getTableModel() {
		if (tableModel == null) {
			tableModel = new GoodsTableModel();
			tableModel.addTableModelListener(this);
		}
		return tableModel;
	}

	private void showAddGoodDialog() {
		AddGoodDialog add = new AddGoodDialog(this);
		add.setVisible(true);
	}

	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
	}

} // @jve:decl-index=0:visual-constraint="54,35"
