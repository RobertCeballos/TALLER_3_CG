/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Point;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Owner
 */
public class Poligono {

    /**
     * @param args the command line arguments
     */
    Point[] puntoOrdenado;
    int indice = 0;
    int altura = 0;
    int maximo = 0;
    int minimo = 0;
    ArrayList idx;
    Vector puntoA, puntoB, puntoC;

    //Constructor
    public Poligono() {
        
        Point punto = new Point();
        Point[] puntos = {new Point(-4, -5), new Point(4, -3), new Point(8, 8),
            new Point(5, 5), new Point(5, 10), new Point(-3, 8), new Point(0, 0)};

        altura = dimension(puntos);     
        indice = puntoInicial(puntos);
        
        
        puntoOrdenado=ArregloOrdenado(puntos);
        arreglaPuntoC(puntoOrdenado);
        
    }
    
    //obtiene un arreglo ordenado con el primer y ultimo punto repetido
    Point[] ArregloOrdenado(Point[] punto){
        Point[] puntos = new Point[punto.length+2];
        
        for(int i = 0; i <= punto.length+1; i++){
            
            if(indice == 0){                
                puntos[i] = punto[punto.length-1];
                indice++;
                continue;
            }
            if(i == punto.length+1){
                puntos[i] = punto[0];
                break;
            }
            puntos[i] = punto[indice-1];
            indice++;
          
        }
        
                return puntos;
    }
    
    
    Vector arreglaPuntoC(Point[] puntos) {
        
        int ii = 1;
        Vector pointC = new Vector();
        double[] arregloY = new double[altura+1];
        double[] punto1 = new double[altura+1];
        double[] punto2 = new double[altura+1];
       
        for(int i = 0; i<=altura; i++){
            if(i==0){
                String datoS = idx.get(i).toString();
                double dato = Integer.parseInt(datoS);
                arregloY[i] = dato;
                continue;
            }
            arregloY[i] = arregloY[i-1] + 1;
            
        }
        
            for(int i = 1; i<=altura; i++){                               
                System.out.println("yyyy"+arregloY[15]);
                
                punto1[0] = puntos[i+1].y;
                punto1[1] = puntos[i].x;
                punto1[2] = pendiente(puntos[i].x, puntos[i].y, puntos[i+1].x, puntos[i+1].y);              

                pointC.add(punto1);
                System.out.println("(" +punto1[0]+", "+punto1[1]+", "+punto1[2]+") ("+punto2[0]+", "+punto2[1]+", "+punto2[2]+")");
                
                while(arregloY[ii] != punto1[0] ){
                    punto1[0] = punto1[0];
                    punto1[1] = puntoNuevo(punto1[1], punto1[2]);
                    punto1[2] = punto1[2]; 
                 
                    ii++;
                    System.out.println("ii: "+ii);
                    System.out.println("(" +punto1[0]+", "+punto1[1]+", "+punto1[2]+") ("+punto2[0]+", "+punto2[1]+", "+punto2[2]+")");
                    pointC.add(punto1);
                    if(ii == 15)
                        break;
                }
                    ii++;
                    if(ii == 14){
                        i=altura;
                    }
            //pointC.add(punto1);
            pointC.add(punto2);


            }
            
            for(int i = 0; i<=10; i++){
                
                double[] out= new double[2];
                Vector<double[]> prueba = new Vector();
                
                out[0] = 2;
                out[1] = 4;
                prueba.addElement(out);
                double[] outt = new double[2];
                try{ 
                //outt[0] = Double.parseDouble(pointC.elements().toString());
                    outt = prueba.get(0);
                    double a = outt[0];
                    double b = outt[1];
                    System.out.print(a+"................................................"+b);
                //outt[0] = Double.parseDouble(prueba.elementAt(0).toString());  
                }catch(Exception e){
                    
                }
               
//                try{        
//                out[i]=Double.parseDouble(pointC.get(i).toString());        
//                }catch(Exception e){
//                    
//                }    
//                
          System.out.println("outttttttt "+i+" "+outt[0]);
            }
        
        return pointC;
    }
    
    
    //determina la posicion del punto inicial
    int puntoInicial(Point[] puntos){
        
        int posicion = 0;
        int inicial = puntos[0].y;
         idx= new ArrayList();
        
        for(int i = 0; i < puntos.length; i++){
            if(puntos[i].y < inicial){
                inicial = puntos[i].y;
            
            }
               idx.add(puntos[i].y);
        }
        posicion = idx.indexOf(inicial);     
        
        return posicion;
    }
    
//Entra un arreglo de puntos y retorna la altura del poligono
    int dimension(Point[] puntos) {

        int altura = 0;
        maximo = puntos[0].y;
        minimo = puntos[0].y;

        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i].y > maximo) {
                maximo = puntos[i].y;
            }
            if (puntos[i].y < minimo) {
                minimo = puntos[i].y;
            }

        }
        
        altura = maximo-minimo;
        
        return altura;
    }       
    
    
    double puntoNuevo(double a, double b){
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", simbolos); 
        
        double nuevoX = a + b;
        
        return Double.parseDouble(df.format(nuevoX));
    }
   
    
    
    double pendiente(int x1, int y1, int x2, int y2) {

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", simbolos); 
        
        
            double pend = (double) (y2 - y1) / (x2 - x1);
            double salida;

                 if(x2 - x1 == 0 || y2 -y1 == 0){           
                 return 0;
                 } 
            salida = 1 / pend;
            salida = Double.parseDouble( df.format(salida));
        
        return salida;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        
         
        
        new Poligono();


    }
}
