package com.github.brelok;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Connector {

    public static Map<String, String> getMap(String url) {

        Map<String, String> maps = new LinkedHashMap<>();
        try {
            Connection.Response connection = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            Document document = connection.parse();

            Elements elements = document.select(".page-body > #body > .no-banner > .product > .js_product-body.js_available-coupons" +
                    "> .screening-wrapper > .page-tab-content.click.no-padding.wrapper" +
                    "> .site-full-width.wrapper > .product-offers-group > .product-offers.js_product-offers " +
                    "> tbody > .product-offer.clickable-offer.js_offer-container-click.js_product-offer > .cell-store-logo > a > img");

            List<String> names = elements.stream()
                    .map(element -> element.attr("alt"))
                    .collect(Collectors.toList());

            List<String> value = elements.stream()
                    .map(element -> element.parent().parent().parent())
                    .map(element -> element.select(".cell-price > a > span > .price > .value"))
                    .map(Elements::text)
                    .collect(Collectors.toList());

            List<String> penny = elements.stream()
                    .map(element -> element.parent().parent().parent())
                    .map(element -> element.select(".cell-price > a > span > .price > .penny"))
                    .map(Elements::text)
                    .collect(Collectors.toList());

            List<String> prices = new ArrayList<>();
            for (int i = 0; i < value.size() ; i++) {
                prices.add(value.get(i) + penny.get(i));
            }

            for (int i = 0; i < names.size(); i++) {
                maps.put(names.get(i), prices.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
}


