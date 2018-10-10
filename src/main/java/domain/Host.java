package domain;

public class Host implements Cloneable{

    String petName;

    String name;

    public static int num =0;

    public Host() {
        System.out.println("调用了构造器"+(++num));
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Host clone(){
        try {
            return (Host)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return name +"'s pet :"+petName;
    }
}
