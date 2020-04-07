package com.github.brelok;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ConnectorToWeb {

    final static String URL = "https://www.ceneo.pl/Odkurzacze_automatyczne;0020-30-0-0-8.htm";

    public static void main(String[] args) {

        try {
            Connection.Response connection = Jsoup.connect(URL)
                    .method(Connection.Method.GET)
                    .execute();

            Document document = connection.parse();

            Elements elements = document.select(".page-body > #body > .wrapper.cat-layout > .cat-layout__products >" +
                    ".page-tab-content.products > .category-list > .category-list-body.js_category-list-body.js_search-results" +
                    "> .cat-prod-row.js_category-list-item.js_clickHashData.js_man-track-event");

            List<String> list = new ArrayList<>();
            List<Element> collect = elements.stream()
                    .map(element -> element.select(".cat-prod-row__body > .cat-prod-row__content > .cat-prod-row__desc"))
                    .map(element -> element.get(0))
                    .map(element -> element.child(0).child(0).child(0).child(0))
                    .collect(toList());

            collect.stream()
                    .forEach(element -> list.add(element.text()));


            System.out.println(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
