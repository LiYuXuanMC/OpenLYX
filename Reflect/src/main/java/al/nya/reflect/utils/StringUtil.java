package al.nya.reflect.utils;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumChatFormatting;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.Score;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.ScoreObjective;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.ScorePlayerTeam;
import al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard.Scoreboard;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class StringUtil {
    private static final Random random = new Random();

    public static List<Module> getToggledModules(List<Module> modules) {
        return modules.stream().filter(Module::isEnable).collect(Collectors.toList());
    }

    public static ArrayList<String> getScoreboardAsString() {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.getTheWorld();
        FontRenderer font = mc.getFontRenderer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Scoreboard scoreboard = world.getScoreboard();
        ScoreObjective scoreobjective = new ScoreObjective(null);
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(thePlayer.getName());

        ArrayList<String> scoreboardString = new ArrayList<>();

        if (!scoreplayerteam.isNull()) {
            int i1 = EnumChatFormatting.getColorIndex(scoreplayerteam.getChatFormat());

            if (i1 >= 0) {
                scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + i1);
            }
        }
        ScoreObjective scoreobjective1 = !scoreobjective.isNull() ? scoreobjective
                : scoreboard.getObjectiveInDisplaySlot(1);

        if (!scoreobjective1.isNull()) {
            Collection<Score> collection = scoreboard.getSortedScores(scoreobjective1);
            List<Score> arraylist = new ArrayList<Score>(collection);
            arraylist = arraylist.stream().filter(S -> S.getPlayerName() != null && !S.getPlayerName().startsWith("#")).collect(Collectors.toList());
            List<Score> arraylist1;
            if (arraylist.size() > 15) {
                arraylist1 = new ArrayList<>(arraylist);
                arraylist1 = arraylist1.stream().skip(arraylist.size() - 15).collect(Collectors.toList());
            } else {
                arraylist1 = arraylist;
            }

            for (int i = 0; i < arraylist1.size(); i++) {
                Score score1 = arraylist1.get(arraylist1.size() - i - 1);
                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
                scoreboardString.add(s1);
            }
        }
        return scoreboardString;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    public static String getRandomString(int len) {
        String t = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder n = new StringBuilder();
        for (int r = 0; len > r; ++r) {
            n.append(t.charAt(random.nextInt(t.length())));
        }
        return n.toString();
    }

    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isBoolean(String s) {
        try {
            return Boolean.parseBoolean(s);
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isLong(String s, int radix) {
        try {
            Long.parseLong(s, radix);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isNumber(String s) {
        return isInt(s) || isFloat(s) || isDouble(s) || isLong(s, 16);
    }

    public static float similarityLength(String first, String second) {
        return (float) Math.abs(first.length() - second.length()) / 100;
    }

    public static boolean findMatching(String first, String second) {
        return second.toLowerCase().contains(first.toLowerCase());
    }

    /**
     * Credits https://stackoverflow.com/questions/955110/similarity-string-comparison-in-java
     *
     * @param s1 the first string to compare with
     * @param s2 the second string to compare to
     * @returns the levenshtein distance between string s1 and string s2
     */
    public static double levenshteinDistance(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    /**
     * Integer to Roman Numeral
     * credits: Adilli Adil
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    /**
     * Insert a string inside another string at a given position. Does not check
     * for bounds and therefore may throw.
     *
     * @param original  The original string, where the insertion string will be put
     * @param insertion The string to insert
     * @param position  Where to insert the string at
     * @returns the final string
     */
    public static String insertAt(String original, String insertion, int position) {
        return original.substring(0, position) +
                insertion +
                original.substring(position);
    }

    /**
     * Insert a character inside another string at a given position. Does not
     * check for bounds and therefore may throw.
     *
     * @param original  The original string, where the insertion string will be put
     * @param insertion The character to insert
     * @param position  Where to insert the character at
     * @returns the final string
     */
    public static String insertAt(String original, char insertion, int position) {
        return original.substring(0, position) +
                insertion +
                original.substring(position);
    }

    /**
     * Delete a range of characters in a string. Does not check for bounds and
     * therefore may throw.
     *
     * @param s     The string to manipulate
     * @param start The start of the range
     * @param end   The end of the range (exclusive; character at this position not removed)
     * @returns the final string
     */
    public static String removeRange(String s, int start, int end) {
        return s.substring(0, start) +
                s.substring(end);
    }
}