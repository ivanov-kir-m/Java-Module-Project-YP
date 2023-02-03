package Calculator;

public class Formatter {

    /**
     * Получаем падеж слова рубль
     */
    public static String getRubText(double sum){
        int normalized_sum = (int) Math.floor(sum);
        if (normalized_sum % 100 / 10 == 1)
        {
            return "рублей";
        }
        switch ((int) sum % 10)
        {
            case 1:
                return "рубль";
            case 2:
            case 3:
            case 4:
                return "рубля";
            default:
                return "рублей";
        }
    }
}
