package poly.shopme.site.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import poly.shopme.common.entity.Product;
import poly.shopme.site.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTests {
	
	@Autowired
	ProductRepository repo;
	
	@Test
	public void testFindByAlias() {
		String alias = "laptop-acer-aspire-3-a315-23-r8ba-r3-3250u/4gb/256gb/win-10";
		Product product = repo.findByAlias(alias);
		
		assertThat(product).isNotNull();
	}
}
