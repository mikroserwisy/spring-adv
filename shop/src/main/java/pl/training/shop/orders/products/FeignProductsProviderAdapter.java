package pl.training.shop.orders.products;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.commons.streotype.Adapter;
import pl.training.shop.orders.Product;
import pl.training.shop.orders.ProductsProvider;
import pl.training.shop.orders.ServiceUnavailableException;

import javax.annotation.PostConstruct;

import static lombok.AccessLevel.PACKAGE;

@Primary
@Adapter
@Log
@RequiredArgsConstructor(access = PACKAGE)
class FeignProductsProviderAdapter implements ProductsProvider {

    private final ProductsRestMapper mapper;
    private final ProductsApi productsApi;
    @Value("${api.products}")
    @Setter
    private String productsEndpoint;

    @PostConstruct
    void init() {
        log.info("Available products: " + getProducts(new Page(0,10)));
    }

    @Override
    public ResultPage<Product> getProducts(Page page) {
        var url = productsEndpoint + "?_start={0}&_limit={1}";
        try {
            var productsDto = productsApi.getProducts(page.getIndex() * page.getSize(), page.getSize());
            var totalEntries = productsApi.getProductsCount();
            var resultPage = new ResultPage<>(productsDto, page, totalEntries);
            return mapper.toModel(resultPage);
        } catch (FeignClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

    @Override
    public Product getProduct(Long id) {
        try {
            var productDto = productsApi.getById(id);
            return mapper.toModel(productDto);
        } catch (FeignClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

}
