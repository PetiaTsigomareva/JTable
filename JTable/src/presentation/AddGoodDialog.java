package presentation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Good;
import utils.Util;

public class AddGoodDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JTextField nameFild = new JTextField();

	private JTextField categoryFild = new JTextField();

	private JTextField priceField = new JTextField();

	private JButton okButton = null; // @jve:decl-index=0:visual-constraint="704,120"

	private JButton cancelButton = null; // @jve:decl-index=0:visual-constraint="722,176"

	/**
	 * @param owner
	 */
	public AddGoodDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Add Good");
		this.setSize(250, 150);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();

			GroupLayout layout = new GroupLayout(jContentPane);
			jContentPane.setLayout(layout);

			layout.setAutoCreateContainerGaps(true);
			layout.setAutoCreateGaps(true);

			JLabel name = new JLabel("Name:");
			name.setSize(50, 20);
			JLabel category = new JLabel("Category:");
			name.setSize(50, 20);
			JLabel price = new JLabel("Price:");
			name.setSize(50, 20);

			// Horizontal Dimention X -->
			layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(name).addComponent(category).addComponent(price)).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(getNameFild()).addComponent(getCategoryFild()).addComponent(getPriceField())
							.addGroup(layout.createSequentialGroup().addComponent(getCancelButton()).addComponent(getOkButton()))));

			// Vertical Dimention Y
			layout.setVerticalGroup(layout.createSequentialGroup().addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(name).addComponent(getNameFild())).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(category).addComponent(getCategoryFild())).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(price).addComponent(getPriceField())).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(getCancelButton()).addComponent(getOkButton())));

			layout.linkSize(SwingConstants.HORIZONTAL, getCancelButton(), getOkButton());
		}
		return jContentPane;
	}

	/**
	 * This method initializes nameFild
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getNameFild() {
		if (nameFild == null) {
			nameFild = new JTextField();
			nameFild.setSize(200, 20);
		}
		return nameFild;
	}

	/**
	 * This method initializes categoryFild
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getCategoryFild() {
		if (categoryFild == null) {
			categoryFild = new JTextField();
			categoryFild.setSize(200, 20);
		}
		return categoryFild;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getPriceField() {
		if (priceField == null) {
			priceField = new JTextField();
			priceField.setSize(200, 20);
		}
		return priceField;
	}

	/**
	 * This method initializes ÓÍButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("OK");
			okButton.setSize(80, 20);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Good good = new Good();
					Double validatedPrice;

					good.setName(getNameFild().getText());
					good.setCategory(getCategoryFild().getText());

					try {
						validatedPrice = Util.parseDouble(getPriceField().getText());

						good.setPrice(validatedPrice);
						((GoodsFrame) getOwner()).getTableModel().addGood(good);
						hideAddGoodDialog();
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(new JPanel(), nfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes cancelButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hideAddGoodDialog();
				}
			});
		}
		return cancelButton;
	}

	private void hideAddGoodDialog() {
		this.setVisible(false);
	}
}