

package lesson4;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})
@Data
@Generated("jsonschema2pojo")
public class ResponseCreateShoppingList {

    @JsonProperty("aisles")
    private List<Aisle> aisles;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("startDate")
    private Integer startDate;
    @JsonProperty("endDate")
    private Integer endDate;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "aisle",
            "items"
    })
    @Data
    @Generated("jsonschema2pojo")
    public class Aisle {

        @JsonProperty("aisle")
        private String aisle;
        @JsonProperty("items")
        private List<Item> items;

    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "name",
            "measures",
            "pantryItem",
            "aisle",
            "cost",
            "ingredientId"
    })
    @Data
    @Generated("jsonschema2pojo")
    public class Item {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("measures")
        private Measures measures;
        @JsonProperty("pantryItem")
        private Boolean pantryItem;
        @JsonProperty("aisle")
        private String aisle;
        @JsonProperty("cost")
        private Double cost;
        @JsonProperty("ingredientId")
        private Integer ingredientId;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "original",
            "metric",
            "us"
    })
    @Data
    @Generated("jsonschema2pojo")
    public class Measures {

        @JsonProperty("original")
        private Original original;
        @JsonProperty("metric")
        private Metric metric;
        @JsonProperty("us")
        private Us us;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "amount",
            "unit"
    })
    @Data
    @Generated("jsonschema2pojo")
    public class Metric {

        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("unit")
        private String unit;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "amount",
            "unit"
    })
    @Data
    @Generated("jsonschema2pojo")
    public class Original {

        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("unit")
        private String unit;

    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "amount",
            "unit"
    })
    @Data
    @Generated("jsonschema2pojo")
    public class Us {

        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("unit")
        private String unit;

    }
}
