package selection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import baiboly.Vocabulaire;
import session.SessionBdd;

public class OperationRelationelle{
    Vocabulaire voca = new Vocabulaire();
    SessionBdd session = new SessionBdd();

public String lesColones(File f)throws Exception{
    FileReader lecture=new FileReader(f);
	BufferedReader lire=new BufferedReader(lecture);
    String valiny = lire.readLine();
    lire.close();
    lecture.close();
    return valiny;
}
public int nombreColone(File f1,File f2)throws Exception{
    String col1 = lesColones(f1);
    String col2 = lesColones(f2);
    String[] cols1 = col1.split("::");
    String[] cols2 = col2.split("::");
    if (cols1.length != cols2.length) {
        throw new Exception("Le nombre de colones ne sont pas pareilles");
    }
    int n = cols2.length;
    return n;
}
public Vector<String> getAll(File f) throws Exception{
    Vector<String> val = new Vector();
    FileReader lecture=new FileReader(f);
	BufferedReader lire=new BufferedReader(lecture);
    String line;
    while((line=lire.readLine()) !=null)
	{
		val.add(line);
	}
    lire.close();
    lecture.close();
    return val;
}

public Vector<String> getUnion(File f1,File f2)throws Exception{
    Vector<String> valiny = new Vector();
    Vector<String> val1 = getAll(f1);
    Vector<String> val2 = getAll(f2);
    for (int i = 1; i < val1.size(); i++) {
        
        valiny.add(val1.elementAt(i));
    }
    for (int j = 1; j < val2.size(); j++) {
        valiny.add(val2.elementAt(j));
    }
    for (int k = 0; k < valiny.size(); k++) {
        for (int l = 0; l < valiny.size(); l++) {
            if (k!=l && (valiny.elementAt(k)).equalsIgnoreCase(valiny.elementAt(l))==true) {
                    valiny.remove(l);
                }
            }
        }
        return valiny;
    }

    public Vector<String> getInter(File f1,File f2)throws Exception{
        Vector<String> valiny = new Vector();
        Vector<String> val1 = getAll(f1);
        Vector<String> val2 = getAll(f2);
        Vector<String> tenaValiny = new Vector();
        for (int i = 1; i < val1.size(); i++) {
            valiny.add(val1.elementAt(i));
        }
        for (int j = 1; j < val2.size(); j++) {
            valiny.add(val2.elementAt(j));
        }
        for (int k = 0; k < valiny.size(); k++) {
            for (int l = 0; l < valiny.size(); l++) {
                if (k!=l && (valiny.elementAt(k)).equalsIgnoreCase(valiny.elementAt(l))==true) {
                        tenaValiny.add(valiny.elementAt(k));
                        valiny.remove(l);
                    }
                }
            }
            return tenaValiny;
        }
    public Vector<String> getDifference(File f1,File f2)throws Exception{
        Vector<String> valiny = new Vector();
        Vector<String> val1 = getAll(f1);
        Vector<String> val2 = getAll(f2);
        Vector<String> tenaValiny = new Vector();
        for (int k = 0; k < val1.size(); k++) {
            for (int l = 0; l < val2.size(); l++) {
                if (((String)val1.elementAt(k)).equalsIgnoreCase((String)val2.elementAt(l))==true) {
                        val1.remove(k);
                    }
                }
            }
            return val1;
        }

    public Vector<String> getProduitCartesien(File f1,File f2)throws Exception{
        String valiny;
        String titre;
        String enTete1  = lesColones(f1);
        String enTete2  = lesColones(f2);
        enTete1 = enTete1.concat("::");
        titre = enTete1.concat(enTete2);
        Vector<String> val1 = new Vector();
        Vector<String> val2 = new Vector<>();
        val1 = getAll(f1);
        val2 = getAll(f2);
        Vector<String> tenaValiny = new Vector();
        tenaValiny.add(titre);
        for (int k = 1; k < val1.size(); k++) {
            valiny = val1.elementAt(k);
            valiny = valiny.concat("::");
            for (int l = 1; l < val2.size(); l++) {
                valiny = valiny.concat(val2.elementAt(l));
                tenaValiny.add(valiny);
                valiny = val1.elementAt(k);
                valiny = valiny.concat("::");
            }
        }
        return tenaValiny;
    }

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


    public String union(String r1 , String r2)throws Exception{
        String nomBdd = session.database();
        File f1 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r1);
        File f2 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r2);

        if (f1.exists()==false && f2.exists()==false) {
            throw new Exception("table '"+r1+"' et '"+r2+"' n'existe pas ");
        }else if (f1.exists()==false) {
            throw new Exception("table '"+r1+"' n'existe pas ");
        }else if(f2.exists()==false) {
            throw new Exception("table '"+r2+"' n'existe pas ");
        }else{
            int a1 = nombreColone(f1, f2);
            if (lesColones(f1).compareToIgnoreCase(lesColones(f2))==0) {
                Vector<String> val = getUnion(f1, f2);
                String colones = lesColones(f1);
                val.add(0,colones);
                return montrerResultat(val);
            }else{
                throw new Exception("les colones sont pas identiques");
            }
        }
    }
    public String inter(String r1 , String r2)throws Exception{
        String nomBdd = session.database();
        File f1 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r1);
        File f2 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r2);

        if (f1.exists()==false && f2.exists()==false) {
            throw new Exception("table '"+r1+"' et '"+r2+"' n'existe pas ");
        }else if (f1.exists()==false) {
            throw new Exception("table '"+r1+"' n'existe pas ");
        }else if(f2.exists()==false) {
            throw new Exception("table '"+r2+"' n'existe pas ");
        }else{
            int a1 = nombreColone(f1, f2);
            if (lesColones(f1).compareToIgnoreCase(lesColones(f2))==0) {
                Vector<String> val = getInter(f1, f2);
                String colones = lesColones(f1);
                val.add(0,colones);
                return montrerResultat(val);
            }else{
                throw new Exception("les colones sont pas identiques");
            }
        }
    }
    public String moin(String r1 , String r2)throws Exception{
        String nomBdd = session.database();
        File f1 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r1);
        File f2 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r2);
        if (f1.exists()==false && f2.exists()==false) {
            throw new Exception("table '"+r1+"' et '"+r2+"' n'existe pas ");
        }else if (f1.exists()==false) {
            throw new Exception("table '"+r1+"' n'existe pas ");
        }else if(f2.exists()==false) {
            throw new Exception("table '"+r2+"' n'existe pas ");
        }else{
            int a1 = nombreColone(f1, f2);
            if (lesColones(f1).compareToIgnoreCase(lesColones(f2))==0) {
                Vector<String> val = getDifference(f1, f2);
                String colones = lesColones(f1);
                val.add(0,colones);
                 return montrerResultat(val);
            }else{
                throw new Exception("les colones sont pas identiques");
            }
        }
    }
    public String produit_cartesien(String r1 , String r2)throws Exception{
        String nomBdd = session.database();
        File f1 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r1);
        File f2 = new File("C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+r2);

        if (f1.exists()==false && f2.exists()==false) {
            throw new Exception("table '"+r1+"' et '"+r2+"' n'existe pas ");
        }else if (f1.exists()==false) {
            throw new Exception("table '"+r1+"' n'existe pas ");
        }else if(f2.exists()==false) {
            throw new Exception("table '"+r2+"' n'existe pas ");
        }else{
            Vector<String> val = getProduitCartesien(f1, f2);
            return montrerResultat(val);
        }
    }
}