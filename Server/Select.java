package selection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import baiboly.Vocabulaire;
import session.SessionBdd;

public class Select {
    SessionBdd session = new SessionBdd();
    Vocabulaire voca = new Vocabulaire();
    public int[] getNombreEspace(Vector<String> res){
        String[] Cols = (res.elementAt(0)).split("::");
        int[] valiny = new int[Cols.length];
        for (int i = 0; i < valiny.length; i++) {
            valiny[i] = 0;
        }
        String[] chaqueLigne;
        for (String ligne : res) {
            chaqueLigne = ligne.split("::");
            for (int j = 0; j < chaqueLigne.length; j++) {
                if (chaqueLigne[j].length() > valiny[j]) {
                    valiny[j] = chaqueLigne[j].length();
                }
            }
        }
        return valiny;
    }
    public String getWithEspace(String valeur,int nombre){
        int a = valeur.length();
        int b = nombre-a;
        String ligne = "|";
        valeur=ligne.concat(valeur);
        for (int i = 0; i < b; i++) {
            valeur = valeur.concat(" ");
        }
        valeur=valeur.concat(ligne);
        return valeur;
    }
    public int getEspaceTotaleNombre(int[] a,int num){
        int nombreT = a[num];
        return nombreT;
    }
    public String montrerResultat(Vector<String> results){
        String[] resultat;
        String valiny="" ;
        int[] a = getNombreEspace(results);
        for (int i = 0; i < results.size(); i++) {
            resultat = results.elementAt(i).split("::");
            for (int j = 0; j < resultat.length; j++) {
               valiny = valiny.concat(getWithEspace(resultat[j],getEspaceTotaleNombre(a,j)));
            }
            valiny = valiny.concat("\n");
        }
        int isany = results.size()-1;
        valiny = valiny.concat("\n");
        valiny = valiny.concat(isany+" "+voca.getMessage(3));
        return valiny;
    }

    public String getAll(String req) throws Exception{
        Vector<String> resultats = new Vector();
        String[] reqs = req.split(" ");
        String nomTable = reqs[3];
        String nomBdd= session.database();
        String path = "C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+nomTable;
        File file=new File(path);
        if(file.exists()==true){ 
			FileReader lecture=new FileReader(file);
			BufferedReader lire=new BufferedReader(lecture);
			String line;
			while((line=lire.readLine()) !=null)
			{
				resultats.add(line);
			}
			lecture.close();
			lire.close();
            return montrerResultat(resultats);
        }else{
            throw new Exception("Base ou Table incorrect");
        }
    }
}
