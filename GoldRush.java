/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
/**
 *
 * @author Lenka
 */
public class GoldRush {
    private FortyNiner fortyNiner;
    private File savedGame;
    protected int week;
    private boolean load;
    
    GoldRush() {  
        load = false;
    }
    
    public void survive()throws IOException{
        if(!load){
            fortyNiner = new FortyNiner();   
            week = 0;
        }
        BufferedReader std;
        std=new BufferedReader(new InputStreamReader(System.in));
        while (week < 20){
            System.out.println("Nedelja: "+week);
            fortyNiner.printFN();
            if(fortyNiner.getEndurance() > 0)
                fortyNiner.useTools();
            else
                System.out.println("Izdrzljivost vam je 0%. Ne mozete da zaradite, odmorite se");
            fortyNiner.removeCradle();
            fortyNiner.buyFood();
            fortyNiner.loseEndurance();
            week++;
            System.out.println("Da li zelite da prekinete igru?");
            if(std.readLine().equals("da")){
                this.saveGame();
                break;
            }
            else{
                fortyNiner.itIsSundayAgain();
                System.out.println("Da li zelite da kupite kolevku?");
                if(std.readLine().equals("da")){
                    System.out.println("Koliko kolevki zelite da kupite?");
                    int i = Integer.parseInt(std.readLine());
                    System.out.println("zelite: "+i+" kolevki");
                    for(int j=0; j<i; ++j){
                        fortyNiner.addCradle(new Cradle());
                    }                    
                }
                
            }
        }
        System.out.println("Svaka cast! Izdrzali ste 20 nedelja zlatne groznice i zaradili: $"+fortyNiner.getMoney());
    }
    
    public void loadGame()throws IOException {
        savedGame = new File("GoldRushSaved.txt");
        if(savedGame.exists())
        {            
            BufferedReader in;
            in=new BufferedReader(new FileReader(savedGame));
            String current;
            current = in.readLine();
            if(current.contains("Week")){
                 Pattern pattern = Pattern.compile("[\\d]+");
                 Matcher matcher = pattern.matcher(current);
                if(matcher.find()){
                        week = Integer.parseInt(matcher.group());
                        //System.out.println("Nedelja ucitana: "+week);
                }                             
            }
            fortyNiner = new FortyNiner(savedGame);
            load = true;
            in.close();
            if(savedGame.delete())
                System.out.println("Obrisana datoteka");
        }
        
        System.out.println("Ucitana prethodna igra");
    }
    public void saveGame()throws IOException{
        PrintWriter saved = new PrintWriter(new FileWriter(savedGame, true));
        saved.write("Week no. "+week+"\n");
        saved.write("49er endurance: "+fortyNiner.getEndurance()+"%\n");
        saved.write("49er money: $"+fortyNiner.getMoney()+"\n");
        ArrayList<Tool> tmp = fortyNiner.getTools();
        saved.write("Sluice durability: "+tmp.get(1).durability+"%\n");
        for(int i=2; i<tmp.size(); ++i){
            saved.write("Cradle durability: "+tmp.get(i).durability+"%\n");
        }
        saved.flush();
        saved.close();
    }
    
}
