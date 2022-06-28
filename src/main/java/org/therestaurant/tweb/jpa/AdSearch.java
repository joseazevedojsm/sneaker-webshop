package org.therestaurant.tweb.jpa;

public class AdSearch {
    
    private String shortDescription;  
    private String[] categories;  
    private String rangePrice;  
    
    public AdSearch(){}  
    
    public String getShortDescription() {  
        return shortDescription;  
    }  
    public void setShortDescription(String shortDescription) {  
        this.shortDescription = shortDescription;  
    }  
    public String getRangePrice() {  
        return rangePrice;  
    }  
    public void setRangePrice(String rangePrice) {  
        this.rangePrice = rangePrice;  
    }  
    public String[] getCategories() {  
        return categories;  
    }  
    public void setCategories(String[] categorie) {  
        categories = categorie;  
    }         
    
}
