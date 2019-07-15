/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Quinde
 */
public class ControladorPersona {
       //Declaracion Variables
       String ruta;
       SimpleDateFormat formatter ;
       private Set<Persona> lista;
       /**
        * Controlador inicializa el formato de fecha y la ruta del archivo
        */
  


    public ControladorPersona(String url, String user, String password) {
    ruta="C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Excepciones\\src\\ec\\edu\\ups\\archivos\\archivo.ups";
        formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    }


    /**
     * Metodo que sirve para ingresar una persona en un archivo binario randomico
     * @param p 
     */   
    public void IngresarPersona(Persona p){
        try{
        RandomAccessFile archivo=new RandomAccessFile(ruta,"rw");
        //Escribe desde el final del archivo
        archivo.seek(archivo.length());
        //Escribir en archivo binario
        
        String nombre=p.getNombres();
        String apellido=p.getApellidos();
        String cedula=p.getCedula();
        int edad=p.getEdad();
        String fecha2=formatter.format(p.getFechaNacimiento());
        String celular=p.getNumeroTelefono();
        Double salario=p.getSalario();
        //Rellena espacios hasta 50 caracteres en el nombre
        if(nombre.length()<50){
            for (int i=nombre.length(); i < 50; i++) {
                nombre=nombre+" ";
            }  
        }
        //Rellena espacios hasta 50 caracteres en el apellido
        if(apellido.length()<50){
            for (int i=apellido.length(); i < 50; i++) {
                apellido=apellido+" ";
            }
        }
        archivo.writeUTF(nombre);
        archivo.writeUTF(apellido);
        archivo.writeUTF(cedula);
        archivo.writeInt(edad);
        archivo.writeUTF(fecha2);
        archivo.writeUTF(celular);
        archivo.writeDouble(salario);
        
            System.out.println("total bytes: "+archivo.length());
        archivo.close();
         }catch(FileNotFoundException ex9){
            System.out.println("Archivo no encontrado");
        } catch (IOException ex10) {
            System.out.println("Error de escritura");;
        }
       }
       /**
        * Metodo que sirve para leer un registro de una persona en el archivo binario a traves de la posicion del registro
        * @param pos
        * @return
        * @throws Exception 
        */
       public Persona Leer(int pos) throws Exception{
           pos=pos-1;
           pos=pos*152;
           try{
        RandomAccessFile archivo=new RandomAccessFile(ruta,"rw");
        archivo.seek(pos);
        Persona p=new Persona();
        //Leer archivo binario
        archivo.seek(pos);
         if(archivo.readUTF()!=null){
        archivo.seek(pos);     
        p.setNombres(archivo.readUTF().trim());
        archivo.seek(pos+52);
        p.setApellidos(archivo.readUTF().trim());
        archivo.seek(pos+104);
        p.setCedula(archivo.readUTF());
        archivo.seek(pos+116);
        p.setEdad(archivo.readInt());
        archivo.seek(pos+120);
        String fecha=archivo.readUTF();
        Date fecha1=formatter.parse(fecha);
        p.setFechaNacimiento(fecha1);
        archivo.seek(pos+132);
        p.setNumeroTelefono(archivo.readUTF());
        archivo.seek(pos+144);
        p.setSalario(archivo.readDouble());
        archivo.close();
        return p;
         }else{
            JOptionPane.showMessageDialog (null,"La persona No existe");
                 
        }
        }catch(FileNotFoundException ex){
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error de escritura");;
        }
        return null;   
       }
       /**
        * Metodo que sirve para Actualizar un Persona en el archivo binario a traves de la posicion
        * @param p
        * @param pos 
        */
        public void ActualizarPersona(Persona p,int pos){
        try{
        RandomAccessFile archivo=new RandomAccessFile(ruta,"rw");
        //Escribe desde el final del archivo
         pos=pos-1;
         pos=pos*152;
        archivo.seek(pos);
        //Escribir en archivo binario
        if(archivo.readUTF()!=null){
        archivo.seek(pos);    
        String nombre=p.getNombres();
        String apellido=p.getApellidos();
        String cedula=p.getCedula();
        int edad=p.getEdad();
        String fecha2=formatter.format(p.getFechaNacimiento());
        String celular=p.getNumeroTelefono();
        Double salario=p.getSalario();
        //Rellena espacios hasta 50 caracteres en el nombre
        if(nombre.length()<50){
            for (int i=nombre.length(); i < 50; i++) {
                nombre=nombre+" ";
            }  
        }
        //Rellena espacios hasta 50 caracteres en el apellido
        if(apellido.length()<50){
            for (int i=apellido.length(); i < 50; i++) {
                apellido=apellido+" ";
            }
        }
        archivo.writeUTF(nombre);
        archivo.writeUTF(apellido);
        archivo.writeUTF(cedula);
        archivo.writeInt(edad);
        archivo.writeUTF(fecha2);
        archivo.writeUTF(celular);
        archivo.writeDouble(salario);
        
            System.out.println("total bytes: "+archivo.length());
        archivo.close();
        }else{
            JOptionPane.showMessageDialog (null,"La persona No existe");
        }
         }catch(FileNotFoundException ex9){
            System.out.println("Archivo no encontrado");
        } catch (IOException ex10) {
            System.out.println("Error de escritura");;
        }
        
       }
       /**
        * Metodo que sirve para eliminar una persona
        * @param pos 
        */ 
       public int EliminarPersona(int pos){
           try{
        RandomAccessFile archivo=new RandomAccessFile(ruta,"rw");
        pos=pos-1;
        pos=pos*152;
        archivo.seek(pos);   
        String vacio=" ";
               for (int i = 1; i < 150; i++) {
                  vacio=vacio+" "; 
               }
           String v=archivo.readUTF();
               if(v.equals(vacio) ){
                   JOptionPane.showMessageDialog (null,"La persona Ya No Existe");
                   return 0;
                   }else{    
          archivo.seek(pos);           
          archivo.writeUTF(vacio);
         System.out.println("total bytes: "+archivo.length());
        archivo.close();
        return 1;
               }
 
         }catch(FileNotFoundException ex9){
            System.out.println("Archivo no encontrado");
        } catch (IOException ex10) {
            System.out.println("Error de escritura");;
        }
           return 0;
       } 
       /**
        * Retorna la lista de los registro grabados en la base de datos
        * @return 
        */
     

       
       
}
