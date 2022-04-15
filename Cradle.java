/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Lenka
 */
public class Cradle extends Tool {
    protected boolean failure=false;
    @Override
    public int useTool(){
        if(rnd.nextInt(5) == 2){
            failure = true;
            durability = 0;
        }
        if(failure == false){
            int ret = rnd.nextInt(30);
            System.out.println("Iskoristili ste kolevku i zaradili: $"+ret);
            return ret;
        }
        else{
            System.out.println("Kolevka je pokvarena");
            return 0;
        }
    }
}
