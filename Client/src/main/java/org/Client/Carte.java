package org.Client;

public class Carte {
	
	
	private String color;
	
	private int age;

	private int num; // Nombre inferieur ou egal au nombre de joueur de la partie(3+ , 4+ etc...)

	private String name;
	
	//constructeur
	
	public Carte(String name, String color, int age, int num) {
		this.name = name;
		this.color = color;
		this.age = age;
		this.num = num;
	}
	
	
	
	//Obtenir le nom de la carte
	
	public String getName() {
		return name;
	}

	
	//Obtenir la couleur de la carte
	 
	public String getColor() {
		return color;
	}

	//Obtenir l'age de la carte
	public int getAge() {
		return age;
	}

	public int getNum() {
		return num;
	}
}