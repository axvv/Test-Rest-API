package lesson4;

import java.util.List;

public class ResponseGetShoppingList {
    private List<Aisle> aisles;
    private double cost;
    private long startDate;
    private long endDate;





    public  List<Aisle> getAisles() {
        return aisles;
    }

    public void setAisles(List<Aisle> aisles) {
        this.aisles = aisles;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }


    public static class Aisle {
        private String aisle;
        public List<Item> items;

        public String getAisle() {
            return aisle;
        }

        public void setAisle(String aisle) {
            this.aisle = aisle;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    public static class Item {
        private int id;
        private String name;
        private Measures measures;
        private List<String> usages;
        private List<String> usageRecipeIds;
        private boolean pantryItem;
        private String aisle;
        private double cost;
        private int ingredientId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Measures getMeasures() {
            return measures;
        }

        public void setMeasures(Measures measures) {
            this.measures = measures;
        }

        public List<String> getUsages() {
            return usages;
        }

        public void setUsages(List<String> usages) {
            this.usages = usages;
        }

        public List<String> getUsageRecipeIds() {
            return usageRecipeIds;
        }

        public void setUsageRecipeIds(List<String> usageRecipeIds) {
            this.usageRecipeIds = usageRecipeIds;
        }

        public boolean isPantryItem() {
            return pantryItem;
        }

        public void setPantryItem(boolean pantryItem) {
            this.pantryItem = pantryItem;
        }

        public String getAisle() {
            return aisle;
        }

        public void setAisle(String aisle) {
            this.aisle = aisle;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public int getIngredientId() {
            return ingredientId;
        }

        public void setIngredientId(int ingredientId) {
            this.ingredientId = ingredientId;
        }
    }

    public static class Measures {
        private Measure original;
        private Measure metric;
        private Measure us;

        public Measure getOriginal() {
            return original;
        }

        public void setOriginal(Measure original) {
            this.original = original;
        }

        public Measure getMetric() {
            return metric;
        }

        public void setMetric(Measure metric) {
            this.metric = metric;
        }

        public Measure getUs() {
            return us;
        }

        public void setUs(Measure us) {
            this.us = us;
        }
    }

    public static class Measure {
        private double amount;
        private String unit;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getUnit() {


            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}







