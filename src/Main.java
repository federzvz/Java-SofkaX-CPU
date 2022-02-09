public class Main {
    public static void main(String[] args) {
            String[] subrutina = {"MOV 5,R00", "MOV 10,R01", "JZ 7", "ADD R02,R01", "DEC R00", "JMP 3", "MOV R02,R42"};
        procesarInstrucciones(subrutina);
    }

    public static void procesarInstrucciones(String[] instrucciones){
        int[] array = new int[43];
        int i=1;
        while(i<=instrucciones.length){
            switch (identificarComando(instrucciones,i-1)){
                case 1://Metodo MOV
                    //System.out.println("Instruccion:"+i+" es de tipo MOV");
                    //obtenerSegundoArgumentoDeTresElementos(instruccines[0]);
                    metodoMOV(instrucciones[i-1], array);
                    i++;
                    break;
                case 2://Metodo ADD
                    //System.out.println("Instruccion:"+i+" es de tipo ADD");
                    metodoADD(instrucciones[i-1], array);
                    i++;
                    break;
                case 3://Metodo DEC FINALIZADO: FEDERICO
                    //System.out.println("Instruccion:"+i+" es de tipo DEC");
                    //System.out.println(ProcesoStringToIntDadoDosElementos(instrucciones[i-1]));
                    metodoDEC(ProcesoStringToIntDadoDosElementos(instrucciones[i-1]),array);
                    i++;
                    break;
                case 4://Metodo INC
                   // System.out.println("Instruccion:"+i+" es de tipo INC");
                    i++;
                    break;
                case 5://Metodo INV FINALIZADO: FEDERICO
                    //System.out.println("Instruccion:"+i+" es de tipo INV");
                    metodoINV(ProcesoStringToIntDadoDosElementos(instrucciones[i-1]),array);
                    i++;
                    break;
                case 6://Metodo JMP
                    //System.out.println("Instruccion:"+i+" es de tipo JMP");
                    //System.out.println(obtenerUnicoArgumentoDadoDosElementos(instruccines[i-1]));
                    i = metodoJMP(instrucciones[i-1]);
                    break;
                case 7://Metodo JZ
                    //System.out.println("Instruccion:"+i+" es de tipo JZ");
                    //System.out.println(obtenerUnicoArgumentoDadoDosElementos(instruccines[i-1]));
                    i = metodoJZ(obtenerUnicoElemento (instrucciones[i-1]), array);
                    break;
                case 8://Metodo NOP
                    //System.out.println("Instruccion:"+i+" es de tipo NOP");
                    i++;
                    break;
                 default://La instrucción no contiene ninguna instrucción Válida
                    break;
            }
        }
        System.out.print(array[42]);
    }

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

    //Obtiene segundo argumento de MOV,ADD,ETC
    public static int obtenerSegundoArgumentoDeTresElementos(String instruccionCompleta){
        int indexInicial = instruccionCompleta.indexOf(' ');
        int indexFinal = instruccionCompleta.indexOf(',');
        //System.out.println(instruccionCompleta.substring(indexInicial+1, indexFinal));

        return Integer.parseInt(instruccionCompleta.substring(indexInicial+1, indexFinal));
    }

    //Obtiene tercer argumento de MOV,ADD,ETC
    public static int obtenerTercerArgumentoDeTresElementos(String instruccionCompleta){
        int indexInicial = instruccionCompleta.indexOf(' ');
        int indexFinal = instruccionCompleta.indexOf(',');
        //System.out.println(instruccionCompleta.substring(indexInicial+1, indexFinal));

        return Integer.parseInt(instruccionCompleta.substring(indexInicial+1, indexFinal));
    }

    //Obtiene segundo argumento de JZ,JMP -->> Este metodo está modificado(obtenerUnicoElemento)
    public static int obtenerUnicoArgumentoDadoDosElementos(String instruccionCompleta){
        if(instruccionCompleta.contains("JZ") || instruccionCompleta.contains("JMP")) {
            int indexInicial = instruccionCompleta.indexOf(' ');
            return Integer.parseInt(instruccionCompleta.substring(indexInicial + 1));
        }else if(instruccionCompleta.contains("DEC")){
            //METODO PARA CONVERTIR RXX en X
        }
        return 0;
    }

    //Convertir proceso de tipo String a int (DEC,INC,INV)
    public static int ProcesoStringToIntDadoDosElementos(String instruccionCompleta){
        int indexInicial = instruccionCompleta.indexOf('R');
        return Integer.parseInt(instruccionCompleta.substring(indexInicial + 1));
    }

    public static int ProcesoStringToIntDadoTresElementos(String instruccionCompleta){
        int indexInicial = instruccionCompleta.indexOf(',');
        int indexFinal = instruccionCompleta.indexOf('R');
        return Integer.parseInt(instruccionCompleta.substring(indexInicial + 1,indexFinal));
    }
    
    
    // Obtiene el primer elemento
    public static String obtenerPrimerElemento (String item) {
        int indexInicial = item.indexOf(' ');
        int indexFinal = item.indexOf(',');

        return item.substring(++indexInicial, indexFinal);
    }
    public static String obtenerSegundoElemento (String item) {
        int indexInicial = item.indexOf(',');

        return item.substring(++indexInicial);
    }
    public static int obtenerUnicoElemento (String item) {
            int index = item.indexOf(' ');
            return Integer.parseInt(item.substring(++index));
    }

    public static int posicionArray (String argumento) {
        String posicionSinR = argumento.substring(1);
        return Integer.parseInt(posicionSinR);
    }

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
    public static void metodoADD(String item, int[] array) {
            //Obtiene los argumentos
            String arg1 = obtenerPrimerElemento(item);
            String arg2 = obtenerSegundoElemento(item);

            //Los argumentos son posicion del array
            int posicionArg1 = posicionArray(arg1);

            int posicionArg2 = posicionArray(arg2);

            array[posicionArg1] += array[posicionArg2];
    }

    public static void metodoDEC(int indexRegistro, int[] array){
        array[indexRegistro]--;
    }

    public static void metodoINV(int indexRegistro, int[] array){
        int valorRegistro=array[indexRegistro];
        //Convertir Int to Binario
        String binario = Integer.toBinaryString(valorRegistro);
        //Invertir Binario
        binario.replace('0', '2').replace('1', '0').replace('2', '1');
        //Convertir binario to Int
        int valorRegistroBinarioInvertido= Integer.parseInt(binario, 2);
        array[indexRegistro]=valorRegistroBinarioInvertido;
    }

    public static int metodoJMP(String item) {
           return obtenerUnicoElemento(item);
    }
    
    public static Integer metodoJZ(Integer posicion, int[] registros) {
        Integer index = 0;
        if (registros[0]==0) {
            index = posicion;
            System.out.println("está iterando!");
        } else {
            index++;
        }
        return index;
    }
}
