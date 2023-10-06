package com.trocolis.api.promator.model.repository.helper;

import com.trocolis.api.promator.model.dto.product.ProductDTO;
import com.trocolis.api.promator.model.dto.product.request.SearchProductRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Esta classe implementa as regras de consultas de produtos
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */
@Component
public class ProductDao {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Este método trata a requisição de busca de produtos
     *
     * @param request
     * @return Lista de produtos DTO
     */
    public List<ProductDTO> searchProducts(SearchProductRequest request) {

        var sql = new StringBuilder("SELECT new com.trocolis.api.promator.model.dto.product.ProductDTO(p.id, p.name, p.description, p.price, p.status, p.image, p.creationDateTime) FROM Product p ");
        sql.append("WHERE (p.name LIKE '%'||:keyword||'%' OR p.description LIKE '%'||:keyword||'%')");

        if (request.maxPrice() != null) {
            sql.append(" AND p.price <= :maxPrice");
        }

        if (request.minPrice() != null) {
            sql.append(" AND p.price >= :minPrice");
        }

        TypedQuery<ProductDTO> query = entityManager.createQuery(sql.toString(), ProductDTO.class);

        query.setParameter("keyword", request.keyword().trim());

        if (request.maxPrice() != null) {
            query.setParameter("maxPrice", request.maxPrice());
        }

        if (request.minPrice() != null) {
            query.setParameter("minPrice", request.minPrice());
        }

        sql.append(" ORDER BY p.creationDateTime DESC");

        return query.getResultList();
    }
}
