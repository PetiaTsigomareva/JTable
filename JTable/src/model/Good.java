package model;

public class Good {
	private String name;

	private String category;

	private double price;

	public Good(String name, String category, double price) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public Good() {
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static int getColumnsCount() {
		return 3;
	}
}