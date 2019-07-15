/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author José Quinde
 */
public class ControladorPersonadb {
    private BaseDeDatos db;
    /**
     * Inicializa la clase base de datos para la conexion
     */
    public ControladorPersonadb(String url, String user, String password) {
        db=new BaseDeDatos( url,  user,  password);
    }
    /**
     * Metodo que sirve para crear una persona en una base de datos
     * @param p 
     */
    public void create(Persona p){
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");        
        String fecha=sd.format(p.getFechaNacimiento());
        String sql="INSERT INTO \"PERSONA\" VALUES ('"
                                                    +p.getCedula()+"','"
                                                    +p.getNombres()+"','"
                                                    +p.getApellidos()+"',"
                                                    +p.getEdad()+",'"
                                                    +fecha+"','"
                                                    +p.getNumeroTelefono()+"',"
                                                    +p.getSalario()+")";
        db.conectar();
        try{
        Statement sta=db.getConexionDb().createStatement();
        sta.execute(sql);
        db.desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    /**
     * Metodo que sirve para actualizar una persona en una base de datos a traves de su cedula
     * @param p
     * @param cedula 
     */
    public void update(Persona p,String cedula){
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        String fecha=sd.format(p.getFechaNacimiento());
        String sql= "UPDATE \"PERSONA\" SET " +                                                   
                                                "\"PER_NOMBRE\"='" +p.getNombres()+"',"+
                                                "\"PER_APELLIDO\"='" +p.getApellidos()+"',"+
                                                 "\"PER_EDAD\"="+p.getEdad()+","+
                                                  "\"PER_FECHA_NACIMIENTO\"='" +fecha+"',"+
                                                   "\"PER_CELULAR\"='"+p.getNumeroTelefono()+"',"+
                                                    "\"PER_SALARIO\"="+p.getSalario()
                +" WHERE"+" \"PER_CEDULA\"="+"'"+cedula+"'";
        //System.out.println(sql);
         db.conectar();
        try{
        Statement sta=db.getConexionDb().createStatement();
        sta.execute(sql);
        db.desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
                
    }
    /**
     * Metodo que sirve para eliminar una persona de la base de datos a traves de su cedula
     * @param cedula 
     */
    public void delete(String cedula){
        String sql="DELETE FROM  \"PERSONA\" WHERE"+" \"PER_CEDULA\"= "+"'"+cedula+"'";
         db.conectar();
        try{
        Statement sta=db.getConexionDb().createStatement();
        sta.execute(sql);
        db.desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Metodo que sirve para buscar en la base de datos una persona a traves de su cedula
     * @param cedula
     * @return
     * @throws Exception 
     */
    public Persona search(String cedula) throws Exception {
        Persona p=new Persona();
        String SQL = "SELECT * FROM \"PERSONA\" WHERE \"PER_CEDULA\"=" + "'"+cedula+"'";
        db.conectar();
        try {
            Statement sta = db.getConexionDb().createStatement();
            ResultSet res = sta.executeQuery(SQL);
            res.next();
            p.setCedula(res.getString("PER_CEDULA"));
            p.setNombres(res.getString("PER_NOMBRE"));
            p.setApellidos(res.getString("PER_APELLIDO"));
            p.setEdad(res.getInt("PER_EDAD"));
            p.setFechaNacimiento(res.getDate("PER_FECHA_NACIMIENTO"));
            p.setNumeroTelefono(res.getString("PER_CELULAR"));
            p.setSalario(res.getDouble("PER_SALARIO"));
            res.close();
            sta.close();
            db.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en buscar Persona");
        }
        return p;
    }
    /**
     * Retorna la lista de Personas en la base de Datos
     * @return
     * @throws Exception 
     */
    public Set<Persona> listaPersonas() throws Exception {
        Set<Persona> personas = new HashSet<Persona>();
        String sentenciaSQL = "SELECT * FROM \"PERSONA\" ";
        db.conectar();
        try {
            Statement sta = db.getConexionDb().createStatement();
            ResultSet res = sta.executeQuery(sentenciaSQL);
            while (res.next()) {                
                Persona p=new Persona();
                p.setCedula(res.getString("PER_CEDULA"));
                p.setNombres(res.getString("PER_NOMBRE"));
                p.setApellidos(res.getString("PER_APELLIDO"));
                p.setEdad(res.getInt("PER_EDAD"));
                p.setFechaNacimiento(res.getDate("PER_FECHA_NACIMIENTO"));
                p.setNumeroTelefono(res.getString("PER_CELULAR"));
                p.setSalario(res.getDouble("PER_SALARIO"));                      
                personas.add(p);
            }
            sta.close();
            res.close();
            db.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error Listar Personas: " + ex);
        }
        return personas;
    }

public Set<Persona> Listar() throws Exception {
        Set<Persona> personas = new HashSet<Persona>();
        String sql = "SELECT * FROM \"PERSONA\" ";
        System.out.println(sql);
        db.conectar();
        try {
            try (Statement sta = db.getConexionDb().createStatement()) {
                ResultSet res = sta.executeQuery(sql);
                while (res.next()) {
                    Persona persona = new Persona();
                    persona.setCedula(res.getString("PER_CEDULA"));
                    persona.setNombres(res.getString("PER_NOMBRES"));
                    persona.setApellidos(res.getString("PER_APELLIDOS"));
                    persona.setGenero(res.getString("PER_GENERO"));
                    persona.setFechaNacimiento(res.getDate("PER_FECHA_NACIMIENTO"));
                    persona.setEdad(res.getInt("PER_EDAD"));
                    persona.setNumeroTelefono(res.getString("PER_CELULAR"));
                    persona.setSalario(res.getDouble("PER_SALARIO"));
                    System.out.println(persona.toString());
                    personas.add(persona);
                }
                res.close();
                sta.close();
                db.desconectar();
            }
            db.desconectar();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return personas;
    }

            
           
}
