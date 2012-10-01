/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Point;
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
public class ScanPoligono {

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
    public ScanPoligono() {
        
        Point punto = new Point();
        Point[] puntos = {new Point(-4, -5), new Point(4, -3), new Point(8, 8),
            new Point(5, 5), new Point(5, 10), new Point(-3, 8), new Point(0, 0)};

        altura = dimension(puntos);     
        indice = puntoInicial(puntos);
        System.out.println("idx "+idx.indexOf(0));
        System.out.println("indice "+indice);
        System.out.println("altura "+altura);
        System.out.println("tamañoArregloInicial "+puntos.length);
        System.out.println();
        
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
                System.out.println("nuevo arreglo "+puntos[i]);
                continue;
            }
            if(i == punto.length+1){
                puntos[i] = punto[0];
                System.out.println("nuevo arreglo "+puntos[i]);
                break;
            }
            puntos[i] = punto[indice-1];
            indice++;
          
            System.out.println("nuevo arreglo "+puntos[i]);
        }
        
                return puntos;
    }
    
    
    Vector arreglaPuntoC(Point[] puntos){
        
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

                int ii = 1;
                punto1[0] = puntos[i+1].y;
                punto1[1] = puntos[i].x;
                punto1[2] = pendiente(puntos[i].x, puntos[i].y, puntos[i+1].x, puntos[i+1].y); 

                System.out.println("(" +punto1[0]+", "+punto1[1]+", "+punto1[2]+
                    ") ("+punto2[0]+", "+punto2[1]+", "+punto2[2]+")");

                pointC.add(punto1);
                while(arregloY[ii] != punto1[0]){
                    System.out.println("i: "+i);
                    //punto1[0] = puntos[i+1].y;
                    punto1[1] = puntoNuevo(punto1[1], punto1[2]);
                    punto1[2] = punto1[2]; 

                    System.out.println("(" +punto1[0]+", "+punto1[1]+", "+punto1[2]+
                    ") ("+punto2[0]+", "+punto2[1]+", "+punto2[2]+")");
                    ii++;
                    System.out.println("ii: "+ii);
                    pointC.add(punto1);
                }
    //            punto2[0] = puntos[i-1].y;
    //            punto2[1] = puntos[i].x;
    //            punto2[2] = pendiente(puntos[1].x, puntos[1].y, puntos[i-1].x, puntos[i-1].y);
    //        System.out.println("i "+i);
    //        
    //        System.out.println("(" +punto1[0]+", "+punto1[1]+", "+punto1[2]+
    //                ") ("+punto2[0]+", "+punto2[1]+", "+punto2[2]+")");

            //pointC.add(punto1);
            pointC.add(punto2);


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
               System.out.println("aaaaarreglo idx = "+idx.get(i));
        }
        posicion = idx.indexOf(inicial);
//        int idx = Arrays.binarySearch(puntos, inicial);
        //System.out.println("index "+ posicion +" inicial "+inicial);
        
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
        //System.out.println("altura = "+altura);
        
        return altura;
    }
    
//    ArrayList generaArregloSencillo(){
//        int valorY = minimo;
//        
//        ArrayList ldr = new ArrayList(altura);
//        for(int i=0; i<altura+1; i++){
//                ldr.add(valorY);
//                valorY++;
//                             
//            }
//        return ldr;
//    }
    
    
    double puntoNuevo(double a, double b){
        
        double nuevoX = a + b;
        
        return nuevoX;
    }

    
//    ArrayList algoritmoLDR(int alturaP, Point[] puntos, int pos) {
//
//        int altura = alturaP;
//        //System.out.println("altura "+altura);
//        
//        int anterior = 6;
//        
//        puntoA = new Vector();
//        puntoB = new Vector();
//        
//        double[] datoA= new double[3];
//        double[] datoB= new double[3];
//          
//                  
////        datoB[0] = puntos[anterior].y;
////        datoB[1] = puntos[pos].x;
////        datoB[2] = pendiente(puntos[pos].x, puntos[pos].y, puntos[pos+1].x, puntos[pos+1].y);
//        
////        for(int i = 1; i <= altura; i++){
////            
//////            if(datoA[0]==idx.get(i)){
//////                           
//////            }
////            
////            datoA[0] = puntos[pos+i].y;
////            datoA[1] = puntos[pos].x;
////            datoA[2] = pendiente(puntos[pos].x, puntos[pos].y, puntos[pos+1].x, puntos[pos+1].y);          
////        
////        puntoA.add(datoA);
////        //System.out.println("( "+datoA[0]+ " , "+ datoA[1] + " ,  "+datoA[2]+" )" );
////        
//////        while(datoA[0]!= idx.get(i)){
//////            //System.out.println("datoA "+datoA[0]);
//////            datoA[0] = puntos[pos+1].y;
//////            datoA[1] = puntoNuevo(datoA[1], datoA[2]);
//////            datoA[2] = pendiente(puntos[pos].x, puntos[pos].y, puntos[pos+1].x, puntos[pos+1].y);
//////            i++;
//////            //System.out.println("( "+datoA[0]+ " , "+ datoA[1] + " ,  "+datoA[2]+" )" );
//////            
//////        }
////        
////          
////       }
//        
//        
//        
//        
//        
//            
//        //System.out.println("size "+ldr.size());
//        //ldr[0]=
//
//        return 0;
//        
//    }
    
    
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
    
//    void arregloOrdenY(){
//        
//         out = new int[indi.size()];
//        int z = 0;
//        
//        for(int i = 0; i < indice.length+1; i++){
//            for(int k = 0; k < indi.size(); k++){
//                if(i == 16)
//                    break;
//                if(indice[i]== indi.get(k)){
//                    out[z] = indice[i];
//                    //System.out.println("out "+out[i]);
//                    i++;
//                    z++;
//                    
//                }
//            }   
//        }         
//            System.out.println("out "+out[0]);
//            System.out.println("out "+out[1]);
//            System.out.println("out "+out[2]);
//            System.out.println("out "+out[3]);
//            System.out.println("out "+out[4]);
//            System.out.println("out "+out[5]);
//            System.out.println("out "+out[6]);
//    }
    

    public static void main(String[] args) {
        // TODO code application logic here
        
         
        
        new ScanPoligono();


    }
}
//for(int i = 0; i < eliminaV1.size(); i++){
//            System.out.println("salidaaaaaaaaaaaaaaa:"+i);
//           arreglo = (double[]) eliminaV1.get(i);
//            System.out.println("arreglo: ["+arreglo[0]+" ,"+arreglo[1]+" ,"+arreglo[2]+"]");
//            
//        }