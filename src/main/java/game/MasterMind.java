package game;

import java.util.Random;
import java.util.Scanner;

public class MasterMind {
    public static void main(String[] args) {
        System.out.println("Witaj w grze MasterMind!");
        System.out.println("Zgadnij 4-cyfrowy kod, składający się z liczb od 1 do 6.");

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int codeLength = 4;//długość tajnego kodu
        int maxDigit = 6;// maksymalna wartość cyfr

        int[] secretCode = new int[codeLength];
        int[] userCode = new int[codeLength];
        boolean guessed = false;


        //generowanie tajnego kodu
        for (int i = 0; i < secretCode.length; i++) {
            secretCode[i] = random.nextInt(maxDigit) + 1;
        }

        //pętla while - trwa, dopóki kod nie zostanie odgadnięty
        while (guessed == false) {

            System.out.print("Wprowadz swoją próbę: ");
            String guess = sc.nextLine();

            try {
                //sprawdzenie długośći kodu
                if (guess.length() != codeLength) {
                    throw new NumberFormatException();
                }
                for (int i = 0; i < codeLength; i++) {
                    userCode[i] = Character.getNumericValue(guess.charAt(i));
                    if (userCode[i] < 1 || userCode[i] > maxDigit) {
                        throw new NumberFormatException();
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wprowadż liczby od 1 do " + maxDigit + ".");
            }

            int identicalButNotInPlace = 0;
            int identicalAndInPlace = 0;
            boolean[] countedInUserCode = new boolean[codeLength];
            boolean[] countedInSecretCode = new boolean[codeLength];


            //szukanie cyfr poprawnych na właściwej pozycji
            for (int i = 0; i < codeLength; i++) {
                if (userCode[i] == secretCode[i]) {
                    identicalAndInPlace++;
                    countedInUserCode[i] = true;
                    countedInSecretCode[i] = true;
                }
            }

            //szukanie cyfr poprawnych na złej pozycji
            for (int i = 0; i < codeLength; i++) {
                if (countedInUserCode[i] != true) {
                    for (int j = 0; j < codeLength; j++) {
                        if (countedInSecretCode[j] == false && userCode[i] == secretCode[j]) {
                            identicalButNotInPlace++;
                            countedInUserCode[i] = true;
                            countedInSecretCode[i] = true;
                        }
                    }
                }
            }

            //sprawdzenie, czy gracz odgadł kod
            if (identicalAndInPlace == codeLength) {
                System.out.println("Gratulacje! Kod został odgadnięty");
                guessed = true;
            } else {
                System.out.println("Poprawne cyfry na poprawnej pozycji: " + identicalAndInPlace);
                System.out.println("Poprawne cyfry na niepoprawnych pozycjach: " + identicalButNotInPlace);
            }
        }
        sc.close();
    }
}


