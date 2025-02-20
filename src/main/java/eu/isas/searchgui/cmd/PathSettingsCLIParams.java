package eu.isas.searchgui.cmd;

import com.compomics.software.settings.UtilitiesPathParameters;
import eu.isas.searchgui.parameters.SearchGUIPathParameters;
import java.util.ArrayList;
import org.apache.commons.cli.Options;

/**
 * Parameters for the path settings command line.
 *
 * @author Marc Vaudel
 * @author Harald Barsnes
 */
public enum PathSettingsCLIParams {

    ALL("temp_folder", "A folder for temporary file storage. Use only if you encounter problems with the default configuration."),
    LOG_FOLDER("log", "Folder where the log files are written."),
    USE_LOG_FOLDER("use_log_folder", "Use the log folder. 0: write to standard output, 1: use the log folder. Default: 1.");

    /**
     * The id of the command line option.
     */
    public String id;
    /**
     * The description of the command line option.
     */
    public String description;

    /**
     * Constructor.
     *
     * @param id the id of the command line option
     * @param description the description of the command line option
     */
    private PathSettingsCLIParams(String id, String description) {

        this.id = id;
        this.description = description;

    }

    /**
     * Creates the options for the command line interface based on the possible
     * values.
     *
     * @param aOptions the options object where the options will be added
     */
    public static void createOptionsCLI(Options aOptions) {

        for (PathSettingsCLIParams pathSettingsCLIParam : values()) {
            aOptions.addOption(pathSettingsCLIParam.id, true, pathSettingsCLIParam.description);
        }

        for (SearchGUIPathParameters.SearchGUIPathKey searchGUIPathKey : SearchGUIPathParameters.SearchGUIPathKey.values()) {
            aOptions.addOption(searchGUIPathKey.getId(), true, searchGUIPathKey.getDescription());
        }

        for (UtilitiesPathParameters.UtilitiesPathKey utilitiesPathKey : UtilitiesPathParameters.UtilitiesPathKey.values()) {
            aOptions.addOption(utilitiesPathKey.getId(), true, utilitiesPathKey.getDescription());
        }

    }

    /**
     * Returns the list of supported command line options.
     *
     * @return the list of supported command line options
     */
    public static ArrayList<String> getOptionIDs() {

        ArrayList<String> options = new ArrayList<>();

        for (PathSettingsCLIParams pathSettingsCLIParam : values()) {
            options.add("-" + pathSettingsCLIParam.id);
        }

        for (SearchGUIPathParameters.SearchGUIPathKey searchGUIPathKey : SearchGUIPathParameters.SearchGUIPathKey.values()) {
            options.add("-" + searchGUIPathKey.getId());
        }

        for (UtilitiesPathParameters.UtilitiesPathKey utilitiesPathKey : UtilitiesPathParameters.UtilitiesPathKey.values()) {
            options.add("-" + utilitiesPathKey.getId());
        }

        return options;

    }

    /**
     * Returns the options as a string.
     *
     * @return the options as a string
     */
    public static String getOptionsAsString() {

        String output = "";
        String formatter = "%-35s";

        output += "Log Folder:\n\n";
        output += "-" + String.format(formatter, LOG_FOLDER.id) + " " + LOG_FOLDER.description + "\n";
        output += "-" + String.format(formatter, USE_LOG_FOLDER.id) + " " + USE_LOG_FOLDER.description + "\n";

        output += "Generic Temporary Folder:\n\n";
        output += "-" + String.format(formatter, ALL.id) + " " + ALL.description + "\n";

        output += "\n\nSpecific Path Setting:\n\n";

        for (SearchGUIPathParameters.SearchGUIPathKey searchGUIPathKey : SearchGUIPathParameters.SearchGUIPathKey.values()) {
            output += "-" + String.format(formatter, searchGUIPathKey.getId()) + " " + searchGUIPathKey.getDescription() + System.getProperty("line.separator");
        }

        for (UtilitiesPathParameters.UtilitiesPathKey utilitiesPathKey : UtilitiesPathParameters.UtilitiesPathKey.values()) {
            output += "-" + String.format(formatter, utilitiesPathKey.getId()) + " " + utilitiesPathKey.getDescription() + System.getProperty("line.separator");
        }

        return output;

    }
}
