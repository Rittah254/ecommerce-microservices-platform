package com.rittah.product.service;
import com.rittah.product.dto.ProductRequest;
import com.rittah.product.entity.ProductStatus;
import com.rittah.product.repository.ProductRepository;
import com.rittah.product.exception.DuplicateProductException;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock ProductRepository repository;
    @InjectMocks ProductService service;

    @BeforeEach void setup(){MockitoAnnotations.openMocks(this);}

    @Test void duplicateCodeRejected(){
        when(repository.existsByProductCode("SAM-S25")).thenReturn(true);
        ProductRequest r=new ProductRequest("Samsung","SAM-S25","phone","Mobile","Samsung",
            new BigDecimal("799.99"),BigDecimal.TEN,new BigDecimal("18"),ProductStatus.ACTIVE);
        assertThrows(DuplicateProductException.class,()->service.create(r));
    }
}
