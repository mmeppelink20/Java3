package meppelink.fun_stuff;

public class Country implements Comparable<Country>, Cloneable{
    private String name;
    private String continent;
    private int population;
    private String abreviation;
    private String latitude;
    private String longitude;
    private int area;

    public Country() {
        this("Unknown", "Unknown", 0, "NA", "0", "0", 0);
    }

    public Country(String name, String continent, int population, String abreviation, String latitude, String longitude, int area) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.abreviation = abreviation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getAbreviation() {
        return abreviation;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getArea() {
        return area;
    }
}