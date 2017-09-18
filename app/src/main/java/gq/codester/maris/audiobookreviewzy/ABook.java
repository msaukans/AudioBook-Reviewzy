package gq.codester.maris.audiobookreviewzy;



public class ABook {

    private String name;
    private String authName;
    private String desc;
    private String date_of_release;
    private String genre;

    //also might need: String file, link_to_book;

    public ABook(){

    }

    public ABook(String name, String authName, String desc, String date_of_release, String genre) {
        this.name = name;
        this.date_of_release = date_of_release;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate_of_release() {
        return date_of_release;
    }

    public void setDate_of_release(String date_of_release) {
        this.date_of_release = date_of_release;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ABook{" + "name='" + name + '\'' +", authName='" + authName + '\'' +
                ", desc='" + desc + '\'' + ", date_of_release='" + date_of_release + '\'' +
                ", genre='" + genre + '\'' + '}';
    }


}
