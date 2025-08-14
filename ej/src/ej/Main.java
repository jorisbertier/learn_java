package ej;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ej.blocs.IBloc;
import ej.blocs.Mur;
import ej.blocs.Porte;
import ej.blocs.Toit;
import ej.blocs.Type;
import ej.exceptions.IllegalBlocException;
import ej.kits.KitDemarrage;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);	

	public static void main(String[] args) {

		logger.info("Lancement du programme Epicrafter's Journey.");
		
		try {
			// Le programme commence avec un Kit de démarrage.
			KitDemarrage kit = new KitDemarrage(constructionSetBlocs());
			System.out.println("Vous possédez un kit de démarrage !");

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String reponse = "";

			while (!reponse.equals("1") && !reponse.equals("2")) {
			    System.out.println(
			        "Que souhaitez-vous afficher ?"
			        + "\n\t1 - Les idées de constructions. "
			        + "\n\t2 - Le nombre de blocs pour chaque type de blocs présent dans le kit"
			    );

			    // Lire la réponse **à chaque tour**
			    reponse = reader.readLine();

			    if (reponse.equals("1")) {
			        System.out.println("Voici quelques idées de constructions avec le Kit de démarrage : ");
			        Set<String> motsCles = kit.getMotsCles();
			        motsCles.forEach(System.out::println);
			    } else if (reponse.equals("2")) {
			        System.out.println("Voici le nombre de blocs de chaque type contenu dans le Kit de démarrage : ");
			        Map<Type, Integer> quantiteBloc = new TreeMap<>();
			        kit.getBlocs().forEach((bloc) -> {
			            Type type = Type.valueOf(bloc.getClass().getSimpleName().toUpperCase());
			            int quantite = quantiteBloc.getOrDefault(type, 0) + 1;
			            quantiteBloc.put(type, quantite);
			        });
			        quantiteBloc.keySet().forEach((type) -> System.out.println(type + " " + quantiteBloc.get(type)));
			    } else {
			        System.out.println("La valeur saisie n'est pas valide - tapez 1 ou 2.");
			    }
			}
		} catch (IllegalBlocException e) {
			System.out.println("Impossible de construire le Kit de démarrage.");
		} catch (IOException e) {
			logger.error("Impossible de récupérer la saisie utilisateur.");
		}
		
		logger.info("Arret du programme Epicrafter's Journey.");		
	}
	
	private static Set<IBloc> constructionSetBlocs() throws IllegalBlocException {
	    
	    Set<IBloc> blocs = new LinkedHashSet<IBloc>();
	    ExecutorService executorService = Executors.newFixedThreadPool(10);
	    
	    Callable<IBloc> taskMur1 = () -> { return new Mur(3, 2, 2, true); };
	    Callable<IBloc> taskMur2 = () -> { return new Mur(2, 1, 2, false); };
	    Callable<IBloc> taskPorte = () -> { return new Porte(1, 2, 2, true); };
	    Callable<IBloc> taskToit = () -> { return new Toit(3, 1, 1); };
	    
	    List<Callable<IBloc>> tasks = Arrays.asList(taskMur1, taskMur1, taskMur2, taskMur2, taskPorte, taskToit);
	    try {
	        List<Future<IBloc>> resultas = executorService.invokeAll(tasks);
	        resultas.forEach((resultat) -> {
	        try {
	            blocs.add(resultat.get());
	        } catch (InterruptedException | ExecutionException e) {
	            logger.error("Erreur lors de création parallèle des blocs.");
	        }
	        });
	    } catch (InterruptedException e) {
	        logger.error("Erreur lors de création parallèle des blocs.");
	    }
	    executorService.shutdown();
	    return blocs;
	}
}