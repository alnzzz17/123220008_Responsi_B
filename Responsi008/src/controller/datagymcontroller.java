/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdatagym.datagymDAO;
import DAOimplement.datagymimplement;
import model.*;
import view.MainView;

public class datagymcontroller {
    MainView frame;
    datagymimplement implDataGym;
    List<datagym> dg;
    private String dataLama;
    
    public datagymcontroller(MainView frame){
        this.frame = frame;
        implDataGym = new datagymDAO();
        dg = implDataGym.getAll();
    };
    
    public void isitabel(){
        dg = implDataGym.getAll();
        modeltabeldatagym mg = new modeltabeldatagym(dg);
        frame.getTabelDataGym().setModel(mg);
    }
    
    public void insert(){
        datagym dg = new datagym();
        dg.setNamapenyewa(frame.getjTextNamaPenyewa().getText());
        dg.setNamaalat(frame.getjTextNamaAlat().getText());
        dg.setNotelp(frame.getjTextNomorTelepon().getText());
        Integer waktuSewa = Integer.parseInt(frame.getjTextWaktuSewa().getText());
        dg.setWaktusewa(waktuSewa);
        
        Integer biaya;
        
        //menghitung biaya sewa
        if(waktuSewa < 2){
            biaya = waktuSewa * 50000;
        } else {
            biaya = (waktuSewa * 50000) + (waktuSewa * 25000);
        }
        
        dg.setBiayasewa(biaya);
        implDataGym.insert(dg);
    }
    
    public void update(){
        datagym dg = new datagym();
        
        //menyimpan nama penyewa semula
        String dataLama = frame.getTabelDataGym().getValueAt(frame.getTabelDataGym().getSelectedColumn(),0).toString();
        
        dg.setNamapenyewa(frame.getjTextNamaPenyewa().getText());
        dg.setNamaalat(frame.getjTextNamaAlat().getText());
        dg.setNotelp(frame.getjTextNomorTelepon().getText());
        
        Integer waktuSewa = Integer.parseInt(frame.getjTextWaktuSewa().getText());
        dg.setWaktusewa(waktuSewa);
        
        Integer biaya;
        
        //menghitung biaya sewa
        if(waktuSewa < 2){
            biaya = waktuSewa * 50000;
        } else {
            biaya = (waktuSewa * 50000) + (waktuSewa * 25000);
        }
        
        dg.setBiayasewa(biaya);
        implDataGym.update(dg, dataLama);
    }
    
    public void delete(){
        String namaPenyewa = frame.getjTextNamaPenyewa().getText();
        implDataGym.delete(namaPenyewa);
    }
}
