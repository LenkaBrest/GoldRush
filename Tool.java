import java.util.Random;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenka
 */
abstract class Tool {
    protected int durability;
    protected Random rnd;
    
    Tool(){
        rnd = new Random();
        durability = 100;
    }
    public int getDurability(){
        return durability;
    }
    
    abstract public int useTool();
}
