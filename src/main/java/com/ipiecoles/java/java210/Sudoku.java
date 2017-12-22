package com.ipiecoles.java.java210;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sudoku {

    /**
     * Constructeur par défaut
     */
    /**
     * chaine de fin de saisie utilisateur
     */
    public static final String FIN_SAISIE = "FIN";

    /**
     * Booléen permettant de voir si le sudoku a été réalisé
     */
    private boolean resolu = false;

    /**
     * Declaration du tableau sudoku
     */
    private short[][] sudokuAResoudre;

    public short[][] getSudokuAResoudre() {
        return sudokuAResoudre;
    }

    public void setSudokuAResoudre(short[][] sudokuAResoudre) {
        this.sudokuAResoudre = sudokuAResoudre;
    }

    public Sudoku() {
        /**
         * initialisation du tableau 9 * 9
         */
        setSudokuAResoudre(new short[9][9]);  // similaire à : sudokuAResoudre = new short [9][9];

    }

    public boolean ligneSaisieEstCoherente(String ligneSaisie) {
        //exercice 6 ( ne jamais utiliser == pour comparer des chaines de caractères) :
        if (ligneSaisie == null || ligneSaisie.trim().equals("")) {
            System.out.println("Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces");
            return false;
        }

        //exercice 7 (trim supprime espaces) :
        if (ligneSaisie.length() != 3) {
            System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères");
            return false;
        }

        //exercice 8 - 9 - 10 :
        if (!ligneSaisie.substring(0, 1).matches("[0-8]") || !ligneSaisie.substring(1, 2).matches("[0-8]") || !ligneSaisie.substring(2, 3).matches("[1-9]")) {
            System.out.println("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9");
            return false;
        }

        return true;
    }


    /**
     * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
     * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
     * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie.
     * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
     * comportant les coordonnées des chiffres saisis.
     * <p>
     * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
     * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
     * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
     *
     * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
     */
    public String[] demandeCoordonneesSudoku() {
        System.out.println("Bonjour et bienvenu sur la saisie de coordonnées du Sudoku");
        System.out.println("Choisis une abscisse X (entre 0 et 8), une ordonnée Y (entre 0 et 8) et une valeur Z (entre 1 et 9)");
        Scanner coordonnees = new Scanner(System.in);
        String[] tabcoordonnees = new String[81];
        String coordonneessaisies = null;

        // Initialisation de l'indice
        int indiceTableauCoordonnees = 0;

        //Boucle tant que l'utilisateur n'a pas saisi "FIN" et l'utilisateur a saisi moins de 81 coordonnées (while, for)
        do {
            // Recupérer la saisie utilisateur
            // String coordonneessaisies = coordonnees.nextLine(); => NoSuchElementException, try catch
            try {
                coordonneessaisies = coordonnees.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Erreur technique :" + e.getMessage());
                break;
            }
            // Controler la saisie utilisateur

            if (ligneSaisieEstCoherente(coordonneessaisies)) {
                // Insérer dans le tableau incrémenter pour stocker coordonneesaisies dans le tableau
                tabcoordonnees[indiceTableauCoordonnees++] = coordonneessaisies;
            } else {
                System.out.println("Choisis une abscisse X (entre 0 et 8), une ordonnée Y (entre 0 et 8) et une valeur Z (entre 1 et 9)");
            }

        }
        while (!FIN_SAISIE.equals(coordonneessaisies) && indiceTableauCoordonnees < 81);

        return tabcoordonnees;
    }

    /**
     * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant
     * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
     * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est
     * rencontrée dans le tableau, on arrête le traitement
     * <p>
     * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
     *
     * @param tableauCoordonnees
     */
    public void remplitSudokuATrous(String[] tableauCoordonnees) {
        // parcourir le tableau de coordonnées
        int i = 0;
        for (i = 0; i < tableauCoordonnees.length; i++) {
            //pour chaque élément, on extrait l'abscisse, l'ordonnée et la valeur
            //on convertit les chaines en type short
            //et on remplit la sudokuAResoudre au bon endroit
            //si une valeur nulle est rencontrée, on sort de la boucle
            if (tableauCoordonnees[i] == null) {
                break;
            }
            String abscisse = tableauCoordonnees[i].substring(0, 1);
            String ordonnee = tableauCoordonnees[i].substring(1, 2);
            String valeur = tableauCoordonnees[i].substring(2); //car notre chaine fait 3 caractères

            //Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string) // Cast to Short
            sudokuAResoudre[stringToInt(abscisse)][stringToInt(ordonnee)] = (short) stringToInt(valeur);


        }



    }

    private int stringToInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Cette méthode affiche un sudoku de manière formatée sur la console.
     * Cela doit ressembler exactement à :
     * -----------------------
     * |   8   | 4   2 |   6   |
     * |   3 4 |       | 9 1   |
     * | 9 6   |       |   8 4 |
     *  -----------------------
     * |       | 2 1 6 |       |
     * |       |       |       |
     * |       | 3 5 7 |       |
     *  -----------------------
     * | 8 4   |       |   7 5 |
     * |   2 6 |       | 1 3   |
     * |   9   | 7   1 |   4   |
     *  -----------------------
     *
     * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non).
     *               Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur
     *               non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
     */
    public void ecrireSudoku(short[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                if (sudoku[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(sudoku[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }



    /**
     * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
     * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes :
     * <p>
     * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
     * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
     * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
     *
     * @param ligne
     * @param colonne
     * @param chiffre
     * @param sudoku
     * @return
     */
    public boolean estAutorise(int colonne, int ligne, short chiffre, short[][] sudoku) {

        for (int k = 0; k < 9; k++) {
            //Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
            if (chiffre == sudoku[ligne][k]) {
                return false;
            }
            //Si la valeur est déjà dans la colonne, le chiffre n'est pas autorisé
            if (chiffre == sudoku[k][colonne]) {
                return false;
            }

        }

        //Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
        int boiteDecalageLigne = (ligne / 3) * 3; //ligne est un entier si ligne = 4 ligne / 2 = 1
        int boiteDecalageColonne = (colonne / 3) * 3;
        for (int k = 0; k < 3; k++) {
            for (int m = 0; m < 3; m++) { //box
                if (chiffre == sudoku[boiteDecalageLigne + k][boiteDecalageColonne + m]) {
                    return false;
                }

            }
        }

        return true;
    }

    public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
        //Pour commencer, on teste si on est arrivé au bout de la ligne, si c'est
        //le cas, on remet l'abscisse à 0 et on augmente l'ordonnée mais si on est
        //arrivé à 9, on retourne true (on a traité tout le sudoku)
        if (abscisse == 9) {
            //Si on est arrivé au bout de la ligne, on remet l'abscisse à 0
            abscisse = 0;
            //Et on augmente l'ordonnée
            if (++ordonnee == 9) {
                //On sort de la méthode si on dépasse la dernière colonne
                return true;
            }
        }

        //On passe les éléments déjà remplis en incrémentant l'abscisse
        if (sudoku[abscisse][ordonnee] != 0) {
            //Pour les autres on appelle la méthode de résolution
            return resoudre(abscisse+1,ordonnee,sudoku);
        }

        //Sinon, on essaye chaque valeur dans la case en appelant la méthode estAutorise
        //Si c'est le cas, on met cette valeur dans le sudoku et on appelle de
        //nouveau la méthode de résolution en incrémentant l'abscisse et si
        //cette dernière retourne true, on retourne true.
        for (short val = 1; val <= 9; ++val) {
            if (estAutorise(abscisse,ordonnee,val,sudoku)) {
                sudoku[abscisse][ordonnee] = val;
                if (resoudre(abscisse+1,ordonnee,sudoku)) {
                    return true;
                }
            }
        }
        //Si aucune valeur n'est autorisée, on remet la valeur 0
        //dans le sudoku
        //et on fait machine arrière en retournant false
        sudoku[abscisse][ordonnee] = 0;
        return false;
    }
}
