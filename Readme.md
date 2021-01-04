Projet de PCD 2019.




Membres du groupe : 

FAZOUANE Souhail
REHIOUI Walid
HMIZA Jaber 
LATA Warren





Release Day 1:
 * Mise en place de la vue principale
 * Recherche d'un flux RSS et sur le parsing du XML correspondant
 * Tester le lancement du 1er Media
 * Conception du Podcast (Ajout des classes du Modèle)
 * Ajout des classes des vues( Combination FXML/JavaFX)

Release Day 2:
 * Réalisation d'une base de donnée en dur(format JSON) vu qu'on avait pas d'accès à l' API
 * Liaison entre Vue et modèle (PodcastApp)
 * Liaison base de donnée et modèle (iTunes data)
 * Installation gradle
 * Visualisation de l'interface (PodcastView et EpisodeView) => Update de l'interface EpisodeView selon le Podcast sélectionné
 * Ajout lecture d'un Média se trouvant en local (Bouton Openfile)
 * Ajout d'une version prototype des fonctionnalités correspondantes au MediaPlayer (Play/Pause/Speed ratio/Stop)
 * récupération de la clé pour accéder à l'api partagée (ListenNotes)

Release Day 3:
 * Ajout de la classe PodcastApiFetcher qui récupère les flux RSS à travers une requête de l'utilisateur
 * Optimisation de l'interface Graphique (coloration)
 * Ajout D'un bouton Download qui permet le téléchargement en local Des épisodes d'un podcast sans bloquer l'interface (Thread)
 * Modification de la classe PodcastApiFetcher pour qu'elle donne des flux RSS correspondant à une requête de recherche selon un mot-clé ou un genre ou la langue
 * Modification de la classe Podcast et PodcastFinder pour qu'elle puisse inclure aussi l'image d'un Podcast, son langage et sa description.
 * Modification de la classe PodcastApp pour qu'elle sOIT compatible avec les fonctionnalités requises (mode préférence,mode accueil, mode recherche).

Release Day 4:
 * Réussite des requêtes faites avec l'API : ( affichage des 5 podcasts populaires et récents au hasard, en mode actualité + chercher les flux rss correspondant
 	à des critères spécifiés par l'utilisateur. ) 
 * Travail sur l'interface graphique : amélioration et liaison des fonctionalités du model à chaque vue) . 
 * Ajout de la fonctionalité 'ajouter' ou 'supprimer' des podcasts dans la liste de préférence.
 * Ajout de la fonctionalité mediaplayer d'un épisode d'un podcast (barre d'avancement de la vidéo + slider pour controler le son. );
 

Release Final:
 * Amélioration des interfaces pour une meilleur visualisation. 
 * Correction des derniers bugs de  l'application (affichage inutile, réduction du tempsd'éxécution . 
 * Ajout d'un média player interactif pour la lecture des audios et videos
 

Lancement:
 
 Notre application est munie d'un gradle. Pour la runner il suffit donc soit de faire un ./gradlew run dans le répertoire de notre application Fortify(cd Fortify).

 Ou de faire un ./gradlew jar ( en étant toujours dans le répertoire Fortify) et lancer par la suite java -jar /build/libs/PodcastApp-Fortify.jar. 

Ou de lancer ./gradlew run
 
 Commentaire: Pour faire une recherche sur l'application il faut obligatoirement spécifier un mot-clé(qui peut s'apparenter à un thème,une personne, un sujet etc...)
 

