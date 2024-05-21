/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOimplement;
import java.util.List;
import model.*;

public interface datagymimplement {
    public void insert(datagym g);
    public void update(datagym g, String dataLama);
    public void delete(String namapenyewa);
    public List<datagym> getAll();
}
