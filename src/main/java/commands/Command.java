package commands;

import java.io.Serializable;
import java.util.Map;

public abstract class Command implements Serializable {

    private String name;
    private Map<String, Object> flags;

    public abstract void execute();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, Object> flags) {
        this.flags = flags;
    }
}
