package com.hritik.blog.services.impl;

import com.hritik.blog.entities.Category;
import com.hritik.blog.exception.ResourceNotFoundException;
import com.hritik.blog.payloads.CategoryDto;
import com.hritik.blog.repositories.CategoryRepo;
import com.hritik.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.categoryDtoToCategory(categoryDto);
        Category createdCategory = this.categoryRepo.save(category);
        return this.categoryToCategoryDto(createdCategory);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        return this.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        if(!this.categoryRepo.findAll().isEmpty()){
        List<Category> listOfAllCategory = this.categoryRepo.findAll();
        List<CategoryDto> listOfAllCategoryDto = listOfAllCategory.stream().map(category -> this.categoryToCategoryDto(category)).collect(Collectors.toList());
        return listOfAllCategoryDto;
        }else {
            throw new ResourceNotFoundException("No category present in database");
        }

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category oldCategory = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        oldCategory.setCategoryDescription(categoryDto.getCategoryDescription());
        oldCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        Category updatedCategory =this.categoryRepo.save(oldCategory);
        return this.categoryToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category categoryToBeDeleted = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        this.categoryRepo.delete(categoryToBeDeleted);
    }

    private Category categoryDtoToCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }

    private CategoryDto categoryToCategoryDto(Category category){
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }
}
