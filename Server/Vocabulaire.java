package baiboly;
import java.util.Vector;
public class Vocabulaire {
    Vector<String> lisitra = new Vector();
    Vector<String> message = new Vector();

    public String getMot(int i){
        return lisitra.elementAt(i);
    }
    public String getMessage(int i){
        return message.elementAt(i);
    }

    public Vocabulaire(){
     String create = "Create";
     String database = "Database";
     String from = "from";
     String table = "table";
     String use = "use";
     String exit = "exit";
     String insert = "insert";
     String into = "into";
     String values = "values";
     String select = "select";
     String tout = "tout";
     String dans = "dans";
     String union = "union";
     String inter = "inter";
     String difference = "moin";
     String projection = "voici";
     String where = "where";
     String produit_cartesien = "produit_cartesien";
     String et = "et";
     
     lisitra.add(create);//0
     lisitra.add(database);//1
     lisitra.add(from);//2
     lisitra.add(table);//3
     lisitra.add(use);//4
     lisitra.add(exit);//5
     lisitra.add(insert);//6
     lisitra.add(into);//7
     lisitra.add(values);//8
     lisitra.add(select);//9
     lisitra.add(tout);//10
     lisitra.add(dans);//11
     lisitra.add(union);//12
     lisitra.add(inter);//13
     lisitra.add(difference);//14
     lisitra.add(projection);//15
     lisitra.add(where);//16
     lisitra.add(produit_cartesien);//17
     lisitra.add(et);//18

     String bye = "bye bye ^_^";
     String create_table = "succes create";
     String succes_use = "base de donner utiliser";
     String value_ligne = "lignes selectionner";
     message.add(bye);//0
     message.add(create_table);//1
     message.add(succes_use);//2
     message.add(value_ligne);//3

    }
}
