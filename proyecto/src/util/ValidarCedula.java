/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author Darkane
 */
public class ValidarCedula {
    
    public static boolean valida(String x){
    int suma=0;
    if(x.length()==9){
      System.out.println("Ingrese su cedula de 10 digitos");
      return false;
    }else{
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
        suma=suma+a[i]+b[i];
      } 
      int aux=suma/10;
      int dec=(aux+1)*10;
      if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length()-1))))
        return true;
      else
        if(suma%10==0 && x.charAt(x.length()-1)=='0'){
          return true;
        }else{
          return false;
        }
     
  }
}

    void ConvertirObjectToString(Object z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
