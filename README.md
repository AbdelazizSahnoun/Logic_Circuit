# Projet de session

## 1.Objectif du projet

Développer un système de modélisation et de calcul pour des circuits logiques.

### 1.1. Objectifs Pédagogique
* Concevoir et développer un logiciel de grande envergure
* Conception incrémentale
* Livraison régulière de valeur à un client


### 1.2 Environnement nécessaire et outillage
* Java (version 8 ou supérieure)
* Maven (version 3.x)
* Un environnent de développement récent (p.-ex, IntelliJ)
* Un outil de modélisation permettant de réaliser des diagrammes UML conformes (p.-ex. PlantUML, Visual Paradigm)


## 3. Execution

Le projet s'exécute à travers les deux commandes : 	
  *  mvn clean package
  *  mvn -q exec:java


## 4. Description du domaine
Ce projet de session vise à réaliser la conception et l’implémentation d’un logiciel de modélisation de circuits logiques. Un circuit logique est un ensemble de portes logiques reliées entre‐elles à travers leurs entrées et leur sorties. Principalement utilisé en électronique, un circuit logique permet d’utiliser la logique booléenne pour concevoir des circuits intégrés; les portes logiques étant remplacées par des diodes ou des transistors. Les 3 portes de base correspondent aux trois opérateurs booléens de base, à savoir les portes : AND (ET logique), OR (OU inclusif) et NOT. (Négation).

