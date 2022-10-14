package com.productmanager.productmanagerapi.mapper;

import com.productmanager.productmanagerapi.dto.CategoryDTO;
import com.productmanager.productmanagerapi.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categoryToCategoryDTO(Category category);

    List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories);

    Category categoryDtoToCategory(CategoryDTO categoryDTO);
}
