package com.clphub.utils;

//CODE By Irakli Charkhalashvili

import java.math.BigDecimal;

public class NumberInWordsGeorgian {

    public static String[] ones = {
            "ნული", //0
            "ერთი",//1
            "ორი",//2
            "სამი",//3
            "ოთხი",//4
            "ხუთი",//5
            "ექვსი",//6
            "შვიდი",//7
            "რვა",//8
            "ცხრა",//9
            "ათი"//10
    };

    public static String[] tens = {
            "ათი", //0
            "ოცი",//1
            "ოცდაათი",//2
            "ორმოცი",//3
            "ორმოცდაათი",
            "სამოცი",//4
            "სამოცდაათი",//5
            "ოთხმოცი",//6
            "ოცხმოცდაათი"//7

    };

    public static String[] onesTeens = {
            "ერთი",//0
            "თერთმეტი",//1
            "ორი",//2
            "თორმეტი",//3
            "სამი",//4
            "ცამეტი",//5
            "ოთხი",//6
            "თოთხმეტი",//7
            "ხუთ",//8
            "თხუთმეტი",//9
            "ექვსი",//10
            "თექვსმეტი", //11
            "შვიდი",
            "ჩვიდმეტი",
            "რვა",
            "თვრამეტი",
            "ცხრა",
            "ცხრამეტი",
            "ათი"
    };

    public static String[] teens = {
            "თერთმეტი",          // 0
            "თორმეტი",          // 1
            "ცამეტი",    // 2
            "თოთხმეტი",    // 3
            "თხუთმეტი",     // 4
            "თექვსმეტი",     // 5
            "ჩვიდმეტი",     // 6
            "თვრამეტი",   // 7
            "ცხრამეტი",    // 8
            "ოცი"
    };

    public static String[] tensPrefix = {
            "ოცდა",          // 0
            "ოცდა",          // 1
            "ორმოცდა",    // 2
            "ორმოცდა",    // 3
            "სამოცდა",     // 4
            "სამოცდა",     // 5
            "ოთხმოცდა",     // 6
            "ოთხმოცდა"  // 7
    };

    public static String[] aseulebi = {
            "ას",          // 0
            "ორას",          // 1
            "სამას",    // 2
            "ოთხას",    // 3
            "ხუთას",     // 4
            "ექვსას",     // 5
            "შვიდას",     // 6
            "რვაას",  // 7
            "ცხრაას",
            "ათას"
    };


    public static String getOnes(int i) {
        return ones[i];
    }


    public static String twentyToHundred(int number) {
        int x = number / 10;
        int y = number - x * 10;

        if (number % 10 == 0) {
            return tens[x - 1];
        }

        if (x % 2 == 0) {
            return tensPrefix[number / 10 - 2] + ones[y];
        } else {
            return tensPrefix[number / 10 - 2] + ((y == 0) ? tens[x] : onesTeens[y * 2 - 1]);
        }
    }

    public static String hundreds(int number) throws Exception {
        int x = number / 100;
        int y = number - x * 100;

        if (number % 100 == 0) {
            return ones[x] + "ასი";
        }

        return aseulebi[x - 1] + " " + analyseNumber(y);
    }

    public static String thousands(int number) throws Exception {
        int x = number / 1000;
        int y = number - x * 1000;
        if (x > 10) {
            return teens[x - 11] + " ათას " + analyseNumber(y);
        }
        if (x > 1) {
            return ones[x] + " ათას " + analyseNumber(y);
        } else {
            return "ათას " + analyseNumber(y);
        }

    }

    public static String tenThousands(int number) throws Exception {
        int x = number / 1000;
        int y = number - x * 1000;
        return analyseNumber(x) + " ათას " + analyseNumber(y);
    }

    public static String hundredThousands(int number) throws Exception {
        int x = number / 1000;
        int y = number - x * 1000;
        return analyseNumber(x) + " ათას " + analyseNumber(y);
    }

    public static String millions(int number) throws Exception {
        int x = number / 1000000;
        int y = number - x * 1000000;
        if (x > 10) {
            return analyseNumber(x) + " მილიონ " + analyseNumber(y);
        } else {
            return ones[x] + " მილიონ " + analyseNumber(y);
        }
    }


    public static String getNumberInGeorgianWords(BigDecimal number) throws Exception {
        int lari = number.intValue();
        BigDecimal tetri = number.subtract(new BigDecimal(lari)).multiply(new BigDecimal(100));
        tetri = tetri.setScale(0);
        return analyseNumber(lari) + " ლარი და " + tetri.toString() + " თეთრი";
    }

    public static String analyseNumber(int number) throws Exception {
        if (number == 0) {
            return getOnes(0);
        } else if (number > 0 && number <= 10) {
            return getOnes(number);
        } else if (number > 10 && number <= 20) {
            return teens[number - 11];
        } else if (number > 20 && number < 100) {
            return twentyToHundred(number);
        } else if (number == 100) {
            return "ასი";
        } else if (number > 100 && number < 1000) {
            return hundreds(number);
        } else if (number == 1000) {
            return "ათასი";
        } else if (number > 1000 && number < 10000) {
            return thousands(number);
        } else if (number == 10000) {
            return "ათი ათასი";
        } else if (number > 10000 && number < 100000) {
            return tenThousands(number);
        } else if (number == 100000) {
            return "ასი ათასი";
        } else if (number > 100000 && number < 1000000) {
            return hundredThousands(number);
        } else if (number == 1000000) {
            return "მილიონი";
        } else if (number > 1000000 && number < 100000000) {
            return millions(number);
        }
        return "Number Can't Be Converted";
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                getNumberInGeorgianWords(new BigDecimal("9545.36"))
        );
    }
}
