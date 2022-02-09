public class Main {
    public static void main(String[] args) {
        String[] subrutina = {"MOV 5,R00", "MOV 10,R01", "JZ 7", "ADD R02,R01", "DEC R00", "JMP 3", "MOV R02,R42"};
        procesarInstrucciones(subrutina);
    }

    public static void procesarInstrucciones(String[] instruccines){
        int i=1;
        while(i<=instruccines.length){
            switch (identificarComando(instruccines,i-1)){
                case 1://Metodo MOV
                    //System.out.println("Instruccion:"+i+" es de tipo MOV");
                    //obtenerSegundoArgumentoDeTresElementos(instruccines[0]);
                    i++;
                    break;
                case 2://Metodo ADD
                    //System.out.println("Instruccion:"+i+" es de tipo ADD");
                    i++;
                    break;
                case 3://Metodo DEC
                    //System.out.println("Instruccion:"+i+" es de tipo DEC");
                    System.out.println(ProcesoStringToIntDadoDosElementos(instruccines[i-1]));
                    i++;
                    break;
                case 4://Metodo INC
                   // System.out.println("Instruccion:"+i+" es de tipo INC");
                    i++;
                    break;
                case 5://Metodo INV
                    //System.out.println("Instruccion:"+i+" es de tipo INV");
                    i++;
                    break;
                case 6://Metodo JMP
                    //System.out.println("Instruccion:"+i+" es de tipo JMP");
                    //System.out.println(obtenerUnicoArgumentoDadoDosElementos(instruccines[i-1]));
                    i++;
                    break;
                case 7://Metodo JZ
                    //System.out.println("Instruccion:"+i+" es de tipo JZ");
                    //System.out.println(obtenerUnicoArgumentoDadoDosElementos(instruccines[i-1]));
                    i++;
                    break;
                case 8://Metodo NOP
                    //System.out.println("Instruccion:"+i+" es de tipo NOP");
                    i++;
                    break;
                 default://La instrucción no contiene ninguna instrucción Válida
                    break;
            }
        }
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

    //Obtiene segundo argumento de JZ,JMP
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


}
