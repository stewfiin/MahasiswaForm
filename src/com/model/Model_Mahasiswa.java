/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_mahasiswa;
import com.koneksi.koneksi;
import com.view.Form_Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Model_Mahasiswa implements controller_mahasiswa{
    String jk;
    @Override
    public void Simpan(Form_Mahasiswa mhs) throws SQLException {
        if(mhs.rbLaki.isSelected()){
            jk= "Laki- Laki";
        } else{
            jk = "Perempuan";
        } try {
            Connection con = koneksi.getKoneksi();
            String sql = "insert mahasiswa values(?,?,?,?)";
            PreparedStatement prepare= con.prepareStatement(sql);
            prepare.setString(1, mhs.txtNim.getText());
            prepare.setString(2, mhs.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) mhs.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        }
        finally{
            Tampil(mhs);
            mhs.setLebarKolom();
        }
    }

    @Override
    public void Ubah(Form_Mahasiswa mhs) throws SQLException {
        if(mhs.rbLaki.isSelected()){
            jk= "Laki- Laki";
        } else{
            jk = "Perempuan";
        } try {
            Connection con = koneksi.getKoneksi();
            String sql = "update mahasiswa set Nama=?, Jenis_Kelamin= ?, Jurusan= ? where NIM= ?";
            PreparedStatement prepare= con.prepareStatement(sql);
            prepare.setString(4, mhs.txtNim.getText());
            prepare.setString(1, mhs.txtNama.getText());
            prepare.setString(2, jk);
            prepare.setString(3, (String)mhs.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");
            prepare.close();
        }catch (Exception e){
            System.out.println(e);
        }
        finally{
            Tampil(mhs);
            mhs.setLebarKolom();
            Baru(mhs);
        }
    }

    @Override
    public void Hapus(Form_Mahasiswa mhs) throws SQLException {
        try {
           
            
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM mahasiswa WHERE NIM = ?";
            PreparedStatement prepare= con.prepareStatement(sql);
            
            prepare.setString(1, mhs.txtNim.getText());
            prepare.setString(2, mhs.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) mhs.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Tampil(mhs);
            mhs.setLebarKolom();
            Baru(mhs);
        }
    }

    @Override
    public void Tampil(Form_Mahasiswa mhs) throws SQLException {
        
            mhs.tblmodel.getDataVector().removeAllElements();
            mhs.tblmodel.fireTableDataChanged();
            try{
                Connection con = koneksi.getKoneksi();
                Statement stt = con.createStatement();
                String sql = "select * from mahasiswa order by NIM asc";
                ResultSet res = stt.executeQuery(sql);
                while(res.next())
                {
                    Object[]ob= new Object[8];
                    ob[0] = res.getString(1);
                    ob[1] = res.getString(2);
                    ob[2] = res.getString(3);
                    ob[3] = res.getString(4);
                    mhs.tblmodel.addRow(ob);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        
    }

    @Override
    public void Baru(Form_Mahasiswa mhs) {
        mhs.txtNim.setText("");
        mhs.txtNama.setText("");
        mhs.rbLaki.setSelected(true);
        mhs.cbJurusan.setSelectedIndex(0);
    }

    @Override
    public void KlikTabel(Form_Mahasiswa mhs) throws SQLException {
        try{
            int pilih = mhs.tabel.getSelectedRow();
            if(pilih == -1){
                return;
            }
            mhs.txtNim.setText(mhs.tblmodel.getValueAt(pilih, 0).toString());
            mhs.txtNama.setText(mhs.tblmodel.getValueAt(pilih, 1).toString());
            mhs.cbJurusan.setSelectedItem(mhs.tblmodel.getValueAt(pilih, 3).toString());
            jk = String.valueOf(mhs.tblmodel.getValueAt(pilih, 2));
        
        } catch (Exception e){
            
        }
        if(mhs.rbLaki.getText().equals(jk)){
            mhs.rbLaki.setSelected(true);
        }
        else{mhs.rbPerempuan.setSelected(true);}
    }
    
}
