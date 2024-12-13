import java.util.Scanner;

public class lab2 {

    public static void main(String[] args) {
        try {
            // Введення тексту користувачем
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть текст:");
            StringBuffer text = new StringBuffer(scanner.nextLine());

            System.out.print("Введіть довжину слова для пошуку: ");
            int wordLength = scanner.nextInt();

            // Створюємо об'єкт класу та виконуємо обробку тексту
            lab2 processor = new lab2();
            StringBuffer result = processor.dividingSentence(text, wordLength);

            // Виводимо результати
            System.out.println("Слова довжини " + wordLength + " у питальних реченнях: " + result);
        } catch (Exception e) {
            System.err.println("Виникла помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Знаходить та повертає унікальні слова заданої довжини з питальних речень тексту.
     *
     * @param text вхідний текст у форматі StringBuffer
     * @param wordLength довжина слів для пошуку
     * @return StringBuffer, що містить унікальні слова відповідної довжини
     */
    public StringBuffer dividingSentence(StringBuffer text, int wordLength) {
        StringBuffer words = new StringBuffer();

        // Розбиваємо текст на речення вручну
        StringBuffer sentence = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sentence.append(c);
            if (c == '?' || c == '.' || c == '!') {
                if (c == '?') {
                    findWords(sentence, wordLength, words);
                }
                sentence.setLength(0); // Очищаємо для наступного речення
            }
        }

        return words;
    }

    /**
     * Знаходить слова заданої довжини в одному реченні та додає їх до результату.
     *
     * @param sentence вхідне речення у форматі StringBuffer
     * @param wordLength довжина слів для пошуку
     * @param words StringBuffer для збереження унікальних слів
     */
    private void findWords(StringBuffer sentence, int wordLength, StringBuffer words) {
        StringBuffer word = new StringBuffer();
        for (int i = 0; i <= sentence.length(); i++) {
            char c = i < sentence.length() ? sentence.charAt(i) : ' '; // Додаємо пробіл наприкінці
            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else {
                if (word.length() == wordLength && !containsWord(words, word)) {
                    if (words.length() > 0) {
                        words.append(", ");
                    }
                    words.append(word);
                }
                word.setLength(0);
            }
        }
    }

    /**
     * Перевіряє, чи вже існує слово в StringBuffer результату.
     *
     * @param words StringBuffer, що містить унікальні слова
     * @param word слово для перевірки
     * @return true, якщо слово існує, false - інакше
     */
    private boolean containsWord(StringBuffer words, StringBuffer word) {
        String[] existingWords = words.toString().split(", ");
        for (String existingWord : existingWords) {
            if (existingWord.equals(word.toString())) {
                return true;
            }
        }
        return false;
    }
}