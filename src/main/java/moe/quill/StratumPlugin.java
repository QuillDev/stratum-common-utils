package moe.quill;

import moe.quill.StratumCommonApi.Commands.StratumCommand;
import moe.quill.StratumCommonApi.Database.IDatabaseService;
import moe.quill.StratumCommonApi.KeyManager.IKeyManager;
import moe.quill.StratumCommonApi.Plugin.StratumConfig;
import moe.quill.StratumCommonApi.Serialization.ISerializer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StratumPlugin extends JavaPlugin {

    private final StratumConfig stratumConfig;

    protected PluginManager pluginManager;
    protected final Logger logger = LoggerFactory.getLogger(getName());

    private IDatabaseService databaseService;
    private IKeyManager keyManager;
    private ISerializer serializer;

    public StratumPlugin(StratumConfig stratumConfig) {
        this.stratumConfig = stratumConfig;
        this.pluginManager = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {

        //Try to get any services we may need
        final var servicesManager = getServer().getServicesManager();


        //Get the database if we require it
        if (stratumConfig.isUseDatabase()) {
            final var dbRegistration = servicesManager.getRegistration(IDatabaseService.class);
            if (dbRegistration == null) {
                logger.error(String.format("Unable to find the database manager! [%s]", IDatabaseService.class.hashCode()));
                pluginManager.disablePlugin(this, true);
                return;
            }
            databaseService = dbRegistration.getProvider();
        }

        //get the key manager if we require it
        if (stratumConfig.isUseKeyManager()) {
            final var keyRegistration = servicesManager.getRegistration(IKeyManager.class);
            if (keyRegistration == null) {
                logger.error(String.format("Unable to find the key manager! [%s]", IKeyManager.class.hashCode()));
                pluginManager.disablePlugin(this, true);
                return;
            }
            keyManager = keyRegistration.getProvider();
        }

        //get the key manager if we require it
        if (stratumConfig.isUseSerialization()) {
            final var serializerRegistration = servicesManager.getRegistration(ISerializer.class);
            if (serializerRegistration == null) {
                logger.error(String.format("Unable to find the key serializer! [%s]", ISerializer.class.hashCode()));
                pluginManager.disablePlugin(this, true);
                return;
            }
            serializer = serializerRegistration.getProvider();
        }

    }

    /**
     * Register any given commands to the server
     *
     * @param commands to register with the server
     */
    public void registerCommand(StratumCommand... commands) {
        for (final var command : commands) {
            final var pluginCommand = getCommand(command.getName());

            //If the command does not exist log it
            if (pluginCommand == null) {
                logger.warn(String.format("Could not find a command with the name '%s'", command.getName()));
                continue;
            }

            //Set the executor of the command
            pluginCommand.setExecutor(command.getExecutor());
            if (command.getTabCompleter() == null) continue; // if this command has no tab completer, return null
            pluginCommand.setTabCompleter(command.getTabCompleter());
        }
    }

    /**
     * Register any given event
     *
     * @param events to register with the server
     */
    public void registerEvent(Listener... events) {
        for (final var event : events) {
            pluginManager.registerEvents(event, this);
        }
    }

    /**
     * Get the KeyManger service found in all stratum plugins
     *
     * @return the key manager service
     */
    public IKeyManager getKeyManager() {
        if (!stratumConfig.isUseKeyManager()) {
            logger.error("KeyManger service is not enabled! Please enable it in the constructor config!");
            return null;
        }
        return keyManager;
    }

    /**
     * Get the database service found in all stratum plugins
     *
     * @return the database service
     */
    public IDatabaseService getDatabaseService() {
        if (!stratumConfig.isUseDatabase()) {
            logger.error("Database service is not enabled! Please enable it in the constructor config!");
            return null;
        }
        return databaseService;
    }

    /**
     * Get the database service found in all stratum plugins
     *
     * @return the database service
     */
    public ISerializer getSerializer() {
        if (!stratumConfig.isUseSerialization()) {
            logger.error("Serializer service is not enabled! Please enable it in the constructor config!");
            return null;
        }
        return serializer;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
