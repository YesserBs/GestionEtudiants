package model;

public class Personne {
	private static int n;
	private int id;
	private String nom;
	private int age;
	
	public Personne(String nom, int age) {
		id = ++n;
		this.nom = nom;
		this.age = age;
	}

	public Personne(int id, String nom, int age) {
		this.id = id;
		this.nom = nom;
		this.age = age;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAge() {
		return age;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
