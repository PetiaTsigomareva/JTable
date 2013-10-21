package presentation;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import sun.misc.Launcher;
import java.awt.Dimension;

public class TestDialod extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JTextField nameField= new JTextField();  //  @jve:decl-index=0:visual-constraint="359,35"

	private JTextField ageField = new JTextField();  //  @jve:decl-index=0:visual-constraint="358,59"

	private JTextField maleField= new JTextField();  //  @jve:decl-index=0:visual-constraint="360,85"

	private JButton okButton;  //  @jve:decl-index=0:visual-constraint="739,7"

	private JButton cancelButton;  //  @jve:decl-index=0:visual-constraint="742,51"

	/**
	 * @param owner
	 */
	public TestDialod(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Petia");
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

			JLabel name = new JLabel("name:");
			name.setSize(50, 20);
			JLabel age = new JLabel("age:");
			name.setSize(50, 20);
			JLabel male = new JLabel("male:");
			name.setSize(50, 20);
			layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(name).addComponent(age).addComponent(male)).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(getNameField()).addComponent(getAgeField()).addComponent(getMaleField())).addGroup(
					layout.createSequentialGroup().addComponent(okButton).addComponent(cancelButton)));
			
			layout.setVerticalGroup(layout.createSequentialGroup().addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(name).addComponent(getNameField())).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(age).addComponent(getAgeField())).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(male).addComponent(getMaleField())).addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(getCancelButton()).addComponent(getOkButton())));

			layout.linkSize(SwingConstants.HORIZONTAL, getCancelButton(), getOkButton());

		}
		return jContentPane;
	}

	/**
	 * This method initializes getOKTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getNameField() {
		if (nameField == null) {
			nameField = new JTextField();
			nameField.setSize(200, 20);
		}
		return nameField;
	}

	private JTextField getAgeField() {
		if (ageField == null) {
			ageField = new JTextField();
			ageField.setSize(200, 20);
		}
		return ageField;
	}

	private JTextField getMaleField() {
		if (maleField == null) {
			maleField = new JTextField();
			maleField.setSize(200, 20);
		}
		return maleField;
	}

	/**
	 * This method initializes getOkButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("OK");
			okButton.setSize(80, 20);
		}
		return okButton;
	}

	/**
	 * This method initializes getCancelButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.setSize(80, 20);
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
