package ec.edu.uees.proyectoso;

public class Item implements Comparable<Item>{
    private String nombre;
    private int num;
    public Item(int num){
        this.num = num;
    }
    public String getNombre() {
        return ("item #"+num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    @Override
    public String toString(){
        return this.getNombre();
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(getNum(), o.getNum());
    }
}
