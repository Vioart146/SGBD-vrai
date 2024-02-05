package traitementrequette;
import java.lang.String.*;
import creation.Create;
import insertion.Insert;
import selection.AlgrebreRelationelle;
import selection.OperationRelationelle;
import selection.Select;
import session.*;
import baiboly.Vocabulaire;
public class Traitement{
	String[] motReq;
	Vocabulaire voca = new Vocabulaire();
	SessionBdd session = new SessionBdd();
	public String traiter(String req) throws Exception{
		motReq = req.split(" ");
		if (motReq[0].equalsIgnoreCase(voca.getMot(0))) {
			Create creer = new Create();
			return creer.verification1(req);	
		}else if(motReq[0].equalsIgnoreCase(voca.getMot(4)) && motReq.length==2){
			return session.useDatabase(motReq[1]);
		}else if(motReq[0].equalsIgnoreCase(voca.getMot(6))){
			Insert ins = new Insert();
			return ins.verification(req);
		}else if(motReq[0].equalsIgnoreCase(voca.getMot(9))){
			if(session.useIsExist()==true){				
				if (motReq[1].compareToIgnoreCase(voca.getMot(10))==0) {
					if (motReq.length == 4) {
						if (motReq[2].compareToIgnoreCase(voca.getMot(2))==0) {
							Select sel = new Select();
							 return sel.getAll(req);
						}else{
							throw new Exception("Erreur au niveau du from");
						}
					}else if(motReq.length == 8){
						if (motReq[2].compareToIgnoreCase(voca.getMot(2))==0) {
							if (motReq[4].compareToIgnoreCase(voca.getMot(16))==0) {
								AlgrebreRelationelle algebre = new AlgrebreRelationelle();
								 return algebre.selection(motReq[3],motReq[5],motReq[6],motReq[7]);
							}else{
								throw new Exception("Erreur au niveau de where");
							}
						}else{
							throw new Exception("Erreur au niveau du from");
						}
					}else if(motReq.length == 6){
						if (motReq[2].compareToIgnoreCase(voca.getMot(11))==0) {
							if (motReq[4].compareToIgnoreCase(voca.getMot(12))==0) {
								OperationRelationelle operation = new OperationRelationelle();
								return operation.union(motReq[3],motReq[5]);
							}else if(motReq[4].compareToIgnoreCase(voca.getMot(13))==0){
								OperationRelationelle operation = new OperationRelationelle();
								return operation.inter(motReq[3],motReq[5]);
							}else if(motReq[4].compareToIgnoreCase(voca.getMot(14))==0){
								OperationRelationelle operation = new OperationRelationelle();
								 return operation.moin(motReq[3],motReq[5]);
							}else{
								throw new Exception("commande '"+req+"' invalide");
							}
						}else{
							throw new Exception("Erreur au niveau de 'Dans'");
						}
					}else{
						throw new Exception("commande '"+req+"' invalide");
					}
				}else if (motReq[1].compareToIgnoreCase(voca.getMot(17))==0) {
					if (motReq[3].compareToIgnoreCase(voca.getMot(18))==0) {
						OperationRelationelle operation = new OperationRelationelle();
						return operation.produit_cartesien(motReq[2],motReq[4]); 
					}else{
						throw new Exception("Erreur au niveau de 'et'");
					}
				}else if(motReq[1].compareToIgnoreCase(voca.getMot(15))==0){
					if (motReq.length == 5 && (motReq[3].compareToIgnoreCase(voca.getMot(2))==0)) {
						AlgrebreRelationelle algebre = new AlgrebreRelationelle();
						return algebre.projection(motReq[2],motReq[4]);
						
					}else{
						if (motReq.length != 5) {
							throw new Exception("nombre de mot du Commande '"+req+"' incorrect ");
						}else if(motReq[3].compareToIgnoreCase(voca.getMot(2))!=0){
							throw new Exception("Erreur au niveau du from");
						}else{
							throw new Exception("le filtre est incorrect 1");
						}
					}
				}else{
					throw new Exception("le filtre est incorrect 2");
				}
			}else{
				throw new Exception("Base de donner pas selectionner");
			}
		}else{
			throw new Exception("Commande '"+ motReq[0]+"' invalide");
		}
	}
}