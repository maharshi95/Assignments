package assign3c;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DAO {
	
	private static DAO dao = null;
	
	public static DAO getInstance() {
		if(dao == null) {
			dao = new DAO();
		}
		return dao;
	}
	
	private DAO() {
		
	}
	
	public boolean insertCategories(Set<Category> catSet) {
		String query = "INSERT INTO Category(description) VALUES(?);";
		PreparedStatement stmt = null;
		Connection conn = null;
		boolean success = false;
		try {
			conn = DBUtils.getConnection();
			if(conn != null) {
				conn.setAutoCommit(false);
				stmt = conn.prepareStatement(query);
				for(Category cat : catSet) {
					stmt.clearParameters();
					stmt.setString(1, cat.getDescription());
					stmt.addBatch();
				}
				stmt.executeBatch();
				conn.commit();
				success = true;
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			DBUtils.close(stmt);
			DBUtils.close(conn);
		}
		
		return success;
	}
	
	public boolean insertAddresses(Set<Address> addSet) {
		String query = "INSERT INTO Address(street1,street2,city,state,country,zipcode,mobile) VALUES(?,?,?,?,?,?,?);";
		PreparedStatement stmt = null;
		Connection conn = null;
		boolean success = false;
		try {
			conn = DBUtils.getConnection();
			if(conn != null) {
				stmt = conn.prepareStatement(query);
				for(Address add : addSet) {
					stmt.clearParameters();
					stmt.setString(1, add.getStreet1());
					stmt.setString(2, add.getStreet2());
					stmt.setString(3, add.getCity());
					stmt.setString(4, add.getState());
					stmt.setString(5, add.getCountry());
					stmt.setString(6, add.getZipcode());
					stmt.setString(7, add.getMobile());
					stmt.addBatch();
				}
				stmt.executeBatch();
				success = true;
			}
		} catch (SQLException e) {	
		} finally {
			DBUtils.close(stmt);
			DBUtils.close(conn);
		}
		
		return success;
	}
	
	public boolean insertCustomers(Set<Customer> custSet) {
		String select_query = "SELECT address_code from Address where street1 = ?";
		String insert_query = "INSERT INTO Customer(email_id,company_name,first_name,last_name,contact,current_add_code) VALUES(?,?,?,?,?,?);";
		PreparedStatement insert_stmt = null;
		PreparedStatement select_stmt = null;
		Connection conn = null;
		boolean success = false;
		try {
			conn = DBUtils.getConnection();
			if(conn != null) {
//				conn.setAutoCommit(false);
				select_stmt = conn.prepareStatement(select_query);
				insert_stmt = conn.prepareStatement(insert_query);
				for(Customer cust : custSet) {
					select_stmt.clearParameters();
					select_stmt.setString(1, cust.getStreet1());
					ResultSet rs = select_stmt.executeQuery();
					rs.next();
					int add_code = rs.getInt(1);
					DBUtils.close(rs);
					
					insert_stmt.clearParameters();
					insert_stmt.setString(1, cust.getEmail_id());
					insert_stmt.setString(2, cust.getCompany_name());
					insert_stmt.setString(3, cust.getFirst_name());
					insert_stmt.setString(4, cust.getLast_name());
					insert_stmt.setString(5, cust.getContact());
					insert_stmt.setInt(6, add_code);
					insert_stmt.executeUpdate();
				}
//				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(insert_stmt);
			DBUtils.close(select_stmt);
			DBUtils.close(conn);
		}
		return success;
	}
	
	public boolean insertProducts(Set<Product> prodSet) {
		String select_query = "SELECT category_id from Category where description = ?";
		String insert_query = "INSERT INTO Product(product_code,category_id,name,description,buy_price,sell_price,quantity) VALUES(?,?,?,?,?,?,?);";
		PreparedStatement insert_stmt = null;
		PreparedStatement select_stmt = null;
		Connection conn = null;
		boolean success = false;
		try {
			conn = DBUtils.getConnection();
			if(conn != null) {
				conn.setAutoCommit(false);
				select_stmt = conn.prepareStatement(select_query);
				insert_stmt = conn.prepareStatement(insert_query);
				for(Product prod : prodSet) {
					select_stmt.clearParameters();
					select_stmt.setString(1, prod.cat_des);
					ResultSet rs = select_stmt.executeQuery();
					rs.next();
					int cat_code = rs.getInt(1);
					DBUtils.close(rs);
					
					insert_stmt.clearParameters();
					insert_stmt.setString(1, prod.getProduct_code());
					insert_stmt.setInt(2, cat_code);
					insert_stmt.setString(3, prod.getName());
					insert_stmt.setString(4, prod.getDescription());
					insert_stmt.setDouble(5, prod.getBuy_price());
					insert_stmt.setDouble(6, prod.getSell_price());
					insert_stmt.setInt(7, prod.getQuantity());
					insert_stmt.executeUpdate();
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(insert_stmt);
			DBUtils.close(select_stmt);
			DBUtils.close(conn);
		}
		return success;
	}
	
	public boolean insertOrders(Set<Order> oSet) throws ParseException {
		String select_query1 = "SELECT customer_id,company_name from Customer";
		String select_query2 = "SELECT product_id,product_code from Product";
		String select_query3 = "SELECT order_id, customer_id, time_created from Orders";
		String insert_query1 = "INSERT INTO Orders(customer_id,time_created,order_status,payment_mode,payment_status) VALUES(?,?,?,'online','done');";
		String insert_query2 = "INSERT INTO OrderDetails(order_id,product_id,quantity,selling_price) VALUES(?,?,?,?);";
		
		PreparedStatement insert_stmt1 = null;
		PreparedStatement insert_stmt2 = null;
		
		PreparedStatement select_stmt1 = null;
		PreparedStatement select_stmt2 = null;
		PreparedStatement select_stmt3 = null;
		
		Connection conn = null;
		boolean success = false;
		
		try {
			conn = DBUtils.getConnection();
			if(conn != null) {
				conn.setAutoCommit(false);
				select_stmt1 = conn.prepareStatement(select_query1);
				select_stmt2 = conn.prepareStatement(select_query2);
				select_stmt3 = conn.prepareStatement(select_query3);
				insert_stmt1 = conn.prepareStatement(insert_query1);
				insert_stmt2 = conn.prepareStatement(insert_query2);
				
				ResultSet rs1 = select_stmt1.executeQuery();
				ResultSet rs2 = select_stmt2.executeQuery();
				
				Map<String, Integer> cust_map = new HashMap<String,Integer>();
				Map<String, Integer> prod_map = new HashMap<String,Integer>();
				
				while(rs1.next()) {
					cust_map.put(rs1.getString(2), rs1.getInt(1));
				}
				
				while(rs2.next()) {
					prod_map.put(rs2.getString(2), rs2.getInt(1));
				}
				
				DBUtils.close(rs1);
				DBUtils.close(rs2);
				System.out.println("Select 1 2 done");
				int cnt = 0;
				for(Order o : oSet) {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Timestamp tstamp = new Timestamp(format.parse(o.getOrder_date() + " 00:00:00").getTime());
					
					insert_stmt1.clearParameters();
					insert_stmt1.setInt(1, cust_map.get(o.getCust_name()));
					insert_stmt1.setTimestamp(2, tstamp);
					insert_stmt1.setString(3, o.getStatus());
					insert_stmt1.addBatch();
					System.out.println(++cnt);
				}
				insert_stmt1.executeBatch();
				System.out.println("Insert 1 done");
				ResultSet rs3 = select_stmt3.executeQuery();
				Map<String,Integer> order_map = new HashMap<String,Integer>();
				while(rs3.next()) {
					order_map.put(rs3.getTimestamp(3).toString() + rs3.getInt(2), rs3.getInt(1));
				}
				DBUtils.close(rs3);
				System.out.println("Select 3 done");
				cnt = 0;
				for(Order o : oSet) {
					insert_stmt2.clearParameters();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Timestamp tstamp = new Timestamp(format.parse(o.getOrder_date() + " 00:00:00").getTime());
					Integer pid = prod_map.get(o.getProduct_code());
					insert_stmt2.setInt(1, order_map.get(tstamp.toString() + cust_map.get(o.getCust_name())));
					insert_stmt2.setInt(2, pid);
					insert_stmt2.setInt(3, o.getQuantity());
					insert_stmt2.setDouble(4, o.getPrice());
					insert_stmt2.addBatch();
					System.out.println(++cnt);
				}
				insert_stmt2.executeBatch();
				System.out.println("Insert 2 done");
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(insert_stmt1);
			DBUtils.close(insert_stmt2);
			DBUtils.close(select_stmt1);
			DBUtils.close(select_stmt2);
			DBUtils.close(select_stmt3);
			DBUtils.close(conn);
		}
		return success;
	}
	
}