|![image](https://user-images.githubusercontent.com/37906695/137015228-c93a3f93-dd40-4f01-83dd-79b3836df860.png)|
|:--:| 
| *Porte OR* |

|![image](https://user-images.githubusercontent.com/37906695/137015464-1a931cc1-a222-4927-94c1-7e4fd6225d39.png)|
|:--:| 
| *Porte AND* |

|![image](https://user-images.githubusercontent.com/37906695/137015525-69160a81-5927-497f-8d25-ba4c8c6972c8.png)|
|:--:| 
| *Porte NOT* |

Plusieurs autres portes communément utilisées peuvent être construites en utilisant ces portes telles que NAND, NOR, XOR et XNOR. 

Un circuit logique comporte un nombre déterminé d’entrées et de sorties booléennes (0 ou 1). Pour qu’un circuit soit valide, if faut que : 
* Chacune des entrées du circuit doit obligatoirement être reliée à au moins une entrée d’une porte1; 
* Chacune des sorties du circuit doit obligatoirement être reliée à au plus une sortie d’une porte1. 
* Pour chacune des portes qui constituent le circuit, chacune de ses entrées doit obligatoirement être reliées à une et une seule source. Une source pouvant être soit la sortie d’une autre porte1 ou une entrée du circuit. 
* Nous supposerons, pour simplifier, qu’un circuit valide ne doit par comporter de boucles. 

La Figure 1, ci--dessous, donne un exemple de circuit logique valide comportant trois entrées (E1, E2 et E3), une sortie (S1) et qui est constitué de deux portes AND, une porte1 NOT et une porte1 OR.

|![image](https://user-images.githubusercontent.com/37906695/137015655-edd01689-b94b-4d57-aec4-a3c1baef52c5.png)|
|:--:| 
| *Figure 1 : Circuit valide* |

Le circuit représenté dans la Figure2 est invalide car une des entrées de la deuxième porte1 AND est reliée à deux sources.
|![image](https://user-images.githubusercontent.com/37906695/137015756-4bb5aa0d-9f5d-4686-8f7a-7900c9d0a5ae.png)|
|:--:| 
| *Figure 2 : Circuit invalide* |

Comme pour une porte1, à chaque circuit logique, on associe une table de vérité.Une table de vérité associée à chaque jeu d’entrées les sorties correspondantes. Le nombre de ligne d’une table de vérité dépend du nombre d’entrées du circuit :Si nous avons n entrées dans notre circuit, nous aurons 2n lignes. Le tableau suivant donne la table de vérité pour le circuit de la Figure1.

|![image](https://user-images.githubusercontent.com/37906695/137016037-e9271413-51b1-4318-b9f1-8f106cd62d83.png)|
|:--:| 
| *Table de vérité du circuit de la figure 1* |

## 5. Exigences Logicielles 

Les sous‐sections suivantes énumèrent les exigences du logiciel à concevoir et à implémenter. Les exigences sont regroupées à la fois logiquement et par priorité.

### 5.1 Fonctionnalisés de bases (Base) - priorité haute
 
* Base1: À l’ouverture de l’application, un circuit vide est crée comportant deux (2) entrées et une (1) sortie. 
* Base2 : Un circuit peut être sauvegardé à tout moment sur le disque dur. 
* Base2.1 : Un circuit doit être sauvegardé au format XMI. 
* Base2.2 : Lorsque l’usager demande à sauvegarder un circuit, le système doit lui demander le nom du fichier à sauvegarder ainsi que le dossier dans lequel il * doit être sauvegardé. 
* Base3 : À tout moment, l’usager doit pouvoir charger un circuit, préalablement sauvegardé depuis le disque dur. Le système doit demander à l’utilisateur de sélectionner le fichier à charger en indiquant le dossier et le fichier à charger. 
* Base4 : Le système doit permettre la création d’un nouveau circuit. Le circuit en cours d’édition doit alors être fermé. 
* Base5 : À tout moment, un seul circuit est affiché par le logiciel. Lors du chargement d’un circuit, le circuit étant déjà à l’écran est fermé par le logiciel.
* Base6 : Le logiciel doit demander une confirmation de fermeture du circuit en cours lors du chargement d’un autre circuit (Base3) ou lors de la fermeture de l’application si celui‐ci a subi des modifications depuis la dernière sauvegarde.

### 5.2 Fonctionnalités d’édition (Edit) – Priorité haute  

* Edit1: L’utilisateur doit être en mesure d’ajouter ou de supprimer des entrées et des sorties au circuit. 
* Edit1.1 : Le circuit doit comporter à tout moment au moins une entrée et au moins une sortie. Si une seule entrée ou une seule sortie existe et l’utilisateur demande à la supprimer, le système doit afficher un message d’erreur et l’opération doit être annulée. 
* Edit1.2 : Le système doit empêcher la création de plus de 5 entrées et de plus de 5 sorties dans un même circuit. 
* Edit2 : L’utilisateur doit pouvoir définir un nom unique pour chaque entrée ou sortie;
* Edit2.1 : Le nom d’une entrée ou d’une sortie ne doit pas excéder 5 caractères alphanumériques. 
* Edit2.2 : Le système doit générer un nom par défaut automatiquement pour chaque entrée ou sortie de la forme Ex pour une entrée et Sy pour les sorties (x et y étant des entiers). Ex. : La première entrée ajoutée du circuit se nommera E1, la seconde E2, etc. 
* Edit2.3 : Si l’utilisateur spécifie un nom d’entrée ou de sortie déjà existant, le système doit afficher un message d’erreur et annuler la modification. 
* Edit3: Si une entrée ou une sortie du circuit est supprimée alors que celle‐ci était reliée à une porte1, la liaison avec la porte1 est également supprimée. 
* Edit4 : L’utilisateur doit être en mesure d’ajouter une porte1 au circuit. 
* Edit4.1 : Le système doit permettre l’ajout, par défaut, des portes standard à savoir : AND, OR et NOT. 
* Edit4.2 : Un circuit donné ne peut contenir plus de 50 portes. 
* Edit5 : L’utilisateur doit être en mesure de supprimer une porte1 du circuit. 
* Edit5.1 : Lorsqu’une porte1 est supprimée du circuit, toutes les liaisons de la porte1 (avec d’autres portes ou des entrée/sorties du circuit) doivent être supprimées automatiquement. 
* Edit6 : L’utilisateur doit être en mesure de relier une source à une entrée d’une porte1 ou à une sortie du circuit.
* Edit6.1 : Le système ne doit pas permettre de relier une source à une entrée d’une porte1 (ou une sortie du circuit) qui est déjà reliée à une source.

### 5.3 Fonctionnalités de calcul et de validation (Calc) – Priorité moyenne 

* Calc1 : Le système doit afficher en permanence la table de vérité du circuit.
* Calc1.1 : Le système doit générer automatiquement, dans la table de vérité, les différentes combinaisons d’entrées dans la table de vérité, selon le nombre d’entrées du circuit. 
* Calc1.2 : Le système doit afficher initialement la valeur « ND » (pour « non‐déterminée ») dans la/les colonne(s) de sorties de la table de vérité.  
* Calc1.3 : Le système doit actualiser les colonnes de la table de vérité à chaque ajout ou suppression d’une entrée ou d’une sortie du circuit. Chaque colonne doit porter le nom de l’entrée ou de la sortie correspondante. 
* Calc2 : L’utilisateur doit être en mesure de demander au système de calculer la table de vérité. Les valeurs des sorties sont alors remplacées par les valeurs calculées. 
* Calc2.1 : La fonctionnalité de calcul de la table de vérité ne doit pas être disponible si le circuit n’est pas valide. 
* Calc2.2 : Suite à toute modification du circuit (Edit1, Edit4, Edit5 et Edit6), les valeurs des sorties de la table de vérité doivent être réinitialisées à leur valeur par défaut « ND ». 
* Calc3 : Le système doit vérifier, suite à chaque modification du circuit (i.e. en temps réel) la validité du circuit.


### 5.7 Exigences non-fonctionnelles 

* NF1 : Le système doit manipuler le circuit intégralement en mémoire. Aucun système de buffer ou de cache sur le disque n’est permis. Compte tenu de la taille maximale d’un circuit et de la mémoire généralement disponible dans les ordinateurs contemporains, nous estimons que ceci est une exigence raisonnable. 
* NF2 : Le système doit fournir une interface utilisateur permettant à un utilisateur ayant déjà utilisé le système pendant 30mn, d’ajouter une porte1 à un circuit et relier chacune de ses entrées/sorties en moins de 30sec. En d’autres termes, l’implémentation d’une interface graphique implémentant le Drag&Drop pour la fenêtre d’édition du circuit n’est pas obligatoire (bien que préférable) pour peu que le système soit utilisable convenablement. 
* NF3 : Le système doit être conçu de façon à favoriser sa maintenabilité. L’utilisation de patrons de conception, lorsque approprié est requise. 
* NF4 : Le système doit permettre l’ajout de fonctionnalités d’analyse et de calcul sur le circuit sans affecter la structure des circuits. En d’autres termes, on doit prévoir l’ajout de fonctionnalités de calcul à l’avenir sans que les circuits créés avec une version précédente de l’application ne doivent être modifiés ou convertis.






