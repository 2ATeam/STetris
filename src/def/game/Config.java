package def.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    private static Path configPath = Paths.get("config.cfg");

    // modifiable params with default values:
    public static long spawnFrequency = 500L;
    public static long levelScoreLimit = 500L;
    public static long lineCost = 100L;
    public static int mapWidth = 10;
    public static int mapHeight = 20;
    public static String tilesetPath = "tilesets/tileset.png";


    // available param indicators:
    private static final String PARAM_SF = "spawn_frequency";
    private static final String PARAM_MW = "map_width";
    private static final String PARAM_MH = "map_height";
    private static final String PARAM_LC = "line_cost";
    private static final String PARAM_TP = "tileset_path";
    private static final String PARAM_LS = "level_score_limit";

    public static void load() {
        String line;
        String[] param = new String[0];
        boolean success = true;
        try(BufferedReader reader = Files.newBufferedReader(configPath, StandardCharsets.UTF_8)) {
            while ((line = reader.readLine()) != null) {
                param = line.split(":");
                if (param.length == 2) {
                    switch (param[0]) {
                        case PARAM_SF:
                            spawnFrequency = Long.valueOf(param[1]);
                            break;
                        case PARAM_MW:
                            mapWidth = Integer.valueOf(param[1]);
                            break;
                        case PARAM_MH:
                            mapHeight = Integer.valueOf(param[1]);
                            break;
                        case PARAM_TP:
                            tilesetPath = param[1];
                            break;
                        case PARAM_LC:
                            lineCost = Long.valueOf(param[1]);
                            break;
                        case PARAM_LS:
                            levelScoreLimit = Long.valueOf(param[1]);
                            break;
                        default:
                            System.err.println("Unavailable param name: " + param[0]);
                            success = false;
                            break;
                    }
                } else {
                    System.err.println("Error reading param");
                    success = false;
                }
            }
        } catch (IOException | NumberFormatException ex){
            if (ex instanceof IOException) {
                System.err.println("Error loading configuration file.");
            } else {
                System.err.println("Invalid param value: " + param[1] + "for param: " + param[0]);
            }
            success = false;
        }
        if (success) {
            System.out.println("Configuration successfully loaded.");
        } else {
            System.err.println("Configuration loading finished with errors.");
        }
    }
}