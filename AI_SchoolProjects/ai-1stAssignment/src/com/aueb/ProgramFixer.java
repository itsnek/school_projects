package com.aueb;

import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Iterator;

public class ProgramFixer{	

	public static void main(String[] args){

		//Attributes and variables
		ArrayList<String> Courses = new ArrayList<>();
		String TeachersArray[][] = new String[20][7];
		String LessonsArray[][] = new String[55][4];
		ArrayList<String> Lessons = new ArrayList<>();
		String line;
		int n = 1;


		//First file :
		try (BufferedReader br = new BufferedReader(new FileReader("./res/lessons.csv"))) {

			int i = 0;
			line = br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lesson = line.split(",");
				Courses.add(lesson[1]);
				for(int j = 0; j < lesson.length; j++){
					LessonsArray[i][j] = lesson[j];
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Second file :
		try (BufferedReader br = new BufferedReader(new FileReader("./res/teachers.csv"))) {

			int j = 0;
			line = br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(",");
				for(int i = 0; i < country.length; i++){
					TeachersArray[j][i] = country[i];
				}
				j++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		Genetic g = new Genetic();
		Chromosome x = g.geneticAlgorithm(60, 0.1, 395, 100, Courses, TeachersArray);
		//x.print();


		//Creating output file.
		try {
			FileWriter csvWriter = new FileWriter("outProg/new.csv");
			for (int i = 0; i < 60; i ++) {
				csvWriter.append(" ");
				if (i == 3 || i == 10 || i == 16) {

					csvWriter.append("A" + n);
					csvWriter.append(",");
					n++;
					if (n == 4) n = 1;

				}
				if (i == 23 || i == 30 ||i == 37) {

					csvWriter.append("B" + n);
					csvWriter.append(",");
					n++;
					if (n == 4) n = 1;

				}
				if (i == 44 || i == 51 || i==58) {

					csvWriter.append("Γ" + n);
					n++;
					if (n != 4) csvWriter.append(",");
				}
			}

			csvWriter.append("\n");

			for (int i = 0; i < 45; i += 5) {

				csvWriter.append("Δ");
				csvWriter.append(",");
				csvWriter.append("Τ");
				csvWriter.append(",");
				csvWriter.append("Τ");
				csvWriter.append(",");
				csvWriter.append("Π");
				csvWriter.append(",");
				csvWriter.append("Π");
				if (i!=44)csvWriter.append(",");

			}

			csvWriter.append("\n");

			for (int i = 0; i < 7; i++) {
                for (int j = i; j < 315; j += 7) {
                    for (int k = 0; k < 54; k++) {
                        if (LessonsArray[k][1].equals(x.getGenes()[0][i])) {
                            Lessons.add(LessonsArray[k][0]);
                        }
                    }
                }
                csvWriter.append(String.join(",", Lessons));
                csvWriter.append("\n");
                Lessons.clear();
            }

           	csvWriter.flush();
			csvWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
