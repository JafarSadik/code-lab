package learning.webapps;


public class Shop {
    public Shop(String name, String address, String city, String url) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.url = url;
    }

    private String name;

    private String address;

    private String city;

    private String url;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
