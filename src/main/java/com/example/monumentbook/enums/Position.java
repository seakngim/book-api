package com.example.monumentbook.enums;

import com.example.monumentbook.model.Positions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public enum Position {
    Admin("ADMIN"),USER("USER");

    private final String positionValue;

    public String getValue() {
        return positionValue;
    }

    public static List<GrantedAuthority> getPositionAuthorities(Positions positions) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Position posEnum : Position.values()) {
            if (posEnum.getValue().equalsIgnoreCase(positions.getName())) {
                authorities.add(new SimpleGrantedAuthority(posEnum.getValue()));
                break;
            }
        }

        return authorities;
    }
}
