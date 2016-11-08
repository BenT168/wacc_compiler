package frontEnd.tree;

import frontEnd.tree.Type.Type;

public class Array extends Type {
    private Type elementType;
    private int elements;

    public Array(String type) {
        super(type);
    }

    public Type getElementType() {
        return elementType;
    }

    public void setElementType(Type elementType) {
        this.elementType = elementType;
    }

    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }
}
