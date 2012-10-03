/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Point;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author roberacc
 */
public class PoligonoFinal {

    int tamaño;
    int inicialY;
    int finalY;
    int k = 0;
    int[] indiceY;
    int[] out;
    boolean acep = false;
    ArrayList indi;
    Vector vIniciales = new Vector();
    Vector vAux = new Vector();
    Vector vPuntos = new Vector();
    
    
    Vector v3 = new Vector();

    public PoligonoFinal() {

        Point[] puntosA = {new Point(-4, -5), new Point(4, -3), new Point(8, 8),
            new Point(5, 5), new Point(5, 10), new Point(-3, 8), new Point(0, 0)};
        Point[] puntosB = {new Point(-4,-5), new Point (0,-3), new Point (8,-8),
            new Point (5,8), new Point (5,12), new Point (0,10), new Point (-5, 8)};  
        
//        arregloInicial(puntosB);
//        scanLine(puntosB);
        
        arregloInicial(puntosA);
        scanLine(puntosA);
        vPuntosFinales();

    }

    //Crea el Vector auxiliar
    void scanLine(Point[] puntos) {

        for (int i = 0; i < puntos.length; i++) {

            if (i == 0) {
                vIniciales.add(ordenVector(puntos[puntos.length - 1], puntos[i], puntos[i + 1], puntos[i].y));
                continue;
            }

            if (i == puntos.length - 1) {
                vIniciales.add(ordenVector(puntos[i - 1], puntos[i], puntos[0], puntos[i].y));
                break;
            }
            
            if(puntos[i].y > puntos[i-1].y && puntos[i].y > puntos[i+1].y){
                continue;
            }

            vIniciales.add(ordenVector(puntos[i - 1], puntos[i], puntos[i + 1], puntos[i].y));

        }
        
    }

    //Genera el vector con los vectores finales
    void vPuntosFinales() {
        
        int idx = indiceY[0];
        vPuntos.add(obtieneVectorFinal(vIniciales, idx));
        Vector text = (Vector) vPuntos.get(0);
        v3 = text;
        
        
            for (int i = 0; i < text.size(); i++) {
                double[] mostrar = new double[10];
                mostrar= (double[]) text.get(i);
                System.out.println("vectorMostrar ["+mostrar[0]+", "+mostrar[1]+", "+mostrar[2]+"] ");
            }
        
            for (int i = 1; i < tamaño + 1; i++) {

                idx = indiceY[i];
                Vector vec =(obtieneVectorFinal(vIniciales, idx));

                    if(acep && i > 1){
                        v3 = (incrementoAutomatico(v3));
                        v3 = unirVectores(v3, vec);
                        v3 = EliminaFinales(v3, idx);
                        
                        vPuntos.add(v3);
                        Vector vPuntosFinales = (Vector) vPuntos.get(i);

                            for(int r = 0; r < vPuntosFinales.size(); r++){
                            double[] point = new double[3];

                            point = (double[]) vPuntosFinales.get(r);
                            System.out.println("punto: en "+r+" ["+point[0]+", "+point[1]+", "+point[2]+"]");

                            }
                    acep = false;
                    continue;
                    }

                    v3 =(incrementoAutomatico(v3));
                    v3 = EliminaFinales(v3, idx);

                vPuntos.add(v3);

                Vector vPuntosFinales = (Vector) vPuntos.get(i);

                    for(int r = 0; r < vPuntosFinales.size(); r++){
                    double[] point = new double[3];

                        point = (double[]) vPuntosFinales.get(r);
                        System.out.println("punto: en "+r+" ["+point[0]+", "+point[1]+", "+point[2]+"]");

                   }
            }

    }
    
    //Une un vector del vector auxiliar, con el vector incrementado
    Vector unirVectores(Vector v1, Vector v2){
       
        for (int i = 0; i < v2.size(); i++) {
            double[] arr = new double[3];
            arr = (double[]) v2.get(i);
            v1.add(arr);
        }
        
        acep = false;
        return v1;
    }

