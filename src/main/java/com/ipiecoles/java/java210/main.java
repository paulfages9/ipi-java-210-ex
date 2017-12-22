package com.ipiecoles.java.java210;

public class main {
    public static void main(String[] args) {
        //nouvelle instance
        Sudoku s = new Sudoku();

        String [] saisie = s.demandeCoordonneesSudoku();
        s.remplitSudokuATrous(saisie);
        s.ecrireSudoku(s.getSudokuAResoudre());

        if (s.resoudre(0,0,s.getSudokuAResoudre())) {
            s.ecrireSudoku(s.getSudokuAResoudre());
        }
        else {
            System.out.print("Pas de solution");
        }

    }
}
