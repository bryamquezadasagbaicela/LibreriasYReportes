/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.modelo;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author José Quinde
 */
public class Persona {
    //Declaracion variables
   private String cedula;
   private String nombres;
   private String apellidos;
   private int edad;
   private String numeroTelefono;
   private Date fechaNacimiento;
   private Double salario;
   private String genero;

    /**
     * Constructor vacio
     */
    public Persona() {
    }

    public Persona(String cedula, String nombres, String apellidos, int edad, String numeroTelefono, Date fechaNacimiento, Double salario, String genero) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.numeroTelefono = numeroTelefono;
        this.fechaNacimiento = fechaNacimiento;
        this.salario = salario;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Constructor con todos los datos
     */
  

    
  
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula)throws Exception {
        //Valida que se ingrese 10 digitos
        if(cedula.length()==10){
            //Valida el numero de cedula
        }
        
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres)throws Exception{  
        int cont=0;
    for (int i = 0; i <nombres.length ();i++) { 
        char c = nombres.charAt (i); 
        //Controla que no ingresen numeros en el nombre
       if(c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9'||c=='0'){
          throw new Exception("Error al ingresar el Nombre----Ingrese solo letras y no numeros");
       }
       if(c==' '){
           cont++;                
       }
    }
    //Controla que se ingrese dos nombres
    if(cont==1){
      this.nombres = nombres;   
   
    }
    }
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos)throws Exception {      
         int cont=0;
    for (int i = 0; i <apellidos.length ();i++) { 
        char c = apellidos.charAt (i); 
        //Controla que no ingresen numeros en el apellido
       if(c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9'||c=='0'){
          throw new Exception("Error al ingresar el Apellido----Ingrese solo letras y no numeros");
       }
       if(c==' '){
           cont++;                
       }
    }
    //Controla que se ingrese dos apellidos
    if(cont==1){
       this.apellidos = apellidos; 
    }
    }

    public int getEdad() {
        return edad;
    }

     public void setEdad(int edad)throws Exception {
        if(edad >= 20 && edad<= 35){
        this.edad = edad;
        }
     }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) throws Exception {
          if(numeroTelefono.length()==10){
            //Valida el numero de telefono
        this.numeroTelefono = numeroTelefono;
          }
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
     
    @Override
    public String toString() {
        return "Persona\n" + 
                "Cedula=" + cedula + 
                "\nNombres=" + nombres + 
                "\nApellidos=" + apellidos + 
                "\nEdad=" + edad +
                "\nFecha Nacimiento =" + fechaNacimiento +
                "\nCelular =" + numeroTelefono+
                "\nSalario =" + salario+
                "\nGenero ="+ genero;
    }

    
   
}
