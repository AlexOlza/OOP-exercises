package ratonesclonados;
import java.util.ArrayList;
import java. util. Iterator;
import static ratonesclonados.Raton.aparear;
import static ratonesclonados.Raton.contador;
/**
 *
 * @author Alexander Olza
 */
enum Color{BLANCO,NEGRO,GRIS,ROJO,VERDE;}

/**
 *
 * @author alex
 */
public class Raton {
    //los att y met pueden ser
    //final, static (de la clase, no del obj)
    /**@param contador: Numero de ratones vivos.*/
    static int contador=0;
    double peso; 
    Color color;
    int edad;
    int esperanza_vida;
    final boolean clonado;
    /** Por defecto los ratones están vivos (true). Cuando sobrepasan su esperanza de vida mueren (vivo=false)*/
    boolean vivo=true;
    
    
    //CONSTRUCTOR: Mismo nombre que la clase. Sin return type
    public Raton(double peso, Color color, int edad, int esperanza_vida,boolean clonado){
        contador++;
        this.peso=peso;
        this.color=color;
        this.edad=edad;
        this.esperanza_vida=esperanza_vida;
        this.clonado=clonado;
        
    }
    /**Otro constructor con edad=0 y esperanza_vida=8 por defect
     * @param peso
     * @param color
     * @param clonadoo*/
    public Raton(double peso, Color color,boolean clonado){
        this(peso,color,0,8,clonado);
      }
    
    //Usar explicitamente el destructor en java es habitualmente mala idea
    //El garbaje collector se encarga automaticamente de todo
    /**Este metodo se invoca cuando los ratones llegan a su esperanza de vida. Decrementa el contador de ratones y asigna vivo=false.*/
    protected void muerte(){
        contador--;
        vivo=false;
        System.out.println("Ha muerto un ratón");
    }
    static void nombreLatin(){
        System.out.println("Mus Musculus");
    }//static pq puedo llamarlo sin crear ningun obj
    
    /** * Constructor de copia.Clonación de un ratón (con menor esperanza de vida)
     * @param otro*/
    public Raton(Raton otro){
        contador++;
        color=otro.color;
        peso=otro.peso;
        edad=0;
        esperanza_vida=4*otro.esperanza_vida/5;//los clones viven menos
        clonado=true;
        System.out.println("Clonacion");
    }
    /** * Crea un nuevo ratón (no clonado).Su color depende de los colores de los progenitores.
     * @param padre
     * @param madre
     * @return */
    public static Raton aparear(Raton padre, Raton madre){
        Color color_hijo;
        System.out.println("Apareando");
        if(padre.color==madre.color)color_hijo=padre.color;
        else color_hijo=Color.GRIS;
        Raton hijo=new Raton((padre.peso+madre.peso)/2.0,color_hijo,false);
        return hijo;
    }
    /**Incrementa edad. 
     * Cuando llegan a la mitad de su vida, los clones se vuelven rojos y el resto verdes.
     * En el final de la vida se invoca muerte().*/
    public void envejecer(){
        edad++;
        if(clonado==true && edad>Math.floor(esperanza_vida/2.0)){
            color=Color.ROJO;
            if(edad==Math.floor(esperanza_vida/2.0)){System.out.println("El raton se ha vuelto rojo");}}
        if(clonado==false && edad>Math.floor(esperanza_vida/2.0)){
            color=Color.VERDE;
            if(edad==Math.floor(esperanza_vida/2.0)){System.out.println("El raton se ha vuelto verde");}}
        if(edad==esperanza_vida) this.muerte();
    }
  
}
/**Una colección de ratones.*/
class Laboratorio{
    ArrayList <Raton> ratones=new ArrayList <Raton>();
    
    /**Añade ratones al laboratorio.*/
    public void añadir(double peso, Color color, int edad, int esperanza_vida,boolean clonado){
        Raton nuevo=new Raton(peso,color,edad,esperanza_vida,clonado);
        ratones.add(nuevo);
    }
 //   public void añadir(double peso, Color color,boolean clonado){ratones.add(new Raton(peso,color,clonado));}
   // public void añadir(Raton raton){ratones.add(new Raton(raton));}
    
    /**Muestra el numero de ratones vivos, con los atributos de cada uno.*/
    public void estado_laboratorio(){
        System.out.println("Color, Edad, Peso, Clonado, Esperanza de vida");
        for (Raton raton:ratones){System.out.println(raton.color+"\t"+raton.edad+"\t"+raton.peso+"\t"+raton.clonado+"\t"+raton.esperanza_vida);}
        System.out.println("Numero de ratones:"+Raton.contador);
    }
    /**Elimina los ratones muertos del laboratorio.*/
    public void eliminarMuertos(){
        
        Iterator<Raton> iter = ratones.iterator();
        //Voy a mirar cada raton y eliminarlo si esta muerto
        while (iter.hasNext()) {
        Raton raton = iter.next(); 
        if (raton.vivo==false) iter.remove();
           }
        //for(Raton raton:ratones){if(raton.vivo==false){ratones.remove(raton);}} da nullpointer exception
    }
    /**Avance en el tiempo. Los ratones envejecen.
     * Se eliminan los muertos.
     * Aleatoriamente dos ratones se aparean o un ratón se clona, en ambos casos se añade el nuevo ratón al laboratorio.*/
    public void avanzar(){
      Raton hijo,clon,padre_aleatorio,madre_aleatorio;
      if(ratones.isEmpty()){System.out.println("No quedan ratones. FIN."); System.exit(0);}
      for (Raton raton:ratones){
          raton.envejecer();
      } 
      this.eliminarMuertos();
      int i = (int)(Math.random() * ratones.size() );
      int j = (int)(Math.random() * ratones.size() );
      if (((i+j)%2==0) & (i!=j)){
        padre_aleatorio=ratones.get(i);
        madre_aleatorio=ratones.get(j);
        hijo=aparear(padre_aleatorio,madre_aleatorio);
        ratones.add(hijo);}
      else{
        clon=new Raton(ratones.get(i));
        ratones.add(clon);}
    }
    
}