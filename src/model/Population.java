package model;

public class Population {

    // guarda a popula��o de itiner�rios
    Tour[] tours;

    // constr�i uma popula��o
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // testa se � necess�rio inicializar uma popula��oo
        if (initialise) {
            // loop para criar as popula��es (itiner�rios)
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // salva todos as popula��es (itiner�rios) em um array
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // retorna um itiner�rio
    public Tour getTour(int index) {
        return tours[index];
    }

    // retorna a melhor popula��o (itiner�rio)
    public Tour getFittest() {
        Tour fittest = tours[0];
        // loop pelas popula��es para descobrir o menor fitness
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        // retorna o menor fitness de todas as popula��es
        return fittest;
    }

    // retorna o tamanho da popula��o
    public int populationSize() {
        return tours.length;
    }
}