package pl.training.shop.orders.products;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.commons.streotype.Adapter;
import pl.training.shop.orders.Product;
import pl.training.shop.orders.ProductsProvider;
import pl.training.shop.orders.ServiceUnavailableException;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;

@Primary
@Adapter
@RequiredArgsConstructor(access = PACKAGE)
class RestTemplateProductsProviderAdapter implements ProductsProvider {

    private final ProductsRestMapper mapper;
    private final RestTemplate restTemplate;
    @Value("${api.products}")
    @Setter
    private String productsEndpoint;

    @Override
    public ResultPage<Product> getProducts(Page page) {
        var url = productsEndpoint + "?_start={0}&_limit={1}";
        try {
            var productsDto = restTemplate.getForObject(url, ProductDto[].class, page.getIndex() * page.getSize(), page.getSize());
            var totalEntries = restTemplate.getForObject(productsEndpoint + "/count", Integer.class);
            var resultPage = new ResultPage<>(List.of(productsDto), page, totalEntries);
            return mapper.toModel(resultPage);
        } catch (RestClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

    @Override
    public Product getProduct(Long id) {
        try {
            var productDto = restTemplate.getForObject(productsEndpoint + "/" + id, ProductDto.class);
            return mapper.toModel(productDto);
        } catch (RestClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

}
