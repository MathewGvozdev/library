package com.mathewgv.library.dao.filter;

import static java.util.stream.Collectors.joining;

public class BookFilter extends SelectFilter {

    private static final String TITLE = "upper(bm.title) LIKE upper(?)";
    private static final String AUTHOR = "upper(author_req.author) LIKE upper(?)";
    private static final String GENRE = "upper(string_agg(g.title, ', ')) LIKE upper(?)";
    private static final String SERIES = "upper(bm.series) LIKE upper(?)";

    private String title;
    private String author;
    private String genre;
    private String series;


    public BookFilter(Integer page,
                      Integer limit,
                      String title,
                      String author,
                      String genre,
                      String series) {
        super(page, limit);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.series = series;
    }

    public static BookFilterBuilder builder() {
        return new BookFilterBuilder();
    }

    @Override
    public String getSqlRequest(String selectSql, SortType type) {
        initConditions();
        String sqlRequest;
        if (getConditions().size() == 0) {
            sqlRequest = selectSql + LIMIT_AND_OFFSET_FOR_QUERY;
        } else {
            String havingSqlCondition = getConditions().stream()
                    .collect(joining(AND_FOR_QUERY, HAVING_FOR_QUERY, LIMIT_AND_OFFSET_FOR_QUERY));
            sqlRequest = selectSql + havingSqlCondition;
        }
        return sqlRequest;
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
        private Integer limit;
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

        public BookFilterBuilder limit(Integer limit) {
            this.limit = limit;
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
            return new BookFilter(page, limit, title, author, genre, series);
        }
    }
}
