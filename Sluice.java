/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Lenka
 */
public class Sluice extends Tool {
    @Override
    public int useTool(){
        if(durability > 0){
            int ret = rnd.nextInt(500+0);
            durability -= (rnd.nextInt(50-20) + 20);
            if(durability < 0)
                durability = 0;
            System.out.println("Iskoristili ste levak. Zaradili ste: $"+ret+", a trenutna izdrzljivost levka je: "+durability);
            return ret;
        }
        else{
            System.out.println("Levak je pokvaren i ne moze se koristiti");
            return 0;
        }
    }
    public void repair(){
        durability = 100;
    }
}
