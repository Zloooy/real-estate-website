package ru.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Уникальный идентификатор")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(notes = "Логин")
    @Column(name="login", nullable = false, unique = true)
    private String login;
    @ApiModelProperty(notes = "Хеш пароля", hidden = true)
    @JsonIgnore
    @Column(name="password", nullable = false, unique = true)
    private String password;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @ApiModelProperty(notes = "Роль пользователя")
    private Role role;
    public User(String login, String password, Role role) {
       this.login = login;
       this.password = password;
       this.role = role;
    }

    public User() {}
    @JsonIgnore
    @Override
    public Collection<Role.Authority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Entity
    @Table(name="user_roles")
    public static class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @ApiModelProperty(notes = "Уникальный идентификатор")
        @Column(name="id")
        private Long id;
        @ApiModelProperty(notes = "Название роли")
        @Column(name="name", nullable = false, unique = true)
        private String name;
        @ApiModelProperty(notes = "Список пользователей с ролью")
        @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY, orphanRemoval = true)
        private Set<User> users = new HashSet<>();
        @JsonIgnore
        //@JsonDeserialize(using=Authority.CustomAuthorityDeserializer.class)
        @ApiModelProperty(notes = "Список прав роли")
        @ManyToMany(fetch = FetchType.EAGER)
        private Set<Authority> authorities;

        public Role(String name, Authority ... authorities){
            this(name, Arrays.stream(authorities).collect(Collectors.toSet()));
        }
        public Role(String name, Set<Authority> authorities) {
            this.name = name;
            this.authorities = authorities;
        }

        public Role() {
           this.name = null;
           this.authorities = new HashSet<>();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonIgnore
        public Set<Authority> getAuthorities() {
            return authorities;
        }

        @JsonIgnore
        public void setAuthorities(Set<Authority> authorities) {
            this.authorities = authorities;
        }

        public Set<User> getUsers() {
            return users;
        }

        public void setUsers(Set<User> users) {
            this.users = users;
        }


       @Entity
       @Table(name="granted_authorities")
        public static class Authority implements GrantedAuthority {
            public static class CustomAuthorityDeserializer extends JsonDeserializer {
                @Override
                public Object deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
                    ObjectMapper om = (ObjectMapper) jp.getCodec();
                    JsonNode jsonNode = om.readTree(jp);
                    Set<Authority> authorities = new HashSet<>();
                    Iterator<JsonNode> elements = jsonNode.elements();
                    while (elements.hasNext()){
                        JsonNode next = elements.next();
                        System.out.println("got element");
                        System.out.println(next.toPrettyString());
                        authorities.add(new Authority(next.get("name").asText()));
                    }
                    return authorities;
                }
            }
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @ApiModelProperty(notes = "Уникальный идентификатор")
           @Column(name="id")
           private Long id;
           @ApiModelProperty(notes = "Название права")
           @Column(name="name", nullable = false, unique = true)
           private String name;
//           @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//           private Set<Role> rolesWithAuthority;

           public Authority(String name){
               this.name = name;
           }

            public Authority() {
               new Authority("");
            }

            @JsonIgnore
            @Override
           public String getAuthority() {
                System.out.printf("returning authority %s\n", getName());
               return getName();
           }

           public Long getId() {
               return id;
           }

           public void setId(Long id) {
               this.id = id;
           }

           public String getName() {
               return name;
           }

           public void setName(String name) {
               this.name = name;
           }

//            public Set<Role> getRolesWithAuthority() {
//                return rolesWithAuthority;
//            }
        }
    }
}
