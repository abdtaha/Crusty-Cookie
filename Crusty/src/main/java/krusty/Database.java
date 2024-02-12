package krusty;

import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static krusty.Jsonizer.toJson;

public class Database {

	/**
	 * Modify it to fit your environment and then use this string when connecting to
	 * your database!
	 */
	private static final String jdbcString = "jdbc:mysql://puccini.cs.lth.se/db01";

	// For use with MySQL or PostgreSQL
	private static final String jdbcUsername = "db01";
	private static final String jdbcPassword = "lku223jd";
	private Connection conn;

	public Database() {
		conn = null;
	}

	public void connect() {

		try {
			conn = DriverManager.getConnection(jdbcString, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;

		System.err.println("Database connection closed.");
	}

	// TODO: Implement and change output in all methods below!

	public String getCustomers(Request req, Response res) {
		String json = "";
		String sql = "SELECT * FROM Customers";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			json = Jsonizer.toJson(rs, "customers");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return json;
	}

	public String getRawMaterials(Request req, Response res) {
		String json = "";
		String sql = "SELECT ingredientName AS name, storedAmount AS amount, unit" + " FROM Raw_materials";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			json = Jsonizer.toJson(rs, "raw-materials");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return json;
	}

	public String getCookies(Request req, Response res) {
		String json = "";
		String sql = "SELECT cookieName AS name FROM Cookies";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			json = Jsonizer.toJson(rs, "cookies");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	public String getRecipes(Request req, Response res) {
		String json = "";
		String sql = "SELECT cookieName AS cookie, Raw_materials.ingredientName AS raw_material, amount, unit"
				+ "FROM Recipes" + "INNER JOIN Raw_materials on Raw_materials.ingredientName = Recipes.ingredientName";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			json = Jsonizer.toJson(rs, "recipes");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	public String getPallets(Request req, Response res) {

		ArrayList<String> values = new ArrayList<String>();
		String sql = "SELECT palletId AS id, cookieName AS cookie,"
				+ "proDate AS production_date, name, IF(blocked, 'yes', 'no')as blocked"
				+ " FROM Pallets LEFT JOIN Orders ON Orders.orderId = Pallets.orderId" + " WHERE TRUE ";

		if (req.queryParams("cookie") != null) {
			sql += " AND cookieName = ?";
			values.add(req.queryParams("cookie"));
		}
		if (req.queryParams("from") != null) {
			sql += "AND proDate >= ?";
			values.add(req.queryParams("from"));
		}
		if (req.queryParams("to") != null) {
			sql += " AND proDate <= ?";
			values.add(req.queryParams("to"));
		}

		if (req.queryParams("blocked") != null) {
			sql += "blocked = ?";
			values.add(req.queryParams("blocked"));
		}

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			for (int i = 0; i < values.size(); i++) {
				ps.setString(i + 1, values.get(i));
			}
			ResultSet rs = ps.executeQuery();
			String json = Jsonizer.toJson(rs, "pallets");
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
			return Jsonizer.anythingToJson("error", "status");
		}
	}

	public String reset(Request req, Response res) {

		try (PreparedStatement ps = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0")) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] tables = { "Raw_materials", "Cookies", "Recipes", "Customers", "Orders", "Pallets" };
		for (String tableName : tables) {
			try (PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE " + tableName)) {
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		addCustomer("Bjudkakor AB", "Ystad");
		addCustomer("Finkakor AB", "Helsingborg");
		addCustomer("Gästkakor AB", "Hässleholm");
		addCustomer("Kaffebröd AB", "Landskrona");
		addCustomer("Kalaskakor AB", "Trelleborg");
		addCustomer("Partykakor AB", "Kristianstad");
		addCustomer("Skånekakor AB", "Perstorp");
		addCustomer("Småbröd AB", "Malmö");

		addCookie("Almond delight");
		addCookie("Amneris");
		addCookie("Berliner");
		addCookie("Nut cookie");
		addCookie("Nut ring");
		addCookie("Tango");

		addRaw_material("Bread crumbs", 500000, "g");
		addRaw_material("Butter", 500000, "g");
		addRaw_material("Chocolate", 500000, "g");
		addRaw_material("Chopped almonds", 500000, "g");
		addRaw_material("Cinnamon", 500000, "g");
		addRaw_material("Egg whites", 500000, "ml");
		addRaw_material("Eggs", 500000, "g");
		addRaw_material("Fine-ground nuts", 500000, "g");
		addRaw_material("Flour", 500000, "g");
		addRaw_material("Ground, roasted nuts", 500000, "g");
		addRaw_material("Icing sugar", 500000, "g");
		addRaw_material("Marzipan", 500000, "g");
		addRaw_material("Potato starch", 500000, "g");
		addRaw_material("Roasted, chopped nuts", 500000, "g");
		addRaw_material("Sodium bicarbonate", 500000, "g");
		addRaw_material("Sugar", 500000, "g");
		addRaw_material("Vanilla sugar", 500000, "g");
		addRaw_material("Vanilla", 500000, "g");
		addRaw_material("Wheat flour", 500000, "g");

		addRecipe("Almond delight", "Butter", 400);
		addRecipe("Almond delight", "Chopped almonds", 279);
		addRecipe("Almond delight", "Cinnamon", 10);
		addRecipe("Almond delight", "Flour", 400);
		addRecipe("Almond delight", "Sugar", 270);
		addRecipe("Amneris", "Butter", 250);
		addRecipe("Amneris", "Eggs", 250);
		addRecipe("Amneris", "Marzipan", 750);
		addRecipe("Amneris", "Potato starch", 25);
		addRecipe("Amneris", "Wheat flour", 25);
		addRecipe("Berliner", "Butter", 250);
		addRecipe("Berliner", "Chocolate", 50);
		addRecipe("Berliner", "Eggs", 50);
		addRecipe("Berliner", "Flour", 350);
		addRecipe("Berliner", "Icing sugar", 100);
		addRecipe("Berliner", "Vanilla sugar", 5);
		addRecipe("Nut cookie", "Bread crumbs", 125);
		addRecipe("Nut cookie", "Chocolate", 50);
		addRecipe("Nut cookie", "Egg whites", 350);
		addRecipe("Nut cookie", "Fine-ground nuts", 750);
		addRecipe("Nut cookie", "Ground, rousted nuts", 625);
		addRecipe("Nut cookie", "Sugar", 375);
		addRecipe("Nut ring", "Butter", 450);
		addRecipe("Nut ring", "Flour", 450);
		addRecipe("Nut ring", "Icing sugar", 190);
		addRecipe("Nut ring", "Roasted, chopped nuts", 225);
		addRecipe("Tango", "Butter", 200);
		addRecipe("Tango", "Flour", 300);
		addRecipe("Tango", "Sodium bicarbonate", 4);
		addRecipe("Tango", "Sugar", 250);
		addRecipe("Tango", "Vanilla", 2);

		try (PreparedStatement ps = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Jsonizer.anythingToJson("ok", "status");

	}

	public String createPallet(Request req, Response res) {
		String status = "";
		Map<String, Integer> recepie = getRecipe(req.queryParams("cookie"));
		if (cookieCheck(req)) {

			for (String materialRequired : recepie.keySet()) {
				try {
					PreparedStatement ps = conn.prepareStatement(
							"UPDATE Raw_materials SET storedAmount = storedAmount - ? WHERE ingredientName = ?");
					ps.setInt(1, recepie.get(materialRequired) * (15 * 10 * 36 / 100));
					ps.setString(2, materialRequired);
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				String sql = "INSERT INTO Pallets(proDate, blocked, cookieName) VALUES (?,?,?)";
				try (PreparedStatement ps = conn.prepareStatement(sql)) {

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String date = formatter.format(new Date());
					ps.setString(1, date);
					ps.setBoolean(2, false);
					ps.setString(3, req.queryParams("cookie"));
					ps.executeUpdate();
					status += Jsonizer.anythingToJson("ok", "status");
				}
				try (PreparedStatement ps = conn.prepareStatement("select last_insert_id()")) {
					ResultSet rs = ps.executeQuery();
					rs.next();
					status += Jsonizer.anythingToJson(rs.getInt(1), "id");
				}
				return status;
			} catch (SQLException e) {
				e.printStackTrace();
				return Jsonizer.anythingToJson("unknown cookie", "status");
			}

		}
		return status;
	}

	public boolean cookieCheck(Request req) {
		String query = "Select cookieName from Cookies" + " where cookieName = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, req.queryParams("cookie"));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	private Map<String, Integer> getRecipe(String cookieName) {
		Map<String, Integer> repecie = new HashMap<String, Integer>();
		ResultSet rs;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from Recipes where cookieName=?");
			ps.setString(1, cookieName);
			rs = ps.executeQuery();
			while (rs.next()) {
				repecie.put(rs.getString("ingredientName"), rs.getInt("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return repecie;
	}

	private void addCustomer(String name, String address) {
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Customers(name, address) VALUES (?, ?)")) {
			ps.setString(1, name);
			ps.setString(2, address);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addCookie(String name) {
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Cookies(cookieName) VALUES (?)")) {
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addRaw_material(String ingredientName, int storedAmount, String unit) {
		try (PreparedStatement ps = conn
				.prepareStatement("INSERT INTO Raw_materials(ingredientName, storedAmount, unit) VALUES (?, ?, ?)")) {
			ps.setString(1, ingredientName);
			ps.setInt(2, storedAmount);
			ps.setString(3, unit);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addRecipe(String cookieName, String ingredientName, int amount) {
		try (PreparedStatement ps = conn
				.prepareStatement("INSERT INTO Recipes(cookieName, ingredientName, amount) VALUES (?, ?, ?)")) {
			ps.setString(1, cookieName);
			ps.setString(2, ingredientName);
			ps.setInt(3, amount);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
