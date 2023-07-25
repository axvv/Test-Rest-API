package lesson4;
import java.util.List;

public class ResponseGetComplexSearch {
   private List<Result> results;
    private float offset;
    private float number;
    private float totalResults;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public float getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(float totalResults) {
        this.totalResults = totalResults;
    }

    public static class Result{
        private float id;
        private String title;
        private String image;
        private String imageType;

        public float getId() {
            return id;
        }

        public void setId(float id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }
    }
}






