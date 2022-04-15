/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Lenka
 */
public class FortyNiner {
    
    private int endurance;
    private int money;
    private ArrayList<Tool> tools;
    private Random rnd;
    
    FortyNiner(){
        endurance = 100;
        money = 100;
        tools = new ArrayList<Tool>();
        tools.add(new Pan());
        tools.add(new Sluice());
        rnd = new Random();
    }
    
    FortyNiner(File saved) throws IOException {
        BufferedReader in;
        in=new BufferedReader(new FileReader(saved));
        String current;
        tools = new ArrayList<Tool>();
        rnd = new Random();
        int i=2;
        tools.add(new Pan());
        tools.add(new Sluice());
        while ((current = in.readLine()) != null){            
            if(current.contains("endurance")){
                Pattern pattern = Pattern.compile("[\\d]+%");
                Matcher matcher = pattern.matcher(current);
                if(matcher.find()){
                    String tmp = matcher.group();
                    Pattern pat = Pattern.compile("[\\d]+");
                    Matcher mat = pat.matcher(tmp);
                    if(mat.find())
                        endurance = Integer.parseInt(mat.group());
                }              
            }
            if(current.contains("money")){
                Pattern pattern = Pattern.compile("\\$[\\d]+");
                Matcher matcher = pattern.matcher(current);
                if(matcher.find()){
                    String tmp = matcher.group();
                    //System.out.println(tmp);
                    Pattern pat = Pattern.compile("[\\d]+");
                    Matcher mat = pat.matcher(tmp);
                    if(mat.find())
                        money = Integer.parseInt(mat.group());
                }              
            }
            
            if(current.contains("Sluice")){
                Pattern pattern = Pattern.compile("\\d%");
                Matcher matcher = pattern.matcher(current);
                if(matcher.find()){
                    String tmp = matcher.group();
                    Pattern pat = Pattern.compile("[\\d]+");
                    Matcher mat = pat.matcher(tmp);
                    if(mat.find())
                        tools.get(0).durability = Integer.parseInt(mat.group());
                    
                }              
            }
            
            if(current.contains("Cradle")){
                Pattern pattern = Pattern.compile("\\d%");
                Matcher matcher = pattern.matcher(current);
                if(matcher.find()){
                    String tmp = matcher.group();
                    Pattern pat = Pattern.compile("[\\d]+");
                    Matcher mat = pat.matcher(tmp);
                    if(mat.find()){
                        tools.add(new Cradle());
                        tools.get(tools.size()-1).durability = Integer.parseInt(mat.group());
                    }
                }              
            }
                 
        }

    }
    
    public int getEndurance(){
        return endurance;
    }
    
    public void setEndurance(int end){
        this.endurance = end;
    }
    
    public int getMoney(){
        return money;
    }
    
    public void setMoney(int mon){
        this.money = mon;
    }
    
    public ArrayList<Tool> getTools(){
        return tools;
    }
    
    public void setTools(ArrayList<Tool> tol){
        this.tools = tol;
    }
    
    public void useTools(){
        tools.forEach( (tool) -> { this.money += tool.useTool();}); 
        System.out.println("Iskoristili ste alate. Trenutno imate: $"+this.money);
    }
    
    public void buyFood(){
        this.money -= (rnd.nextInt(50 - 30) + 30);
        System.out.println("Kupili ste hranu. Trenutno imate: "+this.money);
    }
    
    public void loseEndurance(){
        this.endurance -= (rnd.nextInt(25 - 10) + 10);
        if(endurance < 0)
            endurance = 0;
        System.out.println("Trenutna izdrzljivost: "+this.endurance);
    }
    
    private void goToSaloon(){
        this.money -= (rnd.nextInt(200 - 50) + 50);
        this.endurance += (rnd.nextInt(50 - 5) + 5);
        if(endurance > 100)
            endurance = 100;
        System.out.println("Bili ste u provodu. Trenutno imate: $"+this.money+", a izdrzljivost vam je: "+this.endurance);
    }
    
    private void fixSluice(){
        ((Sluice)tools.get(1)).repair();
        this.money -= 100;
        System.out.println("Popravili ste levak. Trenutno imate: $"+this.money);
    }
    
    public void itIsSundayAgain()throws IOException{
        System.out.println("Izaberi jednu od opcija");
        System.out.println("1. nista");
        System.out.println("2. Popravi levak");
        System.out.println("3. Idi u grad");
        BufferedReader in;
        in = new BufferedReader( new InputStreamReader(System.in));
        int choice = Integer.parseInt(in.readLine());
        switch(choice){
            case 1: 
                    break;
            case 2: this.fixSluice();
                    break;
            case 3: this.goToSaloon();
                    break;
        }
    }
    
    public void addCradle(Cradle crad){
        tools.add(crad);
        money -= 30;
        System.out.println("Dodata kolevka");
    }
    
    public void removeCradle(){
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for(int i=2; i<tools.size(); ++i){
            if(tools.get(i).durability == 0){
                pos.add(i);
            }
        }
        for(int j=0; j<pos.size(); ++j){
            tools.remove(tools.get(pos.get(j)));
            System.out.println("Uklonjena je kolevka na poziciji: "+pos.get(j)+"sada ima: "+tools.size()+"alata");
            
        }
    }
    
    public void printFN (){
        System.out.println("49 endurance: "+endurance);
        System.out.println("49 money: "+money);
    }
}
