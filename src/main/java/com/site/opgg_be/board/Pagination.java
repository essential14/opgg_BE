package com.site.opgg_be.board;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Pagination {

    private static final int DISPLAY_PAGES = 3;
    private int current_page;
    private int start_page;
    private int end_page;
    private int total_pages;
    private int page_size = 5; // 기본 페이지 크기
    private String sort_by;
    private String order;
    private int start_row;
    private int end_row;

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
        calculateRows();
        calculatePages();
    }

    public void setSort_by(String sort_by) {
        if (sort_by == null || sort_by.trim().isEmpty()) {
            this.sort_by = "bno"; // 기본값 설정
        } else {
            this.sort_by = sort_by;
        }
    }

    public void setOrder(String order) {
        if (order == null || (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc"))) {
            this.order = "asc"; // 기본값 설정
        } else {
            this.order = order.toLowerCase();
        }
    }

    private void calculateRows() {
        this.start_row = (this.current_page - 1) * this.page_size + 1;
        this.end_row = this.current_page * this.page_size;
    }

    private void calculatePages() {
        this.start_page = ((current_page - 1) / DISPLAY_PAGES) * DISPLAY_PAGES + 1;
        this.end_page = Math.min(start_page + DISPLAY_PAGES - 1, total_pages);
    }

    public void setTotalPages(int totalCount) {
        this.total_pages = (int) Math.ceil((double) totalCount / this.page_size);
    }
}