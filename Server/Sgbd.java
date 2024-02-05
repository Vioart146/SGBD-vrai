package bdd;
import baiboly.*;
import traitementrequette.*;
public class Sgbd {
    Vocabulaire voca = new Vocabulaire();
    Traitement traite = new Traitement();
    public String connecter(String requette){
        try{
            if(requette.compareTo(voca.getMot(5))==0){
                return voca.getMessage(0);
            }else{		
                return traite.traiter(requette);
            }
        }catch(Exception e){
            return (e.getMessage());
        }
    }
}
