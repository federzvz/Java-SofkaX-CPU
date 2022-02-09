public class Main {
    static String[] subrutina = {"MOV 5,R00", "MOV 10,R01", "JZ 7", "ADD R02,R01", "DEC R00", "JMP 3", "MOV R02,R42"};
    public static void main(String[] args) {
        procesarInstrucciones();


    }

    public static void procesarInstrucciones(){

        for(int i=0;i<subrutina.length;i++) {
            if(subrutina[i].matches("MOV")==true){
                System.out.println(subrutina[i]+" TIENE MOV");
            }

        }
    }


}
