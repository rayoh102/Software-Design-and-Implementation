package marvel;

import com.opencsv.bean.CsvBindByName;

public class MarvelModel {
    @CsvBindByName
    private String hero;

    @CsvBindByName
    private String book;

    public String getHero() {
        return this.hero;
    }

    public String getBook() {
        return this.book;
    }
}
