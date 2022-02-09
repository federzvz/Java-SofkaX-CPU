public class Registro {
    private int[] cantidadRegistro = new int[43];

    public Registro(int[] cantidadRegistro) {
        this.cantidadRegistro = cantidadRegistro;
    }

    public int[] getCantidadRegistro() {
        return cantidadRegistro;
    }

    public void setCantidadRegistro(int[] cantidadRegistro) {
        this.cantidadRegistro = cantidadRegistro;
    }
}
