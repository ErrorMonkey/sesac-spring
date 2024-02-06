package com.sesac.sesacspring.jpa.dto;

// 빌더 구조 설명: 커링 함수
class StudentDTOBuilder {
    private final String name;
    private final String nickname;

    public static class Builder {
        private String name;
        private String nickname;

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder nickname(String nickname){
            this.nickname = nickname;
            return this;
        }
        public StudentDTOBuilder build() { return new StudentDTOBuilder(name, nickname); }
    }
    public StudentDTOBuilder(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }
    public static Builder builder(){ return new Builder(); }
}