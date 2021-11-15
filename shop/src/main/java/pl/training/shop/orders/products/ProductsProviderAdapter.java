package pl.training.shop.orders.products;

import pl.training.shop.commons.LocalMoney;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.commons.streotype.Adapter;
import pl.training.shop.orders.Product;
import pl.training.shop.orders.ProductsProvider;

import java.util.Collections;

@Adapter
class ProductsProviderAdapter implements ProductsProvider {

    @Override
    public ResultPage<Product> getProducts(Page page) {
        return new ResultPage<>(Collections.emptyList(), page, 0);
    }

    @Override
    public Product getProduct(Long id) {
        var product = new Product();
        product.setName("Fake");
        product.setPrice(LocalMoney.of(10));
        return product;
    }

}
