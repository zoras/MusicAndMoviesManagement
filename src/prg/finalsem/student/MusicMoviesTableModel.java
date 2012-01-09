package prg.finalsem.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MusicMoviesTableModel extends AbstractTableModel{
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private int numberOfRows;
	private ResultSet rs;
	private ResultSetMetaData metaData;
	private Statement st;
	private Connection con;
	private boolean isConnected = false;
	
	public MusicMoviesTableModel(String userName,String passWord,String querySQL) throws Exception{
    		Class.forName(driver);
    		con = DriverManager.getConnection(url,userName,passWord);
    		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		isConnected = true;
			setQuery(querySQL);
			System.out.println(querySQL);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) throws IllegalStateException {
		if (!isConnected)
			throw new IllegalStateException( "Not Connected to Database" );
		try {
         String className = metaData.getColumnClassName(column + 1);
         return Class.forName(className );
		} catch(Exception ex){
         System.out.println(ex.getMessage());
      }
      return Object.class;
  	}
	
	@Override
	public int getColumnCount() throws IllegalStateException {
		if (!isConnected)
	         throw new IllegalStateException( "Not Connected to Database" );
		try {
	         return metaData.getColumnCount();
	    } catch (SQLException ex) {
	         System.out.println(ex.getMessage());
	    }
	    return 0;
	}
	
	public String getColumnName(int column) throws IllegalStateException {
		if (!isConnected)
			throw new IllegalStateException( "Not Connected to Database" );
		try {
			return metaData.getColumnName(column + 1);
		
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}
	
	@Override
	public int getRowCount() throws IllegalStateException {
		if (!isConnected)
	         throw new IllegalStateException( "Not Connected to Database" );
	    return numberOfRows;
	}
	
	@Override
	public Object getValueAt(int row, int column) throws IllegalStateException {
		if (!isConnected) {
	         throw new IllegalStateException("Not Connected to Database");
		} else {
			try {
		         rs.absolute(row + 1);
		         return rs.getObject(column + 1);
			} catch (SQLException ex) {
				System.out.println(rs);
		        System.out.println(ex.getSQLState());
		        System.out.println(ex.getMessage());
		    }
		}
		return "";
	}
	
	public void setQuery(String query)throws SQLException, IllegalStateException {
		if (!isConnected)
			throw new IllegalStateException("Not Connected to Database");
			rs = st.executeQuery(query);
			metaData = rs.getMetaData();
			rs.last();
			numberOfRows = rs.getRow();
			fireTableStructureChanged();
 	}
	
	public void CloseConnections() throws IllegalStateException {
   		if (!isConnected)
   			throw new IllegalStateException("Not Connected to Database");
   		try {
   			st.close();
   			con.close();
   		}
   		catch(Exception ex) {
   			System.out.println(ex.getMessage());
   		}
   	}
	
	public void setEdit(String query)throws IllegalStateException {
		if (!isConnected)
   			throw new IllegalStateException("Not Connected to Database");
		try {
			st.execute(query);
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"SQL Error: " + ex.getMessage());
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"General Error: " + ex.getMessage());
		}
	}

	public String getTableName(int column){
		try {
			return metaData.getTableName(column);
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"SQL Error: " + ex.getMessage());
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"General Error: " + ex.getMessage());
		}
		return "";
	}
}
