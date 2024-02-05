package session;

import java.io.File;

import baiboly.Vocabulaire;

public class SessionBdd {
    public static String use="";
    Vocabulaire voca = new Vocabulaire();
    
    public boolean useIsExist(){
        if (use.length()==0) {
            return false;
        }else{
            return true;
        }
    }
    public String useDatabase(String u)throws Exception{
        File f = new File("C:/JSQL/Base de Donnees/serveur/"+u);
        if(f.exists()==true){
            use=u;
            return voca.getMessage(2);

        }else{
            throw new Exception("Base de donnees '"+u+"' n'existe pas");
        }
    }
    public String database() throws Exception{
        if(use.length()!=0){
            return use;
           
        }else{
            throw new Exception("Aucun base selectionner");
        }
    }
}
