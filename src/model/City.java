package model;

public class City {
    int id;
    String name;
     
    public City(int id, String name){
        this.id = id;
        this.name = name;
    }
       
    // calcula distância entre a cidade atual e a cidade sucessora
    public double distanceTo(City city){
    	Distance distance = new Distance();    	
    	double d = distance.GetDistance(this.id, city.id);     
        return d;        
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}