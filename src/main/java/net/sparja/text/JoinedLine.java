package net.sparja.text;

public class JoinedLine<KL, V, KR> {
    private KL leftKey;
    private V value;
    private KR rightKey;

    public JoinedLine(KL leftKey, V value, KR rightKey) {
        this.leftKey = leftKey;
        this.value = value;
        this.rightKey = rightKey;
    }

    public KL getLeftKey() {
        return leftKey;
    }

    public V getValue() {
        return value;
    }

    public KR getRightKey() {
        return rightKey;
    }
}
