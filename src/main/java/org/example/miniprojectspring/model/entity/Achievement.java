package org.example.miniprojectspring.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Achievement {
    private int id;
    private String achieveName;
    private String title;
    private String description;
    private String badge;
    private Integer xpRequired;
}
