package Scr;

import java.util.ArrayList;
import java.io.*;



public class Singer {

    

    private String name="", genre;
    private int budget;
    private Studio favStudio;
    private ArrayList<Song> songs, registeredSongs;
    private Ministry ministry;
    private int grantValue;
    private String grantMessage;
    private boolean willApply;

    public     Singer(String n, String g, int b, Ministry min, boolean wantToApply ) {
        //question 1a
        //incoming arguments include n for the name, g for genre, and b for budget).
        name = n;
        genre = g;
        budget = b;
        ministry= min; 
        songs = new ArrayList< Song>();
        registeredSongs= new ArrayList< Song>();
        /*Q1a. Add code to complete this constructor*/
        grantMessage ="";
        this.favStudio=null;
        willApply = wantToApply;

    }

    public     Singer(String name, String genre, int budget, int fav, Ministry ministry, boolean willApply ) {
         this.favStudio= ministry.getStudio(fav);
       //Q1b. Add code to complete this constructor/
        songs= new ArrayList< Song>();
        registeredSongs= new ArrayList< Song>();
        this.name = name;
        this.genre = genre;
        this.budget = budget;
        this.ministry = ministry;
        this.willApply = willApply;
        grantMessage ="";
        
    }

    public Ministry getMinistry() {
        return ministry;
    }

    public boolean applying(){
        return willApply;
    }
    public boolean studioExists(Studio favStudio)
    {
        return (favStudio!=null) ;
    }

    public boolean canAfford(Studio studio)
    {
        return studio.getCost() <= budget;
    }
  
    public int getGrantValue(){
        return grantValue;
    }

    public int sumRegisteredSongs() {
        int sum = 0;
        for (Song regsong:registeredSongs)
            sum+=regsong.getClaimableEarnings();

        return sum;
    }//getClaimableEarnings()
    public int sumEstValue() {
        int sum = 0;

        for(int i = 0; i < songs.size(); i++) {
            sum += songs.get(i).getEstEarnings();
        }
        
      

        /*Code to implement Q3 here */
        return sum;
    }
    
    public String getName() {
        return name;
    }
    
        
    public void tryToRegisterSong(Song song) {
        String str= "Registering "+song.getTitle()+" with budget $"+ String.format("%,d",budget) + ".";
        if (favStudio==null)
            str = str + " No preferred studio.";
        else
        {
            str = str + "Prefers "+favStudio.getName();
            if (!favStudio.isAvailable())
                str+="(Not available).";
            else
                str+="(Available:cost[$"+String.format("%,d",favStudio.getCost())+"]).";
      
        }
        System.out.println(str);
        //////////IN THIS METHOD, DO NOT MODIFY ABOVE THIS LINE /////////////////////
        
      
        /*Code for logic that can pass test cases 5, 6 and 7 can go here
        
        In class Singer, method tryToRegisterSong, update logic to allow ministry to suggest a studio by calling the method getBestAvailableStudio(int budget, Studio preferred) from Ministry, which returns a suggested studio. If the method returns a studio (returned value is not null) then set the studio for the song to the returned studio if the singer is able to afford it. You can then check if the song has been connected with a studio, and if it is, add the song to the list of registered songs.*/
        //45
        
        
        
        if( studioExists(favStudio) && canAfford(favStudio) && favStudio.isAvailable())
        {
            //if (favStudio!=null)
            //if (canAfford(favStudio))
            song.setStudio(favStudio);
            registeredSongs.add(song);
            
            this.budget -= favStudio.getCost();
            favStudio.reserve();
        }else
        {
            Studio x = ministry.getBestAvailStudio(budget, favStudio);
            
            if( /*ministry.getBestAvailStudio(budget, favStudio) != null &&*/ studioExists(x) && canAfford(x) && x.isAvailable())
            {
                
                //if (canAfford(x))
                //if (studioExists(x))
                //if (x.isAvailable())
                song.setStudio(x);
                registeredSongs.add(song);
            //7
            budget -= x.getCost();
                x.reserve();
            }
            
            
        }
        

        
  
            
                
                
            
        

            
    }
    

    
    public void addSong(String title){
            songs.add(new Song(title, genre, this ));
    }
    
    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void applyForGrant() {
        for (Song song:songs)
            tryToRegisterSong(song);
        String response = ministry.processGrant(this);
        String[] responseParts = response.split(";");
        grantValue = Integer.parseInt(responseParts[0]);
        grantMessage =responseParts[1];
    }
    
    
    public String toString(){
        String str="";
        str+="-----------------------------------------------------------------\n";
        str+=name.toUpperCase();
        if (studioExists(favStudio))
            str+="["+favStudio.getName()+"]";
        if (grantValue>0)
        {
            str+="::GRANTED $"+String.format("%,d", grantValue)+"\n";
            str+="SONGS SUPPORTED\n";
            for(int i=0; i<registeredSongs.size();i++)
                str +=registeredSongs.get(i)+"\n";
        }
        else
            str+="::"+grantMessage+"\n";
           
        return str;
        
    }
}
        

        
  
            
                
                
            
        

            

