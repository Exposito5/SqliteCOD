/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atejidosolla;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



/**
 *
 * @author Alex
 */
public class Metodos {
    
    /**
     * Method tocreate a new database
     * @param nombrebase Nmae to te new database
     */
    public static void crearnuevabase(String nombrebase){
     String url = "jdbc:sqlite:"+nombrebase;
        
     String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	Nombre text NOT NULL,\n"
                + "	Apellido text NOT NULL,\n"
                + "     Clase text NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
/**
 * Method to create a table in which the name of the table is requested
 */
    public static void crearNuevaTabla() {
        
        String url = "jdbc:sqlite:BaseProg.db";
        
        String nomtabla=(JOptionPane.showInputDialog("Introduce el nombre a asignar a la tabla"));
        
        String sql = "CREATE TABLE IF NOT EXISTS "+nomtabla+"(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	Nombre text NOT NULL,\n"
                + "	Apellido text NOT NULL,\n"
                + "     Clase text NOT NULL\n"
                + ");";
       
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
           
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method to insert a new row
     * @param tablamos Name of the table in which we want to insert the row
     * @param name Name of the new student
     * @param apellidos Surnames of the new student
     * @param clase class of the new student
     */
        public void insertar(String tablamos,String name, String apellidos,String clase) {
        String sql = "INSERT INTO "+tablamos+"(Nombre,Apellido,Clase) VALUES(?,?,?)";
 
        try (Connection conn = atejidosolla.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, clase);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
        /**
         * Method to view data in a table
         * @param tablamos Name of the table from which we want to display the data
         */
        public void visualiza(String tablamos){
            
        String sql = "SELECT id, Nombre, Apellido,Clase FROM "+tablamos;
        
        try (Connection conn = atejidosolla.conectar();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") +"\t" + 
                                   rs.getString("Nombre") +"\t" +
                                   rs.getString("Apellido")+ "\t" +
                                   rs.getString("Clase"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
        /**
         * 
         * @param tablamos Name of the table in which we want to insert the updated data
         * @param id Id of the row that we want to update
         * @param nombre The new name
         * @param apellidos The new surnames
         * @param clase The new class
         */
         public void actualiza(String tablamos, int id, String nombre, String apellidos, String clase) {
        String sql = "UPDATE "+tablamos+" SET Nombre = ? , "
                + "Apellido = ?, "
                + "Clase = ? "
                + "WHERE id = ?";
 
        try (Connection conn = atejidosolla.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, clase);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
         
         /**
          * Method to delete a row
          * @param id Id of the row we want to delete
          * @param tablamos Name of the table in which we want to delete the row
          */
          public void borrar(int id, String tablamos) {
        String sql = "DELETE FROM "+tablamos+" WHERE id = ?";
 
        try (Connection conn = atejidosolla.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
    }







