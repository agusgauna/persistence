package ar.com.ada.sb.api.persistence.exception;

@Getter @Setter
@NoArgsConstructor
public class ApiEntityError {
    private String entity;
    private String code;
    private String message;

    public ApiEntityError(String entity, String code, String message) {
        this.entity = entity;
        this.code = code;
        this.message = message;
    }
}