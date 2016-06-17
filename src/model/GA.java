package model;
import java.util.concurrent.ThreadLocalRandom;

public class GA {

    /* Parametros do algoritmo genetico */
    // taxa de muta��o de 1,5
    private static final double mutationRate = 0.015;
    // amostra de popula�ao para o crossover
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // evolui as popula��es
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Mantem o melhor individuo
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // aplica o crossover na popula�ao corrente
        // cria uma nova popula�aoo
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // seleciona os pais
            // pai 1
            Tour parent1 = tournamentSelection(pop);
            // pai 2
            Tour parent2 = tournamentSelection(pop);
            // filho, aplicando crossover
            Tour child = crossover(parent1, parent2);
            // adiciona o filho gerado no array de novas popula��es
            newPopulation.saveTour(i, child);
        }

        // faz a muta��o da popula��o, baseado na taxa de muta��o
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        // retorna nova poupula��o
        return newPopulation;
    }

    // aplica o crossover e retorna uma nova popula��oo (itiner�rio)
    public static Tour crossover(Tour parent1, Tour parent2) {
        // cria um itiner�rio filho
        Tour child = new Tour();

        // posi��o inicial partindo do segundo
        int startPos = 2; //resposta da 5
        // sorteia posi��o final
        int endPos = ThreadLocalRandom.current().nextInt(2, parent1.tourSize()); // resposta da 5

        // loop pelo pai 1 pegando os cromossomos entre a posi��oo inicial e a posi��o final resposta da 4
        for (int i = 0; i < child.tourSize(); i++) {
            // se a posi��o inicial for menor que a final
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // se a posi��o incial for maior que a final
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // loop pelos cromossomos do pai 2
        for (int i = 0; i < parent2.tourSize(); i++) {
            // se o filho não possui o cromossomo na posi��o "i", adiciona o cromossomo do pai 2
            if (!child.containsCity(parent2.getCity(i))) {
                // loop pelas posi��es do filho para encontrar posi��o vaga
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // posi��o vazia encontrada, adiciona a cidade
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // aplica muta��oo em uma popula��o
    private static void mutate(Tour tour) {
        // loop pelas cidades
        for(int tourPos1=1; tourPos1 < tour.tourSize(); tourPos1++){
            // testa taxa de muta��oo
            if(Math.random() < mutationRate){
                // gera uma posi��oo aleat�ria de troca
                int tourPos2 = ThreadLocalRandom.current().nextInt(2, tour.tourSize()); 

                // pega as cidades nas posi��es de troca
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // efetua a troca
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    // seleciona as popula��ess candidatas para o crossover
    private static Tour tournamentSelection(Population pop) {
        // armazena as popula��ess sorteadas
        Population tournament = new Population(tournamentSize, false);
        // sorteia uma popula��o
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // retorna a popula��o com menor fitness
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}