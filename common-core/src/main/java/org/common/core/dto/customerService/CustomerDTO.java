package org.common.core.dto.customerService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String userRole;
    private java.sql.Date createdAt;
    private java.sql.Date updatedAt;
}
