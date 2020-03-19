package ar.com.ada.sb.api.persistence.exception.validations;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"field", "code", "message"})
public class ApiFieldError {

    private String field;
    private String code;
    private String message;

    public ApiFieldError(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiFieldError {" +
                "field=" + field +
                ", code=" + code +
                ", message=" + message + '\'' +
                " }";
    }
}