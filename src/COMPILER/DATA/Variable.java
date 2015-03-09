package COMPILER.DATA;

/**
 * Created by alvinjay on 3/7/15.
 */
public class Variable {
    private String name, type, value;

    public Variable(String name, String value, String type) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public boolean isCompatibleWith(Variable a) {
        if (type.equals(a.type))
            return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
