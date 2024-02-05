package selection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import baiboly.Vocabulaire;
import session.SessionBdd;

public class AlgrebreRelationelle {
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

    public void verificationColone(String[] c1 ,String[] c2) throws Exception{
        int a = 0;
        int b = 0;
        for (int i = 0; i < c1.length; i++) {
            a = 0;
            for (int j = 0; j < c2.length; j++) {
                if (c2[j].compareToIgnoreCase(c1[i])==0) {
                    a=1;
                }
            }
            if (a==0) {
                throw new Exception("le colone '"+c1[i]+"' n'existe pas");
            }
        }
    }
    public int[] getIndexOfCol(String[] colN, String[] colEx){
        int[] index = new int[colN.length];
        int a=0;
        for (int i = 0; i < colN.length; i++) {
            for (int j = 0; j < colEx.length; j++) {
                if (colN[i].compareToIgnoreCase(colEx[j])==0) {
                    index[a] = j ;
                    a++;
                }
            }
        }
        return index;
    }
    public Vector<String> getVraiProjection(Vector<String> resultat,int[] index){
        String[] res;
        String re;
        Vector<String> valiny = new Vector<>();
        String donne2="";
        for (int i = 0; i < resultat.size(); i++) {
            re=resultat.elementAt(i);
            res = re.split("::");
            donne2="";
            for (int j = 0; j < res.length; j++) {    
                for (int j2 = 0; j2 < index.length; j2++) {
                    if (j==index[j2]) {
                        donne2 = donne2.concat(res[j]);
                        if (j2<index.length-1) {
                            donne2 = donne2.concat("::");
                        }
                    }
                }  
            }
            valiny.add(donne2);
        }
        return valiny;
    } 
    public Vector<String> getProjection(int[] index,File f)throws Exception{
        FileReader lecture=new FileReader(f);
        BufferedReader lire=new BufferedReader(lecture);
        Vector<String> val = new Vector<>();
        String line;
        while((line=lire.readLine())!=null){
            val.add(line);
        }
        Vector<String> valiny = getVraiProjection(val, index);
        
        lire.close();
        lecture.close();
        return valiny;
    }
    
    public String projection(String col, String tab) throws Exception{
        String nomBdd= session.database();
        String path = "C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+tab;
        File file=new File(path);
        if(file.exists()==true){ 
            String[] cols = col.split(",");
            String vcol = lesColones(file);
            String[] vcols = vcol.split("::");
            verificationColone(cols, vcols);
            int[] indexOfCol = getIndexOfCol(cols,vcols);
            Vector<String> valiny = getProjection(indexOfCol, file);
            return montrerResultat(valiny);
        }else{
            throw new Exception("le Table '"+tab+"' n'existe pas");
        }
    }

    public String selection(String nomTable,String colone,String egalite,String attributs)throws Exception{
        String nomBdd= session.database();
        String path = "C:/JSQL/Base de Donnees/serveur/"+nomBdd+"/"+nomTable;
        File file=new File(path);
        if(file.exists()==true){ 
            //select tout from test where nom egale Rasoa
            String col = lesColones(file);
            String[] lesCol = col.split("::");
            int a = 0;
            boolean b = false;
            while (a<lesCol.length){
                if (colone.compareToIgnoreCase(lesCol[a])==0) {
                    b = true;
                    break;
                }
                a++;
            }
            if (b==true) {
                Vector<String> data = getAll(file);
                Vector<String> val = new Vector<>();
                val.add(col);
                String[] chaqueLigne;
                for (int i = 0; i < data.size(); i++) {
                    chaqueLigne = ((String)data.elementAt(i)).split("::");
                    if (chaqueLigne[a].compareToIgnoreCase(attributs)==0) {
                        val.add(data.elementAt(i));
                    }
                }
                return montrerResultat(val);
            }else{
                throw new Exception("l'attribut'"+colone+"' n'existe pas dans le table"+nomTable);
            }
        }else{
            throw new Exception("le Table '"+nomTable+"' n'existe pas");
        }
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
}
