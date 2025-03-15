package com.kosi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import com.kosi.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

   @NotNull
   @Size(min = 3, max = 50)
   private String username;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @NotNull
   @Size(min = 3, max = 100)
   private String password;

   @NotNull
   @Size(min = 3, max = 50)
   private String name;

   @NotNull
   @Size(min = 1)
   private String email;

   private String birthday;

   private String phoneNumber;

   private String companyName;

   private String companyNumber;

   private Set<AuthorityDto> authorityDtoSet;

   public static MemberDto from(User user) {
      if(user == null) return null;

      return MemberDto.builder()
              .username(user.getUsername())
              .name(user.getName())
    /*          .authorityDtoSet(user.getAuthorities().stream()
                      .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                      .collect(Collectors.toSet()))*/
              .build();
   }
}