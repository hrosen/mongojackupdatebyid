package no.nils.mongoupdate;

public class Scenario {
    public String _id;
    public String name;

    public Scenario(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
}
