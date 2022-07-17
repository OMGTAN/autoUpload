import lombok.Data;

@Data
public class ResposeModel {


    public String code;

    public String message;

    public ResposeModel(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object data;

    public static ResposeModel success(Object data){
        return new ResposeModel("200", "成功", data);
    }

    public static ResposeModel failed(String message) {
        return new ResposeModel("501", message, null);
    }
}
