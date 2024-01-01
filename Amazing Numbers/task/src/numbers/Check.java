package numbers;

import java.util.*;

public class Check {

    public void printProperties(long number) {
        System.out.printf("Properties of %d\n", number);
        System.out.println("        buzz: " + checkBuzz(number));
        System.out.println("        duck: " + checkDuck(number));
        System.out.println("        palindromic: " + checkPalindromic(number));
        System.out.println("        gapful: " + checkGapful(number));
        System.out.println("        spy: " + checkSpy(number));
        System.out.println("        square: " + checkSquare(number));
        System.out.println("        sunny: " + checkSunny(number));
        System.out.println("        jumping: " + checkJumping(number));
        System.out.println("        happy: " + checkHappySad(number));
        System.out.println("        sad: " + !checkHappySad(number));
        System.out.println("        even: " + checkEvenOdd(number));
        System.out.println("        odd: " + !checkEvenOdd(number));
    }

    public void printProperties(long[] longs, List<String> playerProperties) {
        List<String> uncheckedProperties = new ArrayList<>();
        for (String playerProperty : playerProperties) {
            if (playerProperty.contains("-")) {
                uncheckedProperties.add(playerProperty.replace("-", ""));
            }
        }
        playerProperties.removeIf(s -> s.contains("-"));

        StringBuilder stringBuilder = new StringBuilder();
        for (long i = longs[0]; longs[1] > 0; i++) {
            stringBuilder.append(i).append(" is ");
            if (checkBuzz(i)) {
                stringBuilder.append("buzz, ");
            }
            if (checkDuck(i)) {
                stringBuilder.append("duck, ");
            }
            if (checkPalindromic(i)) {
                stringBuilder.append("palindromic, ");
            }
            if (checkGapful(i)) {
                stringBuilder.append("gapful, ");
            }
            if (checkSpy(i)) {
                stringBuilder.append("spy, ");
            }
            if (checkSquare(i)) {
                stringBuilder.append("square, ");
            }
            if (checkSunny(i)) {
                stringBuilder.append("sunny, ");
            }
            if (checkJumping(i)) {
                stringBuilder.append("jumping, ");
            }
            if (checkHappySad(i)) {
                stringBuilder.append("happy, ");
            }
            if (!checkHappySad(i)) {
                stringBuilder.append("sad, ");
            }
            if (checkEvenOdd(i)) {
                stringBuilder.append("even, ");
            }
            if (!checkEvenOdd(i)) {
                stringBuilder.append("odd, ");
            }
            if (!playerProperties.isEmpty() || !uncheckedProperties.isEmpty()) {
                String[] temp = stringBuilder.toString().replaceAll(i + " is ", "")
                        .replaceAll(" ", "").toUpperCase().split(",");
                if (uncheckedProperties.isEmpty()) {
                    if (new HashSet<>(Arrays.asList(temp)).containsAll(playerProperties)) {
                        longs[1]--;
                        if (!stringBuilder.isEmpty()) {
                            stringBuilder.setLength(stringBuilder.length() - 2);
                            System.out.println(stringBuilder);
                        }
                    }
                } else if (!playerProperties.isEmpty()) {
                    if (new HashSet<>(Arrays.asList(temp)).containsAll(playerProperties)
                            && !new HashSet<>(Arrays.asList(temp)).containsAll(uncheckedProperties)) {
                        longs[1]--;
                        if (!stringBuilder.isEmpty()) {
                            stringBuilder.setLength(stringBuilder.length() - 2);
                            System.out.println(stringBuilder);
                        }
                    }
                } else {
                    if (!new HashSet<>(Arrays.asList(temp)).containsAll(uncheckedProperties)) {
                        longs[1]--;
                        if (!stringBuilder.isEmpty()) {
                            stringBuilder.setLength(stringBuilder.length() - 2);
                            System.out.println(stringBuilder);
                        }
                    }
                }
            } else {
                longs[1]--;
                if (!stringBuilder.isEmpty()) {
                    stringBuilder.setLength(stringBuilder.length() - 2);
                    System.out.println(stringBuilder);
                }
            }
            stringBuilder = new StringBuilder();
        }
    }

    private boolean checkEvenOdd(long naturalNumber) {
        return naturalNumber % 2 == 0;
    }

    private boolean checkBuzz(long naturalNumber) {
        if (naturalNumber % 7 == 0 && naturalNumber % 10 == 7) {
            return true;
        } else if (naturalNumber % 7 == 0) {
            return true;
        } else {
            return naturalNumber % 10 == 7;
        }
    }

    private boolean checkDuck(long naturalNumber) {
        String string = Long.toString(naturalNumber);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    private boolean checkPalindromic(long naturalNumber) {
        String string = Long.toString(naturalNumber);
        for (int i = 0, j = string.length() - 1; i < string.length(); i++, j--) {
            if (string.charAt(i) != string.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkGapful(long naturalNumber) {
        if (naturalNumber < 100) {
            return false;
        }
        String string = Long.toString(naturalNumber);
        String temp = "" + (string.charAt(0) - 48) + (string.charAt(string.length() - 1) - 48);
        return naturalNumber % Integer.parseInt(temp) == 0;
    }

    private boolean checkSpy(long naturalNumber) {
        if (naturalNumber < 10) {
            return true;
        }
        long sum = 0;
        long multi = 1;
        String string = Long.toString(naturalNumber);
        for (int i = 0; i < string.length(); i++) {
            sum += (string.charAt(i) - 48);
            multi *= (string.charAt(i) - 48);
        }
        return sum == multi;
    }

    private boolean checkSquare(long naturalNumber) {
        return (long) Math.sqrt(naturalNumber) * (long) Math.sqrt(naturalNumber) == naturalNumber;
    }

    private boolean checkSunny(long naturalNumber) {
        return (long) Math.sqrt(naturalNumber + 1) * (long) Math.sqrt(naturalNumber + 1) == naturalNumber + 1;
    }

    private boolean checkJumping(long naturalNumber) {
        if (naturalNumber > 0 && naturalNumber < 10) {
            return true;
        }
        String string = Long.toString(naturalNumber);
        boolean result = false;
        for (int i = 1; i < string.length(); i++) {
            if ((((string.charAt(i - 1) - 48) + 1) == string.charAt(i) - 48)
                    || (((string.charAt(i - 1) - 48) - 1) == string.charAt(i) - 48)) {
                result = true;
                continue;
            }
            return false;
        }
        return result;
    }

    private boolean checkHappySad(long naturalNumber) {
        Set<Long> uniqueNumbersEncountered = new HashSet<>();
        while (uniqueNumbersEncountered.add(naturalNumber)) {
            int value = 0;
            while (naturalNumber > 0) {
                value += (int) Math.pow(naturalNumber % 10, 2);
                naturalNumber /= 10;
            }
            naturalNumber = value;
        }
        return naturalNumber == 1;
    }
}
