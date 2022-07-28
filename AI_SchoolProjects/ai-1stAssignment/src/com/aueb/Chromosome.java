package com.aueb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome>
{
    //Table that holds the chromosome itself
    //Each position shows the vertical position of a queen in the corresponding column
	private String [][] genes;
    //Integer that holds the fitness score of the chromosome
	private int fitness;
	private ArrayList<String> courses;
	private int wHoursLesson = 4, wHoursTday = 2, wHoursTweek = 3, wsameClass = 6, wPlace = 8;

	
	//Constructs a copy of a chromosome
	public Chromosome(ArrayList<String> Courses, String[][] Teachers){

		this.fitness = 0;
		courses = Courses;
		ArrayList<String> tempT = new ArrayList<String>();
		ArrayList<String> teacherLess = new ArrayList<String>();

		Random random = new Random();
		this.genes = new String[2][315];

		for(int j = 0; j < 20; j++) {
			//Checking the teacher's lessons.
			teacherLess.add(Teachers[j][2]);
			teacherLess.add(Teachers[j][3]);
			teacherLess.add(Teachers[j][4]);
		}

		for(int i = 0; i < 315; i++) {
			Collections.shuffle(Courses);
			this.genes[0][i] = Courses.get(random.nextInt(Courses.size()));
			tempT.add(Teachers[teacherLess.indexOf(this.genes[0][i]) / 3][1]);
			Collections.shuffle(tempT);
			if(this.genes[1][i] == null) this.genes[1][i] = tempT.get(0);
		}
		this.calculateFitness();
	}

	
	public String[][] getGenes()
	{
		return this.genes;
	}
	
	public int getFitness()
	{
		return this.fitness;
	}
	
	public void setGenes(String[][] genes) {
		for(int i=0; i < 2; i++) {
			for(int j=0; j < this.genes[i].length; j++) {
				this.genes[i][j] = genes[i][j];
			}
		}
	}
	
	public void setFitness(int fitness)
	{
		this.fitness = fitness;
	}

	public void calculateFitness() {

		int[] Lhours = new int[54];
		int[] ThoursWeek = new int[20];
		int[] ThoursDay = new int[20];
		int[] maxHourspw = new int[20];
		int correctClass, lesson, teacher, recHours, maxHourspd;

		for(int i = 0; i < this.genes.length; i++) {

			//Lesson Code
			lesson = Integer.parseInt(this.genes[0][i].substring(1, 3));
			correctClass = Integer.parseInt(this.genes[0][i].substring(0, 1));
			recHours = Integer.parseInt(this.genes[0][i].substring(3, 4));

			//Teacher Code
			teacher = Integer.parseInt(this.genes[1][i].substring(1, 3));
			maxHourspd = Integer.parseInt(this.genes[1][i].substring(0, 1));
			if((maxHourspw[teacher - 1]) != (Integer.parseInt(this.genes[1][i].substring(3, 5)))) {
				maxHourspw[teacher - 1] = Integer.parseInt(this.genes[1][i].substring(3, 5));
			}

			//Counts the hours of each lesson/teacher.
			Lhours[lesson - 1]++;
			ThoursWeek[teacher - 1]++;

			if(i % 7 == 0 && i != 0){
				for(int l = 0; l < 20; l++) {
					ThoursDay[l] = 0;
				}
				ThoursDay[teacher - 1]++;
			}else {
				ThoursDay[teacher - 1]++;
			}

			if(ThoursDay[teacher - 1] <= maxHourspd){
				this.fitness += wHoursTday - (maxHourspd-ThoursDay[teacher - 1]);
			}

			//Checks for coverage of a specific teacher.
			for(int k = 0; k < 9; k++) {
				if((genes[1][i].equals(genes[1][i + (34 * k)]))) {
					this.fitness += wPlace;
				}
			}

			//Checks if the recommended hours are matching with the counted hours.
			if(Lhours[lesson - 1] <= recHours) {
				this.fitness += wHoursLesson - (recHours - Lhours[lesson - 1]);
			}

			//Checks if a class is in the correct area of the table. Basically if it is programmed for the correct class.
			switch (correctClass){
				case 1:
					if(i <= 104){
						this.fitness += wsameClass;
					}
					break;
				case 2:
					if((i >= 104) && (i < 209)){
						this.fitness += wsameClass;
					}
					break;
				case 3:
					if(i >= 209){
						this.fitness += wsameClass;
					}
					break;
			}
		}
		for(int j = 0; j < 20; j++){
			if(ThoursWeek[j] <= maxHourspw[j]){
				this.fitness += wHoursTweek - (maxHourspw[j]-ThoursWeek[j]);
			}
		}
	}

	public void mutate() {
		Random r = new Random();
		for(int i = 0;i < 45; i++){
			this.genes[0][r.nextInt(314)] = courses.get(r.nextInt(courses.size()));
		}
		this.genes[0][r.nextInt(314)] = courses.get(r.nextInt(courses.size()));
		this.calculateFitness();
	}
	
	public void print() {
		for(int i = 0; i < 7; i++) {
			for(int j = i; j < 315; j += 7) {
				//if (j % 45 == 0 && j!=0) {
				System.out.print(" | " + genes[0][i] + " | ");
				//} else {
				//	System.out.print(" | " + genes[0][i] + " | ");
				//}
			}
			System.out.println();
		}

	}
	
	@Override
	public boolean equals(Object obj) {
		for(int i = 0; i < this.genes.length; i++) {
			if(this.genes[i] != ((Chromosome)obj).genes[i]) {
					return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hashcode = 0;
		for(int i=0; i<this.genes.length; i++) {
			hashcode += Integer.parseInt(this.genes[0][i]);
		}
		return hashcode;
	}
	
	@Override
    //The compareTo function has been overriden so sorting can be done according to fitness scores
	public int compareTo(Chromosome x) {
		return this.fitness - x.fitness;
	}
}
