package ratonesclonados;

/**
 *
 * @author Alexander Olza
 * @website https://alexanderintoscience.wordpress.com
 */
public class Main {
    public static void main(String[] args) {

     Laboratorio laboratorio=new Laboratorio();
     laboratorio.añadir(50.0,Color.BLANCO,1,5,false);
     laboratorio.añadir(40.0,Color.NEGRO,1,5,false);
     laboratorio.estado_laboratorio();
     int i=0;
     while(i<15){
        i++;
        laboratorio.avanzar();
        laboratorio.estado_laboratorio();}
    }
}
