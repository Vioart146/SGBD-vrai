package insertion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import baiboly.Vocabulaire;
import session.SessionBdd;

public class Insert{
    String requette;
    SessionBdd session = new SessionBdd();
    Vocabulaire voca = new Vocabulaire();
    
    public int nombreDeColone(File path)throws Exception{
        int valiny = 0;
        if (path.exists()==true) {
            FileReader lecture=new FileReader(path);
			BufferedReader lire=new BufferedReader(lecture);
			String line;
			line = lire.readLine();
            String[] colones = line.split("::");
            valiny = colones.length;
			lecture.close();
			lire.close();
        }else{
            throw new Exception("Base ou table introuvable");
        }
        return valiny;
    }
    public int getnombreAttribut(String req){
        String[] at = req.split(":");
        String[] att = at[1].split(",");
        int valiny = att.length;
        return valiny;
    }
    public String inserer(String req,File path)throws Exception{
        String[] colones = req.split(":");
        String colone = colones[1];
        String[] valeur = colone.split(",");
        FileWriter ecriture = new FileWriter(path,true);
		BufferedWriter ecrire = new BufferedWriter(ecriture);
        ecrire.newLine();
        for (int i = 0; i < valeur.length; i++) {
            ecrire.write(valeur[i]);
            if(i<valeur.length-1){
                ecrire.write("::");
            }
        }
        ecrire.close();
        ecriture.close();
        String val = "insertion de '"+colone+"' effectuer";
        return val;
    }
    public String verification(String req)throws Exception{
        if (session.useIsExist()==true){
            String[] reqs = req.split(" ");
            if (reqs[1].compareToIgnoreCase(voca.getMot(7))==0 && reqs.length > 3 ) {
                if (reqs[3].compareToIgnoreCase(voca.getMot(8))!=0) {
                    throw new Exception("le commande Values est incorrect");
                }else{
                    String nomBdd = session.database();
                    File f = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+reqs[2]);
                    int n = nombreDeColone(f);
                    int m = getnombreAttribut(req);
                    if (m==n) {
                        return inserer(req,f);
                    }else{
                        throw new Exception("Le nombre d'attribut et nombre de colone sont pas identique");
                    }
                }
            }else{
                throw new Exception("commande '"+req+"' invalide");
            }
        }else{
            throw new Exception("Base de donnees pas selectionner");
        }
    }
    public Insert() throws Exception{
    }
}