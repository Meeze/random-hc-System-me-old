package de.pooky.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;



public class MySQL {

	public static Connection con;
	
	public static void connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://mysql.mc-host24.de:3306/db_136828?autoReconnect=true", "db_136828", "0a6bd9555a");
				MySQL.Update("CREATE TABLE IF NOT EXISTS `coinTable` (`UUID` text,`coins` text) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				System.out.println("MySQL VERBUNDEN");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("MySQL GETRENNT");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		return (con != null);
	}
	
	public static void createTable() {
		try {
			con.prepareStatement("CREATE TABLE IF NOT EXISTS coinTable (UUID, VARCHAR(100), coinTable INT(16)").executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public static void Update(String qry) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static ResultSet Query(String stCheck) throws SQLException {
        Statement statement = con.createStatement();
        return statement.executeQuery(stCheck);
    }
    
    public static void prepareMoney(Player p) {
        try {
            ResultSet rs = MySQL.Query("SELECT * FROM `coinTable` WHERE `UUID` = '" + p.getUniqueId().toString() + "'");
            if (!rs.next()) {
                MySQL.Update("INSERT INTO `coinTable` (`UUID`, `coins`) VALUES ('" + p.getUniqueId().toString()
                        + "','" + "0" + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
}
