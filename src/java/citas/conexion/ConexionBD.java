/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsschuller
 */
public class ConexionBD {

    public static final String URL = "jdbc:mysql://localhost/tangodentbd";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            throw (ex);
        }
        return con;
    }
}
