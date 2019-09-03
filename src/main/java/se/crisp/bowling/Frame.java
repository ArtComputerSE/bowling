package se.crisp.bowling;

public class Frame {

    Frame(int value, Type type) {
        setValue(value);
        setType(type);
    }


    private int value_;
    private Type type_;

    int getValue() {
        return value_;
    }

    void setValue(int value) {
        this.value_ = value;
    }

    public Type getType() {
        return type_;
    }

    void setType(Type type) {
        this.type_ = type;
    }

    enum Type {
        STRIKE,
        SPARE,
        OPEN
    }
}
