package moe.quill.stratumcommonutils.Plugin.Configuration;

public class StratumConfigBuilder {

    private boolean useDatabase = false;
    private boolean useKeyManager = false;
    private boolean useSerialization = false;

    public StratumConfigBuilder setUseDatabase(boolean useDatabase) {
        this.useDatabase = useDatabase;
        return this;
    }

    public StratumConfigBuilder setUseKeyManager(boolean useKeyManager) {
        this.useKeyManager = useKeyManager;
        return this;
    }

    public StratumConfigBuilder setUseSerialization(boolean useSerialization) {
        this.useSerialization = useSerialization;
        return this;
    }

    public StratumConfig build() {
        return new StratumConfig(useDatabase, useKeyManager, useSerialization);
    }
}
