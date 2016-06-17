package model;
/*
* Tour.java
* Stores a candidate tour
*/

import java.util.ArrayList;

public class Tour{

    // array de cidades
    private ArrayList<City> tour = new ArrayList<City>();
    // guarda o total "fitness"
    private double fitness = 0;
    // guarda a distância total
    private int distance = 0;
    
    // constroi um itinerário em branco
    public Tour(){
        for (int i = 0; i < ControladorRota.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList<City> tour){
        this.tour = tour;
    }

    // cria uma nova população
    public void generateIndividual() {
        // faz um loop pelas cidades e adiciona no itinerário
        for (int cityIndex = 0; cityIndex < ControladorRota.numberOfCities(); cityIndex++) {
          setCity(cityIndex, ControladorRota.getCity(cityIndex));
        }
        // embaralha as cidades
        Util.shuffle(tour, 2, tour.size());
    }

    // retorna uma cidade na posição
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // insere uma cidade em determinada posição do itinerários
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        fitness = 0;
        distance = 0;
    }
    
    // retorna o "fitness" do itinerário corrente
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // retorna a distância total de todas as cidades
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // loop pelas cidades
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // pega a cidade de saída
                City fromCity = getCity(cityIndex);
                // cidade de destino
                City destinationCity = null;
                // testa se a cidade corrente não é a última
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // incrementa distância total
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // retorna total de cidades
    public int tourSize() {
        return tour.size();
    }
    
    // retorna se itinerário tem alguma cidade
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"\n- ";
        }
        if (geneString != "")
        	geneString += getCity(0);
        return geneString;
    }
}