package com.springboot.api.dto;

/*
    DTO -> Data Transfer Object -> 다른 레이어 간의 데티어 교환

   **DTO vs VO
     ->뭐... 둘다 더티너 컨테이너 인대용...
            VO -> 데이터 그 자체로 의미 있는것 -> 데이터의 신뢰서을 확보하기 위해 Read Only
            DTO -> 데이터 전솔을 위해 사용하는 컨테이너 (같은 서버 혹은 다른서버로의...)

 */
public class MemberDto {
    private String name;
    private String email;
    private String organization;

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ",organization = '" + organization + '\'' +
                '}';
    }
}

// 요거 받는 get메서도 -> GetController -> request3
