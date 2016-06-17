package model;
import java.util.ArrayList;

public class ControladorRota {

    // Cria as cidades destino
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    // Add a cidade destino
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    // Retorna a inicial
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }
    
    // Retorna o numero de cidades
    public static int numberOfCities(){
        return destinationCities.size();
    }
}