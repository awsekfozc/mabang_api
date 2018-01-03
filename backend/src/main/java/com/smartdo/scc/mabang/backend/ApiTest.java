package  com.smartdo.scc.mabang.backend;

import com.smartdo.scc.mabang.backend.structs.AmazonLocale;
import com.smartdo.scc.mabang.backend.structs.Request;

public class ApiTest {

    public static void main(String[] args) {
        //KeepaAPI api = new KeepaAPI("agn2s1tgfqoagr9f8jbrp40taggbo1h49nrqii0ehng5ucv6du1jhikaidv8frum");
        MabangAPI api22 = new MabangAPI("agn2s1tgfqoagr9f8jbrp40taggbo1h49nrqii0ehng5ucv6du1jhikaidv8frum");
        Request r = Request.getProductRequest(AmazonLocale.US, 10000, null, "B00012Y38C");
        api22.sendRequest(r)
                .done(result -> {
                    switch (result.status) {
                        case OK:
                            // iterate over received product information
                            for (Product product : result.products) {
                                System.out.println(ProductAnalyzer.getLast(product.csv[Product.CsvType.SALES.index], Product.CsvType.SALES));
                                if (product.productType == Product.ProductType.STANDARD.code || product.productType == Product.ProductType.DOWNLOADABLE.code) {

                                    //get basic data of product and print to stdout
                                    int currentAmazonPrice = ProductAnalyzer.getLast(product.csv[Product.CsvType.AMAZON.index], Product.CsvType.AMAZON);

                                    //check if the product is in stock -1 -> out of stock
                                    if (currentAmazonPrice == -1) {
                                        System.out.println(product.asin + " " + product.title + " is currently out of stock!");
                                    } else {
                                        System.out.println(product.asin + " " + product.title + " Current Amazon Price: " + currentAmazonPrice);
                                    }
                                    // get weighted mean of the last 90 days for Amazon
                                    int weightedMean90days = ProductAnalyzer.calcWeightedMean(product.csv[Product.CsvType.AMAZON.index], KeepaTime.nowMinutes(), 90, Product.CsvType.AMAZON);
                                    System.out.println(weightedMean90days);
                                } else {
                                }
                            }
                            break;
                        default:
                            System.out.println(result);
                    }
                })
                .fail(failure -> System.out.println(failure));
    }
}