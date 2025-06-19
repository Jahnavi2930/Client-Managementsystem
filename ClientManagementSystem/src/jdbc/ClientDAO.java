package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
	private Connection conn;
	
	public ClientDAO() {
		conn = DBConnection.getConnection();
	}
	public void addClient(Client client) {
	    String query = "INSERT INTO clients (client_id, name, email, phone) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, client.getId());
	        stmt.setString(2, client.getName());
	        stmt.setString(3, client.getEmail());
	        stmt.setString(4, client.getPhone());

	        int rowsInserted = stmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("✅ Client added successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// Update Client by ID
	public void updateClient(int id, String newName, String newEmail, String newPhone) {
	    String query = "UPDATE clients SET name = ?, email = ?, phone = ? WHERE client_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	    	
	    	stmt.setString(1, newName);
	        stmt.setString(2, newEmail);
	        stmt.setString(3, newPhone);
	        stmt.setInt(4, id);
	        

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            System.out.println("✅ Client updated successfully.");
	        } else {
	            System.out.println("❌ No client found with ID " + id);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// Delete Client by ID
	public void deleteClient(int client_id) {
        String sql = "DELETE FROM clients WHERE client_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, client_id);

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            System.out.println("🗑️ Client deleted successfully.");
	        } else {
	            System.out.println("❌ No client found with ID " + client_id);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public List<Client> getAllClients(){
		List<Client> list = new ArrayList<>();
		try {
			String sql = "SELECT * from clients";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Client c = new Client(
						rs.getInt("client_id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("phone")
						);
				list.add(c);
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
