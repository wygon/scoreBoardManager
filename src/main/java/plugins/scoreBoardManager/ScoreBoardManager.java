package plugins.scoreBoardManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class ScoreBoardManager extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Rejestracja zdarzeń
        getServer().getPluginManager().registerEvents(this, this);
        // Zaplanuj aktualizację tablisty co 2 sekundy
        new BukkitRunnable() {
            @Override
            public void run() {
                updateTabList();
            }
        }.runTaskTimer(this, 0L, 40L);  // co 40 ticków (2 sekundy)
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Aktualizuj tablistę po wejściu gracza
        updatePlayerTab(player);
    }

    // Aktualizacja tablisty dla wszystkich graczy
    public void updateTabList() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updatePlayerTab(player);
        }
    }
    // Aktualizacja tablisty dla konkretnego gracza
    public void updatePlayerTab(Player player) {
        String playerName = player.getName();
        String health = String.valueOf((int) player.getHealth());  // Zmieniamy na całkowitą liczbę zdrowia
        String ping = String.valueOf(player.getPing());  // Ping gracza
        String deaths = String.valueOf(player.getStatistic(org.bukkit.Statistic.DEATHS)); // Smierci gracza
        int longestNickname = getLongestNickname();
        // Wyświetlanie danych w tabie
        String tabHeader = "§2WITAJ NA QUENDI SMP";  // Nagłówek tablisty (możesz ustawić dowolny tekst)
        String tabFooter = "§2DZIEKI ZA GRE";  // Stopka tablisty
        //Centrowanie informacji
//        playerName = centerText(playerName, longestNickname);
        health = centerText(health, 4);
        ping = centerText(ping, 4);
        deaths = centerText(deaths, 4);
        //playerName = String.format("%-20s", playerName);
        int lengthDifference = getMinecraftTextWidth(playerName) - longestNickname + 1;
        playerName = playerName + " ".repeat(Math.max(0, lengthDifference));
        String tabLine = "| §f" + health + "§4❤" + "§f | " + deaths + "💀 " + "§f| " + ping + "ms";
    //        tabLine = "§f" + playerName +  String.format("%50s",tabLine);
        tabLine = String.format("%-" + lengthDifference + "s"," §f" + playerName) +  String.format("%" + tabLine.length() + "s",tabLine);
        // Zaktualizowanie tablisty
        player.setPlayerListHeader(tabHeader);
        player.setPlayerListFooter(tabFooter);
        player.setPlayerListName(tabLine);  // Ustawienie nicku gracza na tabie
    }
    public int getMinecraftTextWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            width += getCharWidth(c);
        }
        return width;
    }
    public String trimMessage(String message, int maxLength) {
        if (message.length() <= maxLength) return message;

        int lastSpace = message.lastIndexOf(" ", maxLength);
        return (lastSpace != -1) ? message.substring(0, lastSpace) + "..." : message.substring(0, maxLength) + "...";
    }
    public int getCharWidth(char c) {
        switch (c) {
            case ':': return 4;
            case '!': return 2;
            case '"': return 5;
            case '#': return 6;
            case '$': return 6;
            case '%': return 6;
            case '&': return 6;
            case '(': return 5;
            case ')': return 5;
            case '*': return 5;
            case '+': return 6;
            case ',': return 2;
            case '-': return 6;
            case '.': return 2;
            case '/': return 6;
            case '0': return 6;
            case '1': return 6;
            case '2': return 6;
            case '3': return 6;
            case '4': return 6;
            case '5': return 6;
            case '6': return 6;
            case '7': return 6;
            case '8': return 6;
            case '9': return 6;
            case ';': return 2;
            case '<': return 5;
            case '=': return 6;
            case '>': return 5;
            case '?': return 6;
            case '@': return 7;
            case 'A': return 6;
            case 'B': return 6;
            case 'C': return 6;
            case 'D': return 6;
            case 'E': return 6;
            case 'F': return 6;
            case 'G': return 6;
            case 'H': return 6;
            case 'I': return 4;
            case 'J': return 6;
            case 'K': return 6;
            case 'L': return 6;
            case 'M': return 6;
            case 'N': return 6;
            case 'O': return 6;
            case 'P': return 6;
            case 'Q': return 6;
            case 'R': return 6;
            case 'S': return 6;
            case 'T': return 6;
            case 'U': return 6;
            case 'V': return 6;
            case 'W': return 6;
            case 'X': return 6;
            case 'Y': return 6;
            case 'Z': return 6;
            case '[': return 4;
            case ']': return 4;
            case '^': return 6;
            case '_': return 6;
            case '`': return 0;
            case 'a': return 6;
            case 'b': return 6;
            case 'c': return 6;
            case 'd': return 6;
            case 'e': return 6;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 6;
            case 'i': return 2;
            case 'j': return 6;
            case 'k': return 5;
            case 'l': return 3;
            case 'm': return 6;
            case 'n': return 6;
            case 'o': return 6;
            case 'p': return 6;
            case 'q': return 6;
            case 'r': return 6;
            case 's': return 6;
            case 't': return 4;
            case 'u': return 6;
            case 'v': return 6;
            case 'w': return 6;
            case 'x': return 6;
            case 'y': return 6;
            case 'z': return 6;
            case '{': return 5;
            case '|': return 2;
            case '}': return 5;
            case '~': return 7;
            default: return 6; // Średnia dla innych znaków
        }
    }
    public String centerText(String text, int width) {
        int minecraftWidth = getMinecraftTextWidth(text);
        int padding = (width - minecraftWidth) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, padding));
    }
    public int getLongestNickname() {
        int longestLength = 0;
        //String longestNickname = "";
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(getMinecraftTextWidth(player.getName()) > longestLength) {
                longestLength = getMinecraftTextWidth(player.getName());
                //longestNickname = player.getName();
            }
        }
        return longestLength;
    }
//    public String centerText(String text, int width) {
//        int padding = (width - text.length()) / 2; // Oblicz ilość spacji
//
//        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, padding));
//    }
}