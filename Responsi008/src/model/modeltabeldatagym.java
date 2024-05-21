/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
        
public class modeltabeldatagym extends AbstractTableModel{
    List<datagym> dg;
    
    public modeltabeldatagym(List<datagym> dg) {
        this.dg = dg;
    }
    
    @Override
    public int getRowCount(){
        return dg.size(); //menghitung dari data
    }
    
    @Override
    public int getColumnCount(){
        return 5;
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "Nama Penyewa";
            case 1:
                return "Nama Alat";
            case 2:
                return "Nomor Telepon";
            case 3:
                return "Waktu Sewa";
            case 4:
                return "Biaya Sewa";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column){
        switch(column){
            case 0:
                return dg.get(row).getNamapenyewa();
            case 1:
                return dg.get(row).getNamaalat();
            case 2:
                return dg.get(row).getNotelp();
            case 3:
                return dg.get(row).getWaktusewa();
            case 4:
                return dg.get(row).getBiayasewa();
            default:
                return null;
        }
    }
}
