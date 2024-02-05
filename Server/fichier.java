import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class fichier{
	int ligne = 1;
	public void ecrire_fichier(String nom_fichier,String texte_input)
	{
		File f = new File(nom_fichier);
		if(f.exists()==true)
		{
			try
			{
				FileWriter ecriture = new FileWriter(f,true);
				BufferedWriter ecrire = new BufferedWriter(ecriture);
				ecrire.write(texte_input);
				ecrire.newLine();
				ecrire.close();
				ecriture.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}		
		}
	}
	public void lire_fichier(String nom_fichier,int nombre_de_ligne) 
	{
		File file=new File(nom_fichier);
		try
		{
			
			FileReader lecture=new FileReader(file);
			BufferedReader lire=new BufferedReader(lecture);
			String line;
			while((line=lire.readLine()) !=null)
			{
				System.out.println(line);
			}
			lecture.close();
			lire.close();
		}
		catch(Exception e3)
		{
			e3.getStackTrace();
		}
	}
	public fichier()
	{

	}
}