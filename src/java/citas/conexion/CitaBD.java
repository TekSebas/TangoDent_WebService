/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citas.conexion;

import citas.pojos.Cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsschuller
 */
public class CitaBD {
    public static List<Cita> getAllCiclo(){
        try {
            Connection conn = ConexionBD.getConnection();
            ArrayList<Cita> citas= new ArrayList<>();
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM citas");
            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
                Cita cita= new Cita(rs.getInt("idCita"),rs.getString("nombreServicio"),rs.getDate("fecha"),rs.getLong("horaInicio"),
                        rs.getLong("horaFin"),rs.getString("duracion"),rs.getString("nombrePaciente"),
                        rs.getString("dni"),rs.getString("email"),rs.getInt("telefono"),
                        rs.getString("ciudad"),rs.getString("direccion"));
                
            }
           return citas;
        } catch (SQLException ex) {
            Logger.getLogger(CitaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
