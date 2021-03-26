package ru.server.data;

public class Auth {
    public static class Request {
        private String login;
        private String password;

        public Request(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public Request() {
        }

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
    }
    public static class Response {
        private String token;

        public Response(String token) {
            this.token = token;
        }

        public Response() {
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
