package com.zougn.markbook.domain;

import lombok.Data;

@Data
public class BookmarkRequest {
    private String url;
    private String title;
    private String folder;
}

