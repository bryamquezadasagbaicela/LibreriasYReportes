/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Direccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author José Quinde
 */
public class ControladorDirecciondb {
    private BaseDeDatos db;
    /**
     * Inicializa la clase base de datos para la conexion
     */
    public ControladorDirecciondb(String url, String user, String password) {
        db=new BaseDeDatos(url, user, password);
    }
    /**
     * Te devuelve el ultimo codigo del ultimo registro de la base de datos
     * @return 
     */
     public int buscarUltimoCodigo() {
        int resultado = 0;
        db.conectar();
        try {
            Statement sta = db.getConexionDb().createStatement();
            ResultSet res = sta.executeQuery("SELECT MAX (\"DIR_CODIGO\") FROM \"DIRECCION\"");
            res.next();
            resultado = res.getInt(1);
            db.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en la buscarUltimoCodigo " + ex);
        }
        return resultado;
    }
    /**
     * Metodo que sirve para crear una direccion en una base de datos
     * @param d 
     */
    public void create(Direccion d){     
        String sql="INSERT INTO \"DIRECCION\" VALUES ("
                                                    +d.getCodigo()+",'"
                                                    +d.getCalle_principal()+"','"
                                                    +d.getCalle_secundaria()+"',"
                                                    +d.getNumero()+",'"
                                                    +d.getCedula_per()+"'"+")";
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
     * Metodo que sirve para eliminar una direccion por el codigo
     * @param codigo 
     */
     public void delete(int codigo){
        String sql="DELETE FROM  \"DIRECCION\" WHERE"+" \"DIR_CODIGO\"= "+codigo+"";
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
      *Metodo que sirve para listar las direcciones de la base de datos
      * @return 
      */
      public Set<Direccion> listaDirecciones() {
        Set<Direccion> direcciones = new HashSet<Direccion>();
        String sentenciaSQL = "SELECT * FROM \"DIRECCION\" ";
        db.conectar();
        try {
            Statement sta = db.getConexionDb().createStatement();
            ResultSet res = sta.executeQuery(sentenciaSQL);
            while (res.next()) {                
                Direccion d=new Direccion();
                d.setCodigo(res.getInt("DIR_CODIGO")); 
                d.setCalle_principal(res.getString("DIR_CALLE_PRINCIPAL"));
                d.setCalle_secundaria(res.getString("DIR_CALLE_SECUNDARIA"));
                d.setNumero(res.getInt("DIR_NUMERO"));
                d.setCedula_per(res.getString("DIR_CEDULA_PER"));                       
                direcciones.add(d);
            }
            sta.close();
            res.close();
            db.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error Listar Personas: " + ex);
        }
        return direcciones;
    }
      /**
       * Este metodo nos elimina una direccion al momento de actualizar un persona
       * @param callePrin
       * @param CalleSec
       * @param numero 
       */
     public void deleteDir(String callePrin,String CalleSec,int numero){
        String sql="DELETE FROM \"DIRECCION\" WHERE"+" \"DIR_CALLE_PRINCIPAL\"= '"+callePrin+"' AND "+"\"DIR_CALLE_SECUNDARIA\"= '"+CalleSec+"' AND "+"\"DIR_NUMERO\"="+numero;
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
      * Elimina las direcciones de la persona dependiendo la cedula
      * @param cedula 
      */
     public void deletePersona(String cedula){
        String sql="DELETE FROM  \"DIRECCION\" WHERE"+" \"DIR_CEDULA_PER\"= '"+cedula+"'";
         db.conectar();
        try{
        Statement sta=db.getConexionDb().createStatement();
        sta.execute(sql);
        db.desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
