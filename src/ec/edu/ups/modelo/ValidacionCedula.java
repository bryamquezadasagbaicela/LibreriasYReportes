/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.modelo;

/**
 *
 * @author Jose Quinde
 */
public class ValidacionCedula{
   /**
    * Metodo que nos sirve para validar la cedula en la republica del Ecuador
    * @param x
    * @return 
    */ 
  public static boolean validacion(String x){
    int sum=0;
      int a[]=new int [x.length()/2];
      int b[]=new int [(x.length()/2)];
      int c=0;
      int d=1;
      for (int i = 0; i < x.length()/2; i++) {
        a[i]=Integer.parseInt(String.valueOf(x.charAt(c)));
        c=c+2;
        if (i < (x.length()/2)-1) {
          b[i]=Integer.parseInt(String.valueOf(x.charAt(d)));
          d=d+2;
        }
      }    
      for (int i = 0; i < a.length; i++) {
        a[i]=a[i]*2;
        if (a[i] >9){
          a[i]=a[i]-9;
        }
        sum=sum+a[i]+b[i];
      } 
      int aux=sum/10;
      int dec=(aux+1)*10;
      if ((dec - sum) == Integer.parseInt(String.valueOf(x.charAt(x.length()-1))))
        return true;
      else
        if(sum%10==0 && x.charAt(x.length()-1)=='0'){
          return true;
        }else{
          return false;
        }
  } 
}
