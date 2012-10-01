package logica;

import java.lang.*;
import java.util.*;
import java.io.*;

/** CloseWords: Saca probecho de la tendencia a la aglomeración que 
 * posee el método hashCode() de la clase String para buscar palabras "cercanas" 
 * al argumento.
 */
public class CloseWords {
    Hashtable ht;
    String currString;
    String[] args;

    /** En el código siguiente creamos una instancia de la clase Hashtable en la cual almacenaremos
     * nuestra lista de todas las palabras en el dicionario del sistema (si, es muy ineficiente
     * almacenar en memoria palabras mediante este indexado).
     * 
     * @param args 
     */
    CloseWords(){
        
        ht = new Hashtable();
	try
	    {
		DataInputStream in = new DataInputStream(
							 new BufferedInputStream(
										 new FileInputStream("/usr/dict/words")));
		while((currString = in.readLine())!=null)
		    ht.put(new Integer(currString.hashCode()), currString);

		int i = args[0].hashCode();
		int found=0;

		while(found < 5)
		    {
			i--;
			if(ht.get(new Integer(i))!=null)
			    {
				System.out.println(ht.get(new Integer(i)));
				found++;
			    }
		    }
		i = args[0].hashCode();
		found=0;

		while(found < 5)
		    {
			i++;
			if(ht.get(new Integer(i))!=null)
			    {
				System.out.println(ht.get(new Integer(i)));
				found++;
			    }
		    }
	    }
	catch(IOException ioe)
	    {
		    System.out.println("IO Exception");
	    }
    }

        
    
    
    
    public static void main(String[] args)
    {
        new CloseWords();
	 /** En el código siguiente creamos una instancia de la clase Hashtable en la cual almacenaremos
     * nuestra lista de todas las palabras en el dicionario del sistema (si, es muy ineficiente
     * almacenar en memoria palabras mediante este indexado).
     * 
     * @param args 
     */
	
    }
}