package by.htp.ex.dao.impl;
import java.util.ResourceBundle;

public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	

	private ResourceBundle bundle = ResourceBundle.getBundle("database.db");
	
	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
	/*public static void main(String[] args) {
		DBResourceManager DBR = new DBResourceManager();
		String s = DBR.getValue("db.user");
		System.out.println("db.user="+s);
	}*/
	
}
