package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import utils.Util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GoodsTableModel extends AbstractTableModel {

	private static final String SEPARATOR = ",";

	private static final long serialVersionUID = 1L;

	private List<Good> goods = new ArrayList<Good>();

	private String[] ColumnsName = { "Name", "Category", "Price" };

	public GoodsTableModel() {
		super();
	}

	@Override
	public String getColumnName(int column) {
		return this.ColumnsName[column];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public int getColumnCount() {
		return Good.getColumnsCount();
	}

	public int getRowCount() {
		return goods.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Good good = goods.get(rowIndex);
		Object result;
		
		assert columnIndex >=0 && columnIndex <=2 : "Expected column index between 0 and 2, but encountered: "+ columnIndex;
		
		switch (columnIndex) {
		case 0:
			result = good.getName();
			break;
		case 1:
			result = good.getCategory();
			break;
		case 2:
			result = good.getPrice();
			break;
		default:
		  throw new IllegalArgumentException("Expected column index between 0 and 2, but encountered: "+ columnIndex);
		}
		
		return result;
	}

	public void setValueAt(Object value, int row, int col) {
		Good r = goods.get(row);
		assert col >=0 && col <=2 : "Expected column index between 0 and 2, but encountered: "+ col;
		
		switch (col) {
		case 0:
			r.setName((String) value);
			break;
		case 1:
			r.setCategory((String) value);
			break;
		case 2:
			try {
					r.setPrice(Util.parseDouble((String) value));
				} catch (Exception nfe) {
					JOptionPane.showMessageDialog(new JPanel(), nfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			break;
		default:
		  throw new IllegalArgumentException("Expected column index between 0 and 2, but encountered: "+ col);
		}
		fireTableCellUpdated(row, col);
	}

	public void addGood(Good good) {
		goods.add(good);
		fireTableDataChanged();
	}

	public void deleteGood(int rowIndex) {
		goods.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public List<Good> getGoods(){
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
	
	public void exportToPdf(File file) throws FileNotFoundException, DocumentException {
		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(createFirstTable());
		document.close();
	}

	private PdfPTable createFirstTable() {
		// a table with three columns
		PdfPTable table = new PdfPTable(getColumnCount());
		// the cell object
		PdfPCell cell;

		for (int i = 0; i < getColumnCount(); i++) {
			cell = new PdfPCell(new Phrase(getColumnName(i)));
			table.addCell(cell);
		}

		for (int i = 0; i < getRowCount(); i++) {
			cell = new PdfPCell(new Phrase(goods.get(i).getName()));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(goods.get(i).getCategory()));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase( "" + goods.get(i).getPrice()));
			table.addCell(cell);
		}

		return table;
	}

	public void storeToFile(File file) throws FileNotFoundException, IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < goods.size(); i++) {

			writer.write( quote( goods.get(i).getName() ) + SEPARATOR );
			writer.write( quote( goods.get(i).getCategory() ) + SEPARATOR );
			writer.write( quote( "" + goods.get(i).getPrice() ) );
			writer.newLine();
		}

		writer.close();
	}	

	public void loadFromFile(File file)throws FileNotFoundException,IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
	  String line;
	  String name;
	  String category;
	  double price;
	  Good good;
	  Matcher matcher;

	  goods = new ArrayList<Good>();

	  String regex = "\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"";
	  Pattern pattern = Pattern.compile(regex);

	  while ((line = reader.readLine()) != null) {
      // extract the columns from the line with regex

  		matcher = pattern.matcher(line);

  		if ( matcher.matches() ){
	  		name = matcher.group(1);
				category = matcher.group(2);
				price = Util.parseDouble(matcher.group(3));

	     	// add extracted columns to a object
	     	good = new Good();
			  good.setName(name);
		    good.setCategory(category);
		    good.setPrice(price);

	     	// add the object to the list
	     	goods.add(good);
  		}
    }

    reader.close();
    fireTableDataChanged();
	}

	private String quote( String string ){
		return "\"" + string + "\"";
	}
}