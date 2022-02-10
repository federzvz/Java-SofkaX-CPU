import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int arraySize;

        while (true) {
            System.out.println("Tamaño del array: [Número de instrucciones]");
            arraySize = sc.nextInt();
            sc.nextLine();

            if (arraySize > 1024) {
                System.out.println("Tamaño del array muy grande! vuelve a ingresar\n");
                continue;
            } else {
                break;
            }
        }

        String[] subrutina = new String[arraySize];


        System.out.println("Instrucciones del array --> Ej: MOV 10,R05");
        for (int i = 0; i < subrutina.length; i++) {
            subrutina[i] = sc.nextLine();  //Guardamos cada valor en su posicion
        }
        //System.out.println(subrutina[1]);
            //String[] subrutina = {"MOV 5,R00", "MOV 10,R01", "JZ 7", "ADD R02,R01", "DEC R00", "JMP 3", "MOV R02,R42"};
            //String[] subrutina = {"NOP","MOV 45,R42", "DEC R42", "INV R42"};
        procesarInstrucciones(subrutina);
    }

    /**
     * Método que ejecuta el proceso de leer las instrucciones
     * @param instrucciones arreglo que contiene las instrucciones en cadena de texto
     */
    public static void procesarInstrucciones(String[] instrucciones){
        int[] array = new int[43];
        int i=1;
        while(i<=instrucciones.length){
            switch (identificarComando(instrucciones,i-1)){
                case 1://Metodo MOV
                    metodoMOV(instrucciones[i-1], array);
                    i++;
                    break;
                case 2://Metodo ADD
                    metodoADD(instrucciones[i-1], array);
                    i++;
                    break;
                case 3://Metodo DEC FINALIZADO: FEDERICO
                    metodoDEC(ProcesoStringToIntDadoDosElementos(instrucciones[i-1]),array);
                    i++;
                    break;
                case 4://Metodo INC
                    metodoINC(ProcesoStringToIntDadoDosElementos(instrucciones[i-1]),array);
                    i++;
                    break;
                case 5://Metodo INV FINALIZADO: FEDERICO
                    metodoINV(ProcesoStringToIntDadoDosElementos(instrucciones[i-1]),array);
                    i++;
                    break;
                case 6://Metodo JMP
                    i = metodoJMP(instrucciones[i-1]);
                    break;
                case 7://Metodo JZ
                    i = metodoJZ(obtenerUnicoElemento (instrucciones[i-1]), array,i);
                    break;
                case 8://Metodo NOP
                    i++;
                    break;
                 default://La instrucción no contiene ninguna instrucción Válida
                    break;
            }
        }
        
        System.out.println("\nResultado: R42 --> " + array[42]);
        System.out.println("\nEquipo más pro!");
        
    }

    /**
     * Método que identifica el comando dada su posición en el arreglo de Strings
     * @param subrutina Cadena de texto que representa la instrucción
     * @param indiceDeInstrucciones posicion de la instrucción dada
     * @return valor numérico que representa la instrucción
     */
    public static int identificarComando(String[] subrutina, int indiceDeInstrucciones){
        //MOV return 1
        //ADD return 2
        //DEC return 3
        //INC return 4
        //INV return 5
        //JMP return 6
        //JZ  return 7
        //NOP return 8
        if(subrutina[indiceDeInstrucciones].contains("MOV")){
            return 1;
        }
        if(subrutina[indiceDeInstrucciones].contains("ADD")){
            return 2;
        }
        if(subrutina[indiceDeInstrucciones].contains("DEC")){
            return 3;
        }
        if(subrutina[indiceDeInstrucciones].contains("INC")){
            return 4;
        }
        if(subrutina[indiceDeInstrucciones].contains("INV")){
            return 5;
        }
        if(subrutina[indiceDeInstrucciones].contains("JMP")){
            return 6;
        }
        if(subrutina[indiceDeInstrucciones].contains("JZ")){
            return 7;
        }
        if(subrutina[indiceDeInstrucciones].contains("NOP")){
            return 8;
        }
        return 0;
    }

    /**
     * Extrae el valor numerico dado un String de registro
     * @param instruccionCompleta Cadena de texto correspondiente a una instrucción
     * @return valor numérico del argumento
     */
    public static int ProcesoStringToIntDadoDosElementos(String instruccionCompleta){
        int indexInicial = instruccionCompleta.indexOf('R');
        return Integer.parseInt(instruccionCompleta.substring(indexInicial + 1));
    }


    /**
     * Metodo para obtener el primer argumento de la instruccion actual
     * @param item instruccion actual -> elemento del array
     * @return retorna un argumento uno del string cortado
     */
    public static String obtenerPrimerElemento (String item) {
        int indexInicial = item.indexOf(' ');
        int indexFinal = item.indexOf(',');

        return item.substring(++indexInicial, indexFinal);
    }

    /**
     * Metodo para obtener el segundo argumento de la instruccion actual
     * @param item instruccion actual -> elemento del array
     * @return retorna un argumento dos del string cortado
     */
    public static String obtenerSegundoElemento (String item) {
        int indexInicial = item.indexOf(',');

        return item.substring(++indexInicial);
    }

    /**
     * Metodo para obtener el argumento de la instruccion actual
     * @param item instruccion actual -> elemento del array
     * @return retorna el argumento como entero del string cortado
     */
    public static int obtenerUnicoElemento (String item) {
            int index = item.indexOf(' ');
            return Integer.parseInt(item.substring(++index));
    }

    /**
     * Extrae el valor numerico dado un String de registro
     * @param argumento Cadena de carácteres correspondiente a una instrucción dada
     * @return retorna el valor numérico del registro
     */
    public static int posicionArray (String argumento) {
        String posicionSinR = argumento.substring(1);
        return Integer.parseInt(posicionSinR);
    }

    /**
     * Método que si el argumento 1 es un decimal, lo agrega a la posicion que designa el argumento2
     * Si el argumento 1 es posicion, lo copia a la posicion del argumento2
     * @param item Instrccion actual
     * @param array Array de registros
     */
    public static void metodoMOV(String item, int[] array) {
        String arg1 = obtenerPrimerElemento(item);
        String arg2 = obtenerSegundoElemento(item);
        int posicionArg1;
        int posicionArg2 = posicionArray(arg2);

        if (arg1.startsWith("R")) {
            posicionArg1 = posicionArray(arg1);
            array[posicionArg2] = array[posicionArg1];

        } else {
            int argDecimal = Integer.parseInt(arg1);
            array[posicionArg2] = argDecimal;
        }
    }

    /**
     * calcula (Rxx + Ryy) y almacena el resultado en el registro Rxx;
     * @param item Corresponde a la instrucción con sus respectivos argumentos.
     * @param array Arreglo de registros.
     */
    public static void metodoADD(String item, int[] array) {
        //Obtiene los argumentos
        String arg1 = obtenerPrimerElemento(item);
        String arg2 = obtenerSegundoElemento(item);

        //Los argumentos son posicion del array
        int posicionArg1 = posicionArray(arg1);

        int posicionArg2 = posicionArray(arg2);

        array[posicionArg1] += array[posicionArg2];
    }

    /**
     * Decrementa el valor de un registro específico
     * @param indexRegistro Indica el índice del registro en el arreglo de los mismos
     * @param array Arreglo de registros.
     */
    public static void metodoDEC(int indexRegistro, int[] array){
        array[indexRegistro]--;
    }

    /**
     * Obtiene el valor numericode de
     * @param indexRegistro
     * @param array
     */
    public static void metodoINV(int indexRegistro, int[] array){
        int valorRegistro=array[indexRegistro];
        //Convertir Int to Binario
        String binario = Integer.toBinaryString(valorRegistro);
        //Invertir Binario
        String binarioAux= binario.replace('0', '2').replace('1', '0').replace('2', '1');
        //Convertir binario to Int
        int valorRegistroBinarioInvertido= Integer.parseInt(binarioAux, 2);
        array[indexRegistro]=valorRegistroBinarioInvertido;
    }

    /**
     * Método para saltar a la posicion especificada en la instrucción
     * @param item instruccion actual
     * @return retorna el indice al que se salta según la instrucción
     */
    public static int metodoJMP(String item) {
           return obtenerUnicoElemento(item);
    }

    /**
     * Salta a la posicion indicada siempre que R00 sea igual a 0
     * @param posicion Es el número que indica la posicion a saltar
     * @param registros Es el arreglo de los registros
     * @param index es el índice de iteración que hace referencia a la posición de las instrucciones
     * @return el valor de la iteración
     */
    public static Integer metodoJZ(Integer posicion, int[] registros,int index) {
        if (registros[0]==0) {
            index = posicion;
            return index;
        } else {
            index++;
            return index;
        }
    }

    /**
     * Incrementa el valor del registro en 1 unidad, siempre y cuando el valor del registro+1<2^32-1
     * @param indexRegistro  Índice del array de registro.
     * @param array Arreglo de registros.
     */
    public static void metodoINC(int indexRegistro, int[] array){
        if(array[indexRegistro]+1==(int)Math.pow(2,32)-1){
            array[indexRegistro]=0;
        }
        array[indexRegistro]++;
    }
}
