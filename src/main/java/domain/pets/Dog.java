package domain.pets;

import converter.annotation.ReflectField;

public class Dog extends Pet {

    @ReflectField("name")
    String hostName = "withing";

    @ReflectField("petName")
    String name = "jason";

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
