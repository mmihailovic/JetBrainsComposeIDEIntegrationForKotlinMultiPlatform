package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LinkedError {
    private String preLinkPart;
    private String linkPart;
    private String postLinkPart;
    private Integer lineNumber;
    private String className;
}
