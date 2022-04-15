/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
/**
 *
 * @author Lenka
 */
public class Play {
     public static void main(String[] args) throws IOException {
         GoldRush gold = new GoldRush();
         gold.loadGame();
         gold.survive();
     }
}
