/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatagym;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOimplement.datagymimplement;
import com.sun.source.tree.NewArrayTree;
import java.util.logging.Level;
import java.util.logging.Logger;

public class datagymDAO implements datagymimplement{
    Connection connection;
    
    final String select = "SELECT * FROM sewa";
    final String insert = "INSERT INTO sewa (namapenyewa, namaalat, notelp, waktusewa, biayasewa) VALUES (?,?,?,?,?)";
    final String update = "UPDATE sewa SET namapenyewa = ?, namaalat = ?, notelp = ?, waktusewa = ?, biayasewa = ? WHERE sewa.namapenyewa = ?";
    final String delete = "DELETE FROM sewa WHERE namapenyewa = ?";
    
    //connect ke database
    public datagymDAO() {
        connection = connector.connection();
    }
    
    @Override
    public void insert(datagym g){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, g.getNamapenyewa());
            statement.setString(2, g.getNamaalat());
            statement.setString(3, g.getNotelp());
            statement.setInt(4, g.getWaktusewa());
            statement.setInt(5, g.getBiayasewa());
            
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            
            while(rs.next()){
                g.setNamapenyewa(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(datagym g, String dataLama){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            
            statement.setString(1, g.getNamapenyewa());
            statement.setString(2, g.getNamaalat());
            statement.setString(3, g.getNotelp());
            statement.setInt(4, g.getWaktusewa());
            statement.setInt(5, g.getBiayasewa());
            statement.setString(6, dataLama);
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void delete(String namapenyewa){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            
            statement.setString(1, namapenyewa);
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public List<datagym> getAll(){
        List<datagym> dg = null;
        try {
            dg = new ArrayList<datagym>();
            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while (rs.next()) {
                datagym gm = new datagym();
                gm.setNamapenyewa(rs.getString("namapenyewa"));
                gm.setNamaalat(rs.getString("namaalat"));
                gm.setNotelp(rs.getString("notelp"));
                gm.setWaktusewa(rs.getInt("waktusewa"));
                gm.setBiayasewa(rs.getInt("biayasewa"));
                
                dg.add(gm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(datagymDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dg;
    }
}

