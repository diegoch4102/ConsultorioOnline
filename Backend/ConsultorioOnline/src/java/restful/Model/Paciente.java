/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
/**
 *
 * @author diego
 */
public class Paciente {
    private long id;
    private int activo;
    private String email,nombre,apellido,tipoDocumento,fechaNacimiento,
            direccion,ciudad,tel1,tel2,sexo;

    public Paciente() {
    }

    public Paciente(long id, int activo, String email, String nombre, 
            String apellido, String tipoDocumento, String fechaNacimiento, 
            String direccion, String ciudad, String tel1, String tel2, String sexo) {
        this.id = id;
        this.activo = activo;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.sexo = sexo;
    }

    public Paciente(long id, int activo, String email, String nombre, 
            String apellido, String tipoDocumento, String fechaNacimiento, 
            String direccion, String ciudad, String tel1, String sexo) {
        this.id = id;
        this.activo = activo;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tel1 = tel1;
        this.sexo = sexo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    @Override
    public String toString(){
        return "Paciente: "+nombre;
    }
    
}
