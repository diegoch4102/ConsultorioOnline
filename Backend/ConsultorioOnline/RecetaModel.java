/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.Model;

/**
 *
 * @author JOSE
 */
public class RecetaModel {
    private int id;
    private String cita_id;
    private String medicamento_id;
    private String dosis;
    private String cantidad;
    private String intervalo;

    public RecetaModel(int id, String cita_id, String medicamento_id, String dosis, String cantidad, String intervalo) {
        this.id = id;
        this.cita_id = cita_id;
        this.medicamento_id = medicamento_id;
        this.dosis = dosis;
        this.cantidad = cantidad;
        this.intervalo = intervalo;
    }

    public RecetaModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCita_id() {
        return cita_id;
    }

    public void setCita_id(String cita_id) {
        this.cita_id = cita_id;
    }

    public String getMedicamento_id() {
        return medicamento_id;
    }

    public void setMedicamento_id(String medicamento_id) {
        this.medicamento_id = medicamento_id;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

   

    
    
    
    
}
