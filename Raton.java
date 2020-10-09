package ratonesclonados;

/**
 *
 * @author alex
 */
public class Raton {
    //los att y met pueden ser
    //final, static (de la clase, no del obj)
    //...
    double peso; //el peso
    String color;
    int edad;
    int esperanza_vida;
    final boolean clonado;
    
    //CONSTRUCTOR: Mismo nombre que la clase. Sin return type
    public Raton(double peso, String color, int edad, int esperanza_vida,boolean clonado){
        this.peso=peso;
        this.color=color;
        this.edad=edad;
        this.esperanza_vida=esperanza_vida;
        this.clonado=clonado;
            System.out.println("Nuevo raton");
        
    }
    //Otro constructor con edad y esper por defecto
    public Raton(double peso, String color,boolean clonado){
        this.peso=peso;
        this.color=color;
        this.edad=0;
        this.esperanza_vida=8;
        this.clonado=clonado;
            System.out.println("Ha nacido un raton");
        
    }
    static void nombreLatin(){
        System.out.println("Mus Musculus");
    }//static pq puedo llamarlo sin crear ningun obj
    
    public Integer getEsperanza_vida(){
        return esperanza_vida;
    }
    public Boolean getClonado(){
        return clonado;
    }
    //Constructor de copia
    public Raton(Raton otro){
        color=otro.color;
        peso=otro.peso;
        edad=otro.edad;
        esperanza_vida=4*otro.esperanza_vida/5;//los clones viven menos
        clonado=true;
        System.out.println("Clonacion");
    }
    //destructor: No hay en Java
    public void describir(){
        System.out.println("Un raton"+(clonado?" clonado, ":", ")+color+" de edad "+edad+" que pesa "+peso);
    }
    public void envejecer(){
        edad++;
        if(clonado==true && edad>Math.floor(esperanza_vida/2.0)){
            color="rojo";
            if(edad==Math.floor(esperanza_vida/2.0)){System.out.println("El raton se ha vuelto rojo");}}
        if(clonado==false && edad>Math.floor(esperanza_vida/2.0)){
            color="verde";
            if(edad==Math.floor(esperanza_vida/2.0)){System.out.println("El raton se ha vuelto verde");}}
    }
    public void evolucionar(){
        while(edad<esperanza_vida){
            envejecer();
            describir();
        }
        color="muerto";
        describir();
    }
  
}
