/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.DAO;

import DAO.DAO_Implement.IDAO_Kamera;
import errorhandler.H_Error;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Data_Kamera;
import project.SQL_Connection;

/**
 *
 * @author Raynald Krisnawan
 */
public class DAO_Kamera implements IDAO_Kamera{
    Connection connection;
    final String getAll_query = "SELECT * FROM kamera";
    final String getById_query = "SELECT * FROM kamera WHERE id = ?";
    final String Insert_query = "INSERT INTO kamera (id, model, merk, price, img) VALUES (NULL, ?, ?, ?, ?)";
    final String Update_query = "UPDATE kamera SET model = ?, merk = ?, price = ?, img = ? WHERE id = ?";
    final String Delete_query = "DELETE FROM kamera WHERE id = ?";

    public DAO_Kamera(){
        connection = SQL_Connection.connection();
    }

    @Override
    public List<Data_Kamera> getAll() {
        List<Data_Kamera> daka = null;
        try {
            daka = new ArrayList<Data_Kamera>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(getAll_query);
            while(rs.next()){
                Data_Kamera dakam = new Data_Kamera();
                dakam.setId(rs.getInt("id"));
                dakam.setModel(rs.getString("model"));
                dakam.setMerk(rs.getString("merk"));
                dakam.setPrice(rs.getInt("price"));
                dakam.setImg(rs.getString("img"));
                daka.add(dakam);
            }
        } catch (SQLException e) {
            Logger.getLogger(Data_Kamera.class.getName()).log(Level.SEVERE, null, e);
        }
        return daka;
    }

    @Override
    public Data_Kamera getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void InsertData(Data_Kamera data_Kamera) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(Insert_query);
            st.setString(1, data_Kamera.getModel());
            st.setString(2, data_Kamera.getMerk());
            st.setInt(3, data_Kamera.getPrice());
            st.setString(4, data_Kamera.getImg());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void UpdateData(Data_Kamera data_Kamera) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(Update_query);
            st.setString(1, data_Kamera.getModel());
            st.setString(2, data_Kamera.getMerk());
            st.setInt(3, data_Kamera.getPrice());
            st.setString(4, data_Kamera.getImg());
            st.setInt(5, data_Kamera.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            H_Error.data_notfound();
        } finally{
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void DeleteData(int data) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(Delete_query);
            st.setInt(1, data);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
