package com.aueb;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetic {

	//ArrayList that contains the current population of chromosomes
	private ArrayList<Chromosome> population;
	/*
	 * ArrayList that contains indexes of the chromosomes in the population ArrayList
	 * Each chromosome index exists in the ArrayList as many times as its fitness score
	 * By creating this ArrayList so, and choosing a random index from it,
	 * the greater the fitness score of a chromosome the greater chance it will be chosen.
	 */
	private ArrayList<Integer> fitnessBounds;

	public Genetic() {
		this.population = null;
		this.fitnessBounds = null;
	}

	/*
	 * populationSize: The size of the population in every step
	 * mutationPropability: The propability a mutation might occur in a chromosome
	 * minimumFitness: The minimum fitness value of the solution we wish to find
	 * maximumSteps: The maximum number of steps we will search for a solution
	 */
	public Chromosome geneticAlgorithm(int populationSize, double mutationProbability, int minimumFitness, int maximumSteps, ArrayList<String> Courses, String[][] Teachers) {
		//We initialize the population
		initializePopulation(populationSize, Courses, Teachers);
		Random r = new Random();
		for(int step = 0; step < maximumSteps; step++) {

			//Initialize the new generated population
			ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
			for(int i = 0; i < populationSize; i++) {

				//We choose two chromosomes from the population
				//Due to how fitnessBounds ArrayList is generated, the propability of
				//selecting a specific chromosome depends on its fitness score
				int xIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				Chromosome x = this.population.get(xIndex);
				int yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				while(yIndex == xIndex) {
					yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				}
				Chromosome y = this.population.get(yIndex);
				//We generate the "child" of the two chromosomes
				Chromosome child = this.reproduce(x, y, Teachers);
				//We might then mutate the child
				if(r.nextDouble() < mutationProbability) {
					child.mutate();
				}
				//...and finally add it to the new population
				newPopulation.add(child);
			}
			this.population = new ArrayList<Chromosome>(newPopulation);

			//We sort the population so the one with the greater fitness is first
			Collections.sort(this.population, Collections.reverseOrder());
			//If the chromosome with the best fitness is acceptable we return it
			if(this.population.get(0).getFitness() >= minimumFitness) {
				System.out.println("Finished after " + step + " steps...");
				return this.population.get(0);
			}
			//We update the fitnessBounds arrayList
			this.updateFitnessBounds();
		}

		System.out.println("Finished after " + maximumSteps + " steps...");
		return this.population.get(0);
	}

	//We initialize the population by creating random chromosomes
	public void initializePopulation(int populationSize, ArrayList<String> Courses, String[][] Teachers) {
		this.population = new ArrayList<Chromosome>();
		for(int i = 0; i < populationSize; i++) {
			this.population.add(new Chromosome(Courses, Teachers));
		}
		this.updateFitnessBounds();
	}

	//Updates the arraylist that contains indexes of the chromosomes in the population ArrayList
	public void updateFitnessBounds() {
		this.fitnessBounds = new ArrayList<Integer>();
		for (int i = 0; i<this.population.size(); i++) {
			for(int j = 0; j<this.population.get(i).getFitness(); j++) {
				//Each chromosome index exists in the ArrayList as many times as its fitness score
				//By creating this ArrayList so, and choosing a random index from it,
				//the greater the fitness score of a chromosome the greater chance it will be chosen.
				fitnessBounds.add(i);
			}
		}
	}

	//"Reproduces" two chromosomes and generated their "child"
	public Chromosome reproduce(Chromosome x, Chromosome y,String[][] Teachers) {
		Random r = new Random();
		//Randomly choose the intersection point
		int intersectionPoint = r.nextInt(315) + 1;
		ArrayList<String> childGenes = new ArrayList<>();
		//The child has the left side of the x chromosome up to the intersection point...
		for(int i = 0; i < intersectionPoint; i++) {
				childGenes.add(x.getGenes()[0][i]);
		}
		//...and the right side of the y chromosome after the intersection point
		for(int i = intersectionPoint; i < childGenes.size(); i++) {
			childGenes.add(y.getGenes()[0][i]);
		}
		return new Chromosome(childGenes,Teachers);
	}
}
