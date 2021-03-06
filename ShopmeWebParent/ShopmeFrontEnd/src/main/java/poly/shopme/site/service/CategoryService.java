package poly.shopme.site.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.shopme.common.entity.Brand;
import poly.shopme.common.entity.Category;
import poly.shopme.common.exception.CategoryNotFoundException;
import poly.shopme.site.repository.BrandRepository;
import poly.shopme.site.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	public List<Category> listRootCategories() {
		List<Category> listRootCategories = categoryRepository.findRootCategoryEnabled();
		return listRootCategories;
	}
	
	public Category getCategory(String alias) throws CategoryNotFoundException {
		Category category = categoryRepository.findByAliasEnabled(alias);
		if(category == null) {
			throw new CategoryNotFoundException("Không tìm thấy danh mục nào với đường dẫn " + alias);
		}
		
		return category;
	}
	
	public List<Category> getCategoryParents(Category child) {
		List<Category> listParents = new ArrayList<>();
		
		Category parent = child.getParent();
		
		while (parent != null) {
			listParents.add(0, parent);
			parent = parent.getParent();
		}
		
		listParents.add(child);
		
		return listParents;
	}
	
	public List<Brand> listByBrand(int categoryId) {
		return brandRepository.listBrandByCategory(categoryId);
	}
}
