package com.makara.pokemonsystem.WebScrape;

import com.makara.pokemonsystem.Pokemon.Model.Pokemon;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.util.*;


public class PokemonWS {
    public List<Pokemon> scrapePokemonData(int pagesToScrapeAtATime) {
        List<Pokemon> pokemonProducts = new ArrayList<>();
        Set<String> pagesDiscovered = new HashSet<>();
        List<String> pagesToScrape = new ArrayList<>();
        pagesToScrape.add("https://scrapeme.live/shop/page/1/");

        while (!pagesToScrape.isEmpty() && pagesToScrapeAtATime > 0) {
            String url = pagesToScrape.remove(0);
            pagesDiscovered.add(url);
            Document doc;
            try {
                doc = Jsoup
                        .connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                        .get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Elements products = doc.select("li.product");
            for (Element product : products) {
                Pokemon pokemonProduct = new Pokemon();
                pokemonProduct.setImageUrl(product.selectFirst("img").attr("src"));
                pokemonProduct.setName(product.selectFirst("h2").text());
                pokemonProduct.setPrice(parsePrice(product.selectFirst("span").text()));
                pokemonProducts.add(pokemonProduct);
            }

            Elements paginationElements = doc.select("a.page-numbers");
            for (Element pageElement : paginationElements) {
                String pageUrl = pageElement.attr("href");
                if (!pagesDiscovered.contains(pageUrl) && !pagesToScrape.contains(pageUrl)) {
                    pagesToScrape.add(pageUrl);
                }
                pagesDiscovered.add(pageUrl);
            }

            System.out.println(url + " -> page scraped");

            pagesToScrapeAtATime--;
        }

        return pokemonProducts;
    }

    private double parsePrice(String priceText) {
        String numericPart = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numericPart);
    }
}
