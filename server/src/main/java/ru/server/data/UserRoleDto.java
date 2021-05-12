package ru.server.data;

public class UserRoleDto {
    public UserRoleDto(){}

    public UserRoleDto(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    private long id;
    private String roleName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
