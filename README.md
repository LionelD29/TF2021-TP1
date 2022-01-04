# Gestion des inscriptions à un stage
## Présentation du projet
Le projet est un programme de gestion d'inscriptions à des activités de stage.
Il s'agit d'une application en console où l'on peut gérer des types d'activités, des horaires (ajouter des activités, modifier les horaires d'activités ou les supprimer) ainsi que les inscriptions aux différentes activités.
Les données (types d'activités, horaires, inscriptions, ...) sont sauvegardées dans un fichier externe.
Les choix possibles de l'utilisateur sont affichés sous forme de menus. Le code pour la construction et la gestion de menus est repris et amélioré du projet [TF2021_Menu](https://github.com/LionelD29/TF2021-Menu) terminé plus tôt dans la formation.

## Progression du projet
Le projet est toujours en cours de développement. La progression est représentée à l'aide d'icônes.

(*voir la [légende](#légende) en fin de page*)

### ✅ Gestion des types d'activités
Diverses opérations peuvent être effectuées sur les types d'activités :
- [X] **Créer** un nouveau type d'activité
- [X] **Supprimer** un type d'activité
- [X] **Modifier** un type d'activité

Si un type d'activité est utilisé dans l'horaire :
- [X] La suppression du type d'activité est impossible
- [X] La modification du type entraine la modification du type d'activité de chacune des activités de ce type dans l'horaire

### ✔️ Gestion des horaires
On travaille avec des activités qui possèdent un type d'activité, un nom, un moment de début et de fin.

Dans l'horaire, on peut effectuer les actions suivantes :
- [X] **Ajouter** une activité à l'horaire
    * D'un type d'activité déjà existant
    * D'un type d'activité pas encore créé (la création en est alors proposée)
- [X] **Modifier** l'horaire d'une activité
- [X] **Supprimer** une activité de l'horaire
- [X] **Afficher** la liste des types d'activités

Le cas des activités portant des points communs (nom, début ou fin) est encore à travailler.

### ❌ Gestion des inscriptions
À venir...

### ✔️ Sauvegarde des données
Les données de l'application (types d'activités, horaires et inscriptions) sont sauvées dans un fichier externe.

#### Données actuellement sauvées :
- [X] Types d'activités
- [X] Horaire
- [ ] Inscriptions

Les données d'inscriptions ne sont pas encore implémentées.

### ❌ Diagrammes UML de l'application
À venir...

- - - -

#### Légende
✅ : section finie &emsp;|&emsp; ✔️ : section en cours &emsp;|&emsp; ❌ : section à faire
