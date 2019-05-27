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

    public static List<Cita> getAllCitas() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            ArrayList<Cita> citas = new ArrayList<>();
            ps = conn.prepareStatement("SELECT * FROM citas");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("idCita"), rs.getString("nombreServicio"),
                        rs.getDate("fecha"), rs.getString("horaInicio"),
                        rs.getString("horaFin"), rs.getString("duracion"), rs.getString("nombrePaciente"),
                        rs.getString("dni"), rs.getString("email"), rs.getInt("telefono"),
                        rs.getString("direccion"), rs.getString("ciudad")
                );
                citas.add(cita);
            }
            return citas;
        } catch (Exception ex) {
            throw (ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public static Cita getCita(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.getConnection();
            ps = con.prepareStatement("SELECT * FROM citas WHERE idCita = " + id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cita registro = new Cita();
                registro.setId(rs.getInt("idCita"));
                registro.setNombreServicio(rs.getString("nombreServicio"));
                registro.setFecha(rs.getDate("fecha"));
                registro.setHoraInicio(rs.getString("horaInicio"));
                registro.setHoraFin(rs.getString("horaFin"));
                registro.setDuracion(rs.getString("duracion"));
                registro.setNombrePaciente(rs.getString("nombrePaciente"));
                registro.setDni(rs.getString("dni"));
                registro.setEmail(rs.getString("email"));
                registro.setTelefono(rs.getInt("telefono"));
                registro.setDireccion(rs.getString("direccion"));
                registro.setCiudad(rs.getString("ciudad"));
                 
                return registro;
            }
        } catch (Exception ex) {
            throw (ex);
        }
        return null;
    }
    public static void addCita(Cita registro) throws Exception{
        Connection con = ConexionBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str;
            if(registro.getId() != -1){
                str="INSERT INTO citas (idCita,nombreServicio,fecha,horaInicio,horaFin,duracion,nombrePaciente,dni,email,telefono,direccion,ciudad) VALUES ("+
                        registro.getId()+",'"+registro.getNombreServicio()
                        +",'"+registro.getFecha()+",'"+registro.getHoraInicio()
                        +",'"+registro.getHoraFin()+",'"+registro.getDuracion()
                        +",'"+registro.getNombrePaciente()+",'"+registro.getDni()
                        +",'"+registro.getEmail()+",'"+registro.getTelefono()
                        +",'"+registro.getDireccion()+",'"+registro.getCiudad()+"')";
            }else{
                str="INSERT INTO citas (nombreServicio,fecha,horaInicio,horaFin,duracion,nombrePaciente,dni,email,telefono,direccion,ciudad) VALUES("+
                        registro.getNombreServicio()
                        +",'"+registro.getFecha()+",'"+registro.getHoraInicio()
                        +",'"+registro.getHoraFin()+",'"+registro.getDuracion()
                        +",'"+registro.getNombrePaciente()+",'"+registro.getDni()
                        +",'"+registro.getEmail()+",'"+registro.getTelefono()
                        +",'"+registro.getDireccion()+",'"+registro.getCiudad()+"')";
            }
            ps=con.prepareStatement(str);
            ps.executeQuery();
            
        } catch (Exception ex) {
            throw(ex);
        }finally{
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        
    }
    
    public static void deleteCita(int id) throws Exception{
       Connection con = ConexionBD.getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null;
        try {
            ps=con.prepareStatement("DELETE FROM citas WHERE idCita = "+ id);
            ps.executeQuery();
        } catch (Exception ex) {
            throw(ex);
        }finally{
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public static void updateCita(Cita registro) throws Exception{
       Connection con = ConexionBD.getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null;
       
        try {
           
            String str="UPDATE citas SET nombreServicio = '"+ registro.getNombreServicio() 
                    + "'" + ",fecha = '"+registro.getFecha()+ "'" + ",fecha = '"+registro.getHoraInicio()
                    + "'" + ",fecha = '"+registro.getHoraFin()+ "'" + ",fecha = '"+registro.getDuracion()
                    + "'" + ",fecha = '"+registro.getNombrePaciente()+ "'" + ",fecha = '"+registro.getDni()
                    + "'" + ",fecha = '"+registro.getEmail()+ "'" + ",fecha = '"+registro.getTelefono()
                    + "'" + ",fecha = '"+registro.getDireccion()+ "'" + ",fecha = '"+registro.getCiudad();
            
            ps= con.prepareStatement(str);
            ps.executeQuery();
        } catch (Exception ex) {
            throw (ex);
        }finally{
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
}
