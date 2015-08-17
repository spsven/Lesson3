package com.homework._2015_03_06.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Alexandr Kozlov on 06.03.2015.
 */
public class HomeWork3 {

    /**
     * Метод предназначен для чтения строки с консоли.
     * Возвращает строку c текстом.
     */
    private static String readConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        return reader.readLine();
    }

    /**
     * Метод предназначен для:
     * - перевода текста в нижний регистр
     * - разбивки текста на отдельные слова
     * - очистки текста от любых символов, кроме букв
     * <p/>
     * На входе - текст, который ввели с консоли.
     * На выходе - множество уникальных слов из текста в нижнем регистре
     */
    private static Set<String> setCleanAndSplit(String s) {
        s = s.toLowerCase();
        String[] arrWords = s.split(" ");
        // очистка от любых символов, кроме букв
        for (int i = 0; i < arrWords.length; i++) {
            arrWords[i] = arrWords[i].replaceAll("[^a-z]", "").replaceAll(" +", " ").trim();
        }

        //Косвенная проверка на наличие хотя бы одной буквы в ведённом тексте
        int iterator = 0;
        for (String arrWord1 : arrWords) {
            if (arrWord1.equals("")) {
                iterator++;
            }
        }
        Set<String> setAllWord = new HashSet<String>();

        if (iterator == arrWords.length) {
            System.out.println("Вы ввели строку без английских букв!");
        } else {

            // оставляем только уникальные слова из текста
            for (String arrWord : arrWords) {
                setAllWord.add(arrWord);
            }
            // удаляем пробелы из set
            for (int i = 0; i < setAllWord.size(); i++) {
                if (setAllWord.contains("")) {
                    setAllWord.remove("");
                }
            }
        }
        return setAllWord;
    }

    /**
     * Метод предназначен для получения множества уникальных букв,
     * с которых в тексте начинаются слова.
     * На входе - множество слов из текста (уникальных).
     * На выходе - сортированный список из начальных букв слов текста.
     */

    private static List<String> setFirstLetterAndSort(Set<String> setAllWord) {
        // множество на буквы
        Set<String> setAllFirstLetter = new HashSet<String>();

        for (String word : setAllWord) {
            setAllFirstLetter.add(String.valueOf(word.charAt(0)));// первый символ каждого слова
        }

        //Сортировка букв в алфавитном порядке
        List<String> list = new ArrayList<String>(setAllFirstLetter);
        Collections.sort(list);

        return list;
    }

    /**
     * Метод предназначен для вывода текста в нужном итоговом формате.
     *
     * @param listFirstLetter
     * @param listAllWords
     */
    private static void wordPrint(List<String> listFirstLetter, List<String> listAllWords) {

        String[] string = new String[listFirstLetter.size()];

        for (int i = 0; i < listFirstLetter.size(); i++) {
            string[i] = "";
            for (int j = 0; j < listAllWords.size(); j++) {
                // Поиск совпадения первой буквы слова с буквой из списка уникальных букв
                if (listAllWords.get(j).startsWith(listFirstLetter.get(i))) {
                    string[i] = string[i] + listAllWords.get(j) + " ";
                }
            }
        }
        //  Вывод в нужном формате
        for (int i = 0; i < listFirstLetter.size(); i++) {
            System.out.println(listFirstLetter.get(i).toUpperCase() + ": " + string[i]);
        }
    }

    private static void textDecode(String mainText) throws IOException {

        // чистка и обработка текста
        Set<String> setAllWord = setCleanAndSplit(mainText); // чиста и обработка текста

        // получение сортированного списка начальных букв слов из текста
        List<String> listFirstLetter = setFirstLetterAndSort(setAllWord);

        // Сортировка всех слов
        List<String> listAllWords = new ArrayList<String>(setAllWord);
        Collections.sort(listAllWords);
        //
        wordPrint(listFirstLetter, listAllWords);

    }


    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Т.к. Вы не ввели текст как параметр при компиляции через коммандную строку, " +
                    "то введите его сейчас, после этого сообщения.\n" +
                    "Текст должен быть на английском языке.");
            String mainText = readConsole();

            textDecode(mainText);
        } else {
            System.out.println("Вы вводили текст как параметр при компиляции через коммандную строку.\n");

            // Собираем строку из элементов массива args
            String mainText = "";
            for (String text : args) {
                mainText = mainText + text + " ";
            }

            System.out.println("Ваш текст: \n" + mainText + " \n");

            textDecode(mainText);
        }
    }
}
