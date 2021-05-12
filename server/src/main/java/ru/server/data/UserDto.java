package ru.server.data;

import ru.server.models.User;

public class UserDto {
    public UserDto() {
    }
    public UserDto(User u){
        this.id = u.getId();
        this.roleId = u.getRole().getId();
        this.login = u.getLogin();
        this.password = null;
    }

    private String login;
    private String password;
    private long roleId;
    private Long id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
