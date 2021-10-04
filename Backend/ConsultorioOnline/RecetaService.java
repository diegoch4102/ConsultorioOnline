/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.RecetaModel;
import restful.Model.Conexion;
/**
 *
 * @author JOSE
 */
public class RecetaService {
    

    public ArrayList<RecetaModel> getReceta() {
        ArrayList<RecetaModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM recetas";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                RecetaModel recetas = new RecetaModel();
                recetas.setId(rs.getInt("id"));
                recetas.setCita_id(rs.getString("cita_id"));
                recetas.setMedicamento_id(rs.getString("medicamento_id"));
                recetas.setDosis(rs.getString("dosis"));
                recetas.setCantidad(rs.getString("cantidad"));
                recetas.setIntervalo(rs.getString("intervalo"));
                lista.add(recetas);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public RecetaModel getCliente(int id) {
        RecetaModel recetas = new RecetaModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM recetas WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

             recetas.setId(rs.getInt("id"));
             recetas.setCita_id(rs.getString("cita_id"));
             recetas.setMedicamento_id(rs.getString("medicamento_id"));
             recetas.setDosis(rs.getString("dosis"));
             recetas.setCantidad(rs.getString("cantidad"));
             recetas.setIntervalo(rs.getString("intervalo"));
               
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return recetas;
    }

    public RecetaModel addReceta(RecetaModel receta) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO recetas (id,cita_id,medicamento_id,dosis,cantidad, intervalo)";
        Sql = Sql + "values (?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, receta.getId());
            pstm.setString(2, receta.getCita_id());
            pstm.setString(3, receta.getMedicamento_id());
            pstm.setString(4, receta.getDosis());
            pstm.setString(5, receta.getCantidad());
            pstm.setString(6, receta.getIntervalo());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return receta;
    }

    public RecetaModel updateReceta(RecetaModel receta) {
        Conexion conn = new Conexion();
        String sql = "UPDATE receta SET cita_id=?,medicamento_id=?,dosis=?,cantidad=?,intervalo=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, receta.getCita_id());
            pstm.setString(2, receta.getMedicamento_id());
            pstm.setString(3, receta.getDosis());
            pstm.setString(4, receta.getCantidad());
            pstm.setString(5, receta.getIntervalo());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return null;
        }
        return receta;
    }

    public String delReceta(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM recetas WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }

    public RecetaModel getReceta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}