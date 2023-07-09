package models.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostPhoto {
    private String id;
    private String owner_id;
    private String url;
}
