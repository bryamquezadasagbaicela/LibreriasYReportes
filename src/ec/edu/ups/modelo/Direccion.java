/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.modelo;

/**
 *
 * @author José Quinde
 */
public class Direccion {
   //Declaracion variables
    private int codigo;
    private String calle_principal;
    private String calle_secundaria;
    private int numero;
    private String cedula_per;
    /**
     * Constructor vacio
     */
    public Direccion() {
    }
    /**
     * Constructor con todos los datos de direccion
     * @param codigo
     * @param calle_principal
     * @param calle_secundaria
     * @param numero
     * @param cedula_per 
     */
    public Direccion(int codigo, String calle_principal, String calle_secundaria, int numero, String cedula_per) {
        this.codigo = codigo;
        this.calle_principal = calle_principal;
        this.calle_secundaria = calle_secundaria;
        this.numero = numero;
        this.cedula_per = cedula_per;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCalle_principal() {
        return calle_principal;
    }

    public void setCalle_principal(String calle_principal) {
        this.calle_principal = calle_principal;
    }

    public String getCalle_secundaria() {
        return calle_secundaria;
    }

    public void setCalle_secundaria(String calle_secundaria) {
        this.calle_secundaria = calle_secundaria;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCedula_per() {
        return cedula_per;
    }

    public void setCedula_per(String cedula_per) {
        this.cedula_per = cedula_per;
    }
    /**
     * To String de direccion
     * @return 
     */
    @Override
    public String toString() {
        return "Direccion{" + "codigo=" + codigo + ", calle_principal=" + calle_principal + ", calle_secundaria=" + calle_secundaria + ", numero=" + numero + ", cedula_per=" + cedula_per + '}';
    }
    
    
}
