package assign3c;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.opencsv.CSVReader;

public class Controller {
	
	private static String[][] dataArray;
	private static final String dataFilename = "data.csv";
	private static DAO dao  = DAO.getInstance();
	
	public static void main(String[] args) throws ParseException {
		
		Controller reader = new Controller();
		reader.loadData(dataFilename);
		// all tables loaded here	
	}
	
	private void loadData(String datafile) {
		CSVReader reader = null;
		List<String[]> myEntries = null;
		try {
			reader = new CSVReader(new FileReader(datafile));
			myEntries = reader.readAll();
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] row = myEntries.get(0);
		int m = myEntries.size()-1;
		int n = row.length;
		dataArray = new String[m][n];
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				dataArray[i][j] = myEntries.get(i+1)[j].trim();
			}
		}
	}
	
	public Set<Address> getSetsFromCSV() {
		Set<Address> addSet = new TreeSet<Address>();
		for(String[] row : dataArray) {
			Address add = new Address(row);
			addSet.add(add);
		}
		return addSet;
	}
	
	public Set<Customer> getCustomersFromCSV() {
		Set<Customer> custSet = new TreeSet<Customer>();
		for(String[] row: dataArray) {
			Customer cust = new Customer(row);
			custSet.add(cust);
		}
		return custSet;
	}
	
	public Set<Category> getCategoriesFromCSV() {
		Set<Category> catSet = new TreeSet<Category>();
		for(String[] row: dataArray) {
			Category cat = new Category(row);
			catSet.add(cat);
		}
		return catSet;
	}
	
	public Set<Product> getProductsFromCSV() {
		Set<Product> pSet = new TreeSet<Product>();
		for(String[] row: dataArray) {
			Product p = new Product(row);
			pSet.add(p);
		}
		return pSet;
	}
	
	public Set<Order> getOrdersFromCSV() {
		Set<Order> oset = new TreeSet<Order>();
		for(String[] row: dataArray) {
			Order o = Order.parseOrder(row);
			if(o != null) oset.add(o);
		}
		return oset;
	}
}