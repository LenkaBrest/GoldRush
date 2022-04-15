/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Lenka
 */
public class Pan extends Tool {
    @Override
    public int useTool(){
        int ret = rnd.nextInt(60+1);
        System.out.println("Iskoristili ste sito i zaradili: $"+ret);
        return ret;
    }
}