    Vector EliminaFinales(Vector vec, int ind) {       
        
        double[] arreglo = new double[3];

        for (int i = 0; i < vec.size(); i++) {
            arreglo = (double[]) vec.get(i);

            if (k == 15) {
                k = 0;
            }
            if (arreglo[0] == ind) {
                System.out.println("elimina... ");

                vec.remove(i);
                i--;
                continue;
            }
            
        }
        
        return vec;       
   }

    //Ingresa los arreglos del Vector Auxiliar
    Vector obtieneVectorFinal(Vector vectorF, int indi) {

        Vector outt = new Vector();
        Vector out = new Vector();

        System.out.println("indice: "+indi);
        for (int i = 0; i < vIniciales.size(); i++) {
            out = (Vector) vIniciales.get(i);

            if (indi == out.get(0)) {
                Vector outAux = new Vector();

                for (int y = 1; y < out.size(); y++) {
                    outAux.add(out.get(y));
                }                
                
                acep = true;
                System.out.println(acep);
                
                return outAux;
            }
            
        }
        
        return outt;
    }

    //Recibe el vector con los arreglos para ser calculados
    Vector incrementoAutomatico(Vector a) {
                
        Vector outFinal = new Vector();
        double[] dato = new double[3];

        for (int i = 0; i < a.size(); i++) {
            dato = (double[]) a.get(i);
            dato = puntoCalculado(dato);
            outFinal.add(dato);
        }
        
        return outFinal;
    }

    //Retorna arreglo calculado
    double[] puntoCalculado(double[] dato) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", simbolos);
        
        double[] out = new double[3];
        out[0] = dato[0];
        out[1] = Double.parseDouble(df.format(dato[1] + dato[2]));
        out[2] = dato[2];
        
        return out;
    }

    //genera el primer vector con su respectivo indice
    Vector ordenVector(Point puntoA, Point puntoB, Point puntoC, int y) {

        Vector a = new Vector();
        double[] punto1 = new double[3];
        double[] punto2 = new double[3];

        a.add(y);
        if (puntoB.y < puntoA.y) {
            punto1[0] = puntoA.y;
            punto1[1] = puntoB.x;
            punto1[2] = pendiente(puntoA.x, puntoA.y, puntoB.x, puntoB.y);


            a.add(punto1);
        }
        if (puntoB.y < puntoC.y) {
            punto2[0] = puntoC.y;
            punto2[1] = puntoB.x;
            punto2[2] = pendiente(puntoC.x, puntoC.y, puntoB.x, puntoB.y);


            a.add(punto2);
        }
//        System.out.println("Punto 1: en:"+y+" ["+punto1[0]+", "+punto1[1]+", "+punto1[2]+" ]" );
//        System.out.println("Punto 2: en:"+y+" ["+punto2[0]+", "+punto2[1]+", "+punto2[2]+" ]" );

        return a;
    }

    //Obtiene la pendiente entre dos puntos
    double pendiente(int x1, int y1, int x2, int y2) {

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", simbolos);

            double pend = (double) (y2 - y1) / (x2 - x1);
            double salida;

                if (x2 - x1 == 0 || y2 - y1 == 0) {
                    
                    return 0;
                }

            salida = 1 / pend;
                
        return Double.parseDouble(df.format(salida));
    }

    //crea un arreglo del menor al mayor punto en el eje Y
    void arregloInicial(Point[] puntos) {

        inicialY = puntos[0].y;
        finalY = puntos[0].y;

        for (int i = 0; i < puntos.length; i++) {

            if (puntos[i].y < inicialY) {
                inicialY = puntos[i].y;
            }
            
            if (puntos[i].y > finalY) {
                finalY = puntos[i].y;
            }
        }
        tamaño = finalY - inicialY;
        indiceY = new int[tamaño + 1];
        indiceY[0] = inicialY;

        for (int i = 1; i <= tamaño; i++) {
            indiceY[i] = indiceY[i - 1] + 1;
        }

    }

    public static void main(String[] arg) {

        new PoligonoFinal();
        
    }
    
}
