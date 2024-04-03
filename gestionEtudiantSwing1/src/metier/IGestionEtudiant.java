package metier;

import java.util.List;

public interface IGestionEtudiant {
	
	public void ajouterEtudiant(Etudiant e);
	public List<Etudiant> getAllEtudiants();
	public List<Etudiant> getEtudiantBMC(String mc);
	public void trierEtudiantParNom();
	public void supprimerEtudiant(int id);

}
