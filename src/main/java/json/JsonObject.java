package json;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private JsonPair[] jsonObj;
    private int counter = 0;
    public JsonObject(JsonPair... jsonPairs) {
        this.jsonObj = new JsonPair[jsonPairs.length];
        for(JsonPair j: jsonPairs){
            this.jsonObj[this.counter] = j;
            this.counter++;
        }
    }

    @Override
    public String toJson() {
        String jsonStr = "{";
        for (JsonPair j: this.jsonObj){
            jsonStr += new JsonString(j.key).toJson() + ": " + j.value.toJson();
            if (j != this.jsonObj[jsonObj.length-1]){
                jsonStr += ", ";
            }
        }
        jsonStr += "}";
        return jsonStr;
    }

    public void add(JsonPair jsonPair) {
        boolean alreadyEx = false;
        for (int i = 0; i < this.jsonObj.length; i++){
            if (this.jsonObj[i].key == jsonPair.key){
                this.jsonObj[i] = jsonPair;
                alreadyEx = true;
            }
        }

        if (alreadyEx == false){
            JsonPair[] newJsObj = this.jsonObj.clone();
            int count = 0;
            this.counter++;
            this.jsonObj = new JsonPair[this.counter];
            for (JsonPair j: newJsObj){
                jsonObj[count] = j;
                count++;
            }
            jsonObj[count] = jsonPair;
        }

    }

    public Json find(String name) {
        for (JsonPair j: this.jsonObj){
            if (j.key == name){
                return j.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject newJson = new JsonObject();
        for (JsonPair j: this.jsonObj){
            for (String n: names){
                if (j.key == n){
                    newJson.add(j);
                }
            }
        }
        return newJson;
    }
}
