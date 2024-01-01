package numbers;


import java.util.*;
import java.util.stream.Collectors;

public class AmazingNumbers {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] PROPERTIES =
            {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "EVEN", "ODD", "JUMPING", "HAPPY", "SAD",
                    "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY", "-SQUARE", "-SUNNY", "-EVEN", "-ODD", "-JUMPING", "-HAPPY", "-SAD"};
    private long[] longs;
    private List<String> playerProperties;

    private void setPlayerProperties(List<String> playerProperties) {
        this.playerProperties = playerProperties;
    }

    private void setLongs(long[] longs) {
        this.longs = longs;
    }

    private boolean checkProperties(List<String> playerProperties) {
        int count = 0;
        for (int i = 0; i < playerProperties.size(); i++) {
            for (int j = 0; j < playerProperties.size(); j++) {
                if (j != i && playerProperties.get(i).replace("-", "")
                        .equals(playerProperties.get(j).replace("-", ""))) {
                    count++;
                }
            }
            if (count == 1) {
                System.out.println("The request contains mutually exclusive properties: "
                        + playerProperties + "\n"
                        + "There are no numbers with these properties.");
                return false;
            }
        }
        if (new HashSet<>(playerProperties).containsAll(List.of(new String[]{"ODD", "EVEN"}))
                || new HashSet<>(playerProperties).containsAll(List.of(new String[]{"DUCK", "SPY"}))
                || new HashSet<>(playerProperties).containsAll(List.of(new String[]{"SUNNY", "SQUARE"}))
                || new HashSet<>(playerProperties).containsAll(List.of(new String[]{"SAD", "HAPPY"}))
                || new HashSet<>(playerProperties).containsAll(List.of(new String[]{"-ODD", "-EVEN"}))
                || new HashSet<>(playerProperties).containsAll(List.of(new String[]{"-DUCK", "-SPY"}))
                || new HashSet<>(playerProperties).containsAll(List.of(new String[]{"-SAD", "HAPPY"}))) {
            System.out.println("The request contains mutually exclusive properties: "
                    + playerProperties + "\n"
                    + "There are no numbers with these properties.");
            return false;
        }
        return true;
    }

    private boolean checkPropertiesInput(List<String> playerProperties) {
        if (playerProperties == null) {
            return false;
        }
        if (!new HashSet<>(Arrays.asList(PROPERTIES)).containsAll(playerProperties)) {
            System.out.println("The property " + (playerProperties) + " is wrong.\n"
                    + "Available properties:\n"
                    + "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
            return false;
        }
        return checkProperties(playerProperties);
    }

    private boolean checkNumberInput(String[] strings) {
        if (strings == null || strings.length > 7) {
            return false;
        }
        List<String> playerProperties = new ArrayList<>();

        long[] longs = new long[1];
        if (strings.length > 1) {
            longs = new long[2];
        }
        for (int i = 0; i < strings.length; i++) {
            if (i == 0 || i == 1) {
                if (strings[i].matches("^[0-9, -]+$")) {
                    try {
                        longs[i] = Long.parseLong(strings[i]);
                        if (i == 0 && longs[i] < 0) {
                            System.out.println("The first parameter should be a natural number or zero.");
                            return false;
                        }
                        if (i == 1 && longs[i] < 0) {
                            System.out.println("The second parameter should be a natural number or zero.");
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        return false;
                    }
                } else {
                    System.out.println("The number should be numeric");
                    return false;
                }
            } else {
                playerProperties.add(strings[i].toUpperCase());
            }
        }
        setPlayerProperties(playerProperties.stream().distinct().collect(Collectors.toList()));
        setLongs(longs);
        return true;
    }

    private void playerInput() {
        while (true) {
            System.out.println("Enter a request: ");
            String input = scanner.nextLine();
            String[] strings = input.split(" ");
            if (strings.length <= 2 && checkNumberInput(strings)) {
                break;
            }
            if (strings.length > 2 && checkNumberInput(strings) && checkPropertiesInput(playerProperties)) {
                break;
            }
        }
    }

    public void menu() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
        while (true) {
            playerInput();
            if (longs[0] == 0) {
                System.out.println("Goodbye!");
                break;
            }
            if (longs.length == 1) {
                new Check().printProperties(longs[0]);
            } else {
                new Check().printProperties(longs, playerProperties);
            }
        }
    }
}
