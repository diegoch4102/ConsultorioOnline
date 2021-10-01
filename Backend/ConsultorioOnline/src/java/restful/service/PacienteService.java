/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import restful.Model.ConexionBD;
import restful.Model.Paciente;

/**
 *
 * @author diego
 */
public class PacienteService {
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Paciente> getPacientes() throws ClassNotFoundException{
        ArrayList<Paciente> registros = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sqlStmt = "select * from pacientes";
        
        try {
            stmt = conexion.getConnection().createStatement();
            rs = stmt.executeQuery(sqlStmt);
            while (rs.next()){
                Paciente paciente = new Paciente();
                paciente.setId(rs.getLong("id"));
                paciente.setEmail(rs.getString("email"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setTipoDocumento(rs.getString("tipo_documento"));
                paciente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                paciente.setActivo(rs.getInt("activo"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setCiudad(rs.getString("ciudad"));
                paciente.setTel1(rs.getString("tel1"));
                paciente.setTel2(rs.getString("tel2"));
                paciente.setSexo(rs.getString("sexo"));
                
                registros.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {
            // Es buena idea liberar recurusos en un bloque finally en orden 
            // inverso de su creación si no se necesitan más.
            if ( rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEX) {} // Ignorar
                rs = null;
            }
            
            if ( stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEX) {} // Ignorar
                stmt = null;
            }
        }    
        return registros;
    }
    
    public Paciente getPacienteNombre(String nombre) throws ClassNotFoundException{
        Paciente paciente = new Paciente();
        ConexionBD conexion = new ConexionBD();
        
        try {
            ps = conexion.getConnection().prepareStatement(
                    "select * from pacientes where nombre = ?");
            ps.setString(1, nombre);
            while (rs.next()){
                paciente.setId(rs.getLong("id"));
                paciente.setEmail(rs.getString("email"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setTipoDocumento(rs.getString("tipo_documento"));
                paciente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                paciente.setActivo(rs.getInt("activo"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setCiudad(rs.getString("ciudad"));
                paciente.setTel1(rs.getString("tel1"));
                paciente.setTel2(rs.getString("tel2"));
                paciente.setSexo(rs.getString("sexo"));
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {
            // Es buena idea liberar recurusos en un bloque finally en orden 
            // inverso de su creación si no se necesitan más.
            if ( rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEX) {} // Ignorar
                rs = null;
            }
            
            if ( ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlEX) {} // Ignorar
                ps = null;
            }
        }    
        System.out.println(paciente.toString());
        return paciente;
    }
    
    public Paciente addPaciente(Paciente paciente ) throws ClassNotFoundException{
         
        ConexionBD conexion = new ConexionBD();
        
        try {
            ps = conexion.getConnection().prepareStatement(
                    "insert into pacientes(id, email, nombre, apellido," 
                    + "tipo_documento, fecha_nacimiento, activo, direccion,"
                    +"ciudad, tel1, sexo) values (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, paciente.getId());
            ps.setString(2, paciente.getEmail());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getApellido());
            ps.setString(5, paciente.getTipoDocumento());
            ps.setString(6, paciente.getFechaNacimiento());
            ps.setInt(7, paciente.getActivo());
            ps.setString(8, paciente.getDireccion());
            ps.setString(9, paciente.getCiudad());
            ps.setString(10, paciente.getTel1());
            ps.setString(11, paciente.getSexo());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {
            // Es buena idea liberar recurusos en un bloque finally en orden 
            // inverso de su creación si no se necesitan más.
            if ( rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEX) {} // Ignorar
                rs = null;
            }
            
            if ( ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlEX) {} // Ignorar
                ps = null;
            }
        }    
        System.out.println(paciente.toString());
        return paciente;
    }
    
    public Paciente updatePaciente(Paciente paciente ) throws ClassNotFoundException{
         
        ConexionBD conexion = new ConexionBD();
        
        try {
            ps = conexion.getConnection().prepareStatement(
                    "update pacientes set email=?, nombre=?, apellido=?," 
                    + "tipo_documento=?, fecha_nacimiento=?, activo=?, direccion=?,"
                    +"ciudad=?, tel1=?, sexo=? where id=?");
            ps.setString(1, paciente.getEmail());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setString(4, paciente.getTipoDocumento());
            ps.setString(5, paciente.getFechaNacimiento());
            ps.setInt(6, paciente.getActivo());
            ps.setString(7, paciente.getDireccion());
            ps.setString(8, paciente.getCiudad());
            ps.setString(9, paciente.getTel1());
            ps.setString(10, paciente.getSexo());
            ps.setLong(11, paciente.getId());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {
            // Es buena idea liberar recurusos en un bloque finally en orden 
            // inverso de su creación si no se necesitan más.
            if ( rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEX) {} // Ignorar
                rs = null;
            }
            
            if ( ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlEX) {} // Ignorar
                ps = null;
            }
        }    
        System.out.println(paciente.toString());
        return paciente;
    }
    
    public String idPaciente(int id) throws ClassNotFoundException{
         
        ConexionBD conexion = new ConexionBD();
        
        try {
            ps = conexion.getConnection().prepareStatement(
                    "delete from pacientes where id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return "{\"Acccion\":\"Error\"}";
        }
        finally {
            // Es buena idea liberar recurusos en un bloque finally en orden 
            // inverso de su creación si no se necesitan más.
            if ( rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEX) {} // Ignorar
                rs = null;
            }
            
            if ( ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlEX) {} // Ignorar
                ps = null;
            }
        }    
        return "{\"Acccion\":\"REgistro Borrado\"}";
    }
    
}