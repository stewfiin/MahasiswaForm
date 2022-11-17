/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.view.Form_Mahasiswa;
import java.sql.SQLException;
/**
 *
 * @author Administrator
 */
public interface controller_mahasiswa {
     public void Simpan(Form_Mahasiswa mhs)throws SQLException;
     public void Ubah(Form_Mahasiswa mhs)throws SQLException;
     public void Hapus(Form_Mahasiswa mhs)throws SQLException;
     public void Tampil(Form_Mahasiswa mhs)throws SQLException;
     public void Baru(Form_Mahasiswa mhs);
     public void KlikTabel(Form_Mahasiswa mhs)throws SQLException;
     
}
