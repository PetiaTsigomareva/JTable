package presentation;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class JTableColorRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (isSelected) {
			result.setBackground(new Color(0, 191, 255));
		} else {
			if (row % 2 == 0) {
				result.setBackground(Color.white);
			} else {
				result.setBackground(new Color(205, 201, 201));// grey
			}
		}

		return result;
	}
}
