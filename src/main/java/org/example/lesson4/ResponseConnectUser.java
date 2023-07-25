

package lesson4;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "username",
        "spoonacularPassword",
        "hash"
})
@Data
@Generated("jsonschema2pojo")
public class ResponseConnectUser {

    @JsonProperty("status")
    private String status;
    @JsonProperty("username")
    private String username;
    @JsonProperty("spoonacularPassword")
    private String spoonacularPassword;
    @JsonProperty("hash")
    private String hash;

}
