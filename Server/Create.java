package creation;
import java.io.File.*;
import baiboly.Vocabulaire;
import session.SessionBdd;
import java.io.*;
public class Create{
	Vocabulaire voca = new Vocabulaire();
	SessionBdd session =new SessionBdd();
	public String creationTable(String name,String bdd,String req) throws Exception{
		try{
			File f = new File("C:/JSQL/Base de Donnees/serveur/"+bdd+"/"+name);
			if (f.exists()) {
				throw new Exception("table '"+name+"' existe");
			}else{
				f.createNewFile();
				File fichier = new File("C:/JSQL/Base de Donnees/serveur/"+bdd+"/"+name);
				FileWriter fileW = new FileWriter(fichier,true);
				BufferedWriter buffeW = new BufferedWriter(fileW);
				String[] divise = req.split(":");
				String[] colone = divise[1].split(",");
				for (int i = 0; i < colone.length; i++) {
					buffeW.write(colone[i]);
					if(i<colone.length-1){
						buffeW.write("::");
					}
				} 
				buffeW.close();
				fileW.close();
				return voca.getMessage(1);
			}
		}catch(Exception e){
			throw e;
		}
	}

	public String creationDataBase(String name)throws Exception{
		try{
			File f = new File("C:/JSQL/Base de Donnees/serveur/"+name);
			if (f.exists()) {
				throw new Exception("base de donne '"+name+"' existe");
			}else{
				f.mkdirs();
				return "database creer";
			}
		}catch(Exception e){
			throw e;
		}
	}
	public String verification1(String req) throws Exception{
		String valiny = "";
		String requette=req+"";
		String[] motReq = requette.split(" ");
		if (motReq.length>2) {
			if (motReq.length==3 && motReq[1].equalsIgnoreCase(voca.getMot(1))) {
				valiny = creationDataBase(motReq[2]);
				return valiny;
			}else if(motReq.length>=3 && motReq[1].equalsIgnoreCase(voca.getMot(3))){
				valiny = creationTable(motReq[2],session.database(),req);
				return valiny;
			}else{
				throw new Exception("commande '"+ req +"' invalide : fausse indication ");
			}
		}else{
			throw new Exception("commande '"+ req +"' invalide : inferieur a 2");
		}
	}
}