package com.likelion.springhw.guestbook.dto;

public class GuestbookSummaryResponse {

    private final String title;
    private final String writer;
    private final String ps;

    public GuestbookSummaryResponse(String title, String writer, String ps) {
        this.title = title;
        this.writer = writer;
        this.ps = ps;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getPs() {
        return ps;
    }
}
