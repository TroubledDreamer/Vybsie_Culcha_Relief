package Scr;
import java.util.ArrayList;
import java.io.*;

class Song {

    private String title, genre;
    Singer singer;
    private Studio studio;
      
    public Song(String title, String genre,Singer singer)
    {
    
        this.studio = null;
        this.genre = genre;
        this.singer = singer;
        this.title = title;
     
    }
    
        
    public int getEstEarnings() {
        int num = singer.getMinistry().getSongPartEst();
        return  title.length() * num;//Q2. Perform this calculation
    }
    
    public int getClaimableEarnings() {
        int est = getEstEarnings();
        if (est > singer.getMinistry().getMinClaimable())
             return est;
        else
             return 0;
    }
    
    public String getTitle(){
        return title;
    }
    
  
    //44444444444444444
    public void setStudio(Studio studio){
        this.studio = studio;
        
    }
 
        
    public Studio getStudio(){
       return studio;
    }
    private boolean isBestSeller() {
         return getEstEarnings()>singer.getMinistry().getBestSellLimit();
    }

    
    public boolean hasStudio() {
         return (!(studio==null));
    }
     
   public String toString() {
         String outval ="Released ";
        if (isBestSeller())
            outval+="**";
        int cost =0;
        if (!(studio==null))
           cost = studio.getCost();   
        outval+=title+" as a "+genre + " single to earn $"+String.format("%,d", getEstEarnings())+".";

        return outval;
    }
}





