package model;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

   public static void shuffle(ArrayList<City> tour) 
   {        
       int count = tour.size();
       for (int i = count; i > 1; i--) 
           swap(tour, i - 1, ThreadLocalRandom.current().nextInt(i));        
    }

   public static void shuffle(ArrayList<City> tour, int intervalStart, int intervalEnd) 
   {        
       int count = tour.size();
       for (int i = count; i > 1; i--) 
           swap(tour, i - 1, ThreadLocalRandom.current().nextInt(intervalStart, intervalEnd));        
    }
   
    private static void swap(ArrayList<City> tour, int i, int j) {
    	City temp = tour.get(i);
    	tour.set(i, tour.get(j));
    	tour.set(j, temp);
    }
}