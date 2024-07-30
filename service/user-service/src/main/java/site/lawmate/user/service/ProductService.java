package site.lawmate.user.service;

import site.lawmate.user.domain.model.Product;
import site.lawmate.user.domain.dto.ProductDto;

public interface ProductService extends CommandService<ProductDto>, QueryService<ProductDto> {

    default Product dtoToEntity(ProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .itemName(dto.getItemName())
                .price(dto.getPrice())
                .image(dto.getImage())
                .build();
    }

    default ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .itemName(product.getItemName())
                .price(product.getPrice())
                .image(product.getImage())
                .build();
    }
}
