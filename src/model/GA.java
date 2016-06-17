package model;
import java.util.concurrent.ThreadLocalRandom;

public class GA {

    /* Parametros do algoritmo genetico */
    // taxa de mutação de 1,5
    private static final double mutationRate = 0.015;
    // amostra de populaçao para o crossover
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // evolui as populações
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Mantem o melhor individuo
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // aplica o crossover na populaçao corrente
        // cria uma nova populaçaoo
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // seleciona os pais
            // pai 1
            Tour parent1 = tournamentSelection(pop);
            // pai 2
            Tour parent2 = tournamentSelection(pop);
            // filho, aplicando crossover
            Tour child = crossover(parent1, parent2);
            // adiciona o filho gerado no array de novas populações
            newPopulation.saveTour(i, child);
        }

        // faz a mutação da população, baseado na taxa de mutação
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        // retorna nova poupulação
        return newPopulation;
    }

    // aplica o crossover e retorna uma nova populaçãoo (itinerário)
    public static Tour crossover(Tour parent1, Tour parent2) {
        // cria um itinerário filho
        Tour child = new Tour();

        // posição inicial partindo do segundo
        int startPos = 2; //resposta da 5
        // sorteia posição final
        int endPos = ThreadLocalRandom.current().nextInt(2, parent1.tourSize()); // resposta da 5

        // loop pelo pai 1 pegando os cromossomos entre a posiçãoo inicial e a posição final resposta da 4
        for (int i = 0; i < child.tourSize(); i++) {
            // se a posição inicial for menor que a final
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // se a posição incial for maior que a final
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // loop pelos cromossomos do pai 2
        for (int i = 0; i < parent2.tourSize(); i++) {
            // se o filho nÃ£o possui o cromossomo na posição "i", adiciona o cromossomo do pai 2
            if (!child.containsCity(parent2.getCity(i))) {
                // loop pelas posições do filho para encontrar posição vaga
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // posição vazia encontrada, adiciona a cidade
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // aplica mutaçãoo em uma população
    private static void mutate(Tour tour) {
        // loop pelas cidades
        for(int tourPos1=1; tourPos1 < tour.tourSize(); tourPos1++){
            // testa taxa de mutaçãoo
            if(Math.random() < mutationRate){
                // gera uma posiçãoo aleatória de troca
                int tourPos2 = ThreadLocalRandom.current().nextInt(2, tour.tourSize()); 

                // pega as cidades nas posições de troca
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // efetua a troca
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    // seleciona as populaçõess candidatas para o crossover
    private static Tour tournamentSelection(Population pop) {
        // armazena as populaçõess sorteadas
        Population tournament = new Population(tournamentSize, false);
        // sorteia uma população
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // retorna a população com menor fitness
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}