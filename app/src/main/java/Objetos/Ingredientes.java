package Objetos;

public class Ingredientes {
    private String[] ingredientes = {"Tocino", "Extra Queso", "Champiñón", "Salame", "Albahaca"};
    private int[] precio = {350, 500, 250, 300, 450};

    public Ingredientes() {
    }

    public Ingredientes(String[] ingredientes, int[] precio) {
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int[] getPrecio() {
        return precio;
    }

    public void setPrecio(int[] precio) {
        this.precio = precio;
    }

    public int precioIngrediente(String item){
        for(int i=0; i<ingredientes.length; i++){
            if(item == ingredientes[i]){
                return precio[i];
            }
        }
        return 0;
    }
}
