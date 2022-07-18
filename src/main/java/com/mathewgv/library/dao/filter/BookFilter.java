package com.mathewgv.library.dao.filter;

import static java.util.stream.Collectors.joining;

public class BookFilter extends SelectFilter {

    private String title;
    private String author;
    private String genre;
    private String series;

    private static final String TITLE = "bm.title LIKE ?";
    private static final String AUTHOR = "author_req.author LIKE ?";
    private static final String GENRE = "string_agg(g.title, ', ') LIKE ?";
    private static final String SERIES = "bm.series LIKE ?";

    public BookFilter(Integer page,
                      String title,
                      String author,
                      String genre,
                      String series) {
        super(page);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.series = series;
    }

    public static BookFilterBuilder builder() {
        return new BookFilterBuilder();
    }

    @Override
    public String getSqlRequest(String selectSql) {
        initConditions();
        String havingSqlCondition = getConditions().stream()
                .collect(joining(" AND ", "HAVING ", " LIMIT ? OFFSET ?"));
        return selectSql + havingSqlCondition;
    }

    @Override
    protected void initConditions() {
        if (title != null) {
            addCondition(TITLE);
            addParameter("%" + title + "%");
        }
        if (author != null) {
            addCondition(AUTHOR);
            addParameter("%" + author + "%");
        }
        if (genre != null) {
            addCondition(GENRE);
            addParameter("%" + genre + "%");
        }
        if (series != null) {
            addCondition(SERIES);
            addParameter("%" + series + "%");
        }
        addParameter(getLimit());
        addParameter(getOffset());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public static class BookFilterBuilder {
        private Integer page;
        private String title;
        private String author;
        private String genre;
        private String series;

        BookFilterBuilder() {
        }

        public BookFilterBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public BookFilterBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookFilterBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookFilterBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public BookFilterBuilder series(String series) {
            this.series = series;
            return this;
        }


        public BookFilter build() {
            return new BookFilter(page, title, author, genre, series);
        }
    }
}
