package model;

public class Population {

    // guarda a população de itinerários
    Tour[] tours;

    // constrói uma população
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // testa se é necessário inicializar uma populaçãoo
        if (initialise) {
            // loop para criar as populações (itinerários)
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // salva todos as populações (itinerários) em um array
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // retorna um itinerário
    public Tour getTour(int index) {
        return tours[index];
    }

    // retorna a melhor população (itinerário)
    public Tour getFittest() {
        Tour fittest = tours[0];
        // loop pelas populações para descobrir o menor fitness
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        // retorna o menor fitness de todas as populações
        return fittest;
    }

    // retorna o tamanho da população
    public int populationSize() {
        return tours.length;
    }
}